package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 当两个通信实体没有建立连接之前，必须有一个做出“主动姿态”，主动接受另一个通信实体的请求
 * java中能主动接受请求的类是ServerSocket,ServerSocket对象用于监听来自客户端的SOCKET连接，
 * 如果没有连接，它将处于等待状态！！
 * 
 * 方法：
 * accept(): 接收一个socket请求，返回一个与客户端socket对应的socket，否则该方法一直处于等待状态，线程也被阻塞。
 * ServerSocket(int port):
 * ServerSocket(int port,int backlog)
 * ServerSocket(int port,int backlog,InetAddress localAddr)：多个网卡的电脑指定一个ip的socket
 * close()：关闭ServerSocket
 * 
 *  当ServerSocket使用完毕后，应该使用close()方法关闭该ServerSocket，在通常的情况下，服务端不会只接受一个客户端的请求，而是不断接受
 *  所以，java程序通常会循环调用accept方法
 *  
 *  在使用传统BufferedReader 的 readLine()方法读取数据时，在该方法成功返回之前，线程被阻塞，程序无法执行
 *  所以，服务端应该为每个socket单独启动一个线程，专门与每个客户端通信
 *  
 *  同样客户端也是如此，需要另开一个线程
 * 
 * @author Administrator
 *
 */
public class note7_SocketServer {
	//线程安全list
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());	
	
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(3383);
		System.out.println("服务端开启：。。。。");
		while(true){
			// 接收到socket消息，返回一个与客户端socket对应的socket
			// 此行代码会阻塞，将一直等待别人的连接
			Socket s = ss.accept();
			socketList.add(s);
			// 每当客户端连接后，启动一个线程，为该客户端服务
			new Thread(new ServerThread(s)).start();

		}
//		ServerSocket ss = new ServerSocket(1022);
//		Socket s = ss.accept();
//		socketList.add(s);
	}	
}

		

class ServerThread implements Runnable{
	Socket s = null;
	BufferedReader br = null;
	public ServerThread(Socket s) throws IOException{
		this.s = s;
		// 初始化socket对应的输入流
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("有客户端成功连接");
		PrintStream p;
		try {
			p = new PrintStream(s.getOutputStream());
			p.println("欢迎来到王者荣耀");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{			
			String content = null;
			// 如果该客户端socket有变动，向所有的用户发送一次信息
			while((content = readFromClient()) != null){
				for(Socket s : note7_SocketServer.socketList){
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
			note7_SocketServer.socketList.remove(s);
		}
		return null;
		
	}
	
}
