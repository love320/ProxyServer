package com.love320.ps320.mode;

import com.love320.ps320.Config;
import com.love320.ps320.manager.P2PManager;
import com.love320.ps320.processor.ProcessorClient;
import com.love320.ps320.processor.StayConnectedClient;


public class P2PClientReverse  extends java.lang.Thread  {
	
	public static boolean action(){
		P2PManager.msg("Started Listen Port:"+Config.PROXY_HOST+":"+Config.PROXY_TO_DOC);
		StayConnectedClient stayconn = new StayConnectedClient();
		new Thread(stayconn).start();//提供专用通信线程保持通信
		
		P2PClientReverse p2pClientReverse = new P2PClientReverse();
		new Thread(p2pClientReverse).start();//启动客户端
		return true;
	}

	@Override
	public void run() {
		ProcessorClient processor = new ProcessorClient();
		Thread mainthread= null;
		while(true){
			try {
				if(!ProcessorClient.isclose()){
					mainthread= new Thread(processor);//启动专用通信线程
					mainthread.start();
					P2PManager.msg("clinet for server Go ...");
				}
				sleep(1000*5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	

}
