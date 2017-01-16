package my;

/**
 * 
 * @author Administrator
 * 
 * java 的二进制表示 0b,比如 6的二进制表示：0b110
 * 
 * 把整数x转成二进制数，以字符串表示，再输出字符串:Integer.toBinaryString()
 * 
 * 按位运算用于基础类型，不能表示逻辑运算
 */
public class AndOrNotTest {
	
	public boolean isSame(int channelNum,int channel){
//		return channelNum==channel;
		return (channelNum & channel) == channelNum;
	}
	//与运算 ，同true才是true 即 1&1 == 1,1&0=0 0&0=0;
	public int methodAnd(int channelNum,int channel){
		return (channelNum & channel);
	}
	
	public int methodOr(int channelNum,int channel){
		return (channelNum | channel);
	}
	public static void main(String[] args) {
		AndOrNotTest ii = new AndOrNotTest();
		//1000000000000000100000000
		//1000000000000001100000000
		boolean bo=ii.isSame(0b1000000000000000100000000 ,0b1000000000000001100000000); // 110 10
		System.out.println("与运算结果："+bo);
		
		int and=ii.methodAnd(0b110,0b10);
		System.out.println("methodAnd:"+Integer.toBinaryString(and));
		
	}

}
