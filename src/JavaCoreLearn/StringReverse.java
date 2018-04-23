package JavaCoreLearn;

/**
 * 介绍五个反转字符串的方法
 * @author Administrator
 *
 */
public class StringReverse {
	
	public static void main(String[] args) {
		System.out.println(reverse1("long"));		
		System.out.println(reverse2("long"));	
		System.out.println(reverse3("long"));	
		System.out.println(reverse4("long"));	
		System.out.println(reverse5("long"));	
		System.out.println(reverse6("long"));	
		
		
	}
	
	/**
	 * 最简单的方法1
	 * 调用StringBuffer的反转方法 reverse
	 * @param str
	 * @return
	 */
	public static String reverse1(String str){
		return new StringBuffer(str).reverse().toString();
	}
	/**
	 * 最简单的方法2
	 * 调用StringBuilder的反转方法 reverse
	 * @param str
	 * @return
	 */
	public static String reverse2(String str){
		return new StringBuilder(str).reverse().toString();
	}
	
	/**
	 * 把String字符串转换成char[]数组，从下标最大到最小赋值到新的String上
	 * @param s
	 * @return
	 */
	public static String reverse4(String s)
	 { 
	   char[] array = s.toCharArray(); //将string类型参数转化为char[]类型参数
	   String reverse = "";  //注意这是空串，不是null
	   for (int i = array.length - 1; i >= 0; i--) 
	   reverse += array[i]; 
	   return reverse; 
	  }

	/**
	 * C语言中常用的方法：
	 * @param orig
	 * @return
	 */
	 public static String reverse5(String orig)
	 { 
	   char[] s = orig.toCharArray(); 
	   int n = s.length - 1; 
	   int halfLength = n / 2; 
	   for (int i = 0; i <= halfLength; i++) { 
	     char temp = s[i]; 
	     s[i] = s[n - i]; 
	     s[n - i] = temp; 
	   } 
	   return new String(s);  //知道  char数组和String相互转化
	 } 
	 
	 /**
	  * 采用递归方法
	  * @param orig
	  * @return
	  */
	 public static String reverse6(String orig)
	 { 
		 if(orig == null || orig.length() <=1){
			 return orig;
		 }
		 int i = Integer.parseInt(orig);
		 Integer it = Integer.valueOf(orig);
		 String dd = String.valueOf(1);
		 return reverse6(orig.substring(1)) + orig.charAt(0);				 
	 } 
	 
	/**
	 * String字符串调用方法charAt(i)，反方向组成新的String
	 * 
	 * @param s
	 * @return
	 */
	public static String reverse3(String s) {
		int length = s.length();
		String reverse = ""; // 注意这是空串，不是null
		for (int i = 0; i < length; i++) {
			reverse = s.charAt(i) + reverse;// 在字符串前面连接， 而非常见的后面
		}

		return reverse;
	}
}
