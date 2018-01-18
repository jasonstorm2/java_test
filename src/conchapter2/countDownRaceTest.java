package conchapter2;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch类的使用
 * @author Administrator
 * 
 * 
 * 多个线程 通过同时操作相同的多个CountDownLatch对象，来模拟一场跑步比赛
 * 
 * 可见，CountDownLatch 在不同的线程中具有原子性！！
 *
 */
public class countDownRaceTest {
	public static void main(String[] args) throws InterruptedException {
		countDownRaceTest thisClass = new countDownRaceTest();
		CountDownLatch comingTag = new CountDownLatch(10);
		CountDownLatch waitTag = new CountDownLatch(1);
		CountDownLatch waitRunTag = new CountDownLatch(10);
		CountDownLatch begingTag = new CountDownLatch(1);
		CountDownLatch endTag = new CountDownLatch(10);
		
		Thread1[] threadArray = new Thread1[10];
		for(int i=0;i<threadArray.length;i++){
			threadArray[i] = thisClass.new Thread1(comingTag, waitTag, waitRunTag, begingTag, endTag);
			threadArray[i].start();
		}
		
		System.out.println("裁判员等待选手的到来");
		comingTag.await();
		System.out.println("运动员到齐，做准备中");
		Thread.sleep(2000);
		waitTag.countDown();
		System.out.println("各就各位");
		waitRunTag.await();
		Thread.sleep(2000);
		System.out.println("枪声响起");
		begingTag.countDown();
		endTag.await();
		System.out.println("所有运动员到达，统计比赛名次");
	}	

	
	class Thread1 extends Thread{
		private CountDownLatch comingTag;   //裁判等待运动员集合
		private CountDownLatch waitTag;     //等待裁判说准备开始
		private CountDownLatch waitRunTag;  //等待起跑
		private CountDownLatch begingTag;   //开始起跑
		private CountDownLatch endTag;      //所有运动员到大终点
		public Thread1(CountDownLatch comingTag,CountDownLatch waitTag,CountDownLatch waitRunTag,CountDownLatch begingTag,CountDownLatch endTag){
			super();
			this.comingTag = comingTag;
			this.waitTag = waitTag;
			this.waitRunTag = waitRunTag;
			this.begingTag = begingTag;
			this.endTag = endTag;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();			
			try {
				System.out.println("运动员正在集结中。。。");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "到起跑点了");
				comingTag.countDown();
				System.out.println("等待裁判说准备");
				waitTag.await();
				System.out.println("各就各位准备起跑");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "运动员做好准备起跑动作");
				waitRunTag.countDown();				
				begingTag.await();
				System.out.println(Thread.currentThread().getName() + "起跑，跑步中。。。。");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "运动员达到终点");
				endTag.countDown();								
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
