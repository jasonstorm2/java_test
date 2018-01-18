package conchapter4;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 调用shutdown()线程池状态变为SHUTDOWN,当前未执行完的线程继续执行，不在添加新的task，空闲的立刻关闭。不会阻塞，主线程可以结束。
 *     关闭后，若再执行线程池的executor将会报错
 *     
 * 调用shutdownNow() 线程池状态变为STOP,试图关闭所有在运行的线程，空闲的线程立刻关闭，不会阻塞。
 *     若在运行的线程有if判断语句 及 抛出异常的代码，则人为的抛出异常
 *     如果没有if语句 及 异常抛出的代码，则线程会运行完毕
 *     关闭后，若再执行线程池的executor将会报错
 *     shutdownNow()方法会返回未执行的线程列表
 *     
 *     
 *     isShutDown()：判断线程池是否被关闭
 *     isTerminating 和 isTerminated方法：是否正在中断，是否已经中断
 *     awaitTermination(long timeout, TimeUnit unit)：等待线程池关闭完毕
 * @author Administrator
 *
 */
public class ThreadPoolShutdownTest {
	
	public static void main(String[] args) {
		ThreadPoolShutdownTest thisClass = new ThreadPoolShutdownTest();		
		Runnable runnable = new Runnable() {			
			@Override
			public void run() {				
				try {
					System.out.println(Thread.currentThread().getName() +" run "+System.currentTimeMillis());
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() +" over "+System.currentTimeMillis());
					
					if(Thread.currentThread().isInterrupted()){
						System.out.println("该线程已经Interrupted中断");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};	
//		thisClass.initExecutor(runnable);
//		thisClass.runExecutor(runnable);
		thisClass.shutdowenExecutor(runnable);
//		thisClass.shutdowenExecutor2(runnable);
//		thisClass.shutdownNowExecutor();
//		thisClass.shutdownNowExecutor2();
//		thisClass.isShutdownExecutor(runnable);
//		thisClass.isTerminatingExecutor();
//		thisClass.awaitTerminationExecutor(runnable);
	}	
	/**
	 * 实例化线程池不会运行线程，实例化后程序结束
	 * @param runnable
	 */
	public void initExecutor(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		System.out.println("main end");
	}
	
	/**
	 * 实例化线程池，并执行一条线程，线程执行完后，继续存在，等待执行新任务
	 * @param runnable
	 */	
	public void runExecutor(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		executor.execute(runnable);
		System.out.println("main end");
	}
	
	/**
	 * 实例化线程池，并执行一条线程，线程执行完后，继续存在，等待执行新任务
	 * 调用shutdown()方法，等待的线程关闭
	 * @param runnable
	 */	
	public void shutdowenExecutor(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		executor.execute(runnable);
		System.out.println("线程池关闭前，线程池中的线程数量："+executor.getPoolSize());
		executor.shutdown();
		System.out.println("线程池是否被关闭："+executor.isShutdown());
		try {
			Thread.sleep(1000);
			System.out.println("线程池关闭后，线程池中的线程数量："+executor.getPoolSize());
			Thread.sleep(4000);
			System.out.println("线程池关闭后，线程池中的线程数量："+executor.getPoolSize());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main end");
	}
	
	/**
	 * 调用shutdown()线程池关闭
	 * 若线城池关闭，线程池再执行任务就会报错
	 * @param runnable
	 */
	public void shutdowenExecutor2(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.shutdown();
		executor.execute(runnable);
		System.out.println("main end");
	}
	
	/**
	 * shutdownNow()的测试 isInterrupted()判断是否中断线程，并抛出异常，
	 * 若果没有抛出异常，程序将一直运行下去
	 * 返回List<Runnable> runList 为运行的线程
	 */
	public void shutdownNowExecutor(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		Runnable runnable = new Runnable() {	
			@Override
			public void run() {
				
				try {
					
//					for(int i=0;i<Integer.MAX_VALUE / 50;i++){
//						String newString = new String();
//						Math.random();
//						Math.random();
//						Math.random();
//						Math.random();
//						Math.random();
//						Math.random();
//						if(Thread.currentThread().isInterrupted() == true){
//							System.out.println("任务没有完成，就中断了");
////							throw new InterruptedException(); //抛出异常
//						}
//						
//					}			
					
					System.out.println("任务完成！");
				} catch (Exception e) {            //捕获被抛出的异常
					System.out.println("进入catch中断了任务");
					e.printStackTrace();					
				}
				
			}
		};
		try {
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);		
//		Thread.sleep(1000);
		List<Runnable> runList = executor.shutdownNow();	
		System.out.println("未执行的线程数量："+runList.size());
		System.out.println("main end");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * shutdownNow()的测试 线程中没有if判断程序是否中断的判断，在执行的还是执行完毕了
	 * 
	 * 返回List<Runnable> runList 为运行的线程
	 */
	public void shutdownNowExecutor2(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		Runnable runnable = new Runnable() {	
			@Override
			public void run() {
					for(int i=0;i<Integer.MAX_VALUE / 1000;i++){
						Math.random();
						Math.random();
						Math.random();
						Math.random();
						Math.random();
						Math.random();						
					}
					System.out.println("任务完成！");			
				
			}
		};
		try {
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);		
		Thread.sleep(1000);
		List<Runnable> runList = executor.shutdownNow();	
		System.out.println("main end");
		System.out.println("未执行的线程数量："+runList.size());		

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ThreadPoolExecutor.isShutdown()  方法的测试
	 * @param runnable
	 */
	public void isShutdownExecutor(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		executor.execute(runnable);
		executor.shutdown();
		System.out.println("线程池是否被关闭："+executor.isShutdown());
		System.out.println("main end");
	}
	
	/**
	 * isTerminating() 是否正在停止 
	 * isTerminated()  已经停止方法的测试
	 */
	public void isTerminatingExecutor(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		Runnable runnable = new Runnable() {	
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName()+":运行开始");
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName()+":运行完毕");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};
		try {
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);		
		System.out.println(executor.isTerminating() + " "+ executor.isTerminated());
		executor.shutdown();
		Thread.sleep(1000);
		System.out.println(executor.isTerminating() + " "+ executor.isTerminated());		
		Thread.sleep(1000);
		System.out.println(executor.isTerminating() + " "+ executor.isTerminated());
		Thread.sleep(1000);
		System.out.println(executor.isTerminating() + " "+ executor.isTerminated());
		Thread.sleep(1000);
		System.out.println(executor.isTerminating() + " "+ executor.isTerminated());
		
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * awaitTermination(long timeout, TimeUnit unit)
	 * 在单位时间内查看线程池是否已经停止工作，此方法会阻塞，直到时间到了或者线程池工作结束
	 * @param runnable
	 */
	public void awaitTerminationExecutor(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		try {
		System.out.println(System.currentTimeMillis()+" 系统时间");	
		executor.execute(runnable);		
		executor.shutdown();		
		System.out.println(System.currentTimeMillis()+" 开始时间");	
		System.out.println(" 在指定的时间内线程池时候停止工作："+executor.awaitTermination(5, TimeUnit.SECONDS));		
		System.out.println(System.currentTimeMillis()+" 结束时间");	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
