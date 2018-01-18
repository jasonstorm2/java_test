package conchapter3;

import java.util.concurrent.Phaser;


/**
 * 
 * Phaser 类，Phaser(int parties) 定义parties个线程相互等待
 * 
 * arriveAndAwaitAdvance()：等到所有线程到达后继续往下运行，当线程数不够时，线程阻塞
 * 与 CountDownLatch的await()方法大体一样。
 * Phaser 具有设置多屏障的功能，类似比赛的赛段。
 * @author Administrator
 *
 */
public class phaserArriveAndAwaitAdvanceTest {
	public static void main(String[] args){
		phaserArriveAndAwaitAdvanceTest thisClass = new phaserArriveAndAwaitAdvanceTest();
		Phaser phaser = new Phaser(2);
		Service service = thisClass.new Service(phaser);
		Thread1 th1 = thisClass.new Thread1(service);
		Thread2 th2 = thisClass.new Thread2(service);
		th1.start();
		th2.start();
	}	

	class Service{
		public Phaser phaser;	
		
		public Service(Phaser phaser){
			this.phaser = phaser;
		}
		public void methodA(){
			System.out.println(Thread.currentThread().getName()+ "A1开始"+System.currentTimeMillis());
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName()+ "A1结束"+System.currentTimeMillis());
			System.out.println(Thread.currentThread().getName()+ "A2开始"+System.currentTimeMillis());
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName()+ "A2结束"+System.currentTimeMillis());
		}
		
		public void methodB(){			
			try {				
				System.out.println("返回注册的线程数量："+phaser.getRegisteredParties());
				System.out.println(Thread.currentThread().getName()+ "B1开始"+System.currentTimeMillis());
				Thread.sleep(3000);
				phaser.arriveAndAwaitAdvance();
				System.out.println("返回注册的线程数量："+phaser.getRegisteredParties());
				System.out.println(Thread.currentThread().getName()+ "B1结束"+System.currentTimeMillis());
				System.out.println(Thread.currentThread().getName()+ "B2开始"+System.currentTimeMillis());
				Thread.sleep(3000);
				phaser.arriveAndAwaitAdvance();
				System.out.println(Thread.currentThread().getName()+ "B2结束"+System.currentTimeMillis());
				System.out.println("返回注册的线程数量："+phaser.getRegisteredParties());
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
	}
	class Thread1 extends Thread{
		private Service service;
		public Thread1(Service service){
			super();
			this.service = service;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			service.methodA();
		}
		
	}
	
	class Thread2 extends Thread{
		private Service service;
		public Thread2(Service service){
			super();
			this.service = service;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			service.methodB();
		}
		
	}
}
