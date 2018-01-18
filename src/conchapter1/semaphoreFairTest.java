package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore类的测试
 * 此类是用来限制线程并发的数量的
 * 本例使用的构造函数:Semaphore(int permits, boolean fair)
 * 
 * 本例试验公平与非公平信号量：即 线程启动顺序与调用 acquire()的顺序的关系
 * fair true 时线程启动顺序与获取许可执行代码块顺序一致  --  不代表100%，仅仅是概率上得到保证
 * fair false时线程启动顺序与获取许可执行代码块顺序不一致
 * 
 * 
 * @author Administrator
 *
 */
public class semaphoreFairTest {
	public static void main(String[] args) {
		semaphoreFairTest test1 =  new semaphoreFairTest();
		Service service =  test1.new Service();
		Thread1 th = test1.new Thread1(service);
		th.start();
		Thread1[] a = new Thread1[5];
		for(int i=0;i<a.length;i++){
			a[i] = test1.new Thread1(service);
			a[i].start();
		}		
	}
	
	/**
	 * Semaphore(int permits, boolean fair)
	 * fair 的两种状态导致的结果
	 * @author Administrator
	 *
	 */
	class Service{
//		private boolean isFair = false;
		private boolean isFair = true;
		
		private Semaphore semaphore = new Semaphore(1,isFair);		
		public void testMethod(){
			try{
				semaphore.acquire();
				System.out.println("取得许可，开始执行代码块。线程名字："+Thread.currentThread().getName());				
				
			}catch(InterruptedException e){
				e.printStackTrace();				
			}finally{
				semaphore.release();
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
			System.out.println("线程名："+this.getName()+"启动了");
			service.testMethod();			
		}
		
	}

}
