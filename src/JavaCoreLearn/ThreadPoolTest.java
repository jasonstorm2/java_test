package JavaCoreLearn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
	public static void main(String[] args) {
		//创建有6个线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(6);
		
		Runnable target = ()->{
			for (int i = 0; i <200; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" 的 i 的值："+i);
			}
		};
		//向线程池中提交 两个线程
		pool.submit(target);
		pool.submit(target);
		//关闭线程池  虽然关闭了，提交的线程会直到运行完
		pool.shutdown();			
		
	}

}
