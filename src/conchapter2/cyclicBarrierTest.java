package conchapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 类测试，CyclicBarrier和CountDown类有些类似，都是可以控制线程等待
 * CyclicBarrier(int parties, Runnable barrierAction) ： 等待parties个线程都调用await后，执行barrierAction，而等待的线程继续执行
 * 如果等待的线程不足parties个，会一直阻塞
 * CyclicBarrier.getNumberWaiting() 返回目前阻塞的在等待的线程个数
 * @author Administrator
 *
 */
public class cyclicBarrierTest {
	public static void main(String[] args) throws InterruptedException {
		int threadNums = 3;
		cyclicBarrierTest thisClass =  new cyclicBarrierTest();
		CyclicBarrier cyc =  new CyclicBarrier(threadNums, new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("所有的线程都就绪了");
			}
		});
		
		for (int i = 0; i <3; i++) {
			Thread1 thread =thisClass. new Thread1(threadNums,cyc);
			thread.start();
			Thread.sleep(2000);
		}
		
	}
	
	class Thread1 extends Thread{
		private CyclicBarrier cyc;
		private int threadNums;
		public Thread1(int threadNums,CyclicBarrier cyc){
			super();
			this.cyc = cyc;
			this.threadNums = threadNums;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				System.out.println(Thread.currentThread().getName() + "开始时间 = "+System.currentTimeMillis()+"等待凑齐两个继续运行");
				System.out.println("等待的线程有："+cyc.getNumberWaiting()+"个");			
				cyc.await();
//				System.out.println("等待的线程有："+cyc.getNumberWaiting()+"个");
				System.out.println(Thread.currentThread().getName() + "结束时间 = "+System.currentTimeMillis()+"已经凑齐两个继续运行");
				
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
