package com.love320.approxy.processor.command;

import java.net.Socket;
import java.util.Map;

import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.manager.P2PSocket;

public class Kill implements Icommand{
	
	public String code = "KILL";

	@Override
	public String action(String command) {
		String[] coms = command.split(" ");
		if(coms.length <= 0 || !coms[0].toUpperCase().equals(code)) return command;
		for(String com: coms) killThread(com);
		return command+"OK.";
	}
	
	private boolean killThread(String key){
		Socket socket = P2PManager.socketMap.get(key);
		if(socket == null) return false;
		P2PSocket.socketClose(socket);
		P2PManager.socketMap.remove(key);
		return true;
	}

}
