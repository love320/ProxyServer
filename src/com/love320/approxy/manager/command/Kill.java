package com.love320.approxy.manager.command;

import java.net.ServerSocket;
import java.net.Socket;
import com.love320.approxy.manager.socket.P2PServerSocket;
import com.love320.approxy.manager.socket.P2PSocket;
import com.love320.approxy.manager.socket.SocketMap;
import com.love320.approxy.manager.socket.SocketServerMap;

public class Kill implements Icommand{
	
	public String code = "KILL";

	@Override
	public String action(String command) {
		String[] coms = command.split(" ");
		if(coms.length <= 0 || !coms[0].toUpperCase().equals(code)) return command;
		for(String com: coms) {
			killThreadSocketMap(com);
			killThreadSocketServerMap(com);
		}
		return command+"OK.";
	}
	
	private boolean killThreadSocketMap(String key){
		Socket socket = SocketMap.MAP.get(key);
		if(socket == null) return false;
		P2PSocket.socketClose(socket);
		SocketMap.MAP.remove(key);
		return true;
	}
	
	private boolean killThreadSocketServerMap(String key){
		ServerSocket socket = SocketServerMap.MAP.get(key);
		if(socket == null) return false;
		P2PServerSocket.socketClose(socket);
		SocketServerMap.MAP.remove(key);
		return true;
	}

}
