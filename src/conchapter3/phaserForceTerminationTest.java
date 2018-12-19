package conchapter3;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * 
 *  Phaser.forceTermination():使phaser对象的屏蔽功能失效，失效后，线程继续往下运行
 *  与CyclicBarrier的 reset()方法相比，reset执行时出现异常
 *  Phaser.isTerminated()：判断是否被强制失效
 * @author LiZhenhua
 *
 */
public class phaserForceTerminationTest {
	public static void main(String[] args) throws Exception{
		phaserForceTerminationTest thisClass = new phaserForceTerminationTest();
		Phaser phaser = new Phaser(3);
		Thread1 th1 = thisClass.new Thread1(phaser);		
		Thread2 th2 = thisClass.new Thread2(phaser);
		
		th1.setName("Athread");
		th2.setName("Bthread");		
		th1.start();
		th2.start();	
		Thread.sleep(2000);
		
		if(!phaser.isTerminated()){
			System.out.println("强制关掉屏蔽功能");
			phaser.forceTermination();
			System.out.println("isTerminated() = "+phaser.isTerminated());			
			System.out.println("到达的线程:"+phaser.getArrivedParties());
		}

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
}
