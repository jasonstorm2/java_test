package zeroMq_Quartz_Learn;

import org.zeromq.ZMQ;

/**
 * 服务端zmq
 * @author Administrator
 *
 */
public class zmq_service {
	public static void main(String[] args) {
		ZMQ.Context context = ZMQ.context(1);//表示创建用于一个I/O线程的 context
		ZMQ.Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://127.0.0.1:5555");  //绑定端口；
		int i=0;
		while(true){
			
			byte[] req = socket.recv(); // 获取request发送过来的数据
			System.out.println("服务端收到信息："+ new String(req));
			
			
			String res = "我是服务端，已经收到信息";
			socket.send(res);//向客户端 发送信息
			i++;
			if(i>=5){
				break;
			}
		}		
		socket.close(); //关闭socket
		context.close();//关闭 上下文
		
		
		
	}
}
