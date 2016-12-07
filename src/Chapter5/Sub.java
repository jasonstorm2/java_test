package Chapter5;


/**
 * 不要在父类的构造方法中调用，被子类重写 的方法
 * @author Administrator
 *
 */
public class Sub extends Base {
	private String name;

	public void test() {//1
		System.out.println("子类重写父类的方法，其name字符串长度：" + name.length());
		Chapter15.FileTest ft = new Chapter15.FileTest();
	}

	public static void main(String[] args) {
		Sub s = new Sub();//会默认的调用父类的默认构造方法，而父类的构造其中调用了，被子类重写的方法，那么就调用这个被重写的方法。所以出错了
	}
}

class Base {
	public Base() {
		test();
	}

	public void test() {//2
		System.out.println("将被子类重写的方法");
	}
}