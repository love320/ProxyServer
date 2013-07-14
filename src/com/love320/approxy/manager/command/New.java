package com.love320.approxy.manager.command;

import com.love320.approxy.bean.IPort;
import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.mode.P2PServerProxy;
import com.love320.approxy.mode.P2PServerReverse;


//启动新的服务
public class New implements Icommand{
	
	public String code = "NEW";

	@Override
	public String action(String command) {
		String[] coms = command.split(" ");
		if(coms.length <= 0 || !coms[0].toUpperCase().equals(code)) return command;
		//1@9999@113.243.142.23:9999
		String[] ss = coms[1].split("@");
		if(ss.length == 3){
			int type = Integer.parseInt(ss[0]);//类型
			int proxy =   Integer.parseInt(ss[1]);//服务端口
			String[] ipstr = ss[2].split(":");
			String ip = ipstr[0];
			int port = Integer.parseInt(ipstr[1]);
			
			IPort iport = new IPort(proxy,ip,port,type);
			
			if(iport.getType() == 1) {
				P2PManager.msg("Started(Listen Port:"+iport.getProxy()+") for P2PServerProxy");
				P2PServerProxy server= new P2PServerProxy();
				server.setIport(iport);
				new Thread(server).start();
			}
			
			if(iport.getType() == 2) {
				P2PManager.msg("Started(Listen Port:"+iport.getProxy()+") for P2PServerReverse ");
				P2PServerReverse server= new P2PServerReverse();
				server.setIport(iport);
				new Thread(server).start();
			}
			
		}
		
		return command+"...OK";
	}

}
