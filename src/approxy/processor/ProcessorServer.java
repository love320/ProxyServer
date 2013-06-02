package approxy.processor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import approxy.Config;
import approxy.manager.P2PManager;

public class ProcessorServer implements Runnable {
	
	private static ServerSocket serverSocket = null;

	private static Socket socketT = null;
	
	@Override
	public void run() {
		try {
				if(serverSocket == null) serverSocket = new ServerSocket(Config.PROXY_TO_DOC);
				System.out.println("serverSocket:"+serverSocket);
				initSocket();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	public static void initSocket() throws IOException{
		if(socketT == null) socketT = serverSocket.accept();
		System.out.println("socketT:"+socketT);
		while(true){
			try {
				read();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		socketT = null;
		initSocket();
	}
	
	public static void read() throws IOException{
		byte[] buffer = new byte[1024*4];
		int temp = 0;
		temp = socketT.getInputStream().read(buffer);
		//if(temp==-1)break;
		String msg =new String(buffer,0,temp);
		//if(msg.equals(Config.CLIENTOK)) socketT = socketT;
		P2PManager.msg(msg);
	}
	
	public static void sendConnetNewSocket() throws IOException{
		try {
			DataOutputStream out=new DataOutputStream(socketT.getOutputStream());  
			out.write(Config.SERVEROK.getBytes());
		} catch (Exception e) {
			socketT = null;
			initSocket();
			sendConnetNewSocket();
		}
	}
	
	public static Socket getSocket(){
		try {
			if(isclose()) return serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isclose(){
		try {
			if(socketT == null ) initSocket();
			DataOutputStream out = new DataOutputStream(socketT.getOutputStream());
			out.write(Config.TEST.getBytes());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return false;
	}
	

}
