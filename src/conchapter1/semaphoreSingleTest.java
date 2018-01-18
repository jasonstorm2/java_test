package conchapter1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Semaphore类的测试
 * 
 * 多进路-单处理-多出路试验
 * 
 * 开启多个线程，运行的代码模块只有3个许可的情况
 * ReentrantLock锁的功能，同步代码块，即阻塞线程，所以也称单处理
 * 
 * 此时不管有几个许可，只能由一个执行，其他的线程在等待，所以还不如只用一个许可
 * @author Administrator
 *
 */
public class semaphoreSingleTest {
	public static void main(String[] args) {
		semaphoreSingleTest test1 =  new semaphoreSingleTest();
		Service service =  test1.new Service();
		
		Thread1[] a =  new Thread1[12];
		for(int i = 0;i<a.length;i++){
			a[i] =  test1.new Thread1(service);
			a[i].start();
		}

	}
	
	/**
	 * ReentrantLock 程序锁的使用，每次只有一个线程获得该锁，当线程执行完后，释放锁，其他线程才能获得锁
	 * @author Administrator
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(3);		
		private ReentrantLock lock = new ReentrantLock();
		public void testMethod(){
			try{
				semaphore.acquire();
				lock.lock();
				System.out.println(Thread.currentThread().getName() + " begin timer="+System.currentTimeMillis());
				for(int i=0;i<Integer.MAX_VALUE/20;i++){
//					System.out.println(Thread.currentThread().getName()+"打印中");
					//随便用一个业务逻辑
					String str = new String();
					Math.random();	
				}
				System.out.println(Thread.currentThread().getName() + " end timer="+System.currentTimeMillis());
				lock.unlock();
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
