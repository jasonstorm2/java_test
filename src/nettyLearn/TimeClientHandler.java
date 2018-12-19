package nettyLearn;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.Date;
import java.util.List;

//这样看起来非常简单，并且和服务端的那个例子的代码也相差不多。
//然而，处理器有时候会因为抛出IndexOutOfBoundsException而拒绝工作。
//在下个部分我们会讨论为什么会发生这种情况。

//ChannelHandlerAdapter是ChannelHandler的实现类
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
	/************简单的客户端-开始*************/
//    @Override
//	//这里我们覆盖了chanelRead()事件处理方法。 每当从客户端收到新的数据时， 这个方法会在收到消息时被调用，
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
////    	在TCP/IP中，NETTY会把读到的数据放到ByteBuf的数据结构中。
//        ByteBuf m = (ByteBuf) msg; // (1)
//        try {
//            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            ctx.close();
//        } finally {
//            m.release();
//        }
//    }
    /************简单的客户端-结束*************/
    
    
    
//	/************流数据的传输处理1--开始*************/
//	//一个接收方不管他是客户端还是服务端，都应该把接收到的数据整理成一个或者多个更有意思并且能够让程序的业务逻辑更好理解的数据
//	//一个32字节数据是非常小的数据量,可能会被拆分到不同的数据段内，并且拆分的可能性会随着通信量的增加而增加
//	//最简单的方案是构造一个内部的可积累的缓冲，直到4个字节全部接收到了内部缓冲。
//	//
//	private ByteBuf buf;
//
//	/**
//	 * 在将ChannelHandler添加到实际上下文并准备处理事件之后调用。
//	 * Gets called after the ChannelHandler was added to the actual context and it's ready to handle events.
//	 * 
//	 * 再开始处理数据之前，实例化一个4字节的ByteBuf
//	 */
//	@Override
//	public void handlerAdded(ChannelHandlerContext ctx) {
//
////		ChannelHandler有2个生命周期的监听方法：handlerAdded()和handlerRemoved()。
////		你可以完成任意初始化任务只要他不会被阻塞很长的时间
//		buf = ctx.alloc().buffer(4); // (1)
//		System.out.println("TimeClientHandler handlerAdd方法被调用");
//
//	}
//	
//	@Override
//	/**
//	 * 有消息传过来时，将消息放入4字节的ByteBuf中，直到这个ByteBuf可读的长度大于指定值
//	 */
//	public void channelRead(ChannelHandlerContext ctx, Object msg) {
//	System.out.println("TimeClientHandler channelRead方法被调用");
//		ByteBuf m = (ByteBuf) msg;
//		// 所有接收的数据都应该被累积在buf变量里
//		buf.writeBytes(m); // (2)
//		m.release();
//
//		// 处理器必须检查buf变量是否有足够的数据，在这个例子中是4个字节，然后处理实际的业务逻辑。
//		// 否则，Netty会重复调用channelRead()当有更多数据到达,直到4个字节的数据被积累
//		if (buf.readableBytes() >= 4) { // (3)
//
//			long currentTimeMillis = (buf.readInt() - 2208988800L) * 1000L;
//
//			System.out.println(new Date(currentTimeMillis));
//
//			ctx.close();
//		}
//	}
//
//	/**
//	 * 在通道处理程序被从实际上下文中删除后调用，它不再处理事件。
//	 * Gets called after the ChannelHandler was removed from the actual context and it doesn't handle events anymore.
//	 * 数据处理完毕后释放之前实例化的ByteBuf
//	 */
//	@Override
//	public void handlerRemoved(ChannelHandlerContext ctx) {
//
//		buf.release(); // (1)
//		buf = null;
//		System.out.println("TimeClientHandler handlerRemoved方法被调用");
//	}
	/************流数据的传输处理1--结束*************/
	
	
	/************用POJO代替ByteBuf--开始*************/
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("TimeClientHandler channelRead方法被调用");
		UnixTime m = (UnixTime)msg;
		System.out.println(m);
		ctx.close(); //关闭channel连接
	}

	/************用POJO代替ByteBuf--结束*************/
	
	
