package conchapter7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * invokeAny 当线程池中有一个线程结束时，返回该线程，并且调用interrupt()中断其他正在运行的线程
 * 其他线程可以结合if(Thread.currentThread().isInterrupted() == true)来判断线程是否继续运行
 * 如果其他线程有判断，和异常处理方法，那么可以定义该方法
 * 若没有，则该程序继续运行
 * 
 * 如果其他线程有Thread.currentThread().isInterrupted()判断，并throw new Exception(),虽然抛出了异常，但main线程中并不能捕获异常，
 * 必须在callable中使用try-catch显式捕获,才能在控制台打印出异常信息
 * @author Administrator
 *
 */
public class invokeAnyTest {
	
	public static void main(String[] args) {
		invokeAnyTest thisClass = new invokeAnyTest();
		
		
		/********************/
//		try {
//			thisClass.normalThrows();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			System.out.println("普通方法抛出异常的处理");
//		}
		/********************/
//		thisClass.invokeAny();
		
		/********************/
//		thisClass.invokeAny2();//必须捕获异常，否则报错
//		try {//main处没有办法捕获异常
//			thisClass.invokeAny2();
//		} catch (Exception e) {
//			System.out.println("*************主动捕获异常");
//			System.out.println("主线程是否捕捉到异常了？？？？？？？？");
//			e.printStackTrace();
//		}
		
		/********************/
//		thisClass.invokeAny3();
		
		/********************/
//		try {
//			thisClass.invokeAny4();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		System.out.println("主线程是否捕捉到异常了？？？？？？？？");
//		}
		
		/********************/
//		try {
//			thisClass.invokeAny5();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("主线程是否捕捉到异常了？？？？？？？？");
//			e.printStackTrace();
//		}
		
		/********************/
//		try {
//			thisClass.invokeAny6();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("主线程是否捕捉到异常了？？？？？？？？");
//			e.printStackTrace();
//		}
		
		/********************/
//		try {
//			thisClass.invokeAny7();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("mainx线程捕捉到了异常，正在处理");
//			e.printStackTrace();
//		}
		
		/********************/
		try {
			thisClass.invokeAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("main捕获了异常：");
			e.printStackTrace();
		}
		
		
	}
	
	public void shutDownPoll(ExecutorService executor){
		executor.shutdown();
	}
	
	/**
	 * 没有线程中断处理，正常结束
	 * @author Administrator
	 *
	 */
	class Callable1 implements Callable<String>{
		int nums = 0;
		
		public Callable1(int nums){
			this.nums = nums;			
		}
		@Override
		public String call(){
			System.out.println("线程1--开始");
			for(int i=0;i<nums;i++){
				Math.random();
				Math.random();
				Math.random();	
				Math.random();
				Math.random();
				System.out.println("线程1输出："+i);
			}
			System.out.println("线程1---------结束"+nums);
			return "1返回了";			
		}		
	}
	
	/**
	 * 有线程中断处理，抛出异常
	 * 
	 * 并用trycatch进行捕获
	 * 
	 * @author Administrator
	 *
	 */
	class Callable2 implements Callable<String>{
		
		int nums = 0;
		
		public Callable2(int nums){
			this.nums = nums;			
		}
		
		@Override
		public String call() throws Exception{
			try {
				System.out.println("线程2--开始");
				for(int i=0;i<nums;i++){
					if(!Thread.currentThread().isInterrupted()){
						Math.random();
						Math.random();
						Math.random();		
						System.out.println("线程2输出："+i);
					}else{
						System.out.println("2线程异常中断了");
						throw new Exception("2异常，中断线程");
					}
					
				}
				System.out.println("线程2--结束"+nums);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("通过显式调用trycatch捕获异常");
				throw e;
			}

			return "2返回了";			
		}		
	}
	
	/**
	 * 有线程中断处理，抛出异常
	 * 
	 * 无trycatch进行捕获
	 * 
	 * @author Administrator
	 *
	 */
	class Callable3 implements Callable<String> {
		
		int nums = 0;
		
		public Callable3(int nums){
			this.nums = nums;			
		}
		@Override
		public String call() throws Exception {
			System.out.println("线程3--开始");		
			for (int i = 0; i < nums; i++) {
				if (!Thread.currentThread().isInterrupted()) {
					Math.random();
					Math.random();
					Math.random();
					System.out.println("线程3输出：" + i);
				} else {
					System.out.println("3线程异常中断了"+nums);
					throw new Exception("3异常，中断线程"+nums);
				}

			}
			System.out.println("线程4--结束"+nums);		
			return "4返回了";
		}			
	}
	
	/**
	 * 主动抛出异常，无trycatch处理
	 * @author Administrator
	 *
	 */
	class Callable4 implements Callable<String> {
		int nums = 0;
		
