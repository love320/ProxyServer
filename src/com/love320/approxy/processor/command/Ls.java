package com.love320.approxy.processor.command;

import java.sql.Date;
import java.util.Set;

import com.love320.approxy.manager.P2PManager;

public class Ls implements Icommand{
	
	public String code = "LS";

	@Override
	public String action(String command) {
		String[] coms = command.split(" ");
		if(coms.length <= 0 || !coms[0].toUpperCase().equals(code)) return command;
		Set<String> setkey = P2PManager.socketMap.keySet();
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(setkey == null) return command;
		for(String sing : setkey){
			Date tempdate = new Date(new Long((sing.split("-"))[1]));
			command += "\r\n"+sing+"  "+format.format(tempdate)+" "+(P2PManager.socketMap.get(sing).isClosed());
		}
		return command;
	}

}
