package JavaCoreLearn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executors提供四种线程池测试
 * @author Administrator
 * 
 * public ThreadPoolExecutor(int corePoolSize,  核心线程数，如果运行的线程少于corePoolSize，则创建新线程来执行新任务，即使线程池中的其他线程是空闲的
                          int maximumPoolSize,  最大线程数，可允许创建的线程数，corePoolSize和maximumPoolSize设置的边界自动调整池大小：
                          long keepAliveTime,   如果线程数多于corePoolSize,则这些多余的线程的空闲时间超过keepAliveTime时将被终止
                          TimeUnit unit,         keepAliveTime参数的时间单位
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,   使用ThreadFactory创建新线程，默认使用defaultThreadFactory创建线程
                          RejectedExecutionHandler        handler 定义处理被拒绝任务的策略，默认使用ThreadPoolExecutor.AbortPolicy,任务被拒绝时将抛出RejectExecutorException
                          ) //后两个参数为可选参数 
                          
                          corePoolSize <运行的线程数< maximumPoolSize:仅当队列满时才创建新线程
                          corePoolSize=运行的线程数= maximumPoolSize：创建固定大小的线程池
                          
                          workQueue:保存任务的阻塞队列，与线程池的大小有关：
  当运行的线程数少于corePoolSize时，在有新任务时直接创建新线程来执行任务而无需再进队列
  当运行的线程数等于或多于corePoolSize，在有新任务添加时则选加入队列，不直接创建线程
  当队列满时，在有新任务时就创建新线程
 *
 */
public class ThreadPoolTest {
	public static void main(String[] args) throws Exception {
		ThreadPoolTest.submitTest();
//		ThreadPoolTest.excutorFixedThreadPool1();
//		ThreadPoolTest.excutorFixedThreadPool2();	
//		ThreadPoolTest.excutorSingleThreadExecutor();
//		ThreadPoolTest.excutorScheduledThreadPool2();
//		ThreadPoolTest.CompletionServiceTest();
	}
	
	/**
	 * submit（） 与execute（）区别 
	 * 接收的参数不一样: 
	 *   submit（）可以接受runnable和callable  有返回值Futurn<>
	 *   execute（）接受runnable 无返回值
	 * 
	 */
	private static void submitTest(){
		ThreadPoolExecutor tt ;
		ExecutorService executorService = Executors.newCachedThreadPool();  
        List<Future<String>> resultList = new ArrayList<Future<String>>();  

        // 创建10个任务并执行  
        for (int i = 0; i < 10; i++) {  
            // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中  
            Future<String> future = executorService.submit(new TaskWithResult(i));  
            // 将任务执行结果存储到List中  
            resultList.add(future);  
        }  
        executorService.shutdown(); 
        System.out.println("结果集的大小："+resultList.size());
        // 遍历任务的结果  
        for (Future<String> fs : resultList) {
            try {  
                System.out.println("执行结果："+fs.get()); // 打印各个线程（任务）执行的结果  
            } catch (InterruptedException e) {  
            	System.out.println("异常打印1：");
                e.printStackTrace();  
            } catch (ExecutionException  e) {
            	System.out.println("异常打印2：");
                executorService.shutdownNow();  
                e.printStackTrace();  
//                return;  
            }  
        }
	}
	
	// Callable 接口返回的是 Future<>对象
	static class TaskWithResult implements Callable<String> {
	    private int id;  

	    public TaskWithResult(int id) {  
	        this.id = id;  
	    }  

	    /** 
	     * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。 
	     *  
	     * @return 
	     * @throws Exception 
	     */  
	    public String call() throws Exception {
	        System.out.println("id="+id+"  call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());  
	        if (new Random().nextBoolean())  
	            throw new TaskException("###Meet error in task." + Thread.currentThread().getName()+"   id="+id);  
	        // 一个模拟耗时的操作  
	        for (int i = 999999999; i > 0; i--)  
	            ;  
	        return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();  
	    }  
	}
	
	static class TaskException extends Exception {
	    public TaskException(String message) {  
	        super(message);  
	    }  
	} 
	
	
	private static void excutorFixedThreadPool1(){
		//创建有6个线程数的线程池
				ExecutorService pool = Executors.newFixedThreadPool(6);
				
				Runnable target = ()->{
					for (int i = 0; i <200; i++) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+" 的 i 的值："+i);
					}
				};
				//向线程池中提交 两个线程
				pool.submit(target);
				pool.submit(target);
				//关闭线程池  虽然关闭了，提交的线程会直到运行完
				pool.shutdown();	
				new Random().nextBoolean();
	}
	
	private static void excutorFixedThreadPool2(){
		ExecutorService pool = Executors.newFixedThreadPool(6);
		for (int i = 0; i < 10; i++) {
		    final int index = i;
		    pool.execute(new Runnable() {		 
		        @Override
		        public void run() {
		            try {
		                System.out.println("index:"+index+" 线程名字："+Thread.currentThread().getName());		                
		                Thread.sleep(2000);
		            } catch (InterruptedException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		        }
		    });
		}
		// 如果不关掉，所有线程执行完毕，程序还是不会退出
		pool.shutdown();
	}
	
	// 定时线程池，延迟3秒执行
	private static void excutorScheduledThreadPool1(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() {
		 
		    @Override
		    public void run() {
		        System.out.println("delay 3 seconds");
		    }
		}, 3, TimeUnit.SECONDS);
	}
	
	// 定时线程池 延迟1秒执行，每3秒执行1次
	// ScheduledExecutorService比Timer更安全，功能更强大
	private static void excutorScheduledThreadPool2(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			 
		    @Override
		    public void run() {
		    	 System.out.println("线程名字："+Thread.currentThread().getName());		
		        System.out.println("模拟心跳机制");
		    }
		}, 1, 1, TimeUnit.SECONDS);
	}
	
	/**
	 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
	 */
	private static void excutorSingleThreadExecutor(){
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
		    final int index = i;
		    singleThreadExecutor.execute(new Runnable() {
		 
		        @Override
		        public void run() {
		            try {
		                System.out.println("index:"+index);
		                Thread.sleep(2000);
		            } catch (InterruptedException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		        }
		    });
		}
		singleThreadExecutor.shutdown();
	}
	
	private static void CompletionServiceTest() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(10); // 创建含10.条线程的线程池
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
				executor);
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		for (int i = 1; i <= 10; i++) {
			final int result = i;
			Future<Integer> fu = completionService.submit(new Callable<Integer>() {
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000)); // 让当前线程随机休眠一段时间
					return result;
				}
			});
			list.add(fu);
		}
		System.out.println(completionService.take().get()); // 获取执行结果
		executor.shutdown();

	}

}