		public Callable4(int nums){
			this.nums = nums;			
		}
		@Override
		public String call() throws Exception {
			System.out.println("线程4--开始");		
			for (int i = 0; i < nums; i++) {
				Math.random();
				Math.random();
				Math.random();
				System.out.println("线程4输出：" + i);
			}
			throw new Exception("4异常，中断线程"+nums);
		}			
	}
	
	
	/**
	 * 主动抛出异常, 有trycatch处理
	 * 
	 * 
	 * 抛异常处有trycatch处理，才能打印出异常路径
	 * 如果catch处没有再抛出异常，则main线程获取的是本线程的返回值
	 * 如果在抛出异常，main线程获取的另外一个线程的返回值
	 * 
	 * 没有再抛出异常，导致main线程认为本线程是正确的，获取的是本线程的返回
	 * 
	 * @author Administrator
	 *
	 */
	class Callable5 implements Callable<String> {
		int nums = 0;
		
		public Callable5(int nums){
			this.nums = nums;			
		}
		@Override
		public String call() throws Exception {			
			try {
				System.out.println("线程5--开始");		
				for (int i = 0; i < nums; i++) {
					Math.random();
					Math.random();
					Math.random();
					System.out.println("线程5输出：" + i);
				}
				if(1 == 1){
					throw new Exception("5异常，中断线程，本线程自己捕获异常"+nums);//此处线程自己捕获了本线程的异常，会在控制台打印出来
				}				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw e; //不抛出异常则返回的是本线程的返回，抛出异常，main线程可以捕获
			}
			return "return 5 "+nums;
			
		}			
	}
	
	/**
	 * 无主动抛出异常，有中断处理
	 * @author Administrator
	 *
	 */
	class Callable6 implements Callable<String>{
		ExecutorService executor = null;
		int nums = 0;
		
