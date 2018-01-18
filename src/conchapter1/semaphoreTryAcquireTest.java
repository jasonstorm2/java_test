package conchapter1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore类的测试
 * 此类是用来限制线程并发的数量的
 * 
 * 无参方法tryAcquire(),尝试获得1个许可，无法获得返回false，与if语句结合使用，无阻塞程序
 * 有参方法tryAcquire(int permits),尝试获得x个许可，无法获得返回false，
 * 有参方法tryAcquire(long timeout,TimeUnit unit),在有限的时间内尝试获得1个许可，无法获得返回false，
 * 有参方法tryAcquire(int permits, long timeout, TimeUnit unit),在有限的时间内尝试获得x个许可，无法获得返回false，
 * @author Administrator
 *
 */
public class semaphoreTryAcquireTest {
	public static void main(String[] args) {
		semaphoreTryAcquireTest test1 =  new semaphoreTryAcquireTest();
		Service service =  test1.new Service();
		Thread1[] a = new Thread1[3];
		for(int i=0;i<a.length;i++){
			a[i] = test1.new Thread1(service);
			a[i].start();
		}		
	}
	
	/**
	 * tryAcquire 方法
	 * @author Administrator
	 *
	 */
	class Service{
		
		private Semaphore semaphore = new Semaphore(1);
		
		//无参方法tryAcquire()
		public void testMethod1(){
			if(semaphore.tryAcquire()){
				System.out.println(Thread.currentThread().getName() + "首选进入！");
				for(int i=0;i<Integer.MAX_VALUE/50;i++){
					//随便用一个业务逻辑
					String str = new String();
					Math.random();					
				}
				semaphore.release();
				System.out.println(Thread.currentThread().getName() + "运行结束");
			}else{
				System.out.println(Thread.currentThread().getName() + "未成功进入");
			}			
		}	
		
		//有参方法tryAcquire(int permits)
		public void testMethod2(){
			if(semaphore.tryAcquire(2)){
				System.out.println(Thread.currentThread().getName() + "首选进入！");
				for(int i=0;i<Integer.MAX_VALUE/50;i++){
					//随便用一个业务逻辑
					String str = new String();
					Math.random();					
				}
				semaphore.release();
				System.out.println(Thread.currentThread().getName() + "运行结束");
			}else{
				System.out.println(Thread.currentThread().getName() + "未成功进入");
			}			
		}
		
		//有参方法tryAcquire(long timeout,TimeUnit unit)
		public void testMethod3() throws Exception{
			if(semaphore.tryAcquire(3,TimeUnit.SECONDS)){
				System.out.println(Thread.currentThread().getName() + "首选进入！");
				for(int i=0;i<Integer.MAX_VALUE/10;i++){
					//随便用一个业务逻辑
					String str = new String();
					Math.random();					
				}
				semaphore.release();
				System.out.println(Thread.currentThread().getName() + "运行结束");
			}else{
				System.out.println(Thread.currentThread().getName() + "未成功进入");
			}			
		}
		
		// 有参方法tryAcquire(int permits, long timeout, TimeUnit unit)
		public void testMethod4() throws Exception{
			if(semaphore.tryAcquire(2,3,TimeUnit.SECONDS)){
				System.out.println(Thread.currentThread().getName() + "首选进入！");
				for(int i=0;i<Integer.MAX_VALUE/10;i++){
					//随便用一个业务逻辑
					String str = new String();
					Math.random();					
				}
				semaphore.release();
				System.out.println(Thread.currentThread().getName() + "运行结束");
			}else{
				System.out.println(Thread.currentThread().getName() + "未成功进入");
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
			System.out.println(this.getName()+"启动了");
			try {
//			service.testMethod1();		
//			service.testMethod2();			
//			service.testMethod3();
			service.testMethod4();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
		
	}

}
