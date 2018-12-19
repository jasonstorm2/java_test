package conchapter3;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * 
 *  Phaser.awaitAdvanceInterruptibly(int,long,TimeUnit): 在指定栏位，等待x时间，栏位未变则出现异常，否则继续运行
 *  若指定栏位非当前栏位，超出当前栏位，或小于当前栏位，该方法不会报出异常，线程直接往下运行
 *  awaitAdvanceInterruptibly 若在中途等待的过程中线程被中断，将会抛出线程中断异常
 * 
 * @author LiZhenhua
 *
 */
public class phaserAwaitAdvanceInterruptiblyTest {
	public static void main(String[] args){
		phaserAwaitAdvanceInterruptiblyTest thisClass = new phaserAwaitAdvanceInterruptiblyTest();
		Phaser phaser = new Phaser(1);
		Thread1 th1 = thisClass.new Thread1(phaser);		
		Thread2 th2 = thisClass.new Thread2(phaser);
		
		th1.setName("Athread");
		th2.setName("Bthread");		
		th1.start();
		th2.start();	
		
		/* 测试awaitAdvanceInterruptibly的中断异常 */
//		try {
//			Thread.sleep(1000);
//			th2.interrupt();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			System.out.println("中途打断线程，抛出线程中断异常："+System.currentTimeMillis());
//			e.printStackTrace();
//		}
	}	

	class Thread1 extends Thread{
		private Phaser phaser;
		public Thread1(Phaser phaser){
			super();
			this.phaser = phaser;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println(Thread.currentThread().getName() + "A1 bgn="+System.currentTimeMillis());
			System.out.println(Thread.currentThread().getName() + "A1 到达的栏位"+phaser.getPhase());
			phaser.arrive(); 
			phaser.arrive(); 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			phaser.arrive(); 
//			phaser.arrive();
			System.out.println(Thread.currentThread().getName() + "A1 到达的栏位"+phaser.getPhase());
			System.out.println(Thread.currentThread().getName() + "A1 end="+System.currentTimeMillis());			
		}
		
	}
	
	class Thread2 extends Thread{
		private Phaser phaser;
		public Thread2(Phaser phaser){
			super();
			this.phaser = phaser;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println(Thread.currentThread().getName() + "A1 bgn="+System.currentTimeMillis());
			try {
				Thread.sleep(3000);
				phaser.awaitAdvanceInterruptibly(1, 2, TimeUnit.SECONDS);
//				phaser.awaitAdvanceInterruptibly(phaser.getPhase(), 2, TimeUnit.SECONDS); //getPhase取当前的阶段
			} catch (InterruptedException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "A1 end="+System.currentTimeMillis());			
		}	
	}
}
