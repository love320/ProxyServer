package com.love320.approxy.processor;

import com.love320.approxy.manager.P2PManager;

//维护关系 通信关系
public class StayConnectedServer extends java.lang.Thread {

	@Override
	public void run() {
		P2PManager.msg("StayConnected OK .");
		boolean reSocketT = false;//重新获取专用连接状态：false 没进行获取，true:获取中
		for(;;){
				try {
					sleep(1000*30);
					if(P2PManager.isconn) {
						P2PManager.isconn = false;
						reSocketT = false;
					}else{
						if(!reSocketT)ProcessorServer.reSocketT();//重新获取新的
						reSocketT = true;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}

}
