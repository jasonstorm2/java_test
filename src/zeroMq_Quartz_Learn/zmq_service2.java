package zeroMq_Quartz_Learn;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * 服务端zmq
 * @author LiZhenhua
 *
 */
public class zmq_service2 {
	public static void main(String[] args) {
		ZContext context = new ZContext();//表示创建用于一个I/O线程的 context
		context.setIoThreads(1);
		
		ZMQ.Socket socket = context.createSocket(ZMQ.PULL);
		socket.setRcvHWM(0);
		socket.setLinger(3000);		
		socket.bind("tcp://127.0.0.1:5555");  //绑定端口；
		int i=0;
		System.out.println("zmq服务端开启............");
		while(true){
			System.out.println("true");
			byte[] req = new byte[1204];
			System.out.println("waiting.............");
			int recvLen = socket.recv(req, 0, req.length, ZMQ.DONTWAIT);
			System.out.println("receive.!!!!!!!!!!!!");
			if(recvLen != -1){
				System.out.println("服务端收到信息："+ new String(req));
				String res = "我是服务端，已经收到信息";
//				socket.send(res);//向客户端 发送信息
			}
		}
	}
}
