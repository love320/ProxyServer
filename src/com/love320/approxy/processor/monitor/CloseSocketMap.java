package com.love320.approxy.processor.monitor;

import java.net.Socket;
import java.util.ConcurrentModificationException;
import java.util.Set;
import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.manager.P2PSocket;

public class CloseSocketMap  extends java.lang.Thread {

	@Override
	public void run() {
		P2PManager.msg("CloseSocketMap OK .");
		for(;;){
				try {
					sleep(1000*60*5);//每5分钟进行一次
					Set<String> setkey = P2PManager.socketMap.keySet();
					for(String sing : setkey){
						Socket socket = P2PManager.socketMap.get(sing);
						if(socket.isClosed()) P2PSocket.socketClose(socket);//去除并关闭无效连接
						P2PManager.socketMap.remove(sing);//从map中移除
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch(ConcurrentModificationException e){
					e.printStackTrace();
				}
		}
	}

}
