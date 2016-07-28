package WorldSrvTest;

import org.apache.commons.lang3.time.StopWatch;

//使用StopWatch必须导入Apache的jar包


//StopWatch是一个计时器。与机器时间无关，当start时，时间从0开始走。。。直到stop，中间计时器可以暂停，然后又恢复计时；暂停的时间段，不计入计时器计时内

public class StopWatchTest {
	
	private static void test01() throws InterruptedException {  
	    StopWatch watch = new StopWatch();  
	    watch.start();
	    Thread.sleep(1000);  
	    watch.split();  
	    /* 
	     * This is the time between start and latest split. 
	     * 调用start()方法到最后一次调用split()方法耗用的时间 
	     */  
	    Thread.sleep(2000);  
	    watch.split();  
	    Thread.sleep(500);  
	    /* 
	     * This is either the time between the start and the moment this method 
	     * is called, or the amount of time between start and stop 
	     * 调用start()方法到调用getTime()或stop()方法耗用的时间 
	     */  
	    System.out.println("watch.getTime():"+watch.getTime());
	    
	    //最后一次执行的时间间隔
	    System.out.println(watch.getTime()-watch.getSplitTime());

	}  
	
	
	private static void test02() throws InterruptedException {  
	    StopWatch watch = new StopWatch();  
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println(watch.getTime());  
	    /* 复位 归零 */  
	    watch.reset();  
	    //归零后，要再一次启动
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println(watch.getTime());  
	} 
	
	private static void test03() throws InterruptedException{  
	    StopWatch watch = new StopWatch();  
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println(watch.getTime());  
	    /* 暂停 */  
	    watch.suspend();  
	    System.out.println("do something");  
	    Thread.sleep(500);  
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
