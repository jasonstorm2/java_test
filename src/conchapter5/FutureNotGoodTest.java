package conchapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 本类证明线程池的无序性，即执行的顺序跟开始的顺序不一定一致
 * 为了解决这个问题，第六章提出了解决方案
 * @author Administrator
 *
 */
public class FutureNotGoodTest {
	
	public static void main(String[] args) {
		FutureNotGoodTest thisClass = new FutureNotGoodTest();
		
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		
		MyCallable a = thisClass.new MyCallable("A",5000);
		MyCallable b = thisClass.new MyCallable("B",4000);
		MyCallable c = thisClass.new MyCallable("C",3000);
		MyCallable d = thisClass.new MyCallable("D",2000);
		MyCallable e = thisClass.new MyCallable("E",1000);
		List<Future<String>> fuList = new ArrayList<Future<String>>();
		System.out.println("开始时间："+System.currentTimeMillis());
		try {
			fuList.add(executor.submit(a));// 捕获异常
			fuList.add(executor.submit(b));// 捕获异常
			fuList.add(executor.submit(c));// 捕获异常
			fuList.add(executor.submit(d));// 捕获异常
			fuList.add(executor.submit(e));// 捕获异常
			
			for (int i = 0; i < fuList.size(); i++) {
				System.out.println("名字："+fuList.get(i).get());
				System.out.println("时间"+System.currentTimeMillis());
			}
			System.out.println("执行完毕！！"+System.currentTimeMillis());

		} catch (Exception e1) {
			// TODO: handle exception
			System.out.println("有异常抛出");
			e1.printStackTrace();
		}
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
			System.out.println(name+"正在运行");	
			try {
				Thread.sleep(times);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name+"运行完毕");
			return name;			
		}
		
	}

}
