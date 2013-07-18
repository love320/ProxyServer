package com.love320.ps320.manager.socket;

import java.net.ServerSocket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.love320.ps320.bean.IPort;
import com.love320.ps320.manager.P2PManager;

public class SocketServerMap {
	
	public static int connum = 0;
	
	public static Map<String, ServerSocket> MAP = new HashMap<String, ServerSocket>();
	
	//添加到Socket容器
	public static void add(ServerSocket socket){
		Date date = new Date();
		String key = "S"+newconnum()+"-"+date.getTime();
		P2PManager.msg("Add ServerSocket key:"+key);
		MAP.put(key, socket);
	}
	
	//添加到Socket容器
	public static void add(IPort iport,ServerSocket socket){
		Date date = new Date();
		String ipstring = iport.getProxy()+"@"+iport.getIp()+":"+iport.getPort();
		String key = "S"+newconnum()+"-"+date.getTime() +"-"+ipstring+"-"+iport.getType();
		P2PManager.msg("Add ServerSocket key:"+key);
		MAP.put(key, socket);
	}
	
	public static int newconnum(){
		connum ++;
		return connum;
	}
}
