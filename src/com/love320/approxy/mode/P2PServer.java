package com.love320.approxy.mode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.love320.approxy.Config;
import com.love320.approxy.manager.IPort;
import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.processor.ActionSocketServer;
import com.love320.approxy.processor.ProcessorServer;
import com.love320.approxy.processor.StayConnected;




public class P2PServer implements Runnable{
	
	private IPort iport;

	public static void main(String[] args) throws IOException {
		P2PManager.msg("Started(Listen T Port:"+Config.PROXY_TO_DOC+")");
		ProcessorServer processor = new ProcessorServer();
		new Thread(processor).start();//启动专用通信线程
		
		for(IPort iport:Config.IPORTLIST){
			P2PManager.msg("Started(Listen Port:"+iport.getProxy()+")");
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
					Socket clientSocket = proxySocket.accept();//取客户连接
					ActionSocketServer ass = new ActionSocketServer(clientSocket,iport.getIp(),iport.getPort());//绑定相关连接
					new Thread(ass).start();//启动
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	public void setIport(IPort iport) {
		this.iport = iport;
	}
	
	

}
