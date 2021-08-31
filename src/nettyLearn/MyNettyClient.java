package nettyLearn;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 在Netty中,编写服务端和客户端最大的并且唯一不同的使用了不同的BootStrap和Channel的实现
 * @author LiZhenhua
 *
 */
public class MyNettyClient {
    private static Channel channel;

    public static void main(String[] args) throws Exception {

        String host = "127.0.0.1";
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
            channel = f.channel();
            System.out.println("sleep........");
            //测试客户端像服务端发送消息，是否都是同一个线程处理channel？
            for (int i = 50000; i < 50510; i++) {
                if(channel != null){
                    System.out.println("send message to server:"+i);
                    sendMessage(i);
                }
                Thread.sleep(100);
            }
            channel.close();//断开链接


            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }



    }

    public static void sendMessage(int i){
        channel.writeAndFlush(i);
    }


}
