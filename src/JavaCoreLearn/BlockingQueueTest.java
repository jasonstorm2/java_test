package JavaCoreLearn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) throws InterruptedException {
		//定义一个长度为2的阻塞队列
		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(2);
		bq.put("java");//bq.add(),bq.offer()相同
		bq.put("jaja");
		System.out.println("1");
//		bq.put("vava");//阻塞线程
//		bq.add("vava");//抛出异常，队列已满
		bq.offer("vava");//返回false,元素不会放入
		System.out.println("2");

	}

}
