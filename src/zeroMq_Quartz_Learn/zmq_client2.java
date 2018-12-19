package zeroMq_Quartz_Learn;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * 客户端zmq
 * @author LiZhenhua
 *
 */
public class zmq_client2 {
	public static void main(String[] args) {
		System.out.println("打开客户端。。。。。。。。。");
		
		for(int i=0;i<5;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ZContext context = new ZContext();//表示创建用于一个I/O线程的 context
					ZMQ.Socket socket = context.createSocket(ZMQ.PUSH); //zmq模式，客户端请求
					socket.connect("tcp://127.0.0.1:5555");  //与服务端建立连接
			
					String req = "hello";
					socket.send(req);
//					byte[] res = socket.recv();
//					System.out.println("客户端收到服务端返回:"+new String(res));
				}
			}).start();
			
		}	
		
	}
}
