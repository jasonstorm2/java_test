package nettyLearn;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * ��Netty��,��д����˺Ϳͻ������Ĳ���Ψһ��ͬ��ʹ���˲�ͬ��BootStrap��Channel��ʵ��
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
            //���Կͻ��������˷�����Ϣ���Ƿ���ͬһ���̴߳���channel��
            for (int i = 50000; i < 50510; i++) {
                if(channel != null){
                    System.out.println("send message to server:"+i);
                    sendMessage(i);
                }
                Thread.sleep(100);
            }
            channel.close();//�Ͽ�����


            // �ȴ����ӹر�
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }



    }

    public static void sendMessage(int i){
        channel.writeAndFlush(i);
    }


}
