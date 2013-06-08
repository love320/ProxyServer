package com.love320.approxy.manager;

import java.net.Socket;

public class P2PManager {
	
	private static int threadCount=0;
	
	public static void msg(String message){
		System.out.println("Msg :"+message);
	}
	
	//绑定通信
	public static void P2PGO(Socket clientSocket,Socket serverSocket){
		
		P2PSocket p2pC = new P2PSocket(clientSocket,serverSocket);
		P2PSocket p2pS = new P2PSocket(serverSocket,clientSocket);
		
		new Thread(p2pC).start();
		new Thread(p2pS).start();
		
	}

}
