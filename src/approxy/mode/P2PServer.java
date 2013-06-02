package approxy.mode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import approxy.Config;
import approxy.manager.P2PManager;
import approxy.processor.ProcessorServer;

public class P2PServer {
	
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Document-Proxy-Server v1.0 Started(Listen Port:"+Config.PROXY_PORT+")");
		
		ProcessorServer processor = new ProcessorServer();
		new Thread(processor).start();//启动专用通信线程
		
		ServerSocket proxySocket = new ServerSocket(Config.PROXY_PORT);
		while (true) {
			try {
				Socket clientSocket = proxySocket.accept();//取客户连接
				ProcessorServer.sendConnetNewSocket();//通知客户机主动创建连接
				Socket server = ProcessorServer.getSocket();//取客户机的连接

				P2PManager.P2PGO(clientSocket,server);//启动
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}

}
