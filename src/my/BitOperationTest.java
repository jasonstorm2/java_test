package my;

public class BitOperationTest {
	/***
	 * & 两个操作数中位都为1，结果才为1，否则结果为0，
	 * | 两个操作数中位，只要有一个为1，结果为1，否则结果为0，
	 * ~ 如果位为0，结果是1，如果位为1，结果是0，
	 * << : 左移运算符，num << 1, 左移，相当于num乘以2
	 * 
	 * >> : 右移运算符，num >> 1, 右移，相当于num除以2
	 * 
	 * >>> : 无符号右移，忽略符号位，空位都以0补齐
	 * 
	 * @param args
	 */
	public static void main(String[] args) {	
		
		int number = 10;
		System.out.println("原始十进制数值："+number);
		// 原始数二进制
		intToStrBin(number); // 1010
		printIntInfo(number);
		printLine();
		number = number << 1;
		System.out.println("左移一位<<");
		// 左移一位
		intToStrBin(number); // 10100 2^1*(2^3 +2)
		printIntInfo(number);
		printLine();
		System.out.println("右移一位>>");
		
		number = number >> 1;
		intToStrBin(number); //1010
		printIntInfo(number);
		printLine();
		System.out.println("左移二位<<");		
		number = number << 2;
		intToStrBin(number); //101000 2^2*(2^3 +2)
		printIntInfo(number);
		printLine();
		
		printLine();
		StrBinCharTest("111");
		printLine();
		strBinaToInt("911");
		
		
		int a = strBinaToInt("001");
		int b = strBinaToInt("010");
		
		int ad = AND(a,b);
		int or = OR(a,b);
		
		intToStrBin(or);
		intToStrBin(or<<1);
		
		checkStrLength("1");
		checkStrLength("11");
		checkStrLength("111");
		checkStrLength("1111");
		
	}
	
	/**
	 * str转换成char,char转船成String等测试
	 * @param str
	 * @return
	 */
	private static String StrBinCharTest(String str) {
		char[] strChar = str.toCharArray();
		String result = "";
		for (int i = 0; i < strChar.length; i++) {
			int a =Integer.valueOf(strChar[i]);			
			System.out.println("char转换成int："+a);
			String ss = String.valueOf(strChar[i]);
			System.out.println("char转换成String："+ss);
			int b = Integer.valueOf(ss);
			System.out.println("char转换成String,再转换成int："+b);
			
			
			result += Integer.toBinaryString(b) + " ";
		}
		System.out.println("字符串转换成二进制："+result);
		return result;
	}

	/**
	 * 把 十进制数，转换成String格式的二进制
	 * 如：10  ---> 1010
	 * 
	 * @param num
	 */
	private static void intToStrBin(int num) {
		System.out.println("把 十进制数，转换成String格式的二进制:"+Integer.toBinaryString(num));
	}
	
	/**
	 * 转换成二进制String后，不足三位的，用0补足
	 * 如： 1==>001 11==>011
	 * @param str
	 * @return
	 */
	private static String checkStrLength(String str) {
		String result = str;
		int len = str.length();
		if(len<3){
			for(int i=0;i<(3-len);i++){
				result="0"+result;
			}
		}
		return result;
	}
	
	/**
	 * 把 String格式的二进制，直接转换成十进制
	 * @param str
	 * @return
	 */
	private static int strBinaToInt(String str){
		int res = 0;
		try {
			res = Integer.parseInt(str,2);
			System.out.println("把 String格式的二进制，直接转换成十进制:"+res);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("非二进制格式");
		}
		return res;
	}
	
	/**
	 * 十进制与运算 返回的是十进制格式
	 * @param a
	 * @param b
	 * @return
	 */
	private static int AND (int a,int b){
		System.out.println(a+"&"+b+":"+(a&b));
		return a&b;
	}
	
	/**
	 * 十进制或运算 返回的是十进制格式
	 * @param a
	 * @param b
	 * @return
	 */
	private static int OR(int a,int b){
		System.out.println(a+"|"+b+":"+(a|b));
		return a|b;
	}
	
	/**
	 * 输出一个int的十进制数
	 * 
	 * @param num
	 */
	private static void printIntInfo(int num) {
		System.out.println("十进制："+num);
	}
	
	/**
	 * 输出一个分割线
	 * 
	 * @param num
	 */
	private static void printLine() {
		System.out.println("----------------------");
	}	
}
