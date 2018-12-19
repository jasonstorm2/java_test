package conchapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Semaphore类的测试
 * 
 * 字符池的设计
 * 
 * Semaphore信号量的控制，代码块的许可有几个，控制线程数量
 * ReentrantLock 同步锁，高并发下加此锁一次只能一个线程在处理，等到释放后等待的线程才能处理代码块
 * Condition 控制线程的等待和唤醒，若某线程获得锁，却陷入等待状态condition.await()，则其他线程可以把它的锁抢过来
 * @author LiZhenhua
 *
 */
public class semaphorePoolTest {
	public static void main(String[] args) throws InterruptedException {
		semaphorePoolTest thisClass = new semaphorePoolTest();
		ListPool pool = thisClass.new ListPool();
//		Thread1[] threadArray = new Thread1[12];
//		for (int i = 0; i < threadArray.length; i++) {
//			
//			threadArray[i] = thisClass.new Thread1(pool);
//		}
//		
//		for (int i = 0; i < threadArray.length; i++) {
//			threadArray[i].start();
//		}
		
		// 对同一个lock的争抢测试
		Thread1 thread1 =  thisClass.new Thread1(pool);
		Thread2 thread2 =  thisClass.new Thread2(pool);
		thread1.start();
		thread2.start();
		
	}
	
	/**
	 * Semaphore(int permits)
	 * 同一个时间只能有permits个线程同时执行acquire() 和 release()之间的代码
	 * 如果permits>1并不能保证线程安全，可能有脏数据
	 * 
	 * @author LiZhenhua
	 *
	 */
	class ListPool{
		private int size = 5;
		private int semaphorePermits = 3;
		private List<String> list = new ArrayList<String>();
		private Semaphore concurrencySemaphore = new Semaphore(semaphorePermits);
		private ReentrantLock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		
		public ListPool(){
//			super();
//			for(int i=0;i<size;i++){
//				list.add("jason"+(i+1));
//			}
		}
		
		public String get(){
			String getStr =  null;
			try {
				concurrencySemaphore.acquire();
				System.out.println(Thread.currentThread().getName()+"get准备获得锁");
				lock.lock();
				System.out.println(Thread.currentThread().getName()+"get获得锁");
				while(list.size() == 0){
					//线程陷入等待状态
					System.out.println("程序陷入等待状态");
					condition.await();
				}
				getStr = list.remove(0);
				for(int i=0;i<Integer.MAX_VALUE/20;i++){
					//随便用一个业务逻辑
					String str = new String();
					Math.random();					
				}
				System.out.println("------------------------------");
				lock.unlock();				
				System.out.println(Thread.currentThread().getName()+"get释放锁");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return getStr;
		}
		public void put(String value){
			System.out.println(Thread.currentThread().getName()+"put准备获得锁");
			lock.lock();
			System.out.println(Thread.currentThread().getName()+"put获得锁");
			list.add(value);
			//唤醒在此condition等待的所有线程
			condition.signalAll();
			for(int i=0;i<Integer.MAX_VALUE/20;i++){
				//随便用一个业务逻辑
				String str = new String();
				Math.random();					
			}
			System.out.println("------------------------------");
			lock.unlock();		
			System.out.println(Thread.currentThread().getName()+"put释放锁");			
			concurrencySemaphore.release();
		}
		
	}
	
	class Thread1 extends Thread{
		private ListPool listPool;
		public Thread1(ListPool listPool){
			super();
			this.listPool = listPool;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				String getString = listPool.get();
				System.out.println(Thread.currentThread().getName()+"取得值："+getString);
//				listPool.put(getString);
//				System.out.println(Thread.currentThread().getName()+"放入："+getString);
			}
					
		}
		
	}
	
	class Thread2 extends Thread{
		private ListPool listPool;
		public Thread2(ListPool listPool){
			super();
			this.listPool = listPool;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				listPool.put("数据");
				System.out.println(Thread.currentThread().getName()+"放入："+"数据");
			}
					
		}
		
	}

}
