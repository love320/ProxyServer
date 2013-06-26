package com.love320.approxy.go;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import com.love320.approxy.Config;
import com.love320.approxy.manager.FileOutMsg;
import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.mode.P2PClientReverse;
import com.love320.approxy.processor.ProcessorClient;
import com.love320.approxy.processor.StayConnectedClient;
import com.love320.approxy.processor.StayConnectedServer;



public class P2PClient extends java.lang.Thread  {
	
	public static void main(String[] args) throws IOException {
		
		FileOutMsg fom = new FileOutMsg(new File(Config.FILECLIENT));//日志
		new Thread(fom).start();//启动日志
		
		P2PClientReverse.action();//启用逆向代理客户端
	}
	
}
