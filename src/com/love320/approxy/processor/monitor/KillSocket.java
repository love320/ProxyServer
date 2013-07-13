package com.love320.approxy.processor.monitor;

import java.io.IOException;
import java.net.Socket;

import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.manager.socket.P2PSocket;
import com.love320.approxy.processor.ProcessorServer;

public class KillSocket  implements Runnable {

	@Override
	public void run() {
		P2PManager.msg("KillSocket OK .");
		try {
			for(;;){
				if(!P2PManager.acceptWait) break;//若false时，跳出杀等待
				Socket killSocke = ProcessorServer.getSocket();
				P2PManager.msg("KillSocket ."+killSocke);
				P2PSocket.socketClose(killSocke);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
