package JavaCoreLearn;

public class ThreadGroupTest {
	public static void main(String[] args) {
		//获取主线程的 线程组
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();//有一个 默认的线程组
		System.out.println("主线程组 的名字："+mainGroup.getName());
		System.out.println("主线程组 是否是后台线程组："+mainGroup.isDaemon());
		new MyThread("Dont you cry tonight").start();
		
		ThreadGroup tg = new ThreadGroup("新线程组");
		//设置为后台线程
		tg.setDaemon(true);
		
		System.out.println("tg线程组是否是后台线程组："+tg.isDaemon());
		
		MyThread tt = new MyThread(tg, "tg组的线程甲");
		tt.start();
		
		new MyThread(tg, "tg组的线程乙").start();
		
		
	}
	
}

class MyThread extends Thread{
	public MyThread(String name){
		super(name);
	}
	
	public MyThread(ThreadGroup group,String name){//线程构造函数 指定本线程所属的 线程组 和 线程名称
		super(group, name);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 20; i++) {
			System.out.println(getName() + "线程的i变量"+i);
		}
	}
	
}
