package javaExamTest;
/**
 * 继承后，对父类方法的重写
 * @author Administrator
 *
 */
class A {
	protected int method1(int a, int b) {
		return 0;
	}
}

public class extendTest extends A {
//	public int method1(int a, int b) {   //正确：权限可以大于父类方法的权限
//		return 0;
//	} // A
//
//	private int method1(int a, int b) {		//错误：权限可以小于父类方法的权限
//		return 0;
//	} // B
//
//	private int method1(int a, long b) {	//正确：输入参数不一样，已经是一个 新的方法了。
//		return 0;
//	} // C
//
//	public short method1(int a, int b) {	//错误：返回类型 不能和 父类的 返回类型不一致
//		return 0;
//	} // D
//
//	static protected int method1(int a, int b) {		//错误：父类不为静态，子类也不能为静态
//		return 0;
//	} // E
}