package nettyLearn;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * netty 不只可以处理 nio类型的数据，也可以处理oio等类型的数据
 * nio是目前使用最多的传输类型，transport 
 * @author LiZhenhua
 *
 */
public class MyNettyServer1 {
	
    private int port;
    
    private  Logger logInfo = LogManager.getLogger("myInfo");
    private  Logger logWarn = LogManager.getLogger("myWarn");
    private  Logger loggerANY = LogManager.getLogger("ANY");

    public MyNettyServer1(int port) {
        super();
        this.port = port;
    }

    public void run() throws Exception {

        /***
         * NioEventLoopGroup 是用来处理I/O操作的多线程事件循环器，
         * Netty提供了许多不同的EventLoopGroup的实现用来处理不同传输协议。 在这个例子中我们实现了一个服务端的应用，
         * 因此会有2个NioEventLoopGroup会被使用。 第一个经常被叫做‘boss’，用来接收进来的连接。
         * 第二个经常被叫做‘worker’，用来处理已接受连接的通信量， 一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上。
         * 使用了多少线程 以及如何将它们映射到创建的通道 取决于EventLoopGroup实现，甚至可以通过构造函数进行配置。
         * 
         * 因为我们使用NIO transport，所以指定NioEventLoopGroup来接收新连接和处理已经接受的连接
         *  You create and assign the NioEventLoopGroup instances to handle event processing, 
         *  such as accepting new connections, receiving data, writing data, and so on. 
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("准备运行端口：" + port);
        logInfo.info("項目開始運行。。。。。。。。。。");
        logInfo.warn("項目開始運行warn。。。。。。。。。。");
        
        
        logWarn.info("this is a info message by logger2");
        logWarn.warn("this is a warn message by logger2");
        loggerANY.info("this is a info message by loggerANY");
        loggerANY.warn("this is a warn message by loggerANY");
        
        logInfo.error("logInfo.error");
        logWarn.error("logWarn.error");
        loggerANY.error("loggerANY.error");
        
        
        
        try {
            ServerBootstrap b = new ServerBootstrap();   
            b = b.group(bossGroup, workerGroup);
            b = b.channel(NioServerSocketChannel.class);
            b = b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                	ch.pipeline().addLast("pingpong", new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
                    ch.pipeline().addLast(new Plus22(),new PlusDecoder(),new MyNettyServerHandler1());
                }
            });          

            b = b.option(ChannelOption.SO_BACKLOG, 128);// (5)
            b = b.childOption(ChannelOption.SO_KEEPALIVE, true);// (6)

            ChannelFuture f = b.bind(port).sync(); //(7) //调用同步阻塞方法sync，等待绑定操作完成。完成之后Netty会返回一个ChannelFuture
            System.out.println(MyNettyServer1.class.getName() + "开始服务并且监听："+f.channel().localAddress());
  
            f.channel().closeFuture().sync();
        } finally {
            /***
             * 关闭
             */
        	System.out.println("finally语句执行");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            
//            workerGroup.shutdownGracefully().sync();
//            bossGroup.shutdownGracefully().sync();
        }
    }
    
    //将规则跑起来
    public static void main(String[] args) throws Exception {
    	//设置日志路径
    	System.setProperty("logPath", "log");
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new MyNettyServer1(port).run();
        System.out.println("server:run()");
    }
}
