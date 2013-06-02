package approxy;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;


public class P2PSocketProxy  implements Runnable {
	
	Socket connectionSocket;
	Socket serverSocket;
	
	String clientIPAddr="";
	
	private static int threadCount=0;
	
	public P2PSocketProxy(Socket connectionSocket,Socket serverSocket) {
		this.connectionSocket = connectionSocket;
		this.serverSocket = serverSocket;
	}

	public void run() {
		//connect to biee server
		onThreadStart();
		P2PSocket sc = new P2PSocket(connectionSocket,serverSocket);
		System.out.println("Connect to DOCUMENT Server "+Config.DOC_HOST+", Socket:"+serverSocket);
		new Thread(sc).start();
		//
		try {
			//analysis client IP
			clientIPAddr=connectionSocket.getRemoteSocketAddress().toString();
			//   /127.0.0.1:1585
			if(clientIPAddr!=null){
				clientIPAddr=clientIPAddr.substring(1);
				int p=clientIPAddr.indexOf(":");
				if(p>0){
					clientIPAddr=clientIPAddr.substring(0,p);
				}
			}
			else{
				clientIPAddr="";
			}
					
			//start read data from client socket
			System.out.println("Client Address : "+connectionSocket.getRemoteSocketAddress());
			InputStream inFromClient = connectionSocket.getInputStream();
			DataOutputStream outToServer = new DataOutputStream(serverSocket
					.getOutputStream());
	    	byte[] buffer = new byte[1024*4];   
	    	int temp = 0;
	    	while(true){
	    		temp = inFromClient.read(buffer);
	    		if(temp==-1)break;
	    		
	    		if(temp>0){
	    			outToServer.write(buffer,0,temp);
	    		}
	    	}
			serverSocket.close();
			connectionSocket.close();
		} catch (Exception e) {
			System.out.println("Document-Proxy-Exception:" + e.getMessage());
			//e.printStackTrace();
		}
		System.out.println("Client thread exit...");
		onThreadEnd();
	}

	private void onThreadEnd() {
		// TODO Auto-generated method stub
		threadCount--;
		System.out.println("ClentConnection-Thread-Count:"+threadCount);
	}

	private void onThreadStart() {
		// TODO Auto-generated method stub
		threadCount++;
		System.out.println("ClentConnection-Thread-Count:"+threadCount);
		
	}

}
