package conchapter1;

import java.util.concurrent.Semaphore;


/**
 * Semaphore类的测试
 * 此类是用来限制线程并发的数量的
 * 本例使用的类有:Semaphore
 * 本例使用的方法有：Semaphore的acquire(int permits)，release(int permits)，availablePermits()，drainPermits()
 * 
 * 此类证明Semaphore(int permits)只是定义了初始的许可数量为int permits个，可以通过acquire较少和release增加
 * @author Administrator
 *
 */
public class semaphoreTest3 {
	/**
	 * availablePermits() 返回此Semaphore对象中当前可用的许可书
	 * drainPermits() 获取并返回立即可用的所有许可个数，将许可置为0
	 * @param args
	 */
	
	public static void main(String[] args){
		try{
		Semaphore semaphore = new Semaphore(5);
		System.out.println(semaphore.availablePermits());
		semaphore.acquire();
		System.out.println(semaphore.availablePermits());
		semaphore.acquire(2);
		semaphore.acquire();
		semaphore.acquire();
		System.out.println(semaphore.availablePermits());
		semaphore.release(10);
		System.out.println(semaphore.availablePermits());
		semaphore.drainPermits();
		System.out.println(semaphore.availablePermits());
		
		}catch(InterruptedException e){
			e.printStackTrace();
			System.out.println("错误");
		}		
	}
}
