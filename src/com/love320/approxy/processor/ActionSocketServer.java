package com.love320.approxy.processor;

import java.io.IOException;
import java.net.Socket;

import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.manager.socket.P2PSocket;


//处理请求的连接
public class ActionSocketServer  extends java.lang.Thread {
	
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
		
		if(P2PManager.acceptWait){
			//等待连接检测程序进行，进入等待
			try {
				sleep(1000*5);//等待5秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(!P2PManager.isconn){
			//无专用连接,断开客户断连接
			P2PSocket.socketClose(clientSocket);
			return;//关闭
		}
		
		try {
			Socket serverSocket = ProcessorServer.sendConnetNewSocket(ip,port);//通知客户机主动创建连接并取客户机的连接
			P2PManager.msg("PROXY_TO_DOC Server:"+serverSocket);
			P2PManager.P2PGO(clientSocket,serverSocket);//启动
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
