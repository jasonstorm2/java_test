package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore类的测试
 * 此类是用来限制线程并发的数量的
 * 本例使用的类有:Semaphore
 * 本例使用的方法有：Semaphore的acquire(int permits)，release(int permits)
 * @author Administrator
 *
 */
public class semaphoreTest2 {
	public static void main(String[] args) {
		semaphoreTest2 test1 =  new semaphoreTest2();
		Service service =  test1.new Service();
		Thread1[] a = new Thread1[10];
		for(int i=0;i<10;i++){
			a[i] = test1.new Thread1(service);
			a[i].start();
		}
	}
	
	/**
	 * Semaphore(int permits)
	 * 同一个时间只能有permits个线程同时执行acquire() 和 release()之间的代码
	 * 如果permits>1并不能保证线程安全，可能有脏数据
	 * 
	 * acquire(int permits) 每次调用此方法，就使用permits个许可，即减掉permits个许可
	 * 本例有10个许可，每次调用-2个，所以只能调动5次
	 * 
	 * @author Administrator
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(10);		
		public void testMethod(){
			try{
				semaphore.acquire(2);
				System.out.println(Thread.currentThread().getName() + " begin timer="+System.currentTimeMillis());
				int sleepValue = ((int)(Math.random()*10000));
				System.out.println(Thread.currentThread().getName()+"线程停止"+2+"秒");
				Thread.sleep(2000);				
				System.out.println(Thread.currentThread().getName() + " end timer="+System.currentTimeMillis());
				semaphore.release(2);
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
