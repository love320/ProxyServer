package com.love320.approxy.mode;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.love320.approxy.Config;
import com.love320.approxy.manager.FileOutMsg;
import com.love320.approxy.manager.IPort;
import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.processor.ActionSocketServer;
import com.love320.approxy.processor.ProcessorServer;
import com.love320.approxy.processor.StayConnectedServer;




public class P2PServer{
	
	public static void main(String[] args) throws IOException {
		
		FileOutMsg fom = new FileOutMsg(new File(Config.FILESERVER));//日志
		new Thread(fom).start();//启动日志
		
		P2PServerProxy.action();//普通代理服务
		
		P2PServerReverse.action();//逆向代理服务
		
	}

	

}
