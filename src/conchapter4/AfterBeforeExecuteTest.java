package conchapter4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池处理完一条任务 前后的操作
 * 线程池 remove(runnable)操作
 * 
 * 继承ThreadPoolExecutor类，重写两个方法：afterExecute beforeExecute
 * 
 * @author LiZhenhua
 *
 */
public class AfterBeforeExecuteTest {
	
	public static void main(String[] args) {
		AfterBeforeExecuteTest thisClass = new 
				AfterBeforeExecuteTest();	
		thisClass.executeTest();
		
		
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
	 * 继承ThreadPoolExecutor，重写执行前和执行后两个方法
	 * 构造器必须重写
	 * @author LiZhenhua
	 *
	 */
	class  MyThreadPoolExecutor extends ThreadPoolExecutor{

		public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
				long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			// TODO Auto-generated method stub
			super.afterExecute(r, t);
			System.out.println("线程池处理任务完毕,任务名:"+((MyRunnable)r).username);
		}
		
		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			// TODO Auto-generated method stub
			super.beforeExecute(t, r);
			System.out.println("线程池开始处理一条任务,任务名:"+((MyRunnable)r).getUsername());
		}
		
	}


	/**
	 * 
	 * LinkedBlockingDeque 超出容量的情况
	 * @param runnable
	 */
	public void executeTest(){		
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	

		MyThreadPoolExecutor executor = new 
				MyThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked);		
		
		executor.allowCoreThreadTimeOut(true);		
		executor.execute(myRunnable1);//1
		executor.execute(myRunnable2);//2
		executor.execute(myRunnable3);//3
	
		System.out.println("线程池大小："+executor.getPoolSize()+" 队列的大小："+linked.size());
	}	


}
