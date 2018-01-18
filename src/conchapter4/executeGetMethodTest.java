package conchapter4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 
 * 本类介绍ThreadPoolExecutor的各个get方法：
 * 
 * 
 * executor.getActiveCount());  			正在执行任务的线程数
 * executor.getCompletedTaskCount());		有多少个线程已经执行完任务
 * executor.getCorePoolSize());	        	线程池的核心线程数
 * executor.getMaximumPoolSize());			线程池的最大线程数
 * executor.getPoolSize());					线程池中的线程数，不管是否空闲
 * executor.getTaskCount());				有多少个任务发给了线程池
 * 
 * 以及，接口runnable在线程池中执行时乱序的。。。。
 * 
 * @author Administrator
 *
 */
public class executeGetMethodTest {
	
	public static void main(String[] args) {
		executeGetMethodTest thisClass = new 
				executeGetMethodTest();	
		thisClass.executeTest();
		
		
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
				
				System.out.println(this.username + " "+Thread.currentThread().getName() +" run! "+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println(this.username + " "+Thread.currentThread().getName() +" over "+System.currentTimeMillis());			

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 继承ThreadPoolExecutor，重写执行前和执行后两个方法
	 * 构造器必须重写
	 * @author Administrator
	 *
	 */
	class  MyThreadPoolExecutor extends ThreadPoolExecutor{

		public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
				long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			// TODO Auto-generated method stub
			super.afterExecute(r, t);
		}
		
		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			// TODO Auto-generated method stub
			super.beforeExecute(t, r);
		}
		
	}


	/**
	 * 
	 * @param runnable
	 */
	public void executeTest(){		
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		MyRunnable myRunnable4 = new MyRunnable("A4");
		MyRunnable myRunnable5 = new MyRunnable("A5");
		
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>();	

		MyThreadPoolExecutor executor = new 
				MyThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked);		
		
		executor.allowCoreThreadTimeOut(true);		
		executor.execute(myRunnable1);//1
		executor.execute(myRunnable2);//2
		executor.execute(myRunnable3);//3
		executor.execute(myRunnable4);//3
		executor.execute(myRunnable5);//3
		executor.execute(myRunnable5);//3
		executor.execute(myRunnable5);//3
		executor.execute(myRunnable5);//3
		
	
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
		
		System.out.println("有多少个线程正在执行任务："+executor.getActiveCount());
		System.out.println("有多少个线程已经执行完任务："+executor.getCompletedTaskCount());
		System.out.println("corePool线程有几个："+executor.getCorePoolSize());
		System.out.println("maxPool线程有几个"+executor.getMaximumPoolSize());
		System.out.println("线程池中，线程有几个"+executor.getPoolSize());
		System.out.println("有多少个任务发给了线程池"+executor.getTaskCount());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("有多少个线程已经执行完任务："+executor.getCompletedTaskCount());
		
		
	}	


}
