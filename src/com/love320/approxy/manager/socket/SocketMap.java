package com.love320.approxy.manager.socket;

import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.love320.approxy.bean.IPort;
import com.love320.approxy.manager.P2PManager;

public class SocketMap {
	
	public static int connum = 0;
	
	public static Map<String, Socket> MAP = new HashMap<String, Socket>();
	
	//添加到Socket容器
	public static void add(Socket socket){
		Date date = new Date();
		String ipstring =socket.getInetAddress()+":"+socket.getPort();
		String key = "C"+newconnum()+"-"+date.getTime() +"-"+ipstring;
		P2PManager.msg("Add Socket key:"+key);
		MAP.put(key, socket);
	}
	
	//添加到Socket容器
	public static void add(IPort iport,Socket socket){
		Date date = new Date();
		String ipstring = iport.getProxy()+"@"+iport.getIp()+":"+iport.getPort();
		String key = "C"+newconnum()+"-"+date.getTime() +"-"+ipstring+"-"+iport.getType();
		P2PManager.msg("Add Socket key:"+key);
		MAP.put(key, socket);
	}
	
	public static int newconnum(){
		connum ++;
		return connum;
	}
}
