package conchapter4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * 
 * ThreadPoolExecutor类使用上并不是那么方便，在实例化时需要传入很多个参数，
 * 要考虑线程的并发数等于线程池运行效率有关的参数，所以官方建议使用Executors工厂类来创建线程池对象
 * 
 * 
 * 使用定制线程工厂 ThreadFactory接口的 实现implements
 * Executors.newCachedThreadPool(ThreadFactory threadFactory) 
 * 三种线程池的创建及如何使用定制工厂
 *  Executors.newCachedThreadPool();
 *  Executors.newFixedThreadPool(3);
 *  Executors.newSingleThreadExecutor();
 *  
 *  Executor调用用方法：Executor.execute(Runnable command)来运行一条新的线程
 *  
 * @author Administrator
 *
 */
public class newCachedThreadPoolTest {
	
	public static void main(String[] args) {
		newCachedThreadPoolTest thisClass = new newCachedThreadPoolTest();		
		thisClass.newCachedThreadPoolMethod();
//		thisClass.newFixedThreadPoolMethod();
//		thisClass.newSingleThreadMethod();
		
	}
	
	
	// 自定义线程工厂
	class MyThreadFactory  implements ThreadFactory{

		@Override
		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			Thread myThread = new Thread(r);
			myThread.setName("自定义的线程");
			return myThread;
		}	
	}
	
	/**
	 * 无界线程池，可以实现线程自动回收
	 */
	public void newCachedThreadPoolMethod(){
		MyThreadFactory myFa = new MyThreadFactory();
		ExecutorService service = Executors.newCachedThreadPool(myFa);// 定制线程
		ExecutorService service2 = Executors.newCachedThreadPool(); //非定制线程
		// ExecutorService执行多条线程
		for(int i=0;i<5;i++){
			service.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("线程池再运行："+System.currentTimeMillis()+" 线程名字："+Thread.currentThread().getName()+" 线程的id:"+Thread.currentThread().getId());
					
				}
			});
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i=0;i<5;i++){
			service2.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("线程池再运行："+System.currentTimeMillis()+" 线程名字："+Thread.currentThread().getName()+" 线程的id:"+Thread.currentThread().getId());
					
				}
			});
		}
		// 线程的重复使用测试
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i=0;i<5;i++){
			service2.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("线程池再运行："+System.currentTimeMillis()+" 线程名字："+Thread.currentThread().getName()+" 线程的id:"+Thread.currentThread().getId());
					
				}
			});
		}
	}
	
	
	public void newFixedThreadPoolMethod(){
		MyThreadFactory myFa = new MyThreadFactory();
		ExecutorService service = Executors.newFixedThreadPool(3);// 非定制线程
		ExecutorService service2 = Executors.newFixedThreadPool(3,myFa); //定制线程
		// ExecutorService执行多条线程
		for(int i=0;i<5;i++){
			service.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("线程池再运行："+System.currentTimeMillis()+" 线程名字："+Thread.currentThread().getName()+" 线程的id:"+Thread.currentThread().getId());
					
				}
			});
		}

		for(int i=0;i<5;i++){
			service2.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("线程池再运行："+System.currentTimeMillis()+" 线程名字："+Thread.currentThread().getName()+" 线程的id:"+Thread.currentThread().getId());
					
				}
			});
		}
	}
	
	/**
	 * 单一线程池可以实现以队列的方式来执行任务
	 */
	public void newSingleThreadMethod(){
		MyThreadFactory myFa = new MyThreadFactory();
		ExecutorService service = Executors.newSingleThreadExecutor();// 非定制线程
		ExecutorService service2 = Executors.newSingleThreadExecutor(myFa); //定制线程
		// 普通线程
		for(int i=0;i<5;i++){
			service.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("线程池再运行："+System.currentTimeMillis()+" 线程名字："+Thread.currentThread().getName()+" 线程的id:"+Thread.currentThread().getId());
					
				}
			});
		}
		// 制定线程
		for(int i=0;i<5;i++){
			service2.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("线程池再运行："+System.currentTimeMillis()+" 线程名字："+Thread.currentThread().getName()+" 线程的id:"+Thread.currentThread().getId());
					
				}
			});
		}
	}

}
