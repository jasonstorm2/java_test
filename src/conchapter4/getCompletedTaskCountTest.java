package conchapter4;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 返回已经执行完毕的任务数量
 * @author Administrator
 *
 */
public class getCompletedTaskCountTest {
	
	public static void main(String[] args) {
		getCompletedTaskCountTest thisClass = new 
				getCompletedTaskCountTest();	
		thisClass.getCompletedTaskCount();
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
	 * @param runnable
	 */
	public void getCompletedTaskCount(){	

		try {
			MyRunnable myRunnable = new MyRunnable("A1");
			ThreadPoolExecutor executor = new 
					ThreadPoolExecutor(2, 5, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
			executor.allowCoreThreadTimeOut(true);
			
			executor.execute(myRunnable);
			executor.execute(myRunnable);
			executor.execute(myRunnable);
			executor.execute(myRunnable);
			executor.execute(myRunnable);
			System.out.println("当前线程池线程数量：" + executor.getPoolSize());		
			System.out.println("已经执行完毕的任务数量："+executor.getCompletedTaskCount());
			Thread.sleep(300);
			System.out.println("当前线程池线程数量：" + executor.getPoolSize());		
			System.out.println("已经执行完毕的任务数量："+executor.getCompletedTaskCount());
			Thread.sleep(1000);
			System.out.println("当前线程池线程数量：" + executor.getPoolSize());		
			System.out.println("已经执行完毕的任务数量："+executor.getCompletedTaskCount());
			Thread.sleep(4000);
			System.out.println("当前线程池线程数量：" + executor.getPoolSize());		
			System.out.println("已经执行完毕的任务数量："+executor.getCompletedTaskCount());
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
