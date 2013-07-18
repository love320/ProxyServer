package com.love320.ps320.processor;

import java.io.IOException;

import com.love320.ps320.Config;
import com.love320.ps320.manager.P2PManager;

//客户发心跳 维护关系 通信关系
public class StayConnectedClient extends java.lang.Thread {

	@Override
	public void run() {
		P2PManager.msg("StayConnectedClient OK .");
		for(;;){
			try {
				sleep(1000*10);
				ProcessorClient.outWrite(Config.TEST);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				ProcessorServer.reSocketT();//重新获取新的
			}
		}
	}

}
