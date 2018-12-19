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
//			if(--countDown==0){
//				return;
//			}
		}
	}
	
	public static void main(String[] args){
//		System.out.println("路径："+Thread.currentThread().getContextClassLoader().getClass().getPackage());
//		
		for(int i = 0; i<5;i++){
			new ThreadTest().start();
//			System.out.println("ALL THREADS STARTED");
		}
		
//		getThreadStace();
	}
	
	/**
	 * 打印 当前线程所在的位置。
	 */
	public static void ThreadStace(){
//		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//		int i=0;
//		for (StackTraceElement ss : stackTrace) {
//			System.out.println("i的值："+i);
//			System.out.println(ss.getClassName()+" "+ss.getFileName()+" "+ss.getLineNumber()+" "+ss.getMethodName());	
//			i++;
//			
//		}
//		System.out.println(stackTrace[2]);		
		
		String str = "";
		StackTraceElement[] stackElements = Thread.currentThread().getStackTrace();
        if (stackElements != null) {            
            for (int i = 0; i < stackElements.length; i++) {
            	str = str + "\n	at " + stackElements[i].getClassName();
            	str = str + "." + stackElements[i].getMethodName();
            	str = str + "(" + stackElements[i].getFileName();
            	str = str + ":" + stackElements[i].getLineNumber()+")";
            }
        }
        System.out.println(str);
	}
	
	
	public static void getThreadStace(){
		ThreadStace();
	}

}
