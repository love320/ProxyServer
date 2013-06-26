package com.love320.approxy.processor.command;

public class Reboot implements Icommand{
	
	public String code = "REBOOT";

	@Override
	public String action(String command) {
		
		return command;
	}

}
