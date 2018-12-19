package conchapter4;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池 执行线程任务被拒绝时的处理行为，即，当某个线程被拒绝处理后，有相应的操作
 * 
 * 需自定义一个RejectedExecutionHandler接口的实现类，
 * 然后调用ThreadPoolExecutor.setRejectedExecutionHandler(RejectedExecutionHandler handler)
 * 只要有未处理的线程，都可以调动RejectedExecutionHandler实现类的处理方法
 * 
 * 
 * 
 * @author LiZhenhua
 *
 */
public class setGetRejectedExceptionHandlerTest {
	
	public static void main(String[] args) {
		setGetRejectedExceptionHandlerTest thisClass = new 
				setGetRejectedExceptionHandlerTest();	
//		thisClass.catchExceptionTest();
		thisClass.catchExceptionTest2();		
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
				Thread.sleep(4000);
				System.out.println(Thread.currentThread().getName() +" over "+System.currentTimeMillis());			

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 自定义被拒绝执行的处理Handler
	 * @author LiZhenhua
	 *
	 */
	class MyRejectedExecutionHandler implements RejectedExecutionHandler{
		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			System.out.println(((MyRunnable) r).getUsername() + "被拒绝执行");
			
		}		
	}

	/**
	 * 普通线程池执行线程，没有添加未处理线程异常处理
	 * @param runnable
	 */
	public void catchExceptionTest(){
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		MyRunnable myRunnable4 = new MyRunnable("A4");

		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 999, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());		
		executor.execute(myRunnable1);		
		executor.execute(myRunnable2);		
		executor.execute(myRunnable3);		
		executor.execute(myRunnable4);		
	}
	
	/**
	 * 线程池 添加了 未处理线程的处理
	 * 有几条线程未处理就有几个相应的对其的处理
	 * @param runnable
	 */
	public void catchExceptionTest2(){
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		MyRunnable myRunnable4 = new MyRunnable("A4");
		MyRunnable myRunnable5 = new MyRunnable("A5");
		MyRunnable myRunnable6 = new MyRunnable("A6");
		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 999, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());	
		executor.setRejectedExecutionHandler(new MyRejectedExecutionHandler());		
		executor.execute(myRunnable1);		
		executor.execute(myRunnable2);		
		executor.execute(myRunnable3);		
		executor.execute(myRunnable4);	
		executor.execute(myRunnable5);		
		executor.execute(myRunnable6);			
		
		System.out.println(executor.getRejectedExecutionHandler().toString());		
	}

}
