package conchapter4;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池是否允许核心线程超时：即，如果核心线程运行完任务，处于空闲状态
 *                             是否在超时时间后杀死线程。
 *                             
 * ThreadPoolExecutor.allowCoreThreadTimeOut(boolean value)：可以设置是否杀死空闲的核心线程
 * 
 * 不管线程的队列是new LinkedBlockingQueue<Runnable>() 还是 new SynchronousQueue<Runnable>()
 * @author LiZhenhua
 *
 */
public class allowsCoreThreadTimeOutTest {
	
	public static void main(String[] args) {
		allowsCoreThreadTimeOutTest thisClass = new 
				allowsCoreThreadTimeOutTest();	
		thisClass.allowsCoreThreadTimeOutTest();
		thisClass.allowsCoreThreadTimeOutTest2();		
	}	

	
	// 自定义线程
	class MyRunnable implements Runnable{
		private String username;
		
		public MyRunnable(String username){
			this.username = username;
		}
		
		public String getUsername(){
			return username;
		}
		
		public void setUsername(String username){
			this.username = username;
		}

		@Override
		public void run() {
			try {
				
				System.out.println(Thread.currentThread().getName() +" run! "+System.currentTimeMillis());
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() +" over "+System.currentTimeMillis());			

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	/**
	 * 普通线程池执行线程，没有添加未处理线程异常处理
	 * @param runnable
	 */
	public void allowsCoreThreadTimeOutTest(){
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		MyRunnable myRunnable4 = new MyRunnable("A4");

		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(4, 5, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());	
		System.out.println("是否允许核心线程超市："+executor.allowsCoreThreadTimeOut());
		executor.execute(myRunnable1);		
		executor.execute(myRunnable2);		
		executor.execute(myRunnable3);		
		executor.execute(myRunnable4);		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("线程池里面的线程数量："+executor.getPoolSize());
		
	}
	
	/**
	 * 线程池 添加了 未处理线程的处理
	 * 有几条线程未处理就有几个相应的对其的处理
	 * @param runnable
	 */
	public void allowsCoreThreadTimeOutTest2(){
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		MyRunnable myRunnable4 = new MyRunnable("A4");

//		ThreadPoolExecutor executor = new 
//				ThreadPoolExecutor(4, 5, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());	
//		
		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(4, 5, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());	
		
		executor.allowCoreThreadTimeOut(true);
		System.out.println("是否允许核心线程超市："+executor.allowsCoreThreadTimeOut());
		executor.execute(myRunnable1);		
		executor.execute(myRunnable2);		
		executor.execute(myRunnable3);		
		executor.execute(myRunnable4);	
	    try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("线程池里面的线程数量："+executor.getPoolSize());		
	}

}
