package WorldSrvTest;

import org.apache.commons.lang3.time.StopWatch;

/**
 * ʹ��StopWatch���뵼��Apache��jar�� 
 * StopWatch��һ����ʱ���������ʱ���޹أ���startʱ��ʱ���0��ʼ�ߡ�����
 * ֱ��stop���м��ʱ��������ͣ��Ȼ���ָֻ���ʱ����ͣ��ʱ��Σ��������ʱ����ʱ��
 * 
 * @author LiZhenhua
 *
 */



public class StopWatchTest {
	
	/**
	 * ���� split����
	 * @throws InterruptedException
	 */
	private static void test01() throws InterruptedException {
		System.out.println("����������test01");
	    StopWatch watch = new StopWatch();  
	    watch.start();
	    Thread.sleep(1000);  
	    watch.split();  //��¼��ǰ�����ʱ��
	    /* 
	     * This is the time between start and latest split. 
	     * ����start()���������һ�ε���split()�������õ�ʱ�� 
	     */  
	    Thread.sleep(2000);  
//	    watch.split();  
	    Thread.sleep(500);  
	    /* 
	     * This is either the time between the start and the moment this method 
	     * is called, or the amount of time between start and stop 
	     * ����start()����������getTime()��stop()�������õ�ʱ�� 
	     */  
	    System.out.println("watch.getTime():"+watch.getTime());
	    
	    //���һ��ִ�е�ʱ���� - ��ǰʱ��
	    System.out.println(watch.getTime()-watch.getSplitTime());

	}  
	
	
	/**
	 * ���� ��λ reset����
	 * @throws InterruptedException
	 */
	private static void test02() throws InterruptedException {
		System.out.println("����������test02");
	    StopWatch watch = new StopWatch();  
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println("stopwatch���˶೤ʱ�䣺"+watch.getTime());  
	    /* ��λ ���� */  
	    watch.reset();  
	    //�����Ҫ��һ���������൱����������
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println(watch.getTime());  
	} 
	
	/**
	 * ���� ��ͣ��ʱ������suspend() �� resume()����
	 * @throws InterruptedException
	 */
	private static void test03() throws InterruptedException{
		System.out.println("����������test03");
	    StopWatch watch = new StopWatch();  
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println(watch.getTime());  
	    /* ��ͣ */  
	    watch.suspend();   //��ͣ��ֱ���ָ� ���ʱ�䣬�������ʱʱ��
	    System.out.println("do something");  
	    Thread.sleep(2000);  
	    /* �ָ� */  
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
