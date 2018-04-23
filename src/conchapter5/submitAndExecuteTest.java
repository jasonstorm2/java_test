package conchapter5;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * submit 和 execute的区别
 * 
 * submit： 有返回值  可以捕获异常（调用get方法）
 * execute：无返回值  出现异常直接打印信息  也可以捕获异常（自定义线程工厂）
 * 
 * @author Administrator
 *
 */
public class submitAndExecuteTest {
	
	public static void main(String[] args) {
		submitAndExecuteTest thisClass = new submitAndExecuteTest();
		
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(2, 3, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		
		try {
			Future<String> fu = executor.submit(thisClass.new MyCallable("A"));// 捕获异常
			System.out.println("Future捕获异常："+fu.get());  //有get方法才能捕获异常
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("有异常抛出");
			e.printStackTrace();
		}

		executor.execute(thisClass.new MyRunnable("B"));// 直接抛出异常		
//		executor.setThreadFactory(new ThreadFactory() {
//			
//			@Override
//			public Thread newThread(Runnable r) {
//				Thread t = new Thread(r);
//				t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
//					
//					@Override
//					public void uncaughtException(Thread t, Throwable e) {
//						System.out.println("execute()方法通过使用自定义");
//						System.out.println("ThreadFactory也能捕获异常了");
//						e.printStackTrace();					
//					}
//				});
//				
//				return t;
//			}
//		});
//		executor.execute(thisClass.new MyRunnable("B"));//

	}
	
	
	
	
	class MyRejectedExecutionHandler implements RejectedExecutionHandler{

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//			 TODO Auto-generated method stub
//			System.out.println(((FutureTask)r).toString() + "被拒绝");//submit不会报错
//			System.out.println(((MyRunnable)r).name + "被拒绝");//execute不会报错
			System.out.println("添加出错了");
		}
		
	}
	
	
	class MyRunnable implements Runnable{
		
		private String name;
		
		public MyRunnable(String name){
			this.name = name;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(name+"正在运行");		
			Integer.parseInt("a");
			System.out.println("运行完毕");
		}
		
	}
	
	class MyCallable implements Callable<String>{
		
		private String name;
		
		public MyCallable(String name){
			this.name = name;
		}

		@Override
		public String call() {
			// TODO Auto-generated method stub
			System.out.println(name+"正在运行");		
			Integer.parseInt("a");
			System.out.println("运行完毕");
			return name;			
		}
		
	}

}