//	/************流数据的传输处理2,3--开始*************/
//    @Override
//	//这里我们覆盖了chanelRead()事件处理方法。 每当从客户端收到新的数据时， 这个方法会在收到消息时被调用，
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
////    	在TCP/IP中，NETTY会把读到的数据放到ByteBuf的数据结构中。
//        ByteBuf m = (ByteBuf) msg; // (1)
//        try {
//            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            ctx.close();
//        } finally {
//            m.release();
//        }
//    }
//    /************流数据的传输处理2,3--结束*************/


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	System.out.println("TimeClientHandler exceptionCaught方法被调用");
        cause.printStackTrace();
        ctx.close();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	// TODO Auto-generated method stub
    	ctx.write("lala");
    	ctx.flush();
    }
}

/************流数据的传输处理2--开始*************/
//尽管第一个解决方案已经解决了Time客户端的问题了，但是修改后的处理器看起来不那么的简洁，
//想象一下如果由多个字段比如可变长度的字段组成的更为复杂的协议时，你的ChannelHandler的实现将很快地变得难以维护。
//
//正如你所知的，你可以增加多个ChannelHandler到ChannelPipeline ,
//因此你可以把一整个ChannelHandler拆分成多个模块以减少应用的复杂程度，比如你可以把TimeClientHandler拆分成2个处理器：
//
//1.TimeDecoder处理数据拆分的问题
//2.TimeClientHandler原始版本的实现

//	ByteToMessageDecoder是ChannelHandler的一个实现类，他可以在处理数据拆分的问题上变得很简单。
class TimeDecoder extends ByteToMessageDecoder{
	@Override
//	每当有新数据接收的时候，ByteToMessageDecoder都会调用decode()方法来处理内部的那个累积缓冲。
	protected void decode(ChannelHandlerContext arg0, ByteBuf in,
			List<Object> out) throws Exception {
		System.out.println("TimeDecoder decode方法被调用");
//		Decode()方法可以决定当累积缓冲里没有足够数据时，不往out对象里放数据。
//		当有更多的数据被接收了ByteToMessageDecoder会再一次调用decode()方法。直到长度足够了	
		if(in.readableBytes() < 4){
			System.out.println("byte的长度："+in.readableBytes());
			return;
		}
//		如果在decode()方法里增加了一个对象到out对象里，这意味着解码器解码消息成功。
//		ByteToMessageDecoder将会丢弃在累积缓冲里已经被读过的数据。
//		请记得你不需要对多条消息调用decode()，ByteToMessageDecoder将继续调用decode()方法，直到没有向out添加任何内容。
		out.add(in.readBytes(4));		
	}
}
/************流数据的传输处理2--结束*************/

/************流数据的传输处理3--开始*************/
//比第二种方法更简洁
//此外，Netty提供了开箱即用的解码器，使您能够非常容易地实现大多数协议，并帮助您避免以不可维护的单一处理器实现告终。详情请参阅以下资料:
//io.netty.example.factorial for a binary protocol, and
//io.netty.example.telnet for a text line-based protocol.

class TimeDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(
            ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
    	System.out.println("TimeDecoder2 decode方法被调用");
        out.add(in.readBytes(4));
    }
}
/************流数据的传输处理3--结束*************/

/************用POJO代替ByteBuf--开始*************/
/**
 * 解码器，缓存足够的数据，按特定的数据类型解码
 * @author LiZhenhua
 *
 */
class POJODecoder extends ByteToMessageDecoder{
	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf in,
			List<Object> out) throws Exception {
		System.out.println("TimeDecoder3 decode方法被调用");
		System.out.println("byteBuf的长度："+in.readableBytes());
		if (in.readableBytes() < 4) {
			return;
		}
		out.add(new UnixTime(in.readUnsignedInt()));
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception { //如果连接未断开，服务器关掉，此处会调用。
		// TODO Auto-generated method stub
		System.out.println("连接出异常啦");
	}
}
/************用POJO代替ByteBuf--结束*************/

