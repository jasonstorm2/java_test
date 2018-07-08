package nettyLearn;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;

/**
 * ChannelHandlerAdapter是ChannelHandler的实现类 Handles a server-side channel
 * ChannelHandler 提供了你可以override的多个事件处理方法
 * 到目前，你只需要继承ChannelInboundHandlerAdapter方法，而不是自己去实现他的父类接口
 * @author Administrator
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	/**
     * 这里我们覆盖了chanelRead()事件处理方法。 每当从客户端收到新的数据时， 这个方法会在收到消息时被调用，
     * 这个例子中，收到的消息的类型是ByteBuf
     * 
     * @param ctx
     *            通道处理的上下文信息
     * @param msg
     *            接收的消息
     */
	/**************打印数据***************/
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg){
	    try {
	    	ctx.write(msg);
	    	
			 //收到的消息的类型是 ByteBuf
			 ByteBuf in = (ByteBuf) msg;
			 //这个低效的循环实际上可以简化为
			 System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));			 
//			 while(in.isReadable()){
//			 System.out.println((char)in.readByte());
//			 System.out.flush();
//			 }            
        } finally {
            /**
             * ByteBuf是一个引用计数对象，这个对象必须显示地调用release()方法来释放。
             * 请记住处理器有职责是释放所有传递到处理器的引用计数对象。
             */
            // 抛弃收到的数据
            ReferenceCountUtil.release(msg);
            
        }
	}
	
//	  /**************应答客户***************/
//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) {
//		System.out.println("channelRead方法被调用");
//		ByteBuf in = (ByteBuf) msg;
//		 //这个低效的循环实际上可以简化为
//		 System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));			 
//
//		try {
//			// ChannelHandlerContext 对象提供了许多操作，使你能够触发各种各样的 I/O 事件和操作。
//			// 这里我们调用了 write(Object) 方法来逐字地把接受到的消息写入。
//			// 请注意不同于 DISCARD 的例子我们并没有释放接受到的消息，
//			// 这是因为当写入的时候 Netty 已经帮我们释放了。
//			ctx.write(msg); // (1)
//			// ctx.write(Object) 方法不会使消息写入到通道上，他被缓冲在了内部，你需要调用 ctx.flush()
//			// 方法来把缓冲区中数据强行输出。
//			// 或者你可以用更简洁的 cxt.writeAndFlush(msg) 以达到同样的目的。
//			ctx.flush(); // (2)
//
//		} finally {
//			// 没有抛弃数据
//
//		}
//
//	}
	
	/**
	 * 把写进缓存的消息冲刷像远程连接
	 * 并且在操作完成后 关闭连接
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		// 通过ChannelHandlerContext 的方法，把处理过的信息，传递给下一个handler
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}
	
	
	 /**************时间服务器***************/