		public Callable6(int nums,ExecutorService executor){
			this.nums = nums;	
			this.executor = executor;
		}
		@Override
		public String call(){		
			try {				
				System.out.println(nums+"：线程Callable6--开始");
				for(int i=0;i<nums;i++){
					Math.random();
					Math.random();
					Math.random();	
					Math.random();
					Math.random();
					System.out.println(nums+"：线程Callable6输出："+i);
					//如果出现异常没有处理，主线程一直等待，下面的流程不会走
					if(Thread.currentThread().isInterrupted() == true){
						System.out.println("Thread.currentThread().isInterrupted() == true");
						shutDownPoll(executor);
						throw new NullPointerException();
//						return "程序中断返回";// 虽然没有抛出线程，但主线程扔捕获了超时异常
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("call线程捕获到异常");
			}
		
			System.out.println(nums+"：线程Callable6---------结束"+nums);
			return nums+"Callable6返回了";			
		}		
	}
	
	/**
	 * 普通方法抛出异常测试
	 * 
	 * 方法有抛出异常，则需要trycatch或者在方法声明抛出异常
	 * @throws Exception
	 */
	public void normalThrows() throws Exception{
		throw new Exception();		
	}
	
	/**
	 * 当线程池中有一个线程结束时，返回该线程，并且调用interrupt()中断其他正在运行的线程
	 * 其他线程没有Thread.currentThread().isInterrupted()判断，和异常处理方法，
	 * 所以其他线程继续运行直到结束
	 */
	public void invokeAny(){
		List<Callable<String>> call = new ArrayList<Callable<String>>();
		call.add(new Callable1(123));
		call.add(new Callable1(1234));
		
		ExecutorService executor = Executors.newCachedThreadPool();
		try {
			String str = executor.invokeAny(call);
			System.out.println("返回信息："+str);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		shutDownPoll(executor);		
	}
	
	/**
	 * 
	 * invokeAny与执行慢的任务的异常
	 * 返回快任务的返回，慢任务则中断
	 * 
	 * Callable2线程抛出Exception异常，所以该方法也要抛出异常，或者try捕获
	 * 
	 * Callable2线程判断是否中断，并对中断进行处理，抛出了中断异常
	 * 由于Callable2用trycatch捕获异常，主线trycatch异常，但并不能捕获到异常
	 * 说明子线程的异常是不影响main线程的主流程的
	 * 
	 * @throws Exception 
	 * @throws Exception
	 * @throws ExecutionException
	 */
	public void invokeAny2() throws Exception{
		List<Callable<String>> call = new ArrayList<Callable<String>>();
		call.add(new Callable1(123));
		call.add(new Callable2(1234));
		
		ExecutorService executor = Executors.newCachedThreadPool();

		String str = executor.invokeAny(call);
		System.out.println("返回信息："+str);
		shutDownPoll(executor);	
	}
	
	/**
	 * 
	 * invokeAny与执行慢的任务的异常
	 * 返回快任务的返回，慢任务则中断
	 * 
	 * Callable3线程抛出了异常，在本方法中有捕获处理
	 * 
	 * Callable3判断线程是否中断，并对中断进行处理，抛出了中断异常
	 * 由于Callable3不是用trycatch捕获异常，控制台无法打印异常信息
	 * 
	 * @throws Exception 
	 * @throws InterruptedException 
	 */
	public void invokeAny3() {
		List<Callable<String>> call = new ArrayList<Callable<String>>();
		call.add(new Callable1(123));
		call.add(new Callable3(1234));
		ExecutorService executor = Executors.newCachedThreadPool();
		String str;
		try {
			str = executor.invokeAny(call);
			System.out.println("返回信息：" + str);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shutDownPoll(executor);	
	}
	
	/**
	 * 与invokeAny3()一样，只不过是把trycatch换成抛出异常
	 * @throws Exception
	 * @throws ExecutionException
	 */
	public void invokeAny4() throws Exception {
		List<Callable<String>> call = new ArrayList<Callable<String>>();
		call.add(new Callable1(123));
		call.add(new Callable3(1234));
		ExecutorService executor = Executors.newCachedThreadPool();
		String str;
		str = executor.invokeAny(call);
		System.out.println("返回信息：" + str);
		shutDownPoll(executor);	
	}	
	
	/**
	 * invokeAny()方法所有线程都抛出异常，那么，最终出现的异常是最后一个异常
	 * @throws Exception
	 */
	public void invokeAny5() throws Exception {
		List<Callable<String>> call = new ArrayList<Callable<String>>();
		call.add(new Callable4(12345));
		call.add(new Callable4(123456));
		ExecutorService executor = Executors.newCachedThreadPool();
		String str;
		str = executor.invokeAny(call);
		System.out.println("返回信息：" + str);
		shutDownPoll(executor);	
	}
	
	/**
	 * 执行快的任务出现异常，而执行慢的没有异常的情况
	 * 返回的是执行慢异常的返回
	 * 如果都出现异常则返回最后一个出现的异常
	 * 
	 * 如果快任务的catch处没有再抛出异常，则main线程获取的是快任务线程的返回值
	 * 如果在抛出异常，main线程获取的另外一个线程的返回值
	 * @throws Exception
	 */
	public void invokeAny6() throws Exception {
			List<Callable<String>> call = new ArrayList<Callable<String>>();
//			call.add(new Callable4(123));//不打印异常
//			call.add(new Callable5(12));//打印异常
//			call.add(new Callable5(50));//打印异常
			
			call.add(new Callable4(50));//打印异常
			
			call.add(new Callable1(183));
			ExecutorService executor = Executors.newCachedThreadPool();
			String str;
			str = executor.invokeAny(call);
			System.out.println("返回信息：" + str);
			shutDownPoll(executor);	
	}	
	
	/**
	 * 在单位时间内取得第一个执行完任务的结果值，其他线程中断
	 * invokeAny(Collection<? extends Callable<String>> tasks, long timeout, TimeUnit unit) 
	 * 若只有一个线程，并且超时，那么线程抛出异常，main线程能捕获超时异常
	 * 
	 * @throws Exception
	 */
	public void invokeAny7() throws Exception {
		List<Callable<String>> call = new ArrayList<Callable<String>>();

		ExecutorService executor = Executors.newCachedThreadPool();
		call.add(new Callable6(199988, executor));
//		call.add(new Callable6(19, executor));
		
		String str;
		str = executor.invokeAny(call, 1, TimeUnit.SECONDS);
		System.out.println("返回信息：" + str);
		shutDownPoll(executor);
	}
	
	
	/**
	 * invokeAll()返回所有任务的执行结果，此方法会阻塞
	 * 若慢任务出现异常，正确打印出快任务的返回结果，main线程可以捕获慢任务的异常
	 *     若慢任务自己捕获了异常，main线程也是可以捕获异常，因为慢任务抛出了异常
	 * 
	 * @throws Exception
	 */
	public void invokeAll() throws Exception {
		List<Callable<String>> call = new ArrayList<Callable<String>>();

		ExecutorService executor = Executors.newCachedThreadPool();
		/** 快正确，慢异常的情况 **/
//		call.add(new Callable6(12, executor));
//		call.add(new Callable4(50));
////		call.add(new Callable5(50));

		/** 慢正确，快异常的情况 **/
		
		 call.add(new Callable4(12));
//		 call.add(new Callable5(12));
		 call.add(new Callable6(50000, executor));

		List<Future<String>> list = executor.invokeAll(call);
		for (Future<String> future : list) {
			String str = future.get();
			System.out.println("返回信息：" + str);
		}
		System.out.println("是否继续运行1");
		shutDownPoll(executor);
	}

}
