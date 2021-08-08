package nettyLearn;

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
public class MyNettyClient {

    public static void main(String[] args) throws Exception {

        String host = "192.168.31.98";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {        	
            Bootstrap b = new Bootstrap(); // (1)            
            b.group(workerGroup); // (2)            
            b.channel(NioSocketChannel.class); // (3)            
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)        

            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new Plus2(),new PlusDecoder() ,new MyNettyClientHandler());
                }
            });
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
