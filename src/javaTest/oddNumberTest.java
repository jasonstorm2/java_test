package javaTest;

public class oddNumberTest {
	public static void main(String[] args) {
		System.out.println(oddNumberTest.oddOrNot(6));
		System.out.println(oddNumberTest.oddOrNot2(1));
		lll();
	}
	
	/**
	 * 求余
	 * @param num
	 * @return
	 */
	public static boolean oddOrNot(int num){
		System.out.println("求余"+num % 2);
		return num % 2 == 1;
	}
	
	/**
	 * 二进制 与
	 * & 0010
	 * @param num
	 * @return
	 */
	public static boolean oddOrNot2(int num){
		return (num & 2) != 0;
	}
	
	public static void lll(){
//		 int mid = (1 >>> 1) & ~1; // force midpoint to be even
		int mid = 1>>>1;
		System.out.println("1 >>> 1测试：无符号右移，空位以0补齐"+mid);
	}

}
