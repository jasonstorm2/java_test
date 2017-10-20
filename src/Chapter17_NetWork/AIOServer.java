package Chapter17_NetWork;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AIOServer {
	static final int PORT = 30000;
	final static String UTF_8 = "utf-8";
	static List<AsynchronousSocketChannel> channelList =  new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		System.out.println("开始监听");
		AIOServer server = new AIOServer();
		server.startListen();
		Thread.sleep(10000);
		System.out.println("主线程结束");
	}
	
	public void startListen() throws InterruptedException,Exception{
		// 创建一个线程池--线程池与channelgroup对象是什么关系，有什么联系
		ExecutorService executor = Executors.newFixedThreadPool(20);
		// 指定线程池生成AsynchronousChannelGroup对象
		AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executor);
		
		AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(channelGroup).bind(new InetSocketAddress(PORT));
		serverChannel.accept(null, new AcceptHandler(serverChannel));
		
	}
	
	
	class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object>{
		private AsynchronousServerSocketChannel serverChannel;
		public AcceptHandler(AsynchronousServerSocketChannel sc){
			this.serverChannel = sc;		
		}
		ByteBuffer buff = ByteBuffer.allocate(1024);
		

		@Override
		public void completed(final AsynchronousSocketChannel sc, Object attachment) {
			// TODO Auto-generated method stub
			AIOServer.channelList.add(sc);
			serverChannel.accept(null, this);
			sc.read(buff, null, new CompletionHandler<Integer, Object>() {

				@Override
				public void completed(Integer result, Object attachment) {
					// TODO Auto-generated method stub
					buff.flip();
					String content = StandardCharsets.UTF_8.decode(buff).toString();
					
					for(AsynchronousSocketChannel c : AIOServer.channelList){						
						try {
							c.write(ByteBuffer.wrap(content.getBytes(AIOServer.UTF_8))).get();
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					buff.clear();
					sc.read(buff, null, this);					
				}

				@Override
				public void failed(Throwable exc, Object attachment) {
					// TODO Auto-generated method stub
					System.out.println("有人离线啦");
					AIOServer.channelList.remove(sc);
				}
			});
			
		}

		@Override
		public void failed(Throwable arg0, Object arg1) {
			// TODO Auto-generated method stub
			System.out.println("连接失败:"+arg0);			
		}		
	}

}
