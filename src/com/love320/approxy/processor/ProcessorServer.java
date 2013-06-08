package com.love320.approxy.processor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.love320.approxy.Config;
import com.love320.approxy.manager.P2PManager;



public class ProcessorServer implements Runnable {
	
	private static ServerSocket serverSocket = null;

	private static Socket socketT = null;
	
	@Override
	public void run() {
		try {
				if(serverSocket == null) serverSocket = new ServerSocket(Config.PROXY_TO_DOC);
				P2PManager.msg("serverSocket:"+serverSocket);
				
				StayConnected stayconn = new StayConnected();
				new Thread(stayconn).start();//提供专用通信线程保持通信
				
				initSocket();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	public static void reSocketT(){
		socketT = null;
		try {
			initSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initSocket() throws IOException{
		if(socketT == null) socketT = serverSocket.accept();
		P2PManager.msg("socketT:"+socketT);
		while(true){
			try {
				read();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		socketT = null;
		initSocket();
	}
	
	public static void read() throws IOException{
		byte[] buffer = new byte[1024*4];
		int temp = 0;
		temp = socketT.getInputStream().read(buffer);
		//if(temp==-1)break;
		String msg =new String(buffer,0,temp);
		//if(msg.equals(Config.CLIENTOK)) socketT = socketT;
		P2PManager.msg(msg);
	}
	
	//发送文本信息
	public static boolean sendConnetNewSocket(String str) throws IOException{
		outWrite(str);
		return true;
	}
	
	public static void sendConnetNewSocket(String ip,Integer port) throws IOException{
		String msgbag = ip +"#"+port;
		outWrite(msgbag.getBytes());
	}
	
	public static Socket getSocket(){
		try {
			if(isclose()) return serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isclose(){
		try {
			if(socketT == null ) initSocket();
			return outWrite("T.isclose "+Config.TEST);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		try {
			socketT.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean outWrite(String str) throws IOException{
		return outWrite(str.getBytes());
	}
	
	public static boolean outWrite(byte[] bytes) throws IOException{
		DataOutputStream out = new DataOutputStream(socketT.getOutputStream());
		out.write(bytes);
		return true;
	}
	

}