//	@Override
//	
//	//1.channelActive() 方法将会在连接被建立并且准备进行通信时被调用。
//	// 即连接时就直接向客户端发送消息，此时还没进行通信
//	//因此让我们在这个方法里完成一个代表当前时间的32位整数消息的构建工作
//	
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//	System.out.println("channelActive方法被调用");
//		
////		2.为了发送一个新的消息，我们需要分配一个包含这个消息的新的缓冲。
////		因为我们需要写入一个32位的整数，因此我们需要一个至少有4个字节的 ByteBuf。
////		通过 ChannelHandlerContext.alloc() 得到一个当前的ByteBufAllocator，然后分配一个新的缓冲。
//		final ByteBuf time = ctx.alloc().buffer(4);
//		
//		time.writeInt((int)(System.currentTimeMillis()/1000l+2208988800l));
//		
////		3.和往常一样我们需要编写一个构建好的消息。但是等一等，flip 在哪？难道我们使用 NIO 发送消息时不是调用 java.nio.ByteBuffer.flip() 吗？
////		ByteBuf 之所以没有这个方法因为有两个指针，一个对应读操作一个对应写操作。
////		当你向 ByteBuf 里写入数据的时候写指针的索引就会增加，同时读指针的索引没有变化。读指针索引和写指针索引分别代表了消息的开始和结束。
//		
////		比较起来，NIO 缓冲并没有提供一种简洁的方式来计算出消息内容的开始和结尾，除非你调用 flip 方法。
////		当你忘记调用 flip 方法而引起没有数据或者错误数据被发送时，你会陷入困境。
////		这样的一个错误不会发生在 Netty 上，因为我们对于不同的操作类型有不同的指针。
////		你会发现这样的使用方法会让你过程变得更加的容易，因为你已经习惯一种没有使用 flip 的方式。
//		
////		另外一个点需要注意的是 ChannelHandlerContext.write() (和 writeAndFlush() )方法会返回一个 ChannelFuture 对象，
////	    一个 ChannelFuture 代表了一个还没有发生的 I/O 操作。
////		这意味着任何一个请求操作都不会马上被执行，因为在 Netty 里所有的操作都是异步的。
////		举个例子下面的代码中在消息被发送之前可能会先关闭连接。
////		 	Channel ch = ...;
////		    ch.writeAndFlush(message);
////		    ch.close();
//		
////		因此你需要在 write() 方法返回的 ChannelFuture 完成后调用 close() 方法，然后当他的写操作已经完成他会通知他的监听者。
////		请注意,close() 方法也可能不会立马关闭，他也会返回一个ChannelFuture。
//		
//		final ChannelFuture f = ctx.writeAndFlush(time);
//		
////		当一个写请求已经完成是如何通知到我们？这个只需要简单地在返回的 ChannelFuture 上增加一个ChannelFutureListener。
////		这里我们构建了一个匿名的 ChannelFutureListener 类用来在操作完成时关闭 Channel。
////
////		或者，你可以使用简单的预定义监听器代码:直接关闭channel
////		f.addListener(ChannelFutureListener.CLOSE);
//		
//		f.addListener(new ChannelFutureListener() {
//			
//			@Override
//			public void operationComplete(ChannelFuture future) throws Exception {
//				// TODO Auto-generated method stub
//				assert f == future;
//				System.out.println("写请求完成。监听返回");
//				ctx.close();
//			}
//		});
//	}
	
	/************用POJO代替ByteBuf--开始*************/
	@Override
	
	//1.channelActive() 方法将会在连接被建立并且准备进行通信时被调用。因此让我们在这个方法里完成一个代表当前时间的32位整数消息的构建工作
	
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive方法被调用");
		 ChannelFuture f = ctx.writeAndFlush(new UnixTime());		
		 f.addListener(ChannelFutureListener.CLOSE); //操作完毕后，关闭channel连接
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
}

//在这几行代码里还有几个重要的事情。
//第一， 通过ChannelPromise，当编码后的数据被写到了通道上Netty可以通过这个对象标记是成功还是失败。
//第二， 我们不需要调用cxt.flush()。
//因为处理器已经单独分离出了一个方法void flush(ChannelHandlerContext cxt),如果像自己实现flush方法内容可以自行覆盖这个方法。
//剩下的最后一个任务是在TimeServerHandler之前将一个时间编码器插入服务器端的ChannelPipeline，这是一个简单的练习。
/**
* 服务端发给客户端的消息编码
* @author Administrator
*
*/
class POJOEncoder extends ChannelOutboundHandlerAdapter  {
  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
  	System.out.println("POJOEncoder write方法被调用");
      UnixTime m = (UnixTime) msg;
      ByteBuf encoded = ctx.alloc().buffer(4);
      encoded.writeInt((int) m.value());
      ctx.write(encoded, promise); // (1)
  }
}

//进一步简化操作，你可以使用MessageToByteEncode:
class POJOEncoder2 extends MessageToByteEncoder<UnixTime> {
  @Override
  protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
  	System.out.println("TimeEncoder2 encode方法被调用");
      out.writeInt((int) msg.value());
  }
}
