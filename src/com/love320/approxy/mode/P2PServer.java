package com.love320.approxy.mode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.love320.approxy.Config;
import com.love320.approxy.manager.IPort;
import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.processor.ProcessorServer;




public class P2PServer implements Runnable{
	
	private IPort iport;

	public static void main(String[] args) throws IOException {
		System.out.println("Started(Listen T Port:"+Config.PROXY_TO_DOC+")");
		ProcessorServer processor = new ProcessorServer();
		new Thread(processor).start();//启动专用通信线程
		
		for(IPort iport:Config.IPORTLIST){
			System.out.println("Started(Listen Port:"+iport.getProxy()+")");
			P2PServer server= new P2PServer();
			server.setIport(iport);
			new Thread(server).start();
		}
	}

	@Override
	public void run() {
		ServerSocket proxySocket= null;
		try {
			proxySocket = new ServerSocket(iport.getProxy());
		while (true) {
			try {
				Socket clientSocket = proxySocket.accept();//取客户连接
				ProcessorServer.sendConnetNewSocket(iport.getIp(),iport.getPort());//通知客户机主动创建连接
				Socket server = ProcessorServer.getSocket();//取客户机的连接

				P2PManager.P2PGO(clientSocket,server);//启动
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	public void setIport(IPort iport) {
		this.iport = iport;
	}
	
	

}
