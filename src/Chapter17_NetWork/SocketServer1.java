package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer1 {
	private static final int SERVER_PORT = 30000;

	// 服务器记录每个user和其对应的输出流 
	public static CrazyitMap<String, PrintStream> clients = new CrazyitMap<String, PrintStream>();

	public void init() {
		try (ServerSocket ss = new ServerSocket(SERVER_PORT)) {
			while (true) {
				// 接收到socket消息，产生一个socket
				// 此行代码会阻塞，将一直等待别人的连接
				Socket socket = ss.accept();
				// 每当客户端连接后，启动一个线程，为该客户端服务
				new ServerThread(socket).start();
			}
		} catch (Exception e) {
			System.out.println("服务器启动失败，是否端口" + SERVER_PORT + "已被占用？");
		}
	}

	public static void main(String[] args) {
		SocketServer1 server = new SocketServer1();
		server.init();
	}

	class ServerThread extends Thread {
		private Socket socket;
		BufferedReader br = null;
		PrintStream ps = null;

		public ServerThread(Socket socket) {
			this.socket = socket;
		}
		
		// 将读到的内容去掉前后的协议字符，恢复成真实数据
		private String getRealMsg(String line) {
			return line.substring(CrazyitProtocol.PROTOCOL_LEN, line.length()
					- CrazyitProtocol.PROTOCOL_LEN);
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			
			try {
				// 客户端信息
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 服务端返回客户端信息
				ps = new PrintStream(socket.getOutputStream());
				String line = null;
				while((line = br.readLine())!=null){
					//处理客户端传来的是注册信息
					if(line.startsWith(CrazyitProtocol.USER_ROUND) && line.endsWith(CrazyitProtocol.USER_ROUND)){
						// 得到真实消息
						String userName = getRealMsg(line);	
						if(SocketServer1.clients.map.containsKey(userName)){
							System.out.println("用户名重复");
							ps.println(CrazyitProtocol.NAME_REP);
						}else{
							ps.println(CrazyitProtocol.LOGIN_SUCCESS);
							// 保存 用户名-输出流
							SocketServer1.clients.put(userName, ps);
							// 登陆成功 -- 向用户发出恭喜信息，向其他用户发出登陆消息
							ps.println("恭喜您登陆成功，当前用户数量："+SocketServer1.clients.map.size());
							System.out.println("用户数量："+SocketServer1.clients.map.size());	
							for(PrintStream clientPs : SocketServer1.clients.valueSet()){
								if(ps != clientPs){
									clientPs.println(SocketServer1.clients.getKeyByValue(ps)+"上线了");
								}								
							}
							
						}
						//处理私聊信息
					}else if(line.startsWith(CrazyitProtocol.PRIVATE_ROUND )&& line.endsWith(CrazyitProtocol.PRIVATE_ROUND)){
						String userAndMsg = getRealMsg(line);
						
						String user = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[0];
						String msg = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[1];
						//用户名存在才发送，不然报错
						if(SocketServer1.clients.map.containsKey(user)){
							SocketServer1.clients.map.get(user).println(SocketServer1.clients.getKeyByValue(ps) + "悄悄地对你说" + msg);
						}
						
					}else{
						// 处理群聊信息
						String msg = getRealMsg(line);
						// 遍历 clients 中的每个输出流
						for(PrintStream clientPs : SocketServer1.clients.valueSet()){
							clientPs.println(SocketServer1.clients.getKeyByValue(ps)+"说："+msg);
						}						
					}					
				}				
			} catch (IOException e) {
				// 向其他用户发送该用户离开的消息
				for(PrintStream clientPs : SocketServer1.clients.valueSet()){
					if(ps != clientPs){
						clientPs.println(SocketServer1.clients.getKeyByValue(ps)+"离开了");
					}								
				}				
				SocketServer1.clients.removeByValue(ps);
				System.out.println("用户数量："+SocketServer1.clients.map.size());	
				try{
					if(br != null){
						br.close();
					}
					if(ps != null){
						ps.close();
					}
					if(socket != null){
						socket.close();
					}
					
				}catch(IOException ex){
					ex.printStackTrace();
				}	
			}			
		}
	}

}
