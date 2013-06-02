package approxy;

public class Config {
	
	//专用通信端口
	//public static int T_PORT = 6453;
	
	//目标机
	//public static String DOC_HOST="127.0.0.1";
	public static String DOC_HOST="192.168.1.100";
	public static int DOC_PORT=22;
	
	//中转服务器
	public static int PROXY_PORT=6555;
	public static int PROXY_TO_DOC=22;
	public static String PROXY_HOST="127.0.0.1"; //proxy host
	//public static String PROXY_HOST="183.62.141.12"; //proxy host
	
	
	//其它
	public static String CLIENTOK = "clientok";//客户发生成socket
	public static String SERVEROK= "serverok";//服务发生成socket
	public static String TEST = "";
	

}
