package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore类的测试
 * 此类是用来限制线程并发的数量的
 * 本例使用的类有:Semaphore
 * 本例使用的方法有：Semaphore的getQueueLength()，hasQueuedThreads()
 * @author LiZhenhua
 *
 */
public class semaphoreTest5 {
	public static void main(String[] args) {
		semaphoreTest5 test1 =  new semaphoreTest5();
		Service service =  test1.new Service();
		Thread1[] a = new Thread1[10];
		for(int i=0;i<10;i++){
			a[i] = test1.new Thread1(service);
			a[i].start();
		}		
	}
	
	/**
	 * getQueueLength() 获取等待许可的线程数量
	 * hasQueuedThreads() 判断是否有线程在等待
	 * @author LiZhenhua
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(1);		
		public void testMethod(){
			try{
				semaphore.acquire();
				Thread.sleep(1000);
				System.out.println("还有大约"+semaphore.getQueueLength()+"个线程在等待");				
				System.out.println("是否有线程正在等待信号量？"+semaphore.hasQueuedThreads());
				semaphore.release();
			}catch(InterruptedException e){
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
			service.testMethod();			
		}
		
	}

}
