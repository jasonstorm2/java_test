package Chapter17_NetWork;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步IO学习
 * AsynchronousServerSocketChannel 负责监听的channel
 * 其open有两种方法 一个默认，一个指定使用 AsynchronousChannelGroup来创建对象
 * 
 * AsynchronousChannelGroup 异步channel分组管理器，可以实现资源共享
 * 
 * accept方法也有两个，一个会阻塞线程，一个不会
 * 
 *  Future 对象的通用类型是实际操作的结果。比如，读取或写入操作会因为操作返回读或写的字节数，而返回一个 Future<Integer>。
 * @author LiZhenhua
 *
 */
public class SimpleAIOServer {
	static final int PORT = 30000;
	public static void main(String[] args)throws Exception{
		System.out.println("服务器启动");
		
		try(AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open()){			
			serverChannel.bind(new InetSocketAddress(PORT));
			
			while(true){
				// 接受客户端的请求
				Future<AsynchronousSocketChannel> future = serverChannel.accept();	
				System.out.println("get()阻塞");
				// 调用Future的get()方法，获取连接后返回的AsynchronousSocketChannel，此方法会阻塞当前先生,
				// 利用 Future 对象，当前线程可阻塞来等待结果
				AsynchronousSocketChannel socketChannel = future.get();
//				AsynchronousSocketChannel socketChannel = future.get(10,TimeUnit.SECONDS); // 设置阻塞10秒
				// 轮询操作的当前状态，还可取消操作
//				if (!future.isDone()) {
//				    future.cancel(true);
//				}
				
				System.out.println("有客户端连接到");
				//AsynchronousSocketChannel 可以执行IO操作
				socketChannel.write(ByteBuffer.wrap("欢迎您连接异步IO服务器".getBytes("utf-8"))).get();
			}
		}	
	}
}
