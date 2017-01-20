package WorldSrvTest;

import org.apache.commons.lang3.time.StopWatch;

/**
 * 使用StopWatch必须导入Apache的jar包 
 * StopWatch是一个计时器，与机器时间无关，当start时，时间从0开始走。。。
 * 直到stop，中间计时器可以暂停，然后又恢复计时；暂停的时间段，不计入计时器计时内
 * 
 * @author Administrator
 *
 */



public class StopWatchTest {
	
	/**
	 * 测试 split方法
	 * @throws InterruptedException
	 */
	private static void test01() throws InterruptedException {
	    StopWatch watch = new StopWatch();  
	    watch.start();
	    Thread.sleep(1000);  
	    watch.split();  //记录当前分离的时间
	    /* 
	     * This is the time between start and latest split. 
	     * 调用start()方法到最后一次调用split()方法耗用的时间 
	     */  
	    Thread.sleep(2000);  
//	    watch.split();  
	    Thread.sleep(500);  
	    /* 
	     * This is either the time between the start and the moment this method 
	     * is called, or the amount of time between start and stop 
	     * 调用start()方法到调用getTime()或stop()方法耗用的时间 
	     */  
	    System.out.println("watch.getTime():"+watch.getTime());
	    
	    //最后一次执行的时间间隔 - 当前时间
	    System.out.println(watch.getTime()-watch.getSplitTime());

	}  
	
	
	/**
	 * 测试 复位 reset方法
	 * @throws InterruptedException
	 */
	private static void test02() throws InterruptedException {
	    StopWatch watch = new StopWatch();  
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println("stopwatch走了多长时间："+watch.getTime());  
	    /* 复位 归零 */  
	    watch.reset();  
	    //归零后，要再一次启动，相当于重新启动
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println(watch.getTime());  
	} 
	
	/**
	 * 测试 暂停计时器方法suspend() 和 resume()方法
	 * @throws InterruptedException
	 */
	private static void test03() throws InterruptedException{  
	    StopWatch watch = new StopWatch();  
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println(watch.getTime());  
	    /* 暂停 */  
	    watch.suspend();   //暂停后，直到恢复 这段时间，不计入计时时间
	    System.out.println("do something");  
	    Thread.sleep(2000);  
	    /* 恢复 */  
	    watch.resume();  
	    Thread.sleep(2000);  
	    System.out.println(watch.getTime());  
	}
	
	
	public static void main(String[] args) {
		StopWatchTest st = new StopWatchTest();
		try {
			StopWatchTest.test01();
			System.out.println("-------------------------------------");
			StopWatchTest.test02();
			System.out.println("-------------------------------------");
			StopWatchTest.test03();


		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
