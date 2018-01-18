package conchapter1;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * 测试两个线程间的通信
 * 使用Exchanger对象，如果两个线程都使用了同一个Exchanger对象，则两个线程可以互相通信
 * 
 * exchanger.exchange("Thread1")
 * exchanger.exchange(V value,long timeout,TimeUnit unit) 指定时间内没有其他线程通信，则出现异常
 * @author Administrator
 *
 */
public class ExchangeTest {
	
	public static void main(String[] args) {
		ExchangeTest thisClass = new ExchangeTest();
		Exchanger<String> exchanger =  new Exchanger<String>();
		Thread1 t1 = thisClass.new Thread1(exchanger);
		Thread2 t2 = thisClass.new Thread2(exchanger);
		t1.start();
		t2.start();		
		System.out.println("main end");
	}
	
	
	class Thread1 extends Thread{
		private Exchanger<String> exchanger;
		
		public Thread1(Exchanger<String> exchanger){
			this.exchanger = exchanger;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				System.out.println("Thread1 得到 其他线程的值="+ exchanger.exchange("Thread1"));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
	}
	
	
	class Thread2 extends Thread{
		private Exchanger<String> exchanger;
		
		public Thread2(Exchanger<String> exchanger){
			this.exchanger = exchanger;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				System.out.println("Thread2 得到 Thread1的值="+ exchanger.exchange("Thread2",3,TimeUnit.SECONDS));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				System.out.println("没有通信的线程。。。");
				e.printStackTrace();
			}		
			
		}
	}

}
