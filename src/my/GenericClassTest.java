package my;

import java.util.ArrayList;
import java.util.List;


public class GenericClassTest {
	
	public static void getDate(myGeneric<?> s){
		System.out.println(s.get());
	}
	
	//泛型下限设置，？必须是String 及其子类
	public static void getLimitDate(myGeneric<? extends String> ss){
		System.out.println("泛型设置限制哦："+ss.get());
		
	}
	
	public static void main(String[] args) {
		List<String> listString = new ArrayList<String>();
		
		
		
//		List<String>[] lsa = new List<String>[10]; // Not really allowed.
//		Object o = lsa;
//		Object[] oa = (Object[]) o;
//		List<Integer> li = new ArrayList<Integer>();
//		li.add(new Integer(3));
//		oa[1] = li; // Unsound, but passes run time store check
//		String s = lsa[1].get(0); // Run-time error: ClassCastException.
		
		List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
		Object o = lsa;
		Object[] oa = (Object[]) o;
		List<Integer> li = new ArrayList<Integer>();
		li.add(new Integer(3));
		oa[1] = li; // Correct.
		String s = (String) lsa[1].get(0); // Run time error, but cast is explicit.  java.lang.Integer cannot be cast to java.lang.String
		System.out.println("泛型数组测试:s:"+lsa[1].get(0));


		
		
		myGeneric<String> ss = new myGeneric<String>("what");
		myGeneric<String> ss2 = new myGeneric<String>("what the fuck");
		myGeneric<Integer> integer = new myGeneric<Integer>(123);		
		
		System.out.println("ss:"+ss.get());
		System.out.println("ss2:"+ss2.get());
		System.out.println("integer:"+integer.get());
	
		getDate(ss);
		getDate(ss2);
		getDate(integer);
		getLimitDate(ss2);
		
	}

}

// 自定义的泛型类
class myGeneric<K> {

	private K date;

	public myGeneric(K date){
		set(date);
	}
	public K get() {
		return date;
	}

	public void set(K date) {
		this.date = date;
	}
}