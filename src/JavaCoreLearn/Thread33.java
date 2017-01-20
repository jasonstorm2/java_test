package JavaCoreLearn;

/**
 * 实现Runnable接口的类 必须使用 Thread类的实例 才能创建线程。 Runnable定义的子类中没有start()方法
 * 
 * 通过Runnable接口创建线程分为两步：
 *  1. 将实现Runnable接口的类实例化。
 *  2. 建立一个Thread对象，并将第一步实例化后的对象作为参数传入Thread类的构造方法。
 * 
 * 启动线程，最终都需要Thread类的对象
 * 实际上 Thread 实现了 runnable接口
 * 
 * @author Administrator
 *
 */
class Thread33 implements Runnable { // 实现了Runnable接口，jdk就知道这个类是一个线程  

	int t;
	Thread a, b;

	Thread33() {
		t = 300;
		a = new Thread(this);//Thread(Runnable)
		a.setName("Accountant");
		b = new Thread(this);
		b.setName("Cashier");
	}

	//runnable 接口的抽象方法run(),必须 实现
	public void run() {
		System.out.println("this is run");
		while (true) {
			t = t - 50;
			if (Thread.currentThread() == a) {
				System.out.println(a.getName() + " need " + t + " Yuan.");
				if (t <= 150) {
					System.out.println(a.getName() + " is dead...");
					return;
				}
			} else if (Thread.currentThread() == b) {
				System.out.println(b.getName() + " need " + t + " Yuan.");
				if (t <= 0) {
					System.out.println(a.getName() + " is dead...");
					return;
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

			}
		}
	}
}
