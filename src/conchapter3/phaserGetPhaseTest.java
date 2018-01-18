package conchapter3;

import java.util.concurrent.Phaser;


/**
 * 
 * Phaser 类，Phaser(int parties) 定义parties个线程相互等待
 * 
 * getPhase() 获取线程所处的第几个屏蔽点
 * onAdvance() Phaser重写该方法，每次通过屏蔽点时被调动，返回true时，屏障被取消，返回false Phaser继续工作
 * @author Administrator
 *
 */
public class phaserGetPhaseTest {
	public static void main(String[] args){
		phaserGetPhaseTest thisClass = new phaserGetPhaseTest();
		Phaser phaser = new Phaser(2){
		@Override
		protected boolean onAdvance(int phase, int registeredParties) {
			// TODO Auto-generated method stub
			System.out.println("第"+phase+"阶段被调用"+",phaser注册线程数："+registeredParties);
			System.out.println("到达的线程数量："+getArrivedParties());
			if(phase == 3){
				System.out.println("返回true");
				return true;
			}else{
				System.out.println("返回false");
				return false;
			}
			
			
		}};
		Thread1 th1 = thisClass.new Thread1(phaser);
		th1.start();
		Thread1 th2= thisClass.new Thread1(phaser);
		th2.start();		
	}
	
	class Thread1 extends Thread{
		private Phaser phaser;
		public Thread1(Phaser phaser){
			super();
			this.phaser = phaser;
		}		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println("线程达到的阶段："+phaser.getPhase());
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			phaser.arriveAndAwaitAdvance();
			System.out.println("线程达到的阶段："+phaser.getPhase());
			phaser.arriveAndAwaitAdvance();
			System.out.println("线程达到的阶段："+phaser.getPhase());
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("true之后看看变化");
			phaser.arriveAndAwaitAdvance();
			System.out.println("线程达到的阶段："+phaser.getPhase());
			phaser.arriveAndAwaitAdvance();
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("线程达到的阶段："+phaser.getPhase());
			phaser.arriveAndAwaitAdvance();			
		}		
	}
}
