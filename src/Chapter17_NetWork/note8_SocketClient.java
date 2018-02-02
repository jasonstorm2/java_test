package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 客户端通常可以使用socket的构造器来连接到指定的服务端 构造器： Socket(IntAddress/String addr,int port)
 * Socket(IntAddress/String addr,int port,IntAddress/String localAddr,int
 * localPort) :指定本地的ip和端口
 * 
 * 当执行Socket ss = new Socket("127.0.0.1",1022)语句是，将会连接到指定的服务器，服务器的accept方法向下执行
 * 于是，服务端客户端就产生一对相互连接的socket。
 * 
 * Socket的方法
 * 
 * InputStream getInputStream(): 返回该socket对应的输入流，通过该流从socket取出数据 读取
 * 
 * OutputStream getOutputStream():返回该socket对应的输出，通过该流向socket输出数据 写入
 * 
 * 客户端socket 可以设置超时时间，如果在指定时间内服务器没有响应，则认为是超时 先生成一个没有连接的socket，在设置超市参数 Socket ss2
 * = new Socket(); 
 * ss2.connect(new InetSocketAddress(InetAddress.getLocalHost(), 1022), 10000);
 * 
 * @author Administrator
 *
 */
public class note8_SocketClient {
	public static void main(String[] args) throws IOException {
		Socket ss = new Socket("139.199.159.226",3033);
		
		// 启动客户端线程来读取服务器的数据
		new Thread(new ClientThread(ss)).start();
		
		//获取socket的输出流，向服务端输出信息
		PrintStream ps = new PrintStream(ss.getOutputStream());
		String line = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while((line = br.readLine())!=null){ 
			ps.println(line);			
		}
		

//		BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
//		String line = br.readLine();
//		System.out.println("来自服务器的数据："+line);
//		br.close();
//		ss.close();
//		
//		// socket连接 设置超时时间--socket没有构造器可以设置超时的，所以先生成一个实例，在调用connect
		Socket ss2 = new Socket();
		ss2.connect(new InetSocketAddress(InetAddress.getLocalHost(), 1022), 10000);
	}

}

class ClientThread implements Runnable{
	
	private Socket s;
	BufferedReader br = null;
	
	public ClientThread(Socket s) throws IOException{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		
		try {
			String content = null;
			while((content = br.readLine()) != null){
				System.out.println(content);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
