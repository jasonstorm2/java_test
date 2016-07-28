package my;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrencyMapTest {
	public static ConcurrentHashMap<Integer, String> con = new ConcurrentHashMap<Integer, String>();
	
	
	public static void main(String[] args) {
		con.put(1, "a");
		System.out.println("key=1 的值"+con.get(1));
		String returnValue1 = con.putIfAbsent(1, "b");//如果缺少，则放入。。。
		String returnValue2 = con.putIfAbsent(2, "b");
		System.out.println("putInAbsent1的返回值"+returnValue1);
		System.out.println("putInAbsent2的返回值"+returnValue2);
		System.out.println("putInAbsent之后的值1"+con.get(1));
		System.out.println("putInAbsent之后的值2"+con.get(2));
		for(Integer a :con.keySet()){
			System.out.println(a);
		}
		for(String b : con.values()){
			System.out.println(b);
		}
		
	}

}
