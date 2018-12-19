package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore类的测试
 * 此类是用来限制线程并发的数量的
 * 本例使用的类有:Semaphore
 * 本例使用的方法有：Semaphore的acquire()，release()
 * 
 * 多条线程 处理同一个对象，用Semaphore类的方法来控制处理对象的线程数量
 * @author LiZhenhua
 *
 */
public class semaphoreTest1 {
	public static void main(String[] args) {
		semaphoreTest1 test1 =  new semaphoreTest1();
		Service service =  test1.new Service();
		
		Thread1 a =  test1.new Thread1(service);
		Thread1 b =  test1.new Thread1(service);
		Thread1 c =  test1.new Thread1(service);
		a.setName("a");
		b.setName("b");
		c.setName("c");
		// 由于semaphoreTest1的限制，此3个线程是同步的
		a.start();
		b.start();
		c.start();		
	}
	
	/**
	 * Semaphore(int permits)
	 * 同一个时间只能有permits个线程同时执行acquire() 和 release()之间的代码
	 * 如果permits>1并不能保证线程安全，可能有脏数据
	 * 
	 * @author LiZhenhua
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(2);		
		public void testMethod(){
			try{
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName() + " begin timer="+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " end timer="+System.currentTimeMillis());
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
