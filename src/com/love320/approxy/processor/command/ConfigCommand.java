package com.love320.approxy.processor.command;

import java.util.ArrayList;
import java.util.List;

public class ConfigCommand {
	public static List<Icommand> commands = new ArrayList<Icommand>();
	static{
		commands.add(new Kill());
		commands.add(new KillAll());
		commands.add(new Ls());
		commands.add(new Reboot());
	}
}
