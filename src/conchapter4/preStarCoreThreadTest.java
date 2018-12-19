package conchapter4;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池预先启动核心线程准备接受任务
 * 
 * executor.prestartCoreThread()：每次启动一条核心线程，成功返回true，如果核心线程已满，返回false
 * executor.prestartAllCoreThreads()： 启动所有的核心线程，返回启动的核心线程数量
 * @author LiZhenhua
 *
 */
public class preStarCoreThreadTest {
	
	public static void main(String[] args) {
		preStarCoreThreadTest thisClass = new 
				preStarCoreThreadTest();	
//		thisClass.preStarCoreThreadTest();
		thisClass.preStarCoreThreadTest2();		
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
	public void preStarCoreThreadTest(){		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		System.out.println("是否启动了核心线程："+executor.prestartCoreThread());
		System.out.println("当前线程池线程数量："+executor.getPoolSize());
		System.out.println("是否启动了核心线程："+executor.prestartCoreThread());
		System.out.println("当前线程池线程数量："+executor.getPoolSize());
		System.out.println("是否启动了核心线程："+executor.prestartCoreThread());
		System.out.println("当前线程池线程数量："+executor.getPoolSize());
		System.out.println("是否启动了核心线程："+executor.prestartCoreThread());
		System.out.println("当前线程池线程数量："+executor.getPoolSize());
		executor.allowCoreThreadTimeOut(true);
		
	}
	
	/**
	 * 线程池 添加了 未处理线程的处理
	 * 有几条线程未处理就有几个相应的对其的处理
	 * @param runnable
	 */
	public void preStarCoreThreadTest2(){	
		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());	
		
		System.out.println("是否启动了核心线程："+executor.prestartAllCoreThreads());
		System.out.println("当前线程池线程数量："+executor.getPoolSize());
		System.out.println("是否启动了核心线程："+executor.prestartCoreThread());
		System.out.println("当前线程池线程数量："+executor.getPoolSize());
		System.out.println("是否启动了核心线程："+executor.prestartCoreThread());
		System.out.println("当前线程池线程数量："+executor.getPoolSize());
		System.out.println("是否启动了核心线程："+executor.prestartCoreThread());
		System.out.println("当前线程池线程数量："+executor.getPoolSize());
		executor.allowCoreThreadTimeOut(true);
	
	}

}
