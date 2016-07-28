package JavaCoreLearn;

public class ThreadSecond implements Runnable{
	private int i;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (;i < 100; i++) {
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
	}
	
	
	
	public static void main(String[] args) {
		

		for (int i = 0; i < 100; i++) {
			System.out.println("主线程"+Thread.currentThread().getName()+" "+i);
			if(i==20){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ThreadSecond st = new ThreadSecond();
				new Thread(st, "新线程1 ").start();//target类st 两个线程共享
				new Thread(st, "新线程2 ").start();//target类st 两个线程共享
				}
		}
		
		Thread tt = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("执行run与执行start的区别");
			}
		}, "新线程3 ");//target类st 两个线程共享
		
		tt.run();
		tt.start();
	}

}
