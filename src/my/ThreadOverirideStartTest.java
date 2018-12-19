package my;

/**
 * 重写线程的 star方法
 * 调用这个start方法时，是一个普通方法。
 * 除非 super.start 调用父类的start方法，因为此时会开启一个新的线程，并调用线程类的run方法。
 * 如果在这个重写的方法调用run方法，也相当于调用一个普通方法。
 * @author LiZhenhua
 *
 */
public class ThreadOverirideStartTest {
	public static void main(String[] args) {
		System.out.println("ThreadOverirideStartTest main Thread.currentThread(),id 和 名字："+Thread.currentThread().getId() + "  " + Thread.currentThread().getName());
		new myThread().start();
		new myThread2().start();
		new myThread4().start();
//		new myThread4().run();		//普通方法，仍然在main线程中
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("匿名内部类 id:"+Thread.currentThread().getId()+" name:"+Thread.currentThread().getName());
				
			}
		}).start();
	}
}

/**
 * 只重写run方法
 * @author LiZhenhua
 *
 */
class myThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		System.out.println("myThread run() this.getId()，id 和 名字："+this.getId() + "  " + this.getName());
		System.out.println("myThread run() Thread.currentThread()id："+Thread.currentThread().getId());
	}

}


/**
 * 重写了start方法，并没有调用 父类的start方法
 * 所以没有开启一个新的线程。都是普通的线程。
 * @author LiZhenhua
 *
 */
class myThread2 extends Thread{
	public void run() {		
		System.out.println("myThread2 run 方法内 this.getid ，id 和 名字："+this.getId() + "  " + this.getName());
		System.out.println("myThread2 run 方法内 Thread.currentThread()，id："+Thread.currentThread().getId());
	}
	
	public  void start() {
		System.out.println("myThread2 start() this.getId()，id 和 名字："+this.getId() + "  " + this.getName());
		System.out.println("myThread2 start() Thread.currentThread()，id："+Thread.currentThread().getId());
		run();
	}
}



/**
 * 重写的start方法中 调用了super.start()
 * super.start 将会开启一个新的线程，并且调用 线程的run方法
 * 
 * 如果在重写的start 方法中 调用run方法，则是一个普通方法，仍然在main线程中
 * 
 * @author LiZhenhua
 *
 */
class myThread4 extends Thread{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("myThread4 run 方法内 this.getid ，id 和 名字："+this.getId() + "  " + this.getName());
		System.out.println("myThread4 run 方法内 Thread.currentThread()，id："+Thread.currentThread().getId());
		
	}
	
	public  void start() {
		super.start();
		System.out.println("myThread4 start() this.getId()，id 和 名字："+this.getId() + "  " + this.getName());
		System.out.println("myThread4 start() Thread.currentThread()，id："+Thread.currentThread().getId());
		run();
	}
}

