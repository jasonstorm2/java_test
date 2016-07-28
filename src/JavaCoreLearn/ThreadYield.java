package JavaCoreLearn;

public class ThreadYield extends Thread{
	public ThreadYield(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 100; i++) {
			System.out.println(getName()+""+i);
			if(i == 20){
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadYield ty1 = new ThreadYield("高级");
		ty1.setPriority(MAX_PRIORITY);		
		
		ThreadYield ty2 = new ThreadYield("低级");
		ty2.setPriority(NORM_PRIORITY);
		ty2.start();
		ty1.start();
		
		int priority = Thread.currentThread().getPriority();
		
		System.out.println("主线程的优先级:"+priority);
		
		

	}

}
