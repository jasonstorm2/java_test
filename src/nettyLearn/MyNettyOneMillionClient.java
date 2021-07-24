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
 *
 * 想要模拟多个客户端连接服务端，并不需要开启多条客户端线程
 * 只需要实现多个channel连接即可。
 * 此类用一个for循环，实现多个连接。
 * @author LiZhenhua
 */
public class MyNettyOneMillionClient {
    public static int START_PORT = 10000;
    public static int PORT_NUM = 100;

    public static void main(String[] args) throws Exception {


        String host = "localhost";

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)

            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast();
                }
            });
            for (int i = START_PORT; i < START_PORT + PORT_NUM; i++) {
                for (  int j = 0; j < 100; j++) {
                    ChannelFuture f = b.connect(host, i).sync(); // (5)
                    f.channel().close();
                    // 等待连接关闭
//                    f.channel().closeFuture().sync();
                }
            }
        } finally {
            workerGroup.shutdownGracefully();
        }


    }
}
