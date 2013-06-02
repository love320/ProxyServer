package approxy;

import java.util.ArrayList;
import java.util.List;

import approxy.manager.IPort;

public class Config {

	//端口映射
	public static List<IPort> IPORTLIST = new ArrayList<IPort>();
	
	//中转服务器
	public static int PROXY_TO_DOC=22;
	public static String PROXY_HOST="127.0.0.1"; //proxy host
	//public static String PROXY_HOST="183.62.141.12"; //proxy host
	
	
	//其它
	public static String CLIENTOK = "clientok";//客户发生成socket
	public static String SERVEROK= "serverok";//服务发生成socket
	public static String TEST = "";
	
	//端口映射表
	static{
		IPORTLIST.add(new IPort(6522,"127.0.0.1",22)); //6522 -> 127.0.0.1:22
		IPORTLIST.add(new IPort(6555,"192.168.173.23",22)); //6555 ->192.168.173.23:22
	}
	

}
