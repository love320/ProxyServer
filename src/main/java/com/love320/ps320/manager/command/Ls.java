package com.love320.ps320.manager.command;

import java.sql.Date;
import java.util.Set;

import com.love320.ps320.manager.P2PManager;
import com.love320.ps320.manager.socket.SocketMap;
import com.love320.ps320.manager.socket.SocketServerMap;

public class Ls implements Icommand{
	
	public String code = "LS";

	@Override
	public String action(String command) {
		String[] coms = command.split(" ");
		if(coms.length <= 0 || !coms[0].toUpperCase().equals(code)) return command;
		Set<String> setkey = SocketMap.MAP.keySet();
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(setkey == null) return command;
		command+="...OK";
		for(String sing : setkey){
			Date tempdate = new Date(new Long((sing.split("-"))[1]));
			command += "\r\n"+sing+"#"+(SocketMap.MAP.get(sing).isClosed())+"#"+format.format(tempdate);
		}
		return command;
	}

}
