package conchapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * Callable线程和Runnable线程的区别
 * 
 * Callable<V> 接口的call()方法有返回值,返回V类型，返回值是通过Future接口进行获得的。   run()无返回值
 * 				call()方法可以抛出异常，			run()方法不能抛出异常
 * 
 * submit ,callable,和Future get()的关系：线程池如何利用callable取得返回值
 * Future<String> java.util.concurrent.AbstractExecutorService.submit(Callable<String> task)
 * 线程池的submit方法，提交callable线程，返回一个Future对象，
 * 在用Future的get()方法取得返回值。
 * 
 * submit方法也可以传入runnable接口
 * 
 * Future接口的各个方法
 * get()：得到返回值,有阻塞性
 * 
 * isDone()：判断线程是否完毕，结果是否返回，无阻塞
 * 
 * 
 * boolean canceled(boolean mayInterruptIfRunning)
 * 
 * boolean isCancelled()：
 * 
 * Future.get(long timeout, TimeUnit unit) 
 * 
 * @author Administrator
 */
public class CallableFutureTest {
	
	public static void main(String[] args) {
		CallableFutureTest thisClass = new 
				CallableFutureTest();	
//		thisClass.CallableTest();
//		thisClass.RunnableTest();
//		thisClass.futureCancelTest();
//		thisClass.futureGetByTimeTest();
		thisClass.callableExceptionTest();
	}	

	
	// 自定义Callable线程
	class MyCallable implements Callable<String>{
		private String username;
		
		public MyCallable(String username){
			this.username = username;
		}
		
		public String getUsername(){
			return username;
		}
		
		public void setUsername(String username){
			this.username = username;
		}

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			Thread.sleep(2000);
			String str = "返回线程名："+username;
			System.out.println(System.currentTimeMillis()+"执行中。。。");
			Integer.parseInt("a");
			return str;
		}
		
	}
	
	// 自定义Runnable线程
	class MyRunable implements Runnable{
		private String username;
		
		public MyRunable(String username){
			this.username = username;
		}
		
		public String getUsername(){
			return username;
		}
		
		public void setUsername(String username){
			this.username = username;
		}

		@Override
		public void run(){
			// TODO Auto-generated method stub
			try {
				
				String str = "callable线程执行了，线程名："+username;				
				while(true){
					System.out.println("执行中。。。");
					if(Thread.interrupted()){
						throw new InterruptedException();
					}
				}				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	


	/**
	 * submit(Callable<String> task)方法：
	 * @param runnable
	 */
	public void CallableTest(){		
		MyCallable myCallable = new MyCallable("loliy");
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		
		Future<String> fu = executor.submit(myCallable);
		System.out.println(System.currentTimeMillis()+"开始执行");
		try {
			System.out.println("future是否完成："+fu.isDone());
			System.out.println("线程执行结果："+fu.get());
			System.out.println("future是否完成："+fu.isDone());
			System.out.println(System.currentTimeMillis()+"执行完毕");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.allowCoreThreadTimeOut(true);
		
	}
	
	
	/**
	 * submit(Runnable task, T result)方法：
	 * T可以作为执行结果的返回值，而不需要使用get()方法来进行获得
	 * 
	 */
	public void RunnableTest(){	
		String name = "lolita";
		MyRunable myRunnable = new MyRunable(name);

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		
		Future<String> fu = executor.submit(myRunnable,name);
		System.out.println(System.currentTimeMillis()+"开始执行");
		try {
			System.out.println("线程执行结果："+fu.get());
			System.out.println(System.currentTimeMillis()+"执行完毕");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.allowCoreThreadTimeOut(true);
		
	}
	
	
	/**
	 * Future的两个方法
	 * boolean canceled(boolean mayInterruptIfRunning)：是否发送取消任务的命令，是否中断正在运行的任务，取决于mayInterruptIfRunning 。需要if(Thread.isInterrupted())方法配合
	 *         该方法返回的是 发送取消任务的命令 是否成功完成
	 * boolean isCancelled()：表示任务是否取消了
	 */
	public void futureCancelTest(){
		String name = "lolita";
		MyRunable myRunnable = new MyRunable(name);

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		
		Future<String> fu = executor.submit(myRunnable,name);	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(fu.cancel(true) + " "+fu.isCancelled());
		System.out.println(fu.cancel(false) + " "+fu.isCancelled());
		
	}
	
	/**
	 * Future.get(long timeout, TimeUnit unit) 
	 * 在一段时间内等待返回值，如果没有返回，则报错
	 */
	public void futureGetByTimeTest(){	
		try {
			String name = "lolita";
			MyCallable myCallable = new MyCallable(name);

			ThreadPoolExecutor executor = new 
					ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
			executor.allowCoreThreadTimeOut(true);
			
			Future<String> fu = executor.submit(myCallable);			
			System.out.println("获得返回值："+fu.get(1, TimeUnit.SECONDS));			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * callable接口内报错的处理：
	 *    如果报错，则进入catch语句块，而不继续执行get()方法
	 */
	public void callableExceptionTest(){	
		try {
			String name = "lolita";
			MyCallable myCallable = new MyCallable(name);

			ThreadPoolExecutor executor = new 
					ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
			executor.allowCoreThreadTimeOut(true);
			
			Future<String> fu = executor.submit(myCallable);			
			System.out.println("获得返回值："+fu.get(3, TimeUnit.SECONDS));			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			System.out.println("报错。。。");
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
