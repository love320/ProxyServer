package com.love320.approxy.processor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.love320.approxy.Config;
import com.love320.approxy.manager.P2PManager;



public class ProcessorClient implements Runnable {

	public static Socket socketT = null;
	
	@Override
	public void run() {
			try {
				socketT = new Socket(Config.PROXY_HOST,Config.PROXY_TO_DOC);
				while(true){
					read();//读取
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			socketT = null;//异常后，清空连接
	}
	
	public void read() throws IOException{
		
		byte[] buffer = new byte[1024*4];
		int temp = 0;
		temp = socketT.getInputStream().read(buffer);

		String msg =new String(buffer,0,temp);
		String[] ss = msg.split("#");
		Socket clientSocket = null;
		if(temp>0 && ss.length == 2 && isclose() ){
			clientSocket = new Socket(ss[0],Integer.valueOf(ss[1]));
			Socket serverSocket = new Socket(Config.PROXY_HOST,Config.PROXY_TO_DOC);
			P2PManager.P2PGO(clientSocket,serverSocket);//启动
			P2PManager.msg("clientSocket:"+clientSocket);
			P2PManager.msg("server:"+serverSocket);
		}else{
			outWrite(msg.getBytes());//回复
		}
	}
	
	public static boolean isclose(){
		try {
			if(socketT == null ) return false;
			outWrite(Config.TEST.getBytes());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return false;
	}
	
	public static boolean outWrite(byte[] bytes) throws IOException{
		DataOutputStream out = new DataOutputStream(socketT.getOutputStream());
		out.write(bytes);
		return true;
	}
	

}
