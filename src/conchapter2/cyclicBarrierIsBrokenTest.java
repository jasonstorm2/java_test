package conchapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CyclicBarrier 类 
 * isBroken() 查询屏蔽是否处于损坏状态
 * reset() 重置屏蔽 ,当有线程在等待时，便会报错
 * getParties() 得到CyclicBarrier限制线程的数量
 * getNumberWaiting() 得到已经到大屏蔽点的线程的数量
 * 
 * 若有某一个线程遇到异常报错退出，其他线程继续等待
 * 若有某一个线程中途中断interrupt(),其他线程都退出
 * 若有某一个线程中抛出异常,其他线程都退出
 * @author LiZhenhua
 *
 */
public class cyclicBarrierIsBrokenTest {
	public static void main(String[] args){
		int parties = 4;
		CyclicBarrier cyc = new CyclicBarrier(parties, new Runnable() {	
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("都到了");				
			}
		});
//		cyclicBarrierIsBrokenTest thisClass = new cyclicBarrierIsBrokenTest();
//		Service service = thisClass.new Service(cyc);
//		Thread1[] threadArray = new Thread1[3];
//		for (int i = 0; i < threadArray.length; i++) {
//			threadArray[i] =  thisClass.new Thread1(service);
//		}
//		for (int i = 0; i < threadArray.length; i++) {
//			threadArray[i].start();
//		}
		System.out.println("CyclicBarrier要限制的线程数："+cyc.getParties());
		
		testReset(cyc);
	}	

	class Service{
		private CyclicBarrier cyc;
		public Service(CyclicBarrier cyc){
			this.cyc = cyc;
		}		
		public void beginRun(int count){
			try {
				System.out.println(Thread.currentThread().getName() + "准备" + System.currentTimeMillis());
				if(Thread.currentThread().getName().equals("Thread-2")){
					System.out.println("Thread-2进来了");
					Thread.sleep(3000);
					/* 一个线程出现异常报错，其他线程继续等待，如果等待不到就一直一直等。。。*/					
//					Integer.parseInt("a");
					/*   */
//					Thread.currentThread().interrupt();
				}
				
				System.out.println("当前到大屏蔽点的线程有："+cyc.getNumberWaiting());
				System.out.println("当前取得parties个数的线程："+cyc.getParties());
				//等待5秒后，没有permits个线程，则报超时异常，所有的线程退出
				if(Thread.currentThread().getName().equals("Thread-3")){
					cyc.await(5,TimeUnit.SECONDS);
				}				
				
				/* interrupt()方法调用后 调用sleep会报错 */ 
//				Thread.sleep((int)(Math.random()*100));
//				System.out.println("等待线程数："+cyc.getNumberWaiting());
				cyc.await();
				System.out.println(Thread.currentThread().getName() + "结束" +count);			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("进入了InterruptedException,IsBroken="+cyc.isBroken());
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				System.out.println("进入了BrokenBarrierException,IsBroken="+cyc.isBroken());				
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void testA(){
			for (int i = 0; i < 1; i++) {
				beginRun(i+1);
			}
		}
		
	}
	class Thread1 extends Thread{
		private Service service;
		public Thread1(Service service){
			super();
			this.service = service;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();			
			service.testA();
		}
		
	}
	
	/**
	 * 测试reset方法
	 * @param cyc
	 */
	public static void testReset(CyclicBarrier cyc){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("当前线程："+Thread.currentThread().getName());
					cyc.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		try {
			System.out.println("当前线程："+Thread.currentThread().getName());
			System.out.println("当前到大屏蔽点的线程有："+cyc.getNumberWaiting());
			
			System.out.println("当前到大屏蔽点的线程有："+cyc.getNumberWaiting());
			Thread.sleep(2000);
			cyc.reset();	
			cyc.await();
			System.out.println("重置");
			System.out.println("当前到大屏蔽点的线程有："+cyc.getNumberWaiting());
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
