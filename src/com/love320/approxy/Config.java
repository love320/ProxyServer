package com.love320.approxy;

import java.util.ArrayList;
import java.util.List;

import com.love320.approxy.manager.IPort;



public class Config {
	
	//日志文件
	public static String FILESERVER = "proxyFileServer.txt";
	public static String FILECLIENT = "proxyFileClient.txt";

	//中转服务器
	public static int PROXY_TO_DOC=22;
	//public static String PROXY_HOST="127.0.0.1"; //proxy host
	public static String PROXY_HOST="kingdom.love320.com"; //proxy host
	
	//管理端口
	public static int MANAGERPROXY = 6320;
	
	//其它
	public static String TEST = "TEST";
	
	//端口映射
	public static List<IPort> IPORTLIST = new ArrayList<IPort>();
		
	//端口映射表
	static{
		
		//.逆向突破式代理
		IPORTLIST.add(IPort.reverseProxy(6522,"127.0.0.1",22)); //6522 -> 127.0.0.1:22
		
		IPORTLIST.add(IPort.reverseProxy(6512,"172.16.209.12",22)); //6522 -> 172.16.209.12:22
		//IPORTLIST.add(IPort.reverseProxy(6580,"172.16.209.12",8080)); //6522 -> 172.16.209.12:8080
		IPORTLIST.add(IPort.reverseProxy(6590,"172.16.209.12",9090)); //6522 -> 172.16.209.12:8080
		
		//IPORTLIST.add(IPort.reverseProxy(6555,"192.168.173.23",22)); //6555 ->192.168.173.23:22
		//IPORTLIST.add(IPort.reverseProxy(6100,"192.168.1.100",22)); //6100 ->192.168.1.100:22
		
		//普通代理
		IPORTLIST.add(IPort.proxy(6599, "113.243.142.23", 9999));
		
	}
	

}
