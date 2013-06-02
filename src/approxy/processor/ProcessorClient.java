package approxy.processor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import approxy.Config;
import approxy.manager.P2PManager;

public class ProcessorClient implements Runnable {

	public static Socket socketT = null;
	
	@Override
	public void run() {
			try {
				socketT = new Socket(Config.PROXY_HOST,Config.PROXY_TO_DOC);
				
				DataOutputStream out=new DataOutputStream(socketT.getOutputStream());  
				out.write(Config.CLIENTOK.getBytes());
				
				while(true){
					read();//读取
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public void read() throws IOException{
		
		byte[] buffer = new byte[1024*4];
		int temp = 0;
		temp = socketT.getInputStream().read(buffer);

		String msg =new String(buffer,0,temp);
		String[] ss = msg.split("#");
		Socket clientSocket = null;
		if(temp>0 && ss.length == 2 ){
			clientSocket = new Socket(ss[0],Integer.valueOf(ss[1]));
			Socket serverSocket = new Socket(Config.PROXY_HOST,Config.PROXY_TO_DOC);
			System.out.println("clientSocket:"+clientSocket);
			System.out.println("server:"+serverSocket);
			
			P2PManager.P2PGO(clientSocket,serverSocket);//启动

		}
	}
	
	public static boolean isclose(){
		try {
			if(socketT == null ) return false;
			DataOutputStream out = new DataOutputStream(socketT.getOutputStream());
			out.write(Config.TEST.getBytes());
			socketT.sendUrgentData(0xFF);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return false;
	}

}
