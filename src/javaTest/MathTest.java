package javaTest;

public class MathTest {
	public static void main(String[] args) {
//		long time1 = System.currentTimeMillis();
//		long time2 = System.currentTimeMillis()+1000;
//		float sec = 2.0f;
//		boolean isMax = false;
//		if(time2 > time1 + sec * 1000L) {
//			isMax = true;
//		}
//		System.out.println("sjhtest£º" + isMax);
		
		
//		long time1 = System.currentTimeMillis();
//		long time2 = System.currentTimeMillis()+100000;
//		float sec = 2.0f;
//		boolean isMax = false;
//		if(time2 > time1 + sec * 1000L) {
//			isMax = true;
//		}
//		System.out.println("sjhtest£º" + isMax);
		
		long time1 = System.currentTimeMillis();
		long time2 = System.currentTimeMillis()+10000;
		float sec = 2.0f;
		boolean isMax = false;
		if(time2 > (time1 + sec * 1000L)) {
			isMax = true;
		}
		System.out.println(time2);
		
		System.out.println(time1 + 2 * 1000L);
		System.out.println(time2-(time1 + 2 * 1000L));
		
		System.out.println("sjhtest£º" + isMax);
		
	}

}
