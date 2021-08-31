package nettyLearn;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import com.mysql.fabric.xmlrpc.base.Data;

/**
 * ChannelHandlerAdapter是ChannelHandler的实现类 Handles a server-side channel
 * ChannelHandler 提供了你可以override的多个事件处理方法
 * 到目前，你只需要继承ChannelInboundHandlerAdapter方法，而不是自己去实现他的父类接口
 * @author LiZhenhua
 *
 */
public class MyNettyServerHandler1 extends ChannelInboundHandlerAdapter {
	
	
	/**
	 * 不准确，有交互时，空闲IDLE的时间就变了
	 * 
	 * 心跳机制
	 * 
	 * ﻿心跳是在TCP长连接中，客户端和服务端定时向对方发送数据包通知对方自己还在线，保证连接的有效性的一种机制
	 * 在服务器和客户端之间一定时间内没有数据交互时, 即处于 idle 状态时, 客户端或服务器会发送一个特殊的数据包给对方,
	 * 当接收方收到这个数据报文后, 也立即发送一个特殊的数据报文, 回应发送方, 此即一个 PING-PONG 交互. 自然地,
	 * 当某一端收到心跳消息后, 就知道了对方仍然在线, 这就确保 TCP 连接的有效性.
	 * 
	 * 服务端
	 * 添加 IdleStateHandler 心跳检测处理器，并添加自定义处理 Handler 类实现 userEventTriggered()
	 * 方法作为超时事件的逻辑处理；
	 * 
	 * 设定 IdleStateHandler 心跳检测每五秒进行一次读检测，如果五秒内ChannelRead()
	 * 方法未被调用则触发一次userEventTrigger()方法
	 * 
	 * 自定义处理类Handler继承ChannlInboundHandlerAdapter，实现其userEventTriggered()方法，
	 * 在出现超时事件时会被触发，包括读空闲超时或者写空闲超时；
	 * 
	 * 客户端 
	 * 添加IdleStateHandler心跳检测处理器，并添加自定义处理Handler类实现userEventTriggered()
	 * 方法作为超时事件的逻辑处理；
	 * 
	 * 设定IdleStateHandler心跳检测每四秒进行一次写检测，如果四秒内write()方法未被调用则触发一次userEventTrigger(
	 * )方法，实现客户端每四秒向服务端发送一次消息；
	 * 
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object _evt) throws Exception {
		
		
		if (_evt instanceof IdleStateEvent) {
			System.out.println(new Data());
			System.out.println("超时..........");
			System.out.println(System.currentTimeMillis());
			System.out.println("超时channelid:"+ctx.channel().id());
			
			
			IdleStateEvent evt = (IdleStateEvent) _evt;
			if (evt.state() == IdleState.WRITER_IDLE) {// 写超时

			} else if (evt.state() == IdleState.READER_IDLE) {// 读超时
				ctx.channel().close();//超过时间，认为失联，关闭通道
				ctx.close();		//三种关闭方式
				ctx.pipeline().close();
			} else if (evt.state() == IdleState.ALL_IDLE) {// 总超时
		
			}
		}
		super.userEventTriggered(ctx, _evt);
	}
	/**
     * 这里我们覆盖了chanelRead()事件处理方法。 每当从客户端收到新的数据时， 这个方法会在收到消息时被调用，
     * 这个例子中，收到的消息的类型是ByteBuf
     * 
     * @param ctx
     *            通道处理的上下文信息
     * @param msg
     *            接收的消息
     */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg){
		System.out.printf("theadName.......："+Thread.currentThread().getName());
		System.out.println("server的 channelRead方法被调用");
	    try {
	    	int m = (int)msg;
	    	System.out.println("收到的客户端的消息:"+m);
	    	ctx.writeAndFlush(m);   
        } finally {
            ReferenceCountUtil.release(msg);            
        }
	}		
	/**
	 * 把写进缓存的消息冲刷像远程连接
	 * 并且在操作完成后 关闭连接
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		// 通过ChannelHandlerContext 的方法，把处理过的信息，传递给下一个handler
//		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}	

	/************用POJO代替ByteBuf--开始*************/
	@Override	
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("sever 的channelActive方法被调用");
		 ChannelFuture f = ctx.writeAndFlush(10);		
		 System.out.println("服务器连接的channelid:"+ctx.channel().id());
//		 f.addListener(ChannelFutureListener.CLOSE); //操作完毕后，关闭channel连接
	}
	/************用POJO代替ByteBuf--结束*************/

	/***
	 * 这个方法会在发生异常时触发
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println("exceptionCaught方法被调用");
        /**
         * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，即当 Netty 由于 IO
         * 错误或者处理器在处理事件时抛出的异常时。在大部分情况下，捕获的异常应该被记录下来 并且把关联的 channel
         * 给关闭掉。然而这个方法的处理方式会在遇到不同异常的情况下有不 同的实现，比如你可能想在关闭连接之前发送一个错误码的响应消息。
         */
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
		System.out.println("****************channel断开，channelId = "+ctx.channel().id());
	}
}

class Plus1 extends ChannelOutboundHandlerAdapter  {
	  @Override
	  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
		  System.out.println("Plus1 编码被调用");
	      int m = (int) msg;
	      ByteBuf encoded = ctx.alloc().buffer(4);
	      encoded.writeInt(m+1);
	      ctx.write(encoded, promise); // (1)
	  }
	}

class Plus22 extends ChannelOutboundHandlerAdapter  {
	  @Override
	  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
		  System.out.println("Plus22 编码被调用");
	      int m = (int) msg;
	      ByteBuf encoded = ctx.alloc().buffer(4);
	      encoded.writeInt(m+1);
	      ctx.write(encoded, promise); // (1)
	  }
	}

