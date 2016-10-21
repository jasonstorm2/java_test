package my;

import java.util.Date;

/**
 * join()等待线程终止，主线程才继续运行
 * 
 * join(x) 等待x时长，如果线程还没运行完，主线程继续运行
 * @author Administrator
 *
 */
public class JoinTest {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable1(), "r1");
		Thread t2 = new Thread(new Runnable2(), "r2");
		System.out.println("main start"+":"+new Date());
		t1.start();
		t2.start();
		
		try {
			//两个join：
			//从第一个join的时间算起
			
			
			t1.join();
			t2.join(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main over"+":"+new Date());
		
	}

}

class Runnable1 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("r1 beging"+":"+new Date());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("r1 over"+":"+new Date());
		
	}
	
}

class Runnable2 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("r2 beging"+":"+new Date());
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("r2 over"+":"+new Date());
		
	}
	
}

