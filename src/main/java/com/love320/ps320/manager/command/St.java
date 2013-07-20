package com.love320.ps320.manager.command;

import com.love320.ps320.manager.P2PManager;


//启动新的服务
public class St implements Icommand{
	
	public String code = "NEW";

	@Override
	public String action(String command) {
		String[] coms = command.split(" ");
		if(coms.length <= 0 || !coms[0].toUpperCase().equals(code)) return command;
		return P2PManager.isconn+"";
	}

}
