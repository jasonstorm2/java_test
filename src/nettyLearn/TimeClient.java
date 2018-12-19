package nettyLearn;

import java.util.Timer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 在Netty中,编写服务端和客户端最大的并且唯一不同的使用了不同的BootStrap和Channel的实现
 * @author LiZhenhua
 *
 */
public class TimeClient {

    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
        	
//        	BootStrap和ServerBootstrap类似,不过他是对非服务端的channel而言，比如客户端或者无连接传输模式的channel。
            Bootstrap b = new Bootstrap(); // (1)
            
//          如果你只指定了一个EventLoopGroup，那他就会即作为一个‘boss’线程，
//          也会作为一个‘workder’线程，尽管客户端不需要使用到‘boss’线程。
            b.group(workerGroup); // (2)
            
//          代替NioServerSocketChannel的是NioSocketChannel,这个类在客户端channel被创建时使用。
            b.channel(NioSocketChannel.class); // (3)
            
//            不像在使用ServerBootstrap时需要用childOption()方法，因为客户端的SocketChannel没有父channel的概念。
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new TimeClientHandler());
//                }
//            });
//            /************流数据的传输处理2--开始*************/
//            //  添加的通道处理器的顺序很重要，不能随便放顺序
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new TimeDecoder() ,new TimeClientHandler());
//                }
//            });
//            /************流数据的传输处理2--结束*************/
            
//            /************流数据的传输处理3--开始*************/
//            //  添加的通道处理器的顺序很重要，不能随便放顺序
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new TimeDecoder2() ,new TimeClientHandler());
//                }
//            });
//            /************流数据的传输处理3--结束*************/
            
            /************用POJO代替ByteBuf--开始*************/
            //  添加的通道处理器的顺序很重要，不能随便放顺序
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new POJODecoder() ,new TimeClientHandler());
                }
            });
            /************用POJO代替ByteBuf--结束*************/

//            b.remoteAddress(remoteAddress);//连接的方法有很多种
            // 我们用connect()方法代替了bind()方法。 启动客户端
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
