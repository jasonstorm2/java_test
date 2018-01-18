package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore类的测试
 * 
 * 多进路-多处理-多出路试验
 * 
 * 开启多个线程，运行的代码模块只有3个许可的情况，非同步
 * @author Administrator
 *
 */
public class semaphoreMoreTest {
	public static void main(String[] args) {
		semaphoreMoreTest test1 =  new semaphoreMoreTest();
		Service service =  test1.new Service();
		
		Thread1[] a =  new Thread1[12];
		for(int i = 0;i<a.length;i++){
			a[i] =  test1.new Thread1(service);
			a[i].start();
		}

	}
	
	/**
	 * Semaphore(int permits)
	 * 同一个时间只能有permits个线程同时执行acquire() 和 release()之间的代码
	 * 如果permits>1并不能保证线程安全，可能有脏数据
	 * 
	 * @author Administrator
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(3);		
		public void testMethod(){
			try{
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName() + " begin timer="+System.currentTimeMillis());
				for(int i=0;i<5;i++){
					System.out.println(Thread.currentThread().getName()+"打印中");					
				}
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
