package my;

import java.util.concurrent.LinkedBlockingQueue;

import utils.utils;

/**
 * LinkedBlockingQueue 测试
 * @author LiZhenhua
 * 
 * java.util.concurrent包下的新类。
 * LinkedBlockingQueue就是其中之一，顾名思义这是一个阻塞的线程安全的队列，底层应该采用链表实现
 * 
 * LinkedBlockingQueue构造的时候若没有指定大小，则默认大小为Integer.MAX_VALUE，当然也可以在构造函数的参数中指定大小。
 * LinkedBlockingQueue不接受null
 * 
 * put方法，在队尾添加，若向队尾添加元素的时候发现队列已经满了会发生阻塞一直等待空间，以加入元素
 * add方法，在添加元素的时候，若超出了度列的长度会直接抛出异常：
 * offer方法，在队尾添加，在添加元素时，如果发现队列已满无法添加的话，会直接返回false。
 * 
 * poll 取出队头的第一个元素，没有则返回null
 *
 */
public class LinkedBlockingQueueTest {
	public static void main(String[] args) throws Exception {
		 LinkedBlockingQueue<String> queue= new LinkedBlockingQueue<String>(2);        
		 new Thread(){
			 @Override
			public void run() {
				// TODO Auto-generated method stub
				 System.out.println("子线程内 queue 的 大小："+queue.size());
				while(true){
					if(queue.size()!=0){
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						utils.PrintLine("poll前"+queue.toString());
						String str = queue.poll();
						utils.PrintLine("poll后"+queue.toString());
					}else{
						break;
					}
				}
			 }
		 }.start();
		 queue.add("hi");  
		 queue.add("haa");  
         queue.put("hello");  
         System.out.println("打印队列："+queue);
         queue.put("world");  
         System.out.println("打印队列2："+queue);
         queue.put("yes");     
         // 主线程阻塞，直到子线程取出队列的第一个元素
         System.out.println("yes"); 
         boolean bol3=queue.offer("返回值");  
         System.out.println("offer返回值:"+bol3);
        
	}

}
