package nettyLearn;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty 不只可以处理 nio类型的数据，也可以处理oio等类型的数据
 * nio是目前使用最多的传输类型，transport 
 * @author LiZhenhua
 *
 */
public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
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
        try {
            /**
             * ServerBootstrap 是一个启动server的辅助启动类 你可以使用一个Channel来启动一个server。但是这会是一个复杂的处理过程，在很多情况下你并不需要这样做。
             *You create a ServerBootstrap instance to bootstrap the server and bind it later
             */
            ServerBootstrap b = new ServerBootstrap();
            /**
             * 这一步是必须的，如果没有设置group将会报java.lang.IllegalStateException: group not
             * set异常
             */
            b = b.group(bossGroup, workerGroup);
            /***
             * ServerSocketChannel以NIO的selector为基础进行实现的，用来接收新的连接
             * 在这里，我们指定使用NioServerSocketChannel类，它用于实例化一个新的channel来接受传入的连接（connections）
             * 指定NioServerSocketChannel 为channel的类型，当然你也可以使用其他类型，比如OioServerSocketChannel
             */
            b = b.channel(NioServerSocketChannel.class);
            /***
             * You set up a childHandler that executes for every accepted connection. 
             * 这里指定的处理程序(Handler)将始终由新接受的通道(Channel)进行计算。
             * 这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。 
             * ChannelInitializer是一个特殊的处理类(Handler)，他的目的是帮助使用者配置一个新的(Channel)。
             * 也许你想通过增加一些处理类(Handler)比如DiscardServerHandler 来配置一个新的Channel的(ChannelPipeline) 来实现你的网络程序。 
             * 当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，
             * 然后提取这些匿名类到最顶层的类上。
             */
//            b = b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new DiscardServerHandler());// demo1.discard
//                    // ch.pipeline().addLast(new
//                    // ResponseServerHandler());//demo2.echo
//                    // ch.pipeline().addLast(new
//                    // TimeServerHandler());//demo3.time
//                }
//            });
            
            /************用POJO代替ByteBuf--开始*************/ 
            //在ChannelPipeline中添加发送消息的编码处理，注意，在DiscardServerHandler之前
            b = b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new POJOEncoder(),new DiscardServerHandler());// demo1.discard
                    // ch.pipeline().addLast(new
                    // ResponseServerHandler());//demo2.echo
                    // ch.pipeline().addLast(new
                    // TimeServerHandler());//demo3.time
                }
            });
            /************用POJO代替ByteBuf--结束*************/
            
            /***
             * 你可以设置参数。这些参数特定于channel的实现 
             * 我们正在写一个TCP/IP的服务端，
             * 因此我们被允许设置socket的参数选项比如tcpNoDelay和keepAlive。
             * 请参考ChannelOption和详细的ChannelConfig实现的接口文档以此可以对ChannelOptions的有一个大概的认识。
             */
            b = b.option(ChannelOption.SO_BACKLOG, 128);// (5)
            /***
             * option()是提供给NioServerSocketChannel用来接收进来的连接。
             * childOption()是提供给由父管道ServerChannel接收到的连接，在这个例子中父管道是NioServerSocketChannel。
             */
            b = b.childOption(ChannelOption.SO_KEEPALIVE, true);// (6)
            /***
             * 绑定端口并启动去接收进来的连接
             * Here, we bind to the port 8080 of all NICs (network interface cards) in the machine. 
             * You can now call the bind() method as many times as you want (with different bind addresses.)
             * 
             * sync() 方法会引起程序阻塞，直到连接被建立
             */
            ChannelFuture f = b.bind(port).sync(); //(7)
            System.out.println(DiscardServer.class.getName() + "开始服务并且监听："+f.channel().localAddress());
            /**
             * 这里会一直等待，直到socket被关闭
             * 
             */
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
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
        System.out.println("server:run()");
    }
}
