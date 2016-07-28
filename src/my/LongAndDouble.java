package my;

public class LongAndDouble {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double d = 88.88;
		long l = Math.round(d);
		System.out.println(l);
		
		long ll = 100L;
		double dd = (double) ll;
		System.out.println(dd);
	}

}

