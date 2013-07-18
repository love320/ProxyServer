package com.love320.ps320.manager.socket;

import java.io.IOException;
import java.net.ServerSocket;

import com.love320.ps320.manager.P2PManager;


public class P2PServerSocket  implements Runnable{

	@Override
	public void run() {
		
	}


	public static void socketClose(ServerSocket socket){
		P2PManager.msg("Close Server:"+socket);
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (NullPointerException e){
			e.printStackTrace();
		}
	}
	
}
