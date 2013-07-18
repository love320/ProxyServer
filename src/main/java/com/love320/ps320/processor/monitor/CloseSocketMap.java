package com.love320.ps320.processor.monitor;

import java.net.Socket;
import java.util.ConcurrentModificationException;
import java.util.Set;

import com.love320.ps320.manager.P2PManager;
import com.love320.ps320.manager.socket.P2PSocket;
import com.love320.ps320.manager.socket.SocketMap;

public class CloseSocketMap  extends java.lang.Thread {

	@Override
	public void run() {
		P2PManager.msg("CloseSocketMap OK .");
		for(;;){
				try {
					sleep(1000*60*5);//每5分钟进行一次
					Set<String> setkey = SocketMap.MAP.keySet();
					for(String sing : setkey){
						Socket socket = SocketMap.MAP.get(sing);
						if(socket.isClosed()){
							P2PManager.msg("CloseSocketMap ."+socket);
							P2PSocket.socketClose(socket);//去除并关闭无效连接
							SocketMap.MAP.remove(sing);//从map中移除
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch(ConcurrentModificationException e){
					e.printStackTrace();
				}
		}
	}

}
