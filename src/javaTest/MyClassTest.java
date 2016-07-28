package javaTest;

import java.util.ArrayList;
import java.util.List;

public class MyClassTest {
	private String name;
	private static final List<String> names = new ArrayList<String>(){{add("john");}};
	
	public static void main(String args[]){
		MyClassTest m1 = new MyClassTest();
		MyClassTest m2 = new MyClassTest();
		m1.name = m2.name ="m1";
		callMe(m1,m2);
		System.out.println(m1+"&"+m2);		
	}
	
	
	private static void callMe(MyClassTest...m){
		m[0]=m[1];
		m[1].name = "new name";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
