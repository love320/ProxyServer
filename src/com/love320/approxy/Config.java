package com.love320.approxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import com.love320.approxy.manager.IPort;



public class Config {
	
	//外部配置文件
	public static String CONF = "ps320.config";
	
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
		//IPORTLIST.add(IPort.reverseProxy(6522,"127.0.0.1",22)); //6522 -> 127.0.0.1:22
		
		//普通代理
		//IPORTLIST.add(IPort.proxy(6699, "113.243.142.23", 9999));
		
		
		/** 配置文件管理 */
		Properties props = new Properties();
		Set<String> keys = null;
		try {
			InputStream in = new FileInputStream(Config.CONF);
			props.load(in);
			keys = props.stringPropertyNames();
			
			if(props.getProperty("PROXY_HOST") != null ) PROXY_HOST = props.getProperty("PROXY_HOST");
			if(props.getProperty("PROXY_TO_DOC") != null ) PROXY_TO_DOC = Integer.parseInt(props.getProperty("PROXY_TO_DOC"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(keys != null){
			for(String key:keys){
				String value = props.getProperty(key);
				String ss[] = value.split("@");
				if(ss.length == 2){
					String ips[] = ss[1].split(":");
					if(key.toUpperCase().indexOf("A") ==0){
						//普通代理
						IPORTLIST.add(IPort.proxy(Integer.parseInt(ss[0]), ips[0], Integer.parseInt(ips[1])));
					}
					if(key.toUpperCase().indexOf("B") ==0){
						//.逆向突破式代理
						IPORTLIST.add(IPort.reverseProxy(Integer.parseInt(ss[0]), ips[0], Integer.parseInt(ips[1])));
					}
				}
			}
		}

		
		
	}
	

}
