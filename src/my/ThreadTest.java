package my;

public class ThreadTest extends Thread{

	private int countDown = 5;
	private static int threadCount = 0;//��������м�����ʱ��������ռ䣬���ڴ���ֵ����һ����̬����----life cycleȡ���������������
	private int threadNumber = ++threadCount;//�����������ʵ��������ʱ���ܷ���ռ䣬ʵ�����ٸ����󣬾��ж��ٸ�ʵ������

	public ThreadTest(){
//		threadNumber = ++threadCount;
		System.out.println("MAKING��"+threadNumber);
		System.out.println("countDown��"+countDown);
		System.out.println("threadCount��"+threadCount);
		
	}
	public void run(){
//		while(true){
//			System.out.println("Thread"+threadNumber+"("+countDown+")");
////			if(--countDown==0){
////				return;
////			}
//		}
	}
	
	public static void main(String[] args){
//		System.out.println("·����"+Thread.currentThread().getContextClassLoader().getClass().getPackage());
//		
		for(int i = 0; i<5;i++){
			new ThreadTest().start();
//			System.out.println("ALL THREADS STARTED");
		}
		
//		getThreadStace();
	}
	
	/**
	 * ��ӡ ��ǰ�߳����ڵ�λ�á�
	 */
	public static void ThreadStace(){
//		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//		int i=0;
//		for (StackTraceElement ss : stackTrace) {
//			System.out.println("i��ֵ��"+i);
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
