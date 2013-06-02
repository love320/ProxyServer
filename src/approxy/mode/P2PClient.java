package approxy.mode;

import java.io.IOException;
import java.net.Socket;

import approxy.Config;
import approxy.manager.P2PManager;
import approxy.processor.ProcessorClient;

public class P2PClient extends java.lang.Thread  {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Started(Listen Port:"+Config.PROXY_HOST+":"+Config.PROXY_TO_DOC);
		
		ProcessorClient processor = new ProcessorClient();
		
		Thread mainthread= null;
		while(true){
			try {
				if(!ProcessorClient.isclose()){
					mainthread= new Thread(processor);//启动专用通信线程
					mainthread.start();
					System.out.println("clinet for server Go OK");
				}
				sleep(1000*5);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
