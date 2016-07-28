package my;


public class AssignableTest {
	
	public AssignableTest(String name) {
	}
	/**
	 * 判断一个类是否是另一个类的父类
	 * 是打印true
	 * 否打印false
	 */
	public static void testIsAssignedFrom1() {
		System.out.println("String是Object的父类:"+String.class.isAssignableFrom(Object.class));
	}
	/**
	 * 判断一个类是否是另一个类的父类
	 * 是打印true
	 * 否打印false
	 */
	public static void testIsAssignedFrom2() {
		System.out.println("Object是String的父类:"+Object.class.isAssignableFrom(String.class));
	}
	/**
	 * 判断一个类是否和另一个类相同
	 * 是打印true
	 * 否打印false
	 */
	public static void testIsAssignedFrom3() {
		System.out.println("Object和Object相同:"+Object.class.isAssignableFrom(Object.class));
	}

	/**
	 * 判断str是否是Object类的实例
	 * 是打印true
	 * 否打印false
	 */
	public static void testInstanceOf1() {
		String str = new String();
		System.out.print("str是Object的实例:");
		System.out.println(str instanceof Object);
	}
	/**
	 * 判断o是否是Object类的实例
	 * 是打印true
	 * 否打印false
	 */
	public static void testInstanceOf2() {
		Object o = new Object();
		System.out.print("o是Object的实例:");
		System.out.println(o instanceof Object);
	}
	
	public static void main(String[] args) {
		testIsAssignedFrom1();
		testIsAssignedFrom2();
		testIsAssignedFrom3();
		testInstanceOf1();
		testInstanceOf2();
	}
}

