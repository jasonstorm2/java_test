package my;

/**
 * 
 * @author LiZhenhua
 * 
 * java 的二进制表示 0b,比如 6的二进制表示：0b110
 * 
 * 把整数x转成二进制数，以字符串表示，再输出字符串:Integer.toBinaryString()
 * 
 * 按位运算用于基础类型，不能表示逻辑运算
 */
public class AndOrNotTest {
	
	/**
	 * a & b后，值是否等于a
	 * 通常用于类型的判断
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isSame(int a,int b){
		return (a & b) == a;
	}
	
	
	/**
	 * 与运算 ，同true才是true 
	 * 
	 * 即 1&1 == 1,1&0=0 0&0=0;
	 * @param channelNum
	 * @param channel
	 * @return
	 */
	public static int methodAnd(int channelNum,int channel){
		return (channelNum & channel);
	}
	
	/**
	 * 或运算,两个数都转为二进制，然后从高位开始比较，两个数只要有一个为1则为1，否则就为0。
	 * 
	 * 即：1|1 = 1,  1|0 = 1, 0|1 = 1
	 * @param channelNum
	 * @param channel
	 * @return
	 */
	public static int methodOr(int channelNum,int channel){
		return (channelNum | channel);
	}
	
	/**
	 * a|=b :a和b按位或然后赋值给a
	 * @param a
	 * @param b
	 * @return
	 */
	public static int methdoAndEqual(int a,int b){
		int c = a|=b;
		return c;
	}
	
	/**
	 * 把 int 数据 转换成 二进制的 string格式 输出
	 * @param value
	 * @return
	 */
	public static String toBinaryString(int value){
		return Integer.toBinaryString(value);
	}	

	
	public static void main(String[] args) {
		//1000000000000000100000000
		//1000000000000001100000000
		utils.utils.PrintLine("1000000000000000100000000,1000000000000001100000000 与运算后 是否 与1000000000000000100000000,1000000000000001100000000 相等");
		System.out.println("与运算结果："+isSame(0b1000000000000000100000000 ,0b1000000000000001100000000));
		
		utils.utils.PrintLine("110, 010 测试与预算&");
		System.out.println("十进制："+methodAnd(0b110,0b10));
		System.out.println("二进制："+toBinaryString(methodAnd(0b110,0b10)));
		
		utils.utils.PrintLine("11, 10 测试或预算|");	
		System.out.println("十进制："+methodOr(0b11, 0b10));
		System.out.println("二进制："+toBinaryString(methodOr(0b11, 0b10)));
		
		utils.utils.PrintLine("|= 符号测测:0b1111 |= 0b1010");	
		System.out.println("十进制："+methdoAndEqual(0b1111, 0b1010));
		System.out.println("二进制："+toBinaryString(methdoAndEqual(0b1111, 0b1010)));
		
		System.out.println("十进制："+methodAnd(0,4));
		System.out.println("二进制："+toBinaryString(methodAnd(0b1,0b10)));
		
		System.out.println(1<<0);//1
		System.out.println(1<<1);//2  10
		System.out.println(1<<2);//4  100
		System.out.println(1<<3);
		System.out.println(1<<4);
		System.out.println(1<<5);
		System.out.println(1<<6);
		System.out.println(1<<7);
		System.out.println(1<<8);
		System.out.println(1<<9);		
		System.out.println(1<<10);
		
		
	}

}
