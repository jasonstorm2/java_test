package nettyLearn;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class MyNettyClientHandler2 extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("client的 channelRead方法被调用");
		int m = (int)msg;
		System.out.println("收到服务端发来的值："+m);
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	System.out.println("client 的channelActive方法被调用");
    	// TODO Auto-generated method stub
    	ctx.write("100");
    	ctx.flush();
    }
}


