package conchapter5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池自定义被拒绝策略
 * ThreadPoolExecutor.setRejectedExecutionHandler(RejectedExecutionHandler handler)
 * 
 * submit提交线程报错才能返回？
 * execute提交的会出错？
 * 
 * @author Administrator
 *
 */
public class RejectedExecutionHandlerTest {
	
	public static void main(String[] args) {
		RejectedExecutionHandlerTest thisClass = new RejectedExecutionHandlerTest();
		
		ExecutorService service = Executors.newCachedThreadPool();
		ThreadPoolExecutor executor = (ThreadPoolExecutor)service;
		//设置拒绝策略
		executor.setRejectedExecutionHandler(thisClass.new MyRejectedExecutionHandler());
		executor.submit(thisClass.new MyRunnable("A"));
		executor.submit(thisClass.new MyRunnable("B"));
		executor.submit(thisClass.new MyRunnable("C"));
		executor.shutdown();
		executor.submit(thisClass.new MyRunnable("D"));
		
//		executor.execute(thisClass.new MyRunnable("A"));
//		executor.execute(thisClass.new MyRunnable("B"));
//		executor.execute(thisClass.new MyRunnable("C"));
//		executor.shutdown();
//		executor.execute(thisClass.new MyRunnable("D"));
//		
		
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
		}
		
	}

}
