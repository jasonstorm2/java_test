package JavaCoreLearn;

/**
 * 
 * 在Java当中，线程通常都有五种状态，创建、就绪、运行、阻塞和死亡。
 * 
 * 第一是创建状态。 在生成线程对象，并没有调用该对象的start方法，这是线程处于创建状态。
 * 
 * 第二是就绪状态。 当调用了线程对象的start方法之后，该线程就进入了就绪状态， 但是此时线程调度程序还没有把该线程设置为当前线程，此时处于就绪状态。
 * 在线程运行之后，从等待或者睡眠中回来之后，也会处于就绪状态。
 * 
 * 第三是运行状态。 线程调度程序将处于就绪状态的线程设置为当前线程，此时线程就进入了运行状态，开始运行run函数当中的代码。
 * 
 * 第四是阻塞状态。 线程正在运行的时候，被暂停，通常是为了等待某个时间的发生(比如说某项资源就绪)之后再继续运行。sleep,suspend，
 * wait等方法都可以导致线程阻塞。
 * 
 * 第五是死亡状态。 如果一个线程的run方法执行结束或者调用stop方法后，该线程就会死亡。对于已经死亡的线程，无法再使用start方法令其进入就绪。
 * 
 * start() 和 run()的区别
 * 
 * 1.start（）方法来启动线程，真正实现了多线程运行。
 * 通过调用Thread类的start()方法来启动一个线程， 这时此线程是处于就绪状态， 并没有运行。
 * 然后通过此Thread类调用方法run()来完成其运行操作的， 这里方法run()称为线程体，它包含了要执行的这个线程的内容， Run方法运行结束，
 * 此线程终止。然后CPU再调度其它线程
 * 。
 * 2.run（）方法当作普通方法的方式调用。
 * 程序还是要顺序执行，要等待run方法体执行完毕后，才可继续执行下面的代码； 
 * 程序还是在当前线程中运行（可能是main线程），即没有开启新的线程——这一个线程，
 * 其程序执行路径还是只有一条， 这样就没有达到写线程的目的。 
 * 
 * 记住：多线程就是分时利用CPU，宏观上让所有线程一起执行 ，也叫并发
 * 
 * @author LiZhenhua
 *
 */
public class ThreadSecond implements Runnable{
	private int i;

	@Override
	//同步 位置不一样，效果会不一样哦。。。
//	public synchronized void run() {
	public void run() {
		for (;i < 100; i++) {
			synchronized(this){  //同步代码块，防止重复变量出现（卖票重复卖票的现象）
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("线程名字："+Thread.currentThread().getName()+" "+i);

			}
		}
	}
	
	
	
	public static void main(String[] args) {
		
		//runnable接口可以达到共享效果。。这就是 Runnable he Thread 的区别？				
		ThreadSecond st = new ThreadSecond();
		new Thread(st, "新线程1 ").start();//target类st 两个线程共享  ----并发时，安全问题！！
		new Thread(st, "新线程2 ").start();//target类st 两个线程共享
		//如果是实例化两个不同的Thread，那么不能达到共享的效果
		
		Thread tt = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("当前线程的名字："+Thread.currentThread().getName());
				// TODO Auto-generated method stub
				System.out.println("执行run与执行start的区别");
			}
		}, "新线程3 ");//target类st 两个线程共享
		
		tt.run();   //线程还是主线程，并没有开启新线程
		tt.start(); //开启了新线程
	}

}
