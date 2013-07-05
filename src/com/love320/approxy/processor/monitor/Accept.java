package com.love320.approxy.processor.monitor;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.love320.approxy.Config;
import com.love320.approxy.manager.P2PManager;

public class Accept  extends java.lang.Thread {

	@Override
	public void run() {
		P2PManager.msg("Accept OK .");
		for(;;){
				try {
					sleep(1000*30);//每30秒进行一次
					P2PManager.acceptWait = true;//开启等待连接检测
					new Thread(new KillSocket()).start();
					sleep(1000*5);//等待5秒
					if(P2PManager.acceptWait) P2PManager.addSocketMap(new Socket("127.0.0.1",Config.PROXY_TO_DOC));//加入容器(发起请求连接，关闭检测的等待)
					P2PManager.acceptWait = false;//关闭等待连接检测
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
