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


public class MyNettyClientHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("client�� channelRead����������");
		int m = (int)msg;
		System.out.println("�յ�����˷�����ֵ��"+m);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctx.writeAndFlush(20);
//		ctx.close(); //�ر�channel����
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	System.out.println("client ��channelActive����������");
    	// TODO Auto-generated method stub
    	ctx.write("100");
    	ctx.flush();
    }
}

/**
 * �������������㹻�����ݣ����ض����������ͽ���
 * @author LiZhenhua
 *
 */
class PlusDecoder extends ByteToMessageDecoder{
	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf in,
			List<Object> out) throws Exception {
		System.out.println("PlusDecoder ���뱻����");
		if (in.readableBytes() < 4) {
			return;
		}
		out.add((int)(in.readUnsignedInt()));
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception { //�������δ�Ͽ����������ص����˴�����á�
		// TODO Auto-generated method stub
		System.out.println("���ӳ��쳣��");
	}
}

class Plus2 extends ChannelOutboundHandlerAdapter  {
	  @Override
	  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
		  System.out.println("Plus2 ���뱻����");
	      int m = (int) msg;
	      ByteBuf encoded = ctx.alloc().buffer(4);
	      encoded.writeInt(m+100);
	      ctx.write(encoded, promise); // (1)
	  }
}

