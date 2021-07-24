package nettyLearn;

import com.mysql.fabric.xmlrpc.base.Data;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟百万级别的客户端连接
 *
 *
 * 服务端实现监听多个端口，所有监听的端口处理的是同一个handler
 * @author LiZhenhua
 */
public class MyNettyOneMillionServer {

    public static int START_PORT = 10000;
    public static int PORT_NUM = 100;
    public static AtomicInteger atomicInteger = new AtomicInteger(0);


    public MyNettyOneMillionServer() {
        super();
    }

    public void run() throws Exception {


        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup(20);//可以使用默认无参数构造器，也可以指定线程数量
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new CountHandler());
                }
            });

            b.option(ChannelOption.SO_BACKLOG, 128);// (5)
            b.childOption(ChannelOption.SO_KEEPALIVE, true);// (6)
            for (int i = START_PORT; i < START_PORT + PORT_NUM; i++) {
                int port = i;
//                ChannelFuture ff = b.bind(i).addListener((ChannelFutureListener) future -> {
//                    System.out.println(Thread.currentThread().getName() + " bind success port:" + port);
//                }).sync();
//                ff.channel().closeFuture().sync();
                ChannelFuture f = b.bind(port).sync(); //(7) //调用同步阻塞方法sync，等待绑定操作完成。完成之后Netty会返回一个ChannelFuture
                System.out.println("port:"+port);
//                f.channel().closeFuture().sync(); //此处必须注释掉，不然线程卡在这里，服务器无法监听多个端口
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /***
             * 关闭
//             */
//            System.out.println("finally excuse");
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();

//            workerGroup.shutdownGracefully().sync();
//            bossGroup.shutdownGracefully().sync();
        }
    }

    //将规则跑起来
    public static void main(String[] args) throws Exception {
        //设置日志路径
        new MyNettyOneMillionServer().run();
        System.out.println("server:run()");
    }
}

class CountHandler extends ChannelInboundHandlerAdapter {

    private List<testObj> str = new ArrayList<>();
    private  testObj tt = new testObj();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    }

    /**
     * netty 的EventLoopGroup就像是一个线程池。每个线程处理事件。会出现同步问题
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active channelId: " + ctx.channel().id() + " active count:" + MyNettyOneMillionServer.atomicInteger.getAndIncrement()+" Thread:"+Thread.currentThread().getName());
        System.out.println("str:"+this.toString() + " getclass"+this.getClass()+" hashcode"+this.hashCode());
        System.out.println("str hashcode:"+str.hashCode());
        System.out.println("tt hashcode:"+tt.hashCode());
    }

    /***
     * 这个方法会在发生异常时触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("exceptionCaught方法被调用");
        // 出现异常就关闭
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 连接断开时触发
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("channel inactive channelId: " + ctx.channel().id());

    }
}

class testObj{
    testObj(){
        System.out.println("create new testobj. threadName:"+Thread.currentThread().getName());
    }

}
