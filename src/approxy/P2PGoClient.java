package approxy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class P2PGoClient extends java.lang.Thread  {
	
	private static int connum = 10;

	@Override
	public void run(){
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
	
	public static void main(String[] args) throws IOException {
		System.out.println("Document-Proxy-Server v1.0 Started(Listen Port:"+Config.PROXY_HOST+":"+Config.PROXY_TO_DOC+") (DocumentServer:"+Config.DOC_HOST+":"+Config.DOC_PORT+")");
		//
		Socket newsocket=null;
		byte[] buffer = new byte[1024*4];
    	int temp = 0;
		while(true){
			try {
				System.out.println("connect……"+connum);
				//运行前休息
				if(newsocket == null || isclose(newsocket)) {
					sleep(1000*connum);
					if(connum < 300) connum += 10;
				}else{
					connum = 10;
				}
				if(newsocket == null || isclose(newsocket)) newsocket = new Socket(Config.PROXY_HOST,Config.PROXY_TO_DOC);
	    		temp = newsocket.getInputStream().read(buffer);
			
	    		//if(temp==-1)break;
	    		
	    		if(temp>0){
	    			Socket connectionSocket = new Socket(Config.DOC_HOST,Config.DOC_PORT);
	    			Socket server = new Socket(Config.PROXY_HOST,Config.PROXY_TO_DOC);
	    			System.out.println("connectionSocket:"+connectionSocket);
	    			System.out.println("server:"+server);
	    			new Thread(new P2PSocketProxy(connectionSocket,server)).start();
	    		}
    		
			} catch (Exception e) {
			}
			
    	}
	}
	
}
