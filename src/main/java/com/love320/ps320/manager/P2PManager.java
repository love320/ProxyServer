package com.love320.ps320.manager;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.love320.ps320.bean.IPort;
import com.love320.ps320.manager.io.FileOutMsg;
import com.love320.ps320.manager.socket.P2PSocket;
import com.love320.ps320.manager.socket.SocketMap;

public class P2PManager {
	
	public static Socket socketT  = null; //专用通信线程
	public static boolean isconn = false;//专用通信状态，true为正常，false为断开 
	
	public static boolean acceptWait = false;//是否有等待accept， true有，false无
	
	public static void msg(String message){
		System.out.println("Msg :"+message);
		FileOutMsg.setData(message);
	}
	
	//绑定通信
	public static void P2PGO(Socket clientSocket,Socket serverSocket){
		SocketMap.add(clientSocket);//加入容器
		SocketMap.add(serverSocket);//加入容器
		
		P2PSocket p2pC = new P2PSocket(clientSocket,serverSocket);
		P2PSocket p2pS = new P2PSocket(serverSocket,clientSocket);
		
		new Thread(p2pC).start();
		new Thread(p2pS).start();
		
	}
	
	//生成IP:port通信协议
	public static String IPort(String ip,Integer port){
		return "@"+ip +"#"+port+"@";
	}
	
	//解释IP:port通信协议
	public static List<IPort> IPort(String msg){
		List<IPort> list = new ArrayList<IPort>();
		if(msg == null) return list;
		
		String[] iports = msg.split("@");
		for(String sing : iports){
			String[] ss = sing.split("#");
			if(ss.length == 2){
				list.add(new IPort(ss[0],Integer.valueOf(ss[1])));
			}
		}
		return list;
	}

}
