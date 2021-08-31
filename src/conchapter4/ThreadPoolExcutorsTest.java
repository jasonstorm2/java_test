package conchapter4;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * ����ThreadPoolExecutor�̳߳صĸ�������������
 * ע�� ThreadPoolExecutor �̳߳� �� ����ʹ�þ�̬�������ɵ�ExecutorService �̳߳� ������
 * 
 *   ThreadPoolExecutor��ʹ���ϲ�������ô���㣬��ʵ����ʱ��Ҫ����ܶ��������
 *   ��Ҫ�����̵߳Ĳ����������̳߳�����Ч���йصĲ��������Թٷ�����ʹ��Executors�������������̳߳ض���
 *   
 *   ʹ��Executors�������������̳߳ض�����Ȼ���㣬��ϸ��δ֪��ͨ���鿴Executors������Դ���룬��ʵ�ڲ���ʵ������һ��ThreadPoolExecutor���ʵ��
 * 
 * ThreadPoolExecutor ��AbstractExecutorService�����࣬
 * AbstractExecutorService�� ExecutorService�ӿڵľ���ʵ���࣬
 * ExecutorService�ӿڵĸ��ӿ���Executor
 * 
 * ThreadPoolExecutor.ThreadPoolExecutor(
 *    int corePoolSize,    ��׼�߳�����
 *    int maximumPoolSize, ����߳�����
 *    long keepAliveTime,  �����̴߳��ʱ��
 * 	  TimeUnit unit,       ʱ�䵥λ
 *    BlockingQueue<Runnable> workQueue)  ���õĶ���
 *    
 *    ���õĶ��������֣�һ����LinkedBlockingQueue��һ����SynchronousQueue
 *    ��ͬ�Ķ��У��������������ò�ͬ
 * 
 * ������������������
 * @author LiZhenhua
 *
 */
public class ThreadPoolExcutorsTest {
	public static void main(String[] args) {
		ThreadPoolExcutorsTest thisClass = new ThreadPoolExcutorsTest();
		Runnable runnable = new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				try {
					System.out.println(Thread.currentThread().getName() +" run "+System.currentTimeMillis());
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() +" over "+System.currentTimeMillis());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};	
//		thisClass.getBaseMessage();
//		thisClass.addToBlockQueuetest(runnable);
//		thisClass.addToSynchronousQueueTest(runnable);
		thisClass.addToSynchronousQueueTest2(runnable);
		
	}	
	
	/**
	 * ��  �̳߳�û��ִ��һ���̣߳�ֻ��ʵ��������ô�̳߳���û���̣߳����߳�������ϳ���ͽ�����
	 * ��  �̳߳�����������һ���̣߳����߳�������ϣ��̳߳ص��̻߳������У���ȴ�ִ�����񣬰�ť�ʺ�ɫ�������ڼ�������
	 */
	public void getBaseMessage(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("����һ���߳�");
			}
		});
		
		System.out.println(System.currentTimeMillis()+"�����߳�����"+executor.getCorePoolSize());
		System.out.println(System.currentTimeMillis()+"����߳�����"+executor.getMaximumPoolSize());
		System.out.println(System.currentTimeMillis()+"���������߳���"+executor.getPoolSize());
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()+"���������߳���"+executor.getPoolSize());		
		executor.shutdown();
