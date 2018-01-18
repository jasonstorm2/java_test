package conchapter2;

import java.util.concurrent.CountDownLatch;

/**
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
	public static void main(String[] args) throws InterruptedException {
		countDownTest thisClass =  new countDownTest();
		Service service =  thisClass.new Service();
		Thread1 thread = thisClass.new Thread1(service);
		thread.start();
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
		private CountDownLatch down = new CountDownLatch(1);		
		public void testMethod(){
			try{
				
				System.out.println("当前计数为："+down.getCount());
				System.out.println("线程主动调用await()");
				down.await();
				System.out.println("等待结束，线程继续执行");
			}catch(InterruptedException e){
				e.printStackTrace();				
			}

		}
		
		public void downMethod(){
			System.out.println("主线程调用countDown()");
			down.countDown(); //为0时，唤醒了其他等待的线程
			try {
				down.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<Integer.MAX_VALUE/20;i++){
				//随便用一个业务逻辑
				String str = new String();
				Math.random();					
			}
			System.out.println("主线程,当前计数为："+down.getCount());
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
