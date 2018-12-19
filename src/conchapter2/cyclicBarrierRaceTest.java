package conchapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch类的使用
 * @author LiZhenhua
 * 
 * 
 * 多个线程 通过同时操作相同的多个CountDownLatch对象，来模拟一场跑步比赛
 * 
 * 可见，CountDownLatch 在不同的线程中具有原子性！！
 *
 */
public class cyclicBarrierRaceTest {
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		cyclicBarrierRaceTest thisClass = new cyclicBarrierRaceTest();
		CyclicBarrier cyc =  new CyclicBarrier(11,new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		CountDownLatch waitTag = new CountDownLatch(1);
		CountDownLatch begingTag = new CountDownLatch(1);
		System.out.println("裁判员等待选手的到来");
		Thread1[] threadArray = new Thread1[10];
		for(int i=0;i<threadArray.length;i++){
			threadArray[i] = thisClass.new Thread1(cyc, waitTag, begingTag);
			threadArray[i].start();
		}
		cyc.await();
		
		
		System.out.println("运动员到齐，做准备中");
		Thread.sleep(2000);
		waitTag.countDown();
		System.out.println("各就各位");
		cyc.await();
		Thread.sleep(2000);
		System.out.println("枪声响起");
		begingTag.countDown();
		cyc.await();
		System.out.println("所有运动员到达，统计比赛名次");
	}	

	
	class Thread1 extends Thread{
		private CyclicBarrier cyc;
		private CountDownLatch waitTag;     //等待裁判说准备开始
		private CountDownLatch begingTag;   //开始起跑
		public Thread1(CyclicBarrier cyc,CountDownLatch waitTag,CountDownLatch begingTag){
			super();
			this.waitTag = waitTag;
			this.begingTag = begingTag;
			this.cyc = cyc;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();			
			try {
				System.out.println(Thread.currentThread().getName() +"运动员正在集结中。。。");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "到起跑点了");
				cyc.await();
				System.out.println("等待裁判说准备");
				waitTag.await();
				System.out.println("各就各位准备起跑");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "运动员做好准备起跑动作");
				cyc.await();			
				begingTag.await();
				System.out.println(Thread.currentThread().getName() + "起跑，跑步中。。。。");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "运动员达到终点");
				cyc.await();							
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
