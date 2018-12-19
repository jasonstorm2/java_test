package Chapter5;

/**
 * 此类 说明了 子类实例化后的加载顺序:
 * 		先调用父类的无参数构造器
 * 
 * 父类的构造器中调用一个被重写的方法，那么在 子类的，重写的方法将会被调用。
 * 
 * 变量的定义和初始化 早于构造器的调用？？
 * 
 * 方法早于对象存在？？
 * @author LiZhenhua
 *
 */
public class FatherAndSon {
	public static void main(String[] args) {
		new B11(10);
	}
}

abstract class C11 {
	public C11() {
		this.print();// 调用重写的子类的方法
		System.out.println("c的构造器");
	}

	public abstract void print();
}

class B11 extends C11 {
	private int x = 100;

	public B11(int x) {
		this.x = x;
		System.out.println("b的构造器");
		System.out.println("x的值："+x);
	}

	public void print() {
		System.out.println("x=" + x);
		System.out.println("重写的函数print");
	}
}
