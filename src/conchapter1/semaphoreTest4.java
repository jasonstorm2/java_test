package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore类的测试
 * 此类是用来限制线程并发的数量的
 * 本例使用的类有:Semaphore
 * 本例使用的类有：Semaphore的acquireUninterruptibly()，release()
 * 
 * 使用acquireUninterruptibly() 或 Semaphore的acquireUninterruptibly(int permits)方法后，等待的线程不能被中断
 * @author Administrator
 *
 */
public class semaphoreTest4 {
	public static void main(String[] args) throws Exception {
		semaphoreTest4 test1 =  new semaphoreTest4();
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
		
		Thread.sleep(500);
		a.interrupt();
		System.out.println("a是否被中断");
	}
	
	/**
	 * Semaphore对象 使用acquireUninterruptibly() 或 Semaphore的acquireUninterruptibly(int permits)方法后，等待的线程不能被中断
	 * @author Administrator
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(1);		
		public void testMethod(){
			semaphore.acquireUninterruptibly();
			System.out.println(Thread.currentThread().getName() + " begin timer="+System.currentTimeMillis());
			for(int i=0;i<Integer.MAX_VALUE/20;i++){
				//随便用一个业务逻辑
				String str = new String();
				Math.random();					
			}
			System.out.println(Thread.currentThread().getName() + " end timer="+System.currentTimeMillis());
			semaphore.release();

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
