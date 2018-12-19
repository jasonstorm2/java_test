package conchapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 接口CompletionService的介绍
 * 
 * CompletionService以异步形式一边生产新的任务，一边处理已将完成的任务 
 * 使用submit提交任务，使用take取得已完成的任务
 * 
 * CompletionService接口只有一个实现类ExecutorCompletionService><V v>(Executor executor)
 * 
 * CompletionService对象submit任务后，有任务完成后就先返回，不会阻塞。
 * 
 * 使用take获取Future对象
 * 谁先执行完，take就先取得谁的Future,如果没有完成的，则先等待。。还是会有一点阻塞,但比Futrue的get好一点
 * 
 * 使用poll()方法，具有无阻塞的效果。如果有，则Future对象返回，没有则返回null
 * 
 *  poll(long timeout,TimeUnit unit)  :等待指定的timeout时间，在该时间内获取到值时，立刻向下运行。如果超时也立刻向下执行
 * 
 * @author LiZhenhua
 *
 */
public class CompletionServiceTest {
	
	public static void main(String[] args) {
		CompletionServiceTest thisClass = new CompletionServiceTest();
		thisClass.takeTest();
//		thisClass.pollTest();
//		thisClass.pollTest2();
		
		
	}
	
	class MyCallable implements Callable<String>{
		
		private String name;
		private int times;
		
		public MyCallable(String name,int times){
			this.name = name;
			this.times = times;
		}

		@Override
		public String call() {
			// TODO Auto-generated method stub
			System.out.println(System.currentTimeMillis()+" "+name+"正在运行");	
			try {
				Thread.sleep(times);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis()+" "+name+"运行完毕");
			return name;			
		}
		
	}
	
	/**
	 * 开始五个线程任务，
	 * 使用take方法，会阻塞直到有一个线程完成任务，该任务的Future对象，如果没有则一直阻塞
	 */
	public void takeTest(){
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable a = new MyCallable("A",5000);
		MyCallable b = new MyCallable("B",4000);
		MyCallable c = new MyCallable("C",3000);
		MyCallable d = new MyCallable("D",2000);
		MyCallable e = new MyCallable("E",1000);
		List<Future<String>> fuList = new ArrayList<Future<String>>();
		System.out.println("开始时间："+System.currentTimeMillis());
		try {
			fuList.add(csRef.submit(a));// 捕获异常
			fuList.add(csRef.submit(b));// 捕获异常
			fuList.add(csRef.submit(c));// 捕获异常
			fuList.add(csRef.submit(d));// 捕获异常
			fuList.add(csRef.submit(e));// 捕获异常
			
			for (int i = 0; i < 6; i++) {
				System.out.println("等待打印第"+(i+1)+"个值：");
				System.out.println("名字："+csRef.take()+"  时间："+System.currentTimeMillis());
				System.out.println("list的size："+fuList.size());
			}
//			for (int i = 0; i < 6; i++) {
//				System.out.println("等待打印第"+(i+1)+"个值：");
//				System.out.println("名字："+csRef.take().get()+"  时间："+System.currentTimeMillis());
//				System.out.println("list的size："+fuList.size());
//			}
			System.out.println("执行完毕！！"+System.currentTimeMillis());

		} catch (Exception e1) {
			// TODO: handle exception
			System.out.println("有异常抛出");
			e1.printStackTrace();
		}
	}
	
	
	/**
	 * 开始五个线程任务
	 * 使用poll方法，如果有future对象返回，如果没有直接返回null，不会阻塞
	 */
	public void pollTest(){
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable a = new MyCallable("A",5000);
		MyCallable b = new MyCallable("B",4000);
		MyCallable c = new MyCallable("C",3000);
		MyCallable d = new MyCallable("D",2000);
		MyCallable e = new MyCallable("E",1000);
		List<Future<String>> fuList = new ArrayList<Future<String>>();
		System.out.println("开始时间："+System.currentTimeMillis());
		try {
			fuList.add(csRef.submit(a));// 捕获异常
			fuList.add(csRef.submit(b));// 捕获异常
			fuList.add(csRef.submit(c));// 捕获异常
			fuList.add(csRef.submit(d));// 捕获异常
			fuList.add(csRef.submit(e));// 捕获异常
			
			for (int i = 0; i < 6; i++) {
				System.out.println("等待打印第"+(i+1)+"个值：");
				System.out.println("poll结果："+csRef.poll());
				System.out.println("时间"+System.currentTimeMillis());
				System.out.println("list的size："+fuList.size());
			}
			System.out.println("执行完毕！！"+System.currentTimeMillis());

		} catch (Exception e1) {
			// TODO: handle exception
			System.out.println("有异常抛出");
			e1.printStackTrace();
		}
	}
	
	/**
	 * poll(long timeout,TimeUnit unit)测试
	 */
	public void pollTest2(){
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable a = new MyCallable("A",5000);
		MyCallable b = new MyCallable("B",4000);
//		MyCallable c = new MyCallable("C",3000);
//		MyCallable d = new MyCallable("D",2000);
//		MyCallable e = new MyCallable("E",1000);
		List<Future<String>> fuList = new ArrayList<Future<String>>();
		System.out.println("开始时间："+System.currentTimeMillis());
		try {
			fuList.add(csRef.submit(a));// 捕获异常
			fuList.add(csRef.submit(b));// 捕获异常
//			fuList.add(csRef.submit(c));// 捕获异常
//			fuList.add(csRef.submit(d));// 捕获异常
//			fuList.add(csRef.submit(e));// 捕获异常
			
			for (int i = 0; i < 3; i++) {
				System.out.println("等待打印第"+(i+1)+"个值：");
				System.out.println("poll结果："+csRef.poll(3,TimeUnit.SECONDS));
				System.out.println("时间"+System.currentTimeMillis());
				System.out.println("list的size："+fuList.size());
			}
			System.out.println("执行完毕！！"+System.currentTimeMillis());

		} catch (Exception e1) {
			// TODO: handle exception
			System.out.println("有异常抛出");
			e1.printStackTrace();
		}
	}

}
