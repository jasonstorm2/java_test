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
		// 原始数二进制
		printBitInfo(number); // 1010
		printIntInfo(number);
		printLine();
		number = number << 1;
		// 左移一位
		printBitInfo(number); // 10100 2^1*(2^3 +2)
		printIntInfo(number);
		printLine();

		number = number >> 1;
		printBitInfo(number); //1010
		printIntInfo(number);
		printLine();
		
		number = number << 2;
		printBitInfo(number); //101000 2^2*(2^3 +2)
		printIntInfo(number);
		printLine();

	}

	/**
	 * 输出一个int的二进制数
	 * 
	 * @param num
	 */
	private static void printBitInfo(int num) {
		System.out.println(Integer.toBinaryString(num));
	}
	
	/**
	 * 输出一个int的二进制数
	 * 
	 * @param num
	 */
	private static void printIntInfo(int num) {
		System.out.println(num);
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
