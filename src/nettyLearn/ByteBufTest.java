package nettyLearn;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 测试 ByteBuf的各种方法
 * @author Administrator
 *
 */
public class ByteBufTest extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		/**********下标是从0开始的***********/
			ByteBuf buffer = (ByteBuf) msg;
			for (int i = 0; i < buffer.capacity(); i ++) {
			byte b = buffer.getByte(i);
			System.out.println((char) b);
			}
		/**********顺序操作的两个下标 readerIndex ，writerIndex ***********/
			//两个下标将buf分为三段，已读（可放弃），可读，可写
			//注意，在调用discardReadBytes()之后，不能保证可写字节的内容。
			buffer.discardReadBytes(); //放弃已读的字节，回收空间
			
		/**********读操作 ***********/
			//判断buf是否可读
			while (buffer.isReadable()) {
			System.out.println(buffer.readByte());
			buffer.readBytes(buffer);
			}
		/**********写操作 ***********/
			while (buffer.writableBytes() >= 4) {
			buffer.writeInt(new Random().nextInt());
			}		
		/**********清理buffer的下标 ***********/
			//把read下标和write下标调整为0，而不用管理原来的内容
			buffer.clear();
		/**********清理buffer的下标 ***********/
			//搜索操作
			//indexOf()
			//ByteBufProcessor
			//bytesBefore(byte)
			
		/**********下标的标记，有两个，一个保存readerIndex ***********/
			buffer.resetReaderIndex();//把当前的readerIndex下标重置为所标记的下标位置 current readerIndex to   marked readerIndex 
			buffer.readerIndex(10);//设置标记的下标位置
			
	}

}
