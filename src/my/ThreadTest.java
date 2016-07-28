package my;

public class ThreadTest extends Thread{

	private int countDown = 5;
	private int threadNumber;//对象变量，需实例化对象时才能分配空间，实例多少个对象，就有多少个实例变量
	private static int threadCount = 0;//类变量，有加载类时即被分配空间，于内存中值存在一个静态变量----life cycle取决于类的生命周期
	public ThreadTest(){
		threadNumber = ++threadCount;
		System.out.println("MAKING："+threadNumber);
		System.out.println("countDown："+countDown);
		System.out.println("threadCount："+threadCount);
		
	}
	public void run(){
		while(true){
			System.out.println("Thread"+threadNumber+"("+countDown+")");
			if(--countDown==0){
				return;
			}
		}
	}
	
	public static void main(String[] args){
		for(int i = 0; i<5;i++){
			new ThreadTest().start();
			System.out.println("ALL THREADS STARTED");
		}
	}

}
