package conchapter3;

import java.util.concurrent.Phaser;


/**
 * 
 * Phaser 类，Phaser(int parties) 定义parties个线程相互等待
 * 若中途有人退出比赛，调用arriveAndDeregister方法使比赛不会阻塞
 * 
 * arriveAndAwaitAdvance()：等到所有线程到达后继续往下运行，当线程数不够时，线程阻塞
 * arriveAndDeregister() : 是当前线程退出比赛，并使注册的线程数量parties值-1，其他等待的线程-1后继续运行或等待。而本线程退出比赛后继续运行
 * getArrivedParties() :  达到屏蔽点的线程数量
 * getRegisteredParties(): 获得注册parties的数量
 * register(); 动态添加一个parties值--动态添加哦
 * bulkRegister()：动态添加多个parties
 * getArrivedParties()： 获得到达的线程数量
 * getUnarrivedParties():获得未到达的线程数量
 * 
 * 本例Phaser原本只有2个线程注册，后来B方法调用arriveAndDeregister方法，减少了注册的线程数量，Phaser变成了有三个阶段：
 * 第一个阶段A,B相互等待 ，线程注册数2
 * 第二个阶段A执行，       线程注册数1
 * 第三个阶段B执行         线程注册数1
 * @author LiZhenhua
 *
 */
public class phaserDeregisterTest {
	public static void main(String[] args){
		phaserDeregisterTest thisClass = new phaserDeregisterTest();
		Phaser phaser = new Phaser(2);
		Service service = thisClass.new Service(phaser);
		Thread1 th1 = thisClass.new Thread1(service);
		Thread2 th2 = thisClass.new Thread2(service);
		th1.setName("ThreadA");
		th2.setName("ThreadB");
		
		th1.start();
		th2.start();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("到达的线程数："+phaser.getArrivedParties());
		System.out.println("未到达的线程数："+phaser.getUnarrivedParties());
	}	

	class Service{
		public Phaser phaser;	
		
		public Service(Phaser phaser){
			this.phaser = phaser;
		}
		public void methodA(){
			System.out.println(Thread.currentThread().getName()+ System.currentTimeMillis()+" A1开始");
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A到大屏蔽点的线程数量："+phaser.getArrivedParties());
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " 返回注册的线程数量："+phaser.getRegisteredParties());
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A 调用第一个arriveAndAwaitAdvance后,到大屏蔽点的线程数量："+phaser.getArrivedParties());
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A1结束");
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A2开始");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A线程睡5秒后，到大屏蔽点的线程数量："+phaser.getArrivedParties());
			
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A2 调用第二个arriveAndAwaitAdvance后,phaser第几个屏蔽点："+phaser.getPhase());
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A2结束");
		}
		
		
		public void methodB(){			
			try {
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B1 开始");
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B 到大屏蔽点的线程数量："+phaser.getArrivedParties());
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B 线程睡3秒后，到大屏蔽点的线程数量："+phaser.getArrivedParties());
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " 返回注册的线程数量："+phaser.getRegisteredParties());
				phaser.arriveAndDeregister();
//				phaser.arriveAndAwaitAdvance();
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " 返回注册的线程数量："+phaser.getRegisteredParties());
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " 调用 arriveAndDeregister()，B到大屏蔽点的线程数量："+phaser.getArrivedParties());
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B1phaser 第几个屏蔽点："+phaser.getPhase());
				
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B2 开始");
				Thread.sleep(2000);
//				phaser.register(); //动态添加一个parties
				phaser.bulkRegister(10); //动态添加多个parties
				phaser.arriveAndAwaitAdvance();

				
				System.out.println("返回注册的线程数量："+phaser.getRegisteredParties());
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B2 线程睡3秒后，调用phaser arriveAndAwaitAdvance后，第几个屏蔽点："+phaser.getPhase());
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B2 结束");
				
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
