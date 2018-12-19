package conchapter3;

import java.util.concurrent.Phaser;


/**
 * Phaser.awaitAdvance(int phase)： 如果传入的阶段是当前所处的阶段，则在屏障处等待，否则，继续运行
 * 类似旁观者的作用
 * 当调用awaitAdvance  phaser正在等待时，所有处理phaser的线程不能中断
 * 
 * Phaser.awaitAdvanceInterruptibly(int phase)：和awaitAdvance()类似，只是等待时，phaser可以被中断，中断则报线程中断异常
 * 
 * 
 * @author LiZhenhua
 *
 */
public class phaserAwaitAdvanceTest {
	public static void main(String[] args){
		phaserAwaitAdvanceTest thisClass = new phaserAwaitAdvanceTest();
		Phaser phaser = new Phaser(3);
		Thread1 th1 = thisClass.new Thread1(phaser);		
		Thread2 th2 = thisClass.new Thread2(phaser);
		Thread3 th3 = thisClass.new Thread3(phaser);
		Thread4 th4= thisClass.new Thread4(phaser);
		
		th1.setName("Athread");
		th2.setName("Bthread");
		th3.setName("Cthread");
		th4.setName("Dthread");
		
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		
		th1.interrupt();
		th2.interrupt();
		th3.interrupt();
		
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
			phaser.arriveAndAwaitAdvance();
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
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName() + "A1 end="+System.currentTimeMillis());			
		}	
	}
	
	class Thread3 extends Thread{
		private Phaser phaser;
		public Thread3(Phaser phaser){
			super();
			this.phaser = phaser;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				System.out.println(Thread.currentThread().getName() + "A1 bgn="+System.currentTimeMillis());				
				phaser.awaitAdvance(0);//跨栏的栏数 phase是当前的phase，则等待
//				phaser.awaitAdvance(1);//跨栏的栏数 phase不是当前的phase，程序继续运行
				System.out.println(Thread.currentThread().getName() + "A1 end="+System.currentTimeMillis());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}
	
	class Thread4 extends Thread{
		private Phaser phaser;
		public Thread4(Phaser phaser){
			super();
			this.phaser = phaser;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				System.out.println(Thread.currentThread().getName() + "A1 bgn="+System.currentTimeMillis());
				Thread.sleep(3000);
				phaser.arriveAndAwaitAdvance();
				System.out.println(Thread.currentThread().getName() + "A1 end="+System.currentTimeMillis());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
	}
}
