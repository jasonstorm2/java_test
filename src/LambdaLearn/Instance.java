package LambdaLearn;
/**
 * 实例化的顺序测试：
 * 实例化一个对象，再调用该对象的方法，该方法又有一个实例化同类对象。顺序是：调用方法中的实例化再后面。
 * @author Administrator
 *
 */
public class Instance {
	public static void main(String[] args) {
		Ins ins = new Ins("在main里面实例化");
		ins.run();		
		System.out.println("-------------------------");
		ins.work();
	}
}

class Ins {
	
	int age;
	int sex;
	static int eye;
	public Ins(String s) {
		System.out.println(s);
	}

	public void run() {
		Ins ins = new Ins("在run里面实例化");
		ins.jump();
		System.out.println("run方法");
	}

	public void jump() {
		System.out.println("jump方法");
	}
	public void work() {
		this.jump();
		System.out.println("work方法");
	}
	public void work2() {
		jump();   //省略了this，实际上this只是隐藏了，它还在继续发挥影响
		System.out.println("work2方法");
	}
	
	public static void staticMethod() {
//		int ages = age;  static 成员 不能访问 非static成员
		int e  = eye;  //static 方法，只能访问 static 变量
		System.out.println("staticMethod方法");
	}
}
