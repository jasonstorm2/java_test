package Chapter5;

/**
 * instanceof 与(type)类型转算以及 +,-等，都是运算符
 * 判断的是 运行时的类型
 * @author Administrator
 *
 */
public class InstanceofTest {
	public static void main(String[] args) {
		//instanceof运算符的作用是：在进行强制类型转换前，判断前一个对象 是否是 后一个对象 的实例。是否可以成功转换
		
		//声明hello时使用Object，则hello的编译类型是Object
		//Object是所有类的父类，但，hello的实际类型是String
		Object hello = "Hello";
		System.out.println("字符串是否是Object类的实例："+(hello instanceof Object));
		
		System.out.println("字符串是否是String类的实例："+(hello instanceof String));
		
		System.out.println("字符串是否是Math类的实例："+(hello instanceof Math));
		
		System.out.println("字符串是否是Comparable类的实例："+(hello instanceof Comparable));
		
		Object aob =  new A();
		
		Object bob =  new B();
		
		A  a =  new A();
		
		B b =  new B();
		
		
		//  instanceof 前后要有继承关系，不然会报错
		// 判断的是 运行时的类型
		System.out.println(aob instanceof A);//true
		System.out.println(aob instanceof B);//false		
		System.out.println(bob instanceof A);//true
		System.out.println(bob instanceof B);//true
		System.out.println("*******************************");
		System.out.println(a instanceof A);//true
		System.out.println(a instanceof B);//false			
		System.out.println(b instanceof A);//true
		System.out.println(b instanceof B);//true		
		System.out.println("____________________________________");
		System.out.println(aob instanceof C);
		System.out.println(bob instanceof C);
	

		
		
	}

}

class A{
	
	public void get(){
		
	}
	
}

class B extends A{
	
}

class C{
	
}


