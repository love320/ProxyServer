package com.love320.approxy.processor;

import java.io.IOException;
import java.net.Socket;

import com.love320.approxy.manager.P2PManager;


//处理请求的连接
public class ActionSocketServer implements Runnable {
	
	private Socket clientSocket;
	
	private String ip;
	private Integer port;
	
	public ActionSocketServer(Socket clientSocket,String ip,Integer port){
		this.clientSocket = clientSocket;
		this.ip = ip;
		this.port = port;
	}

	@Override
	public void run() {
		
		try {
			if(!ProcessorServer.isclose()) return;//关闭
			ProcessorServer.sendConnetNewSocket(ip,port);//通知客户机主动创建连接
			P2PManager.msg("sendConnetNewSocket."+ip+":"+port);
			Socket serverSocket = ProcessorServer.getSocket();//取客户机的连接
			P2PManager.msg("clientSocket:"+clientSocket);
			P2PManager.msg("server:"+serverSocket);
			P2PManager.P2PGO(serverSocket,clientSocket);//启动
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
