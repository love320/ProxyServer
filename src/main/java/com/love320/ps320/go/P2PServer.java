package com.love320.ps320.go;

import java.io.File;
import java.io.IOException;

import com.love320.ps320.Config;
import com.love320.ps320.manager.io.FileOutMsg;
import com.love320.ps320.mode.P2PManagerServer;
import com.love320.ps320.mode.P2PServerProxy;
import com.love320.ps320.mode.P2PServerReverse;


public class P2PServer{
	
	public P2PServer(){
		FileOutMsg fom = new FileOutMsg(new File(Config.FILESERVER));//日志
		new Thread(fom).start();//启动日志
		
		P2PManagerServer.action();//管理服务
		
		P2PServerProxy.action();//普通代理服务
		
		P2PServerReverse.action();//逆向代理服务
	}
	
	public static void main(String[] args) throws IOException {
			new P2PServer();
	}

	

}
