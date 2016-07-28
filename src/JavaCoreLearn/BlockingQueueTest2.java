package JavaCoreLearn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest2 {
	public static void main(String[] args) {
		//创建一个容量为１的　BlockingQueue
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
		new Producer(bq).start();
//		new Producer(bq).start();
//		new Producer(bq).start();

		Thread tt = new Consumer(bq);
		tt.start();
		System.out.println("线程组："+tt.getThreadGroup());
		System.out.println("活跃的线程："+tt.getThreadGroup().activeCount());
//		tt.getThreadGroup().interrupt();		
		
	}
}

class Producer extends Thread {
	private BlockingQueue<String> bq;

	public Producer(BlockingQueue<String> bq) {
		this.bq = bq;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String[] str = new String[] { "ja", "va", "java" };
		for (int i = 0; i < 20; i++) {
			System.out.println(getName() + "生产者准备生产集合元素！");
			try {
//				Thread.sleep(200);
				bq.put(str[i % 3]);
				System.out.println(getName() + "%%%%%%%%%%%%%%%%%%%：" + str[i % 3]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}

class Consumer extends Thread {
	private BlockingQueue<String> bq;

	public Consumer(BlockingQueue<String> bq) {
		this.bq = bq;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		while (true) {
			System.out.println(getName() + "消费者准备消费元素集合！");
			try {
//				Thread.sleep(200);
				String took = bq.take();
				System.out.println(getName() + "****************************：" + took);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(getName() + "消费完成：" + bq);
		}
	}
}
