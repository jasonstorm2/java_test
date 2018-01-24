package conchapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 类测试，CyclicBarrier和CountDown类有些类似，都是可以控制线程等待
 * 
 * 
 * 
 * 实例化一个CyclicBarrier(int parties, Runnable barrierAction)对象，多个线程公用
 * 线程调用CyclicBarrier.await()进入等待状态，
 * 直到parties个线程调用CyclicBarrier.await()方法,所有线程不再等待继续执行，而且barrierAction线程也开始执行
 * 如果等待的线程不足parties个，会一直阻塞
 * 
 * CyclicBarrier(int parties, Runnable barrierAction) ： 	等待parties个线程都调用await()后，执行barrierAction，而等待的线程继续执行
 * CyclicBarrier(int parties) : 							等待parties个线程都调用await()后,所有等待的线程继续执行。
 * CyclicBarrier.await() :									线程进入等待状态 
 * CyclicBarrier.getNumberWaiting()： 						返回目前阻塞的在等待的线程个数
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class cyclicBarrierTest {
	
	public static final int THREADNUMS = 3;
	
	public static void main(String[] args) throws InterruptedException {
		cyclicBarrierTest thisClass =  new cyclicBarrierTest();
		CyclicBarrier cyc =  new CyclicBarrier(THREADNUMS, new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("所有的线程都就绪了");
			}
		});
		
		CyclicBarrier cyc2 = new CyclicBarrier(THREADNUMS);
		
		for (int i = 0; i <6; i++) {
			Thread1 thread =thisClass. new Thread1(i,cyc);
			thread.start();
			Thread.sleep(2000);
		}
		
	}
	
	class Thread1 extends Thread{
		private CyclicBarrier cyc;
		public Thread1(int id,CyclicBarrier cyc){
			super();
			this.cyc = cyc;
			this.setName("线程"+id);
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				System.out.println(Thread.currentThread().getName() + ":开始时间 = "+System.currentTimeMillis()+"等待凑齐两个继续运行");
				System.out.println("等待的线程有："+cyc.getNumberWaiting()+"个");			
				cyc.await();
				System.out.println("结束等待了，等待的线程有："+cyc.getNumberWaiting()+"个");
				System.out.println(Thread.currentThread().getName() + ":结束时间 = "+System.currentTimeMillis()+"已经凑齐两个继续运行");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
