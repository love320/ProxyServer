package com.love320.ps320.manager.command;

import java.net.Socket;
import java.util.Map;
import java.util.Set;

import com.love320.ps320.manager.P2PManager;
import com.love320.ps320.manager.socket.P2PSocket;
import com.love320.ps320.manager.socket.SocketMap;

public class KillAll implements Icommand{
	
	public String code = "KILLALL";

	@Override
	public String action(String command) {
		String[] coms = command.split(" ");
		if(coms.length <= 0 || !coms[0].toUpperCase().equals(code)) return command;
		Set<String> setkey = SocketMap.MAP.keySet();
		if(setkey == null) return command;
		for(String sing : setkey){
			killThread(sing);
		}
		return command+"OK.";
	}
	
	private boolean killThread(String key){
		Socket socket = SocketMap.MAP.get(key);
		if(socket == null) return false;
		P2PSocket.socketClose(socket);
		SocketMap.MAP.remove(key);
		return true;
	}

}
