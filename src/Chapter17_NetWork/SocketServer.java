package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SocketServer {
	//线程安全list
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());	
	
	
	public static void main(String[] args) throws IOException {
//		ServerSocket ss = new ServerSocket(1022);
//		while(true){
//			// 接收到socket消息，产生一个socket
//			// 此行代码会阻塞，将一直等待别人的连接
//			Socket s = ss.accept();
//			socketList.add(s);
//			// 每当客户端连接后，启动一个线程，为该客户端服务
//			new Thread(new ServerThread(s)).start();
//
//		}
		ServerSocket ss = new ServerSocket(1022);
		Socket s = ss.accept();
		socketList.add(s);
	}
}

class ServerThread implements Runnable{
	Socket s = null;
	BufferedReader br = null;
	public ServerThread(Socket s) throws IOException{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("33");
		
		try{			
			String content = null;
			// 如果该客户端socket有变动，向所有的用户发送一次信息
			while((content = readFromClient()) != null){
				for(Socket s : SocketServer.socketList){
					PrintStream ps = new PrintStream(s.getOutputStream());
					ps.println(content);
				}
				
			}
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	// 定义读取客户端数据的方法
	private String readFromClient(){
		
		try {
			return br.readLine();
			// 如果捕获到异常，表明该socket对应的客户端已经关闭
		} catch (IOException e) {
			// 删除该 socket
			SocketServer.socketList.remove(s);
		}
		return null;
		
	}
	
}
