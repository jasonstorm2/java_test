package my;

public class LongAndDouble {

	/**
	 * @param args
	 * double 和 long 的相互转化
	 * 
	 * long 强制转化为int 
	 */
	public static void main(String[] args) {
		double d = 88.88;
		long l = Math.round(d);
		System.out.println(l);
		
		long ll = 100L;
		double dd = (double) ll;
		System.out.println(dd);
		
		//测试强制转换溢出
		long lll = 123456789123467894l;
		int inte = (int)lll;
		System.out.println("long强制转换为int:"+inte);
	}

}

