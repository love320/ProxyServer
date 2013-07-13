package com.love320.approxy.mode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.love320.approxy.Config;
import com.love320.approxy.bean.IPort;
import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.processor.ActionSocketServer;
import com.love320.approxy.processor.ProcessorServer;




public class P2PServerProxy implements Runnable{
	
	private IPort iport;
	
	public static boolean action(){
		
		for(IPort iport:Config.IPORTLIST){
			if(iport.getType() != 1) continue;//类型判断
			P2PManager.msg("Started(Listen Port:"+iport.getProxy()+") for P2PServerProxy");
			P2PServerProxy server= new P2PServerProxy();
			server.setIport(iport);
			new Thread(server).start();
		}
		
		return true;
	}

	@Override
	public void run() {
		
		try {
			ServerSocket proxySocket = new ServerSocket(iport.getProxy());
			while (true) {
					Socket clientSocket = proxySocket.accept();//取客户连接
					P2PManager.addSocketMap(clientSocket);//加入容器
					P2PManager.msg("My Client for P2PServerProxy:"+clientSocket);
					Socket socket = new Socket(iport.getIp(),iport.getPort());
					P2PManager.msg("My Server for P2PServerProxy:"+socket);
					P2PManager.P2PGO(clientSocket,socket);//启动
			}
		} catch (IOException e) {
			System.out.println("prot error:"+iport.getProxy());
			e.printStackTrace();
		}
		
	}

	public void setIport(IPort iport) {
		this.iport = iport;
	}
	
	

}
