package conchapter4;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 
 * 线程池中的资源全部被占用时，四种对新添加任务的策略
 * 
 * 1:AbortPolicy  线程池满时，有新任务添加时，抛出异常RejectedExecutionException
 * 
 * 2:CallerRunsPolicy 线程池满时，使调用线程池的线程执行该任务，此处是main线程执行任务,main会阻塞
 * 
 * 3.DiscardOldestPolicy 抛弃最旧的任务，添加新任务
 * 
 * 4.DiscardPolicy 当任务添加到线程池中被拒绝时,把被拒绝的任务被抛弃
 * 
 * 
 * @author LiZhenhua
 *
 */
public class ForRefusePolicyTest {
	
	public static void main(String[] args) {
		ForRefusePolicyTest thisClass = new 
				ForRefusePolicyTest();	
//		thisClass.AbortPolicyTest();
//		thisClass.CallerRunsPolicyTest();		
//		thisClass.DiscardOldestPolicyTest();
		thisClass.DiscardPolicyTest();
		
		
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
	 * 
	 * LinkedBlockingDeque 超出容量的情况
	 * @param runnable
	 */
	public void AbortPolicyTest(){		
		MyRunnable myRunnable = new MyRunnable("A1");
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked,new ThreadPoolExecutor.AbortPolicy());
		
		
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(myRunnable);//1
		executor.execute(myRunnable);//2
		executor.execute(myRunnable);//3
		executor.execute(myRunnable);//4
		executor.execute(myRunnable);//5
		executor.execute(myRunnable);//6	
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
	}
	
	
	/**
	 * 
	 * 策略：调用线程池的线程执行多余的任务
	 * 
	 * 一次能运行的线程数 ： 线程池最大线程数 max + 调用线程池的线程1  =  max +1 
	 * 
	 */
	public void CallerRunsPolicyTest(){		
		MyRunnable myRunnable = new MyRunnable("A1");
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked,new ThreadPoolExecutor.CallerRunsPolicy());
		
		
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(myRunnable);//1
		executor.execute(myRunnable);//2
		executor.execute(myRunnable);//3
		executor.execute(myRunnable);//4
		executor.execute(myRunnable);//5
		executor.execute(myRunnable);//6	
		executor.execute(myRunnable);//7
		executor.execute(myRunnable);//8
		executor.execute(myRunnable);//9
		executor.execute(myRunnable);//10
		executor.execute(myRunnable);//11
		executor.execute(myRunnable);//12
		
		
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
	}
	
	
	/**
	 * DiscardOldestPolicy策略：当任务添加到线程池中被拒绝时，线程池会放弃等待队列中，
	 *                          最旧的未处理任务，把被拒绝的任务添加进等待队列
	 */
	public void DiscardOldestPolicyTest(){		
		MyRunnable a1 = new MyRunnable("A1");
		MyRunnable a2 = new MyRunnable("A2");
		MyRunnable a3 = new MyRunnable("A3");
		MyRunnable a4 = new MyRunnable("A4");
		MyRunnable a5 = new MyRunnable("A5");
		MyRunnable a6 = new MyRunnable("A6");
		MyRunnable a7 = new MyRunnable("A7");
		
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked,new ThreadPoolExecutor.DiscardOldestPolicy());		
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(a1);//1
		executor.execute(a2);//2
		executor.execute(a3);//3	
		executor.execute(a4);//4	
		executor.execute(a5);//4	
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
		Iterator<Runnable> iterator = linked.iterator();
		while(iterator.hasNext()){
			Object obj = iterator.next();
			System.out.println("队列里的名字："+((MyRunnable)obj).getUsername());
			 
		}		
		executor.execute(a6);//6
		executor.execute(a7);//6
		
		System.out.println("------------------");
		iterator = linked.iterator();
		while(iterator.hasNext()){
			Object obj = iterator.next();
			System.out.println("队列里的名字："+((MyRunnable)obj).getUsername());
			 
		}
		
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
	}
	
	
	
	/**
	 * DiscardPolicy策略：当任务添加到线程池中被拒绝时,把被拒绝的任务被抛弃
	 */
	public void DiscardPolicyTest(){		
		MyRunnable a1 = new MyRunnable("A1");
		MyRunnable a2 = new MyRunnable("A2");
		MyRunnable a3 = new MyRunnable("A3");
		MyRunnable a4 = new MyRunnable("A4");
		MyRunnable a5 = new MyRunnable("A5");
		MyRunnable a6 = new MyRunnable("A6");
		MyRunnable a7 = new MyRunnable("A7");
		
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked,new ThreadPoolExecutor.DiscardPolicy());		
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(a1);//1
		executor.execute(a2);//2
		executor.execute(a3);//3	
		executor.execute(a4);//4	
		executor.execute(a5);//4	
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
		Iterator<Runnable> iterator = linked.iterator();
		while(iterator.hasNext()){
			Object obj = iterator.next();
			System.out.println("队列里的名字："+((MyRunnable)obj).getUsername());
			 
		}		
		executor.execute(a6);//6
		executor.execute(a7);//6
		
		System.out.println("------------------");
		iterator = linked.iterator();
		while(iterator.hasNext()){
			Object obj = iterator.next();
			System.out.println("队列里的名字："+((MyRunnable)obj).getUsername());
			 
		}
		
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
	}

}
