package JavaCoreLearn;
/**
 * 线程加入
 * @author LiZhenhua
 *
 */
public class ThreadJoin extends Thread{
	//调用父类构造器
	public ThreadJoin(String name){
		super(name);
	}
	public ThreadJoin(Runnable run,String name){
		super(run,name);
	}
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 100; i++) {
			System.out.println(getName() + " "+ i);
		}
	}

	
	public static void main(String[] args) throws InterruptedException {
		new ThreadJoin("第一线程").start();
		for (int i = 0; i < 100; i++) {
			System.out.println("主线程"+Thread.currentThread().getName()+""+i);
			if(i == 20){
				ThreadJoin jo = new ThreadJoin("被加入的线程");
				jo.start();
				//调用join,当前线程将被阻塞。直到jo线程执行完
				jo.join();
//				jo.join(2);
//				jo.join(2,2);
			}
		}
		
		System.out.println("主线程000000000000");
	}
}
