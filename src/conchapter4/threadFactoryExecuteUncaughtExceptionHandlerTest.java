package conchapter4;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 有时候需要对线程池中的线程属性 进行定制化，这就需要配置ThreadFactory线程工厂
 * 
 * ThreadFactory + execute() + ExecuteUncaughtExceptionHandler
 * 
 * 自定义的线程工厂可以捕获线程出现的异常：Thread.setUncaughtExceptionHandler(UncaughtExceptionHandler eh)
 * 
 * ThreadPoolExecutor线程池添加自定义线程工厂有两种方法：
 *    1.在构造函数内添加参数；
 *    2.ThreadPoolExecutor调用setThreadFactory(threadFactory);
 * 
 * 
 * @author Administrator
 *
 */
public class threadFactoryExecuteUncaughtExceptionHandlerTest {
	
	public static void main(String[] args) {
		threadFactoryExecuteUncaughtExceptionHandlerTest thisClass = new 
				threadFactoryExecuteUncaughtExceptionHandlerTest();
		
		MyThreadFactory myFa = thisClass.new MyThreadFactory();
		MyRunnable myRunnable = thisClass.new MyRunnable();

		thisClass.catchExceptionTest(myRunnable,myFa);
	}
	
	// 自定义线程工厂
	class MyThreadFactory  implements ThreadFactory{
		@Override
		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			Thread myThread = new Thread(r);
			myThread.setName("自定义的线程");
			myThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {				
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.out.println("自定义处理异常启用："+t.getName() + " "+e.getMessage());
					e.printStackTrace();//打印异常路径信息
					
				}
			});
			return myThread;
		}	
	}
	
	// 自定义线程
	class MyRunnable implements Runnable{

		@Override
		public void run() {
			try {
				
				System.out.println(Thread.currentThread().getName() +" run "+System.currentTimeMillis());
				String str = null;
				int len = str.indexOf(10);
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() +" over "+System.currentTimeMillis());			

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @param runnable
	 */
	public void catchExceptionTest(Runnable runnable,ThreadFactory threadFactory){
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
//		ThreadPoolExecutor executor = new 
//				ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),threadFactory);
		
		executor.setThreadFactory(threadFactory);		
		try {
		executor.execute(runnable);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
