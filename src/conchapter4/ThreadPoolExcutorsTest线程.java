package conchapter4;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 介绍ThreadPoolExecutor线程池的各个参数的作用
 * 注意 ThreadPoolExecutor 线程池 与 三种使用静态方法生成的ExecutorService 线程池 的区别：
 * 
 *   ThreadPoolExecutor类使用上并不是那么方便，在实例化时需要传入很多个参数，
 *   还要考虑线程的并发数等于线程池运行效率有关的参数，所以官方建议使用Executors工厂类来创建线程池对象
 *   
 *   使用Executors工厂类来创建线程池对象，虽然方便，但细节未知，通过查看Executors工厂类源代码，其实内部是实例化了一个ThreadPoolExecutor类的实例
 * 
 * ThreadPoolExecutor 是AbstractExecutorService的子类，
 * AbstractExecutorService是 ExecutorService接口的具体实现类，
 * ExecutorService接口的父接口是Executor
 * 
 * ThreadPoolExecutor.ThreadPoolExecutor(
 *    int corePoolSize,    标准线程数量
 *    int maximumPoolSize, 最大线程数量
 *    long keepAliveTime,  空闲线程存活时间
 * 	  TimeUnit unit,       时间单位
 *    BlockingQueue<Runnable> workQueue)  采用的队列
 *    
 *    采用的队列有两种，一种是LinkedBlockingQueue，一种是SynchronousQueue
 *    不同的队列，各个参数的作用不同
 * 
 * 方法各个参数的作用
 * @author Administrator
 *
 */
public class ThreadPoolExcutorsTest线程 {
	public static void main(String[] args) {
		ThreadPoolExcutorsTest线程 thisClass = new ThreadPoolExcutorsTest线程();		
		Runnable runnable = new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				try {
					System.out.println(Thread.currentThread().getName() +" run "+System.currentTimeMillis());
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() +" over "+System.currentTimeMillis());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};	
//		thisClass.getBaseMessage();
//		thisClass.addToBlockQueuetest(runnable);
//		thisClass.addToSynchronousQueueTest(runnable);
		thisClass.addToSynchronousQueueTest2(runnable);
		
	}	
	
	/**
	 * 若  线程池没有执行一条线程，只是实例化，那么线程池中没有线程，主线程运行完毕程序就结束了
	 * 若  线程池运行了哪怕一条线程，主线程运行完毕，线程池的线程还在运行，或等待执行任务，按钮呈红色，程序还在继续运行
	 */
	public void getBaseMessage(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("运行一条线程");
			}
		});
		
		System.out.println(System.currentTimeMillis()+"核心线程数："+executor.getCorePoolSize());
		System.out.println(System.currentTimeMillis()+"最大线程数："+executor.getMaximumPoolSize());
		System.out.println(System.currentTimeMillis()+"正在运行线程数"+executor.getPoolSize());
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()+"正在运行线程数"+executor.getPoolSize());		
		executor.shutdown();
//		executor.shutdownNow();
		System.out.println("线程池是否被关闭了："+executor.isShutdown());
		System.out.println(System.currentTimeMillis()+"正在运行线程数"+executor.getPoolSize());
	}
	
	/**
	 *如果 要执行的线程 大于 标准线程数corePoolSize
	 *      线程池立马运行这些线程,把多出来的线程放入link队列中
	 *      
	 * maximumPoolSize，和 线程存活时间 这两个参数没有意义
	 * 
	 * 按钮呈红色，因为线程池中海油线程在等待任务，没有被杀死
	 * @param runnable
	 */
	public void addToBlockQueuetest(Runnable runnable){
		
		try {
			ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
			executor.execute(runnable);//1
			executor.execute(runnable);//2
			executor.execute(runnable);//3
			executor.execute(runnable);//4
			executor.execute(runnable);//5
			executor.execute(runnable);//6
			executor.execute(runnable);//7
			executor.execute(runnable);//8
			executor.execute(runnable);//9
			executor.execute(runnable);//10

			
			Thread.sleep(300);
			System.out.println("A"+executor.getCorePoolSize());
			System.out.println("A"+executor.getPoolSize());
			System.out.println("A"+executor.getQueue().size());
			Thread.sleep(10000);
			System.out.println("B"+executor.getCorePoolSize());
			System.out.println("B"+executor.getPoolSize());
			System.out.println("B"+executor.getQueue().size());
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * 如果 要执行的线程 大于 标准线程数corePoolSize 且小于 最大线程数maximumPoolSize，
	 *      线程池立马运行这些线程,不把多出来的线程放入syn队列中，存活时间过后，多出来的线程即大于corePoolSize的线程--死亡
	 *      其他线程运行后一直等待处理新的task
	 *      
	 * 如果 要执行的线程 大于 最大线程数maximumPoolSize，处理maximumPoolSize的任务，其他抛出异常
	 * 
	 * 按钮呈红色，因为线程池中海油线程在等待任务，没有被杀死
	 * @param runnable
	 */
	public void addToSynchronousQueueTest(Runnable runnable){
		
		try {
			ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 9, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
			executor.execute(runnable);//1
			executor.execute(runnable);//2
			executor.execute(runnable);//3
			executor.execute(runnable);//4
			executor.execute(runnable);//5
			executor.execute(runnable);//6
			executor.execute(runnable);//7
			executor.execute(runnable);//8
			executor.execute(runnable);//9
			executor.execute(runnable);//10

			
			Thread.sleep(300);
			System.out.println("A"+executor.getCorePoolSize());
			System.out.println("A"+executor.getPoolSize());
			System.out.println("A"+executor.getQueue().size());
			Thread.sleep(10000);
			System.out.println("B"+executor.getCorePoolSize());
			System.out.println("B"+executor.getPoolSize());
			System.out.println("B"+executor.getQueue().size());
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * 
	 * 存活时间为0的情况
	 * 
	 * 如果 要执行的线程 大于 标准线程数corePoolSize 且小于 最大线程数maximumPoolSize，
	 *      线程池立马运行这些线程,不把多出来的线程放入syn队列中，存活时间为0，任务执行完毕后空闲线程--立刻死亡
	 *      
	 * 按钮呈红色，因为线程池中海油线程在等待任务，没有被杀死
	 * 
	 * @param runnable
	 */
	public void addToSynchronousQueueTest2(Runnable runnable){		
		
		try {
			ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 9, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//			ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 9, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
			
			executor.execute(runnable);//1
			executor.execute(runnable);//2
			executor.execute(runnable);//3
			executor.execute(runnable);//4
			executor.execute(runnable);//5
			executor.execute(runnable);//6
			executor.execute(runnable);//7
			executor.execute(runnable);//8

			
			Thread.sleep(300);
			System.out.println("A"+executor.getCorePoolSize());
			System.out.println("A"+executor.getPoolSize());
			System.out.println("A"+executor.getQueue().size());
			Thread.sleep(5000);
			System.out.println("B"+executor.getCorePoolSize());
			System.out.println("B"+executor.getPoolSize());
			System.out.println("B"+executor.getQueue().size());
			Thread.sleep(6000);
			System.out.println("C"+executor.getCorePoolSize());
			System.out.println("C"+executor.getPoolSize());
			System.out.println("C"+executor.getQueue().size());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
