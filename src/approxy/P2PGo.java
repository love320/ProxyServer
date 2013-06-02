package approxy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class P2PGo {
	
	private static Socket newsocket = null;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		for(int i=0;i<args.length;i++){
			if(i==0){
				Config.DOC_HOST=args[0];
			}
			if(i==1){
				Config.DOC_PORT=Integer.valueOf(args[1]);
			}
			if(i==0){
				Config.PROXY_PORT=Integer.valueOf(args[2]);
			}
		}
		System.out.println("Document-Proxy-Server v1.0 Started(Listen Port:"+Config.PROXY_PORT+")");
		
		//ServerSocket newSocketServer = new ServerSocket(Config.T_PORT);
		ServerSocket welcomeSocket = new ServerSocket(Config.PROXY_PORT);
		ServerSocket serverSocket = new ServerSocket(Config.PROXY_TO_DOC);
		
		while (true) {
			//专用通信连接 
			if(newsocket == null || isclose(newsocket)) {
				System.out.println("newsocket:"+newsocket);
				newsocket = serverSocket.accept();
				 DataOutputStream out=new DataOutputStream(newsocket.getOutputStream());  
				 out.write(Config.NEWINT);
			}
			
			Socket connectionSocket = welcomeSocket.accept();
			Socket server = serverSocket.accept();
			new Thread(new P2PSocketProxy(connectionSocket,server)).start();
		}

	}
	
	
	public static boolean isclose(Socket socket){
		try{
		      DataOutputStream out=new DataOutputStream(socket.getOutputStream());  
			  out.write(Config.NEWINT);
		      return false;
		}catch(Exception ex){
			 return true;
		}
	}

}
