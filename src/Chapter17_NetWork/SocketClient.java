package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SocketClient {
	public static void main(String[] args) throws IOException {
		Socket ss = new Socket("127.0.0.1",1022);
		
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
//		Socket ss2 = new Socket();
//		ss2.connect(new InetSocketAddress(InetAddress.getLocalHost(), 1022), 10000);
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
