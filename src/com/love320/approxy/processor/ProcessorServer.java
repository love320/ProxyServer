package com.love320.approxy.processor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.love320.approxy.Config;
import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.manager.P2PSocket;



public class ProcessorServer implements Runnable {
	
	private static ServerSocket serverSocket = null;

	@Override
	public void run() {
		try {
				if(serverSocket == null) serverSocket = new ServerSocket(Config.PROXY_TO_DOC);
				P2PManager.msg("Started(Listen T Port:"+Config.PROXY_TO_DOC+")");
				
				StayConnectedServer stayconn = new StayConnectedServer();
				new Thread(stayconn).start();//提供专用通信线程保持通信
				
				initSocket();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	public static void reSocketT(){
		P2PManager.msg("reSocketT:"+P2PManager.socketT);
		P2PSocket.socketClose(P2PManager.socketT);
		P2PManager.socketT = null;
		try {
			initSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initSocket() throws IOException{
		if(P2PManager.socketT == null) P2PManager.socketT = getSocket();
		P2PManager.msg("socketT:"+P2PManager.socketT);
		read();
		reSocketT();//重新获取
	}
	
	public static void read() throws IOException{
		while(true){
			byte[] buffer = new byte[1024*4];
			int temp = 0;
			temp = P2PManager.socketT.getInputStream().read(buffer);
			if(temp==-1)break;
			String msg = new String(buffer,0,temp);
			P2PManager.isconn = true;//收到信息
			P2PManager.msg(msg);
		}
	}
	
	//发送文本信息
	public static boolean sendConnetNewSocket(String str) throws IOException{
		outWrite(str);
		return true;
	}
	
	public static Socket sendConnetNewSocket(String ip,Integer port) throws IOException{
		String msgbag = P2PManager.IPort(ip, port);
		outWrite(msgbag.getBytes());
		return ProcessorServer.getSocket();
	}
	
	public static Socket getSocket() throws IOException{
		Socket newsocket =serverSocket.accept();
		P2PManager.addSocketMap(newsocket);//加入容器
		return newsocket;
	}

	public static boolean isclose(){
		try {
			if(P2PManager.socketT == null ) initSocket();
			return outWrite("T.isclose "+Config.TEST);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		try {
			P2PManager.socketT.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean outWrite(String str) throws IOException{
		return outWrite(str.getBytes());
	}
	
	public static boolean outWrite(byte[] bytes) throws IOException{
		DataOutputStream out = new DataOutputStream(P2PManager.socketT.getOutputStream());
		out.write(bytes);
		out.flush();//即刻写入
		return true;
	}
	

}
