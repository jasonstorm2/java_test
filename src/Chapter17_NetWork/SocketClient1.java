package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class SocketClient1 {
	private static final int SERVER_PORT = 30000;
	private Socket socket;
	private PrintStream ps;
	private BufferedReader brServer;
	private BufferedReader keyIn;
	
	public void init(){
		
		boolean isLogin = false;
		try{
			// 键盘输入
			keyIn = new BufferedReader(new InputStreamReader(System.in));
			socket = new Socket("127.0.0.1",SERVER_PORT);
			// 向服务端输出
			ps = new PrintStream(socket.getOutputStream());
			// 服务端返回
			brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tip = "";
			
			// 向服务端注册
			while (true) {
				String userName = JOptionPane.showInputDialog(tip + "输入用户名");
				if (userName == null || userName.isEmpty()) {
					tip = "名字不能为空!";
					continue;
				}
				ps.println(CrazyitProtocol.USER_ROUND + userName
						+ CrazyitProtocol.USER_ROUND);
				String result = brServer.readLine();

				if (result.equals(CrazyitProtocol.NAME_REP)) {
					tip = "用户名重复！";
					continue;
				}
				if (result.equals(CrazyitProtocol.LOGIN_SUCCESS)) {
					isLogin = true;
					break;
				}
			}			
			
			
		}catch(UnknownHostException ex){
			System.out.println("找不到远程服务器，请确定服务器已经启动");
			closeRs();
			System.exit(1);
			
		}catch (IOException e) {
			System.out.println("网络异常，请重新登录");
			closeRs();
			System.exit(1);			
		}
		
		if(isLogin){
			// 以该socket的输入流，即服务端的返回 启动客户端线程
			new ClientThread(brServer).start();
		}else{
			closeRs();
			System.exit(1);
		}
	
	}
	
	//关闭socket 输入流，输出流的方法
	private void closeRs(){
		try{
			if(keyIn != null){
				ps.close();
			}
			if(brServer != null){
				ps.close();
			}
			if(ps != null){
				ps.close();
			}
			if(socket != null){
				keyIn.close();
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 定义一个读取键盘输出，并向网络发送的方法
	 * 
	 * 客户端收入的语句
	 * 私聊格式-- //name:content  例如:  //jason:你好
	 * 群聊格式-- 除私聊格式外的所有格式
	 */
	private void readAndSend(){
		try{
			String line = null;
			while((line = keyIn.readLine())!=null){
				if(line.indexOf(":")>0 && line.startsWith("//")){
					line = line.substring(2);
					ps.println(CrazyitProtocol.PRIVATE_ROUND + line.split(":")[0] + CrazyitProtocol.SPLIT_SIGN 
							+ line.split(":")[1] + CrazyitProtocol.PRIVATE_ROUND);
				}else{
					ps.println(CrazyitProtocol.MST_ROUND + line + CrazyitProtocol.MST_ROUND);
				}
				
			}
			
		}catch(IOException e){
			System.out.println("网络异常，请重新登录");
			closeRs();
			System.exit(1);
		}
	}

	public static void main(String[] args) throws IOException {
		SocketClient1 client = new SocketClient1();
		client.init();
		client.readAndSend();		
	}
	
	
	class ClientThread extends Thread{
		BufferedReader br = null;
		
		public ClientThread(BufferedReader br){
			this.br = br;
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
			
			finally{
				try{
					if(br != null){
						br.close();
					}
				}catch(IOException ex){
					ex.printStackTrace();
				}
			}
			
		}
		
	}
	
}


