package com.love320.ps320.go;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import com.love320.ps320.Config;
import com.love320.ps320.manager.P2PManager;
import com.love320.ps320.manager.io.FileOutMsg;
import com.love320.ps320.mode.P2PClientReverse;
import com.love320.ps320.processor.ProcessorClient;
import com.love320.ps320.processor.StayConnectedClient;
import com.love320.ps320.processor.StayConnectedServer;



public class P2PClient extends java.lang.Thread  {
	
	public P2PClient(){
		P2PManager.msg("Site: http://www.love320.com ");
		P2PManager.msg("ps320开源 冰迪网络 出品 ");
		FileOutMsg fom = new FileOutMsg(new File(Config.FILECLIENT));//日志
		new Thread(fom).start();//启动日志
		
		P2PClientReverse.action();//启用逆向代理客户端
	}
	
	public static void main(String[] args) throws IOException {
		new P2PClient();
	}
	
}
