package approxy;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class P2PSocket  implements Runnable{
	
	Socket clientSocket;   
	Socket serverSocket;   
	
	private static int threadCount=0;
	

    public P2PSocket (Socket clientSocket,Socket serverSocket){   
        this.clientSocket= clientSocket;   
        this.serverSocket = serverSocket;
    }   
    
    public Socket getServerSocket(){
    	return this.serverSocket;
    }
    
    
    public void run(){
    	onThreadStart();
    	if(serverSocket==null){
    		System.out.println("serverSocket not initialized!");
    		onThreadEnd();
    		return;
    	}
    	String sentence;   
    	String modifiedSentence;  
		try {
	    	DataOutputStream outToClient=new DataOutputStream(clientSocket.getOutputStream());   
	    	InputStream inFromServer=serverSocket.getInputStream();

	    	byte[] buffer = new byte[1024*4];   
	    	int temp = 0;   
	    	while(true){
	    		temp = inFromServer.read(buffer);
	    		//print
	    		String ss=new String(buffer,0,temp);
	    		//log("===============RESPONSE==============\n"+ss);
	    		//
	    		if(temp==-1)break;
	    		outToClient.write(buffer,0,temp);
	    	}
	    	serverSocket.close();  
	    	clientSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    	System.out.println("Server Connection Thread exit.");   
		onThreadEnd();

    }
    
	private void onThreadEnd() {
		threadCount--;
		System.out.println("ServerConnection-Thread-Count:"+threadCount);
	}

	private void onThreadStart() {
		threadCount++;
		System.out.println("ServerConnection-Thread-Count:"+threadCount);
		
	}
	
	

}
