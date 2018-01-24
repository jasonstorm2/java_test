package conchapter2;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * 多个线程公用一个CountDownLatch对象，该对象有一个初始的计数值count，（new CountDownLatch(count)）
 * 1。当一个线程调用CountDownLatch.countDown方法后，count-1，
 * 2。该线程调用CountDownLatch.await()方法，进入等待状态，直到别的线程调用CountDownLatch.countDown，
 * 使count计数为0，所有处于等待的线程继续往下运行
 * 
 * 
 * CountDownLatch类的使用
 * 该类的作用类似门锁，判断count计数不为0时，可以命令当前线程呈wait状态，为0则继续运行
 * 
 * 使用的方法有：
 * CountDownLatch.await()       ：判断计数是否为0，不为0则等待     ----------与Condition.await()的比较
 * CountDownLatch.countDown()   ：将计数-1，计数一直减到0时，所有等待线程继续运行
 * CountDownLatch.getCount()    ：获取当前计数
 * 
 * 一个线程主动调用等待方法await()，等其他的线程countDown()计数，直到为0 时，等待的线程将继续运行
 * @author Administrator
 *
 */
public class countDownTest {
	
	public static final int AWAITNUM = 2;
	
	public static void main(String[] args) throws InterruptedException {
		countDownTest thisClass =  new countDownTest();
		Service service =  thisClass.new Service();
		Thread1 thread = thisClass.new Thread1(service);
		thread.start();
		Thread1 thread2 = thisClass.new Thread1(service);
		thread2.start();
		Thread.sleep(2000);
		service.downMethod();
		
	
	}
	
	/**
	 * getQueueLength() 获取等待许可的线程数量
	 * hasQueuedThreads() 判断是否有线程在等待
	 * @author Administrator
	 *
	 */
	class Service{
		private CountDownLatch down = new CountDownLatch(AWAITNUM);		
		public void testMethod(){
			try{
				
				System.out.println(Thread.currentThread().getName() + ":开始时间 = "+System.currentTimeMillis()+"当前计数为："+down.getCount());
				System.out.println(Thread.currentThread().getName() + ":开始时间 = "+System.currentTimeMillis()+"线程主动调用await()");
				down.countDown();
				System.out.println(Thread.currentThread().getName() + ":开始时间 = "+System.currentTimeMillis()+"当前计数为："+down.getCount());
				
				down.await();
				System.out.println(Thread.currentThread().getName() + ":开始时间 = "+System.currentTimeMillis()+"等待结束，线程继续执行");
			}catch(InterruptedException e){
				e.printStackTrace();				
			}

		}
		
		public void downMethod(){
			System.out.println(Thread.currentThread().getName() + ":开始时间 = "+System.currentTimeMillis()+"主线程调用countDown()");
			try {
				down.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("main进行逻辑运算：");
			long beginTime = System.nanoTime();
			long beginTime2 = System.currentTimeMillis();
			
			for(int i=0;i<Integer.MAX_VALUE/20;i++){
				//随便用一个业务逻辑
				String str = new String();
				Math.random();					
			}
			long endTime = System.nanoTime();
			long endTime2 = System.currentTimeMillis();
			
			System.out.println("主线程逻辑运算的时间："+(endTime-beginTime));
			System.out.println("主线程逻辑运算的时间2："+(endTime2-beginTime2));
			
			System.out.println(Thread.currentThread().getName() + ":结束时间 = "+System.currentTimeMillis()+"主线程,当前计数为："+down.getCount());
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
			service.testMethod();			
		}
		
	}

}
