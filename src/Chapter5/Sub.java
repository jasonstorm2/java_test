package Chapter5;


/**
 * 不要在父类的构造方法中 调用被子类重写的方法
 * 
 * 子类实例化前，会先调用父类的无参数构造方法，若在此方法内，调用一个被子类重写的方法，那么就会执行这个被子类重写的方法，而不是父类中的方法
 * 所以，很容易出错
 * @author Administrator
 *
 */
public class Sub extends Base {
	private String name;

	public void test() {//1
		System.out.println("进入重写的 test方法");
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
		System.out.println("进入父类的test方法");
	}
}