package com.love320.approxy.processor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.love320.approxy.manager.P2PManager;
import com.love320.approxy.manager.socket.P2PSocket;
import com.love320.approxy.processor.command.ConfigCommand;
import com.love320.approxy.processor.command.Icommand;


//处理请求的连接
public class ManagerSocketServer implements Runnable {
	
	private Socket socket;
	
	public ManagerSocketServer(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
	    	DataOutputStream out =new DataOutputStream(socket.getOutputStream());   
	    	InputStream in =socket.getInputStream();

	    	byte[] buffer = new byte[1024];   
	    	int temp = 0;   
	    	while(true){
	    		temp = in.read(buffer);
	    		if(temp==-1)break;
	    		String msg =new String(buffer,0,temp);
	    		P2PManager.msg(msg);
	    		for(Icommand command: ConfigCommand.commands){
	    			String comsg = command.action(msg);
	    			if(!comsg.equals(msg)) out.write((comsg+"\r\n").getBytes());
	    		}
	    		out.flush();
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}   
		P2PSocket.socketClose(socket);
	}
	
	
	
}
