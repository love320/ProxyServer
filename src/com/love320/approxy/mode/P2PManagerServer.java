package com.love320.approxy.mode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.love320.approxy.Config;
import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.processor.ManagerSocketServer;

public class P2PManagerServer implements Runnable{
	
	public static boolean action(){

		P2PManagerServer ms = new P2PManagerServer();
		new Thread(ms).start();//启动管理
		return true;
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(Config.MANAGERPROXY);
			P2PManager.msg("Started(Listen Port:"+Config.MANAGERPROXY+") for P2PManagerServer ");
			while (true) {
					Socket clientSocket = serverSocket.accept();//取客户连接
					P2PManager.msg("My Client for P2PManagerServer:"+clientSocket);
					ManagerSocketServer mss = new ManagerSocketServer(clientSocket);
					new Thread(mss).start();//启动
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	

}
