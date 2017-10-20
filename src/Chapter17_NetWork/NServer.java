package Chapter17_NetWork;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NServer {
	private Selector selector = null;
	static final int PORT = 30000;
	// 定义实现编码解码的 字符集对象
	private Charset charset = Charset.forName("UTF-8");
	
	public void init() throws IOException{
		// 获得一个选择器实例 open方法
		selector = Selector.open();
		
		// 通过静态方法 open获得一个serverSocketChannel对象
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1",PORT);
		// serverSocketChannel绑定在一定地址和端口
		serverSocketChannel.bind(isa);
		// serverSocketChannel设置为非阻塞
		serverSocketChannel.configureBlocking(false);
		// serverSocketChannel向selector注册
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(selector.select() > 0){
			// 依次处理selector上的每个已经选择的selectionkey
			for(SelectionKey sk : selector.selectedKeys()){
				// 删除正在处理的selectionkey
				selector.selectedKeys().remove(sk);
				// 如果 sk 对应的channel包含客户端的 连接请求
				if(sk.isAcceptable()){
					// 类似哦。。调用accept方法，产生服务端的 socketchannel
					SocketChannel sc = serverSocketChannel.accept();
					// 也要设置为非阻塞
					sc.configureBlocking(false);
					// 也要注册在selector上
					sc.register(selector, SelectionKey.OP_READ);
					// 将sk 对应的Channel设置成准备接受其他请求
					sk.interestOps(SelectionKey.OP_ACCEPT);					
				}
				// 对应的 channel有数据需要读
				if(sk.isReadable()){
					// 获取对应的 channel，该channel上有可读数据
					SocketChannel sc = (SocketChannel)sk.channel();
					ByteBuffer buff = ByteBuffer.allocate(1024);
					
					String content = "";
					
					try{
						// 读到 buff中
						while(sc.read(buff)>0){
							buff.flip();
							content += charset.decode(buff);
						}
						
						System.out.println("读取的数据："+content);
						// 将sk对应的channel 设置成准备下一次读取
						sk.interestOps(SelectionKey.OP_READ);					
						//捕获到该 sk 对应 的Channel 出现了异常，说明该channel对应的client出现了问题
						// 所以从 selector中取消 sk 的注册
					}catch(IOException ex){
						// 从 selector 中删除指定的 key
						sk.cancel();
						if(sk.channel() != null){
							sk.channel().close();
						}
					}
					// 聊天信息不为空，向所有客户端发送消息
					if(content.length()>0){
						for(SelectionKey key : selector.keys()){
							Channel targetChannel = key.channel();
							// 如果channel 是 SocketChannel对象
							if(targetChannel instanceof SocketChannel){
								SocketChannel dest = (SocketChannel)targetChannel;
								dest.write(charset.encode(content));
							}
							
						}
						
					}					
				}
				
			}			
		}		
	}
	
	public static void main(String[] args) throws IOException{
		new NServer().init();
	}

}
