package javaExamTest;
/**
 * �����ڲ����ʵ��������
 * @author Administrator
 *
 */
public class Outer {
	public void someOuterMethod() {
//		new Inner(); // A
	}

	public class Inner {
	}

	public static void main(String[] argv) {
//		Outer o = new Outer();
//		new Inner(); // B
//		new o.Inner(); // C
//		new Outer.Inner(); // D	
	}
}