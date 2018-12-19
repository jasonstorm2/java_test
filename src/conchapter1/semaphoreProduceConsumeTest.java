package conchapter1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程下的生产者消费者模型
 * 
 * ReentrantLock lock对象的锁，一次只能有一个线程获得该锁---？？？需确认是否正确
 * Condition setCondition = lock.newCondition() 条件 控制线程的状态，有等待，和唤醒所有线程等功能
 * volatile 的用法
 * 
 * @author LiZhenhua
 *
 */
public class semaphoreProduceConsumeTest {
	public static void main(String[] args) throws InterruptedException {
		semaphoreProduceConsumeTest thisClass = new semaphoreProduceConsumeTest();
		Service service = thisClass.new Service();
		setThread[] threadSet = new setThread[50];
		getThread[] threadGet = new getThread[50];
		
		for (int i = 0; i < threadGet.length; i++) {
			threadSet[i] =thisClass. new setThread(service);
			threadGet[i] =thisClass. new getThread(service);			
		}
		Thread.sleep(2000);
		for (int i = 0; i < threadGet.length; i++) {
			threadSet[i].start();
			threadGet[i].start();
		}
		
	}
	
	class Service{
		volatile private Semaphore setSemaphore = new Semaphore(10);
		volatile private Semaphore getSemaphore = new Semaphore(10);
		
		volatile private ReentrantLock lock = new ReentrantLock();
		volatile private Condition setCondition = lock.newCondition();
		volatile private Condition getCondition = lock.newCondition();
		volatile private Object[] goods = new Object[4];
		
		
		private boolean isEmpty(){
			boolean isEmpty = true;
			for (int i = 0; i < goods.length; i++) {
				if(goods[i] != null){
					isEmpty = false;
					break;
				}				
			}
			return isEmpty;
		}
		
		private boolean isFull(){
			boolean isFull = true;
			for (int i = 0; i < goods.length; i++) {
				if(goods[i] == null){
					isFull = false;
					break;
				}				
			}
			return isFull;
		}
		
		
		public void set(){
			try {
				setSemaphore.acquire();
				lock.lock();
				while(isFull()){
					//满了的话，所有生产线程暂停
					setCondition.await();
				}
				for (int i = 0; i < goods.length; i++) {
					if(goods[i] == null){
						goods[i] = Thread.currentThread().getName()+"商品";
						System.out.println(Thread.currentThread().getName() + "生产了"+goods[i]);
						break;
					}
				}
				getCondition.signalAll();//唤醒所有等待中的消费线程			
				lock.unlock();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				setSemaphore.release();
			}
		}
		
		public void get(){
			try {
				getSemaphore.acquire();
				lock.lock();
				while(isEmpty()){
					getCondition.await();
				}
				for (int i = 0; i < goods.length; i++) {
					if(goods[i] != null){
						System.out.println(Thread.currentThread().getName() + "消费了"+goods[i]);
						goods[i] = null;
						break;
					}
				}
				setCondition.signalAll();//通知生产者可以生产了				
				lock.unlock();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				getSemaphore.release();
			}
		}		
	}
	
	class setThread extends Thread{
		private Service service;
		
		public setThread(Service service){
			this.service = service;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();			
			service.set();			
		}
	}
	
	class getThread extends Thread{
		private Service service;
		
		public getThread(Service service){
			this.service = service;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();			
			service.get();			
		}
	}

}