//		executor.shutdownNow();
		System.out.println("�̳߳��Ƿ񱻹ر��ˣ�"+executor.isShutdown());
		System.out.println(System.currentTimeMillis()+"���������߳���"+executor.getPoolSize());
	}
	
	/**
	 *��� Ҫִ�е��߳� ���� ��׼�߳���corePoolSize
	 *      �̳߳�����������Щ�߳�,�Ѷ�������̷߳���link������
	 *      
	 * maximumPoolSize���� �̴߳��ʱ�� ����������û������
	 * 
	 * ��ť�ʺ�ɫ����Ϊ�̳߳��к����߳��ڵȴ�����û�б�ɱ��
	 * @param runnable
	 */
	public void addToBlockQueuetest(Runnable runnable){
		
		try {
			ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
			executor.execute(runnable);//1
			executor.execute(runnable);//2
			executor.execute(runnable);//3
			executor.execute(runnable);//4
			executor.execute(runnable);//5
			executor.execute(runnable);//6
			executor.execute(runnable);//7
			executor.execute(runnable);//8
			executor.execute(runnable);//9
			executor.execute(runnable);//10

			
			Thread.sleep(300);
			System.out.println("A"+executor.getCorePoolSize());
			System.out.println("A"+executor.getPoolSize());
			System.out.println("A"+executor.getQueue().size());
			Thread.sleep(10000);
			System.out.println("B"+executor.getCorePoolSize());
			System.out.println("B"+executor.getPoolSize());
			System.out.println("B"+executor.getQueue().size());
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * ��� Ҫִ�е��߳� ���� ��׼�߳���corePoolSize ��С�� ����߳���maximumPoolSize��
	 *      �̳߳�����������Щ�߳�,���Ѷ�������̷߳���syn�����У����ʱ����󣬶�������̼߳�����corePoolSize���߳�--����
	 *      �����߳����к�һֱ�ȴ������µ�task
	 *      
	 * ��� Ҫִ�е��߳� ���� ����߳���maximumPoolSize������maximumPoolSize�����������׳��쳣
	 * 
	 * ��ť�ʺ�ɫ����Ϊ�̳߳��к����߳��ڵȴ�����û�б�ɱ��
	 * @param runnable
	 */
	public void addToSynchronousQueueTest(Runnable runnable){
		
		try {
			ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 9, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
			executor.execute(runnable);//1
			executor.execute(runnable);//2
			executor.execute(runnable);//3
			executor.execute(runnable);//4
			executor.execute(runnable);//5
			executor.execute(runnable);//6
			executor.execute(runnable);//7
			executor.execute(runnable);//8
			executor.execute(runnable);//9
			executor.execute(runnable);//10

			
			Thread.sleep(300);
			System.out.println("A"+executor.getCorePoolSize());
			System.out.println("A"+executor.getPoolSize());
			System.out.println("A"+executor.getQueue().size());
			Thread.sleep(10000);
			System.out.println("B"+executor.getCorePoolSize());
			System.out.println("B"+executor.getPoolSize());
			System.out.println("B"+executor.getQueue().size());
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * 
	 * ���ʱ��Ϊ0�����
	 * 
	 * ��� Ҫִ�е��߳� ���� ��׼�߳���corePoolSize ��С�� ����߳���maximumPoolSize��
	 *      �̳߳�����������Щ�߳�,���Ѷ�������̷߳���syn�����У����ʱ��Ϊ0������ִ����Ϻ�����߳�--��������
	 *      
	 * ��ť�ʺ�ɫ����Ϊ�̳߳��к����߳��ڵȴ�����û�б�ɱ��
	 * 
	 * @param runnable
	 */
	public void addToSynchronousQueueTest2(Runnable runnable){		
		
		try {
			ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 9, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//			ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 9, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
			
			executor.execute(runnable);//1
			executor.execute(runnable);//2
			executor.execute(runnable);//3
			executor.execute(runnable);//4
			executor.execute(runnable);//5
			executor.execute(runnable);//6
			executor.execute(runnable);//7
			executor.execute(runnable);//8

			
			Thread.sleep(300);
			System.out.println("A"+executor.getCorePoolSize());
			System.out.println("A"+executor.getPoolSize());
			System.out.println("A"+executor.getQueue().size());
			Thread.sleep(5000);
			System.out.println("B"+executor.getCorePoolSize());
			System.out.println("B"+executor.getPoolSize());
			System.out.println("B"+executor.getQueue().size());
			Thread.sleep(6000);
			System.out.println("C"+executor.getCorePoolSize());
			System.out.println("C"+executor.getPoolSize());
			System.out.println("C"+executor.getQueue().size());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
