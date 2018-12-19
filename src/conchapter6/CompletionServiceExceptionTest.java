package conchapter6;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * 
 * 异常分析  线程池为单线程
 * @author LiZhenhua
 *
 */
public class CompletionServiceExceptionTest {
	
	public static void main(String[] args) throws Exception {
		CompletionServiceExceptionTest thisClass =  new CompletionServiceExceptionTest();
//		thisClass.useTakeException();
//		thisClass.useGetException();
//		thisClass.useGetException2();
//		thisClass.usePollException();
//		thisClass.usePollGet1Exception();
		thisClass.usePollGet1Exception2();
		
	}
	
	class MyCallable1 implements Callable<String>{
		
		private String name;
		private int times;
		
		public MyCallable1(String name,int times){
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
	
	class MyCallable2 implements Callable<String>{
		
		private String name;
		private int times;
		
		public MyCallable2(String name,int times){
			this.name = name;
			this.times = times;
		}

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			System.out.println(System.currentTimeMillis()+" "+name+"正在运行");	
			try {
				Thread.sleep(times);
				int i = 0;
				if(i == 0){
					throw new Exception("抛出异常");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis()+" "+name+"运行完毕");
			return name;			
		}		
	}
	
	
	/**
	 * 只使用take获取Future的话，B不会出现异常
	 * @throws Exception
	 */
	public void useTakeException() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",4000);
		
		
		csRef.submit(b);
		csRef.submit(a);
		
		for(int i=0;i<2;i++){
			System.out.println("结果：：：："+csRef.take());
		}
		System.out.println("main end");
		
		
	}
	
	/**
	 * a先运行，正确返回
	 * b后面运行，抛出 异常
	 * @throws Exception
	 */
	public void useGetException() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",4000);
		
		csRef.submit(a);//a先运行
		csRef.submit(b);//b后面运行
		
		
		for(int i=0;i<2;i++){
			System.out.println("等待结果："+i);
			System.out.println("结果：：：："+csRef.take().get());
		}
		System.out.println("main end");		
	}
	
	
	/**
	 * b先运行，抛出异常,get()方法没有取到结果，等待
	 * a后面运行，由于第一个get没有等待到结果（一直在等待b的结果），所以a没有返回
	 * @throws Exception
	 */
	public void useGetException2() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",4000);
		
		csRef.submit(b);//b先运行
		csRef.submit(a);
		
		
		
		for(int i=0;i<2;i++){
			System.out.println("等待结果："+i);
			System.out.println("结果：：：："+csRef.take().get());
		}
		System.out.println("main end");		
	}
	
	/**
	 * poll 只得到Futrue不抛出异常
	 * @throws Exception
	 */
	public void usePollException() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",4000);
		
		
		csRef.submit(b);
		csRef.submit(a);
		
		for(int i=0;i<2;i++){
			System.out.println("结果：：：："+csRef.poll());
		}
		Thread.sleep(6000);
		System.out.println("结果1：：：："+csRef.poll());
		System.out.println("结果2：：：："+csRef.poll());
		
		System.out.println("main end");
		
		
	}
	
	
	/**
	 * a有返回结果
	 * b抛出异常，一直等待get()，但无法得到。阻塞。。。
	 * @throws Exception
	 */
	public void usePollGet1Exception() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",4000);
		
		
		csRef.submit(a);
		csRef.submit(b);
		
		for(int i=0;i<2;i++){
			System.out.println("结果：：：："+csRef.poll());
		}
		Thread.sleep(6000);
		System.out.println("结果1：：：："+csRef.poll().get());
		System.out.println("结果2：：：："+csRef.poll().get());
		
		System.out.println("main end");
		
		
	}
	
	/**
	 * 抛出异常，等待b的get结果，一直阻塞
	 * a没有返回结果
	 * @throws Exception
	 */
	public void usePollGet1Exception2() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",3000);
		
		
		csRef.submit(b);//b先运行
		csRef.submit(a);
		
		for(int i=0;i<2;i++){
			System.out.println("结果：：：："+csRef.poll());
		}
		Thread.sleep(6000);
		System.out.println("结果1：：：："+csRef.poll().get());
		System.out.println("结果2：：：："+csRef.poll().get());
		
		System.out.println("main end");
		
		
	}
	
	

}
