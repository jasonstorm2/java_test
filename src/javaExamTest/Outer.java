package javaExamTest;
/**
 * 匿名内部类的实例化！！
 * @author LiZhenhua
 *
 */
public class Outer {
	public void someOuterMethod() {
		new Inner(); // A
	}

	public class Inner {
	}

	public static void main(String[] argv) {
		Outer o = new Outer();
//		new Inner(); // B 错误 需要一个外部类的引用
//		new o.Inner(); // C 错误 这样的实例化会报错
//		new Outer.Inner(); // D	错误
		o.new Inner(); // 正确
	}
}