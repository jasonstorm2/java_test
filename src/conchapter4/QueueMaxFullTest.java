package conchapter4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池中，三种队列最大能处理的任务数，以及超出最大数后的表现
 * LinkedBlockingDeque
 * ArrayBlockingQueue
 * SynchronousQueue
 * 
 * @author LiZhenhua
 *
 */
public class QueueMaxFullTest {
	
	public static void main(String[] args) {
		QueueMaxFullTest thisClass = new 
				QueueMaxFullTest();	
//		thisClass.LinkedBlockingDequeTest();
//		thisClass.ArrayBlockingQueueTest();		
		thisClass.SynchronousQueueTest();
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
	 * 
	 * LinkedBlockingDeque 超出容量的情况
	 * 
	 * 若队列使用有参构造，max值被参考
	 * 线程池最大的容量是：max容量3+队列容量2 =5；当继续添加任务后，多余的任务将会报错
	 * 
	 * 若使用无参结构，队列的容量是无限的。每次执行任务，当前线程是core线程，其余的在队列等待执行
	 * @param runnable
	 */
	public void LinkedBlockingDequeTest(){		
		MyRunnable myRunnable = new MyRunnable("A1");
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	
//		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>();		

		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked);
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(myRunnable);//1
		executor.execute(myRunnable);//2
		executor.execute(myRunnable);//3
		executor.execute(myRunnable);//4
		executor.execute(myRunnable);//5
//		executor.execute(myRunnable);//6	
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
	}
	
	
	/**
	 * ArrayBlockingQueue<Runnable>队列，只有有参数的构造器，没有无参数的构造器
	 * 
	 * 当要执行的线程数量大于 队列大小+ max线程时 ，多余的任务无法执行，会报错
	 * 
	 */
	public void ArrayBlockingQueueTest(){		
		MyRunnable myRunnable = new MyRunnable("A1");
//		ArrayBlockingQueue<Runnable> linked = new ArrayBlockingQueue<Runnable>(2);	
		ArrayBlockingQueue<Runnable> linked = new ArrayBlockingQueue<Runnable>(1);		

		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked);
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(myRunnable);//1
		executor.execute(myRunnable);//2
		executor.execute(myRunnable);//3
		executor.execute(myRunnable);//4
		executor.execute(myRunnable);//5
//		executor.execute(myRunnable);//6	
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
	}
	
	
	/**
	 * 
	 * SynchronousQueue<Runnable> 类只有无参数的构造器
	 * 线程池能处理的最大线程数是 max,一旦超出就会报错
	 * 
	 */
	public void SynchronousQueueTest(){
		MyRunnable myRunnable = new MyRunnable("A1");
		SynchronousQueue<Runnable> linked = new SynchronousQueue<Runnable>();	

		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked);
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(myRunnable);//1
		executor.execute(myRunnable);//2
		executor.execute(myRunnable);//3
		executor.execute(myRunnable);//4
		executor.execute(myRunnable);//5
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
	}

}
