package javaTest;

import java.text.NumberFormat;

import JavaCoreLearn.CalendarTest;


public class StaticFieldTest {
	public static int a = 1;
	private int b = 2;
	
	
	public void setB(){
		b=a;
		a+=5;
	}
	
	public int getStaticField(){
		return a;
	}
	public static void main(String[] args) {
		StaticFieldTest t1 = new StaticFieldTest();
		StaticFieldTest t2 = new StaticFieldTest();
		
		t1.setB();
		System.out.println("a value:"+StaticFieldTest.a);	
		System.out.println(t2.getStaticField());
		
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		NumberFormat percent = NumberFormat.getPercentInstance();
		
		double x = 0.2;
		System.out.println(currency.format(x));
		System.out.println(percent.format(x));
		
		CalendarTest ca = new CalendarTest();
		System.out.println(ca.test);
		
		
	}

}
