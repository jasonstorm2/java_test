package my;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

/**
 * 未检查异常：派生于ERROR 或 RuntimeException的所有异常
 * 已检查异常： 除未检查异常外的异常
 * 
 * 一个方法必须声明所有可能抛出的已检查异常，而未检查异常要吗不可控制(ERROR)，要么就应该避免发生(RuntimeException)
 * @author Administrator
 *
 */
public class ThreadExceptionTest {
	public static void main(String[] args) {
		ThreadExceptionTest thisClass = new ThreadExceptionTest();
//		thisClass.tracePrint();
//		thisClass.tracePrint2();		
//		thisClass.tracePrint3();		
//		thisClass.tracePrint4();		
//		thisClass.tracePrint4();	
		thisClass.factorial(5);
	}
	
	/**
	 * 线程抛出异常，得用trycatch捕获，不然会报错。。
	 * 主线程调用，会抛出异常打印信息
	 * 
	 */
	private void thread1(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
					try {
						throw new Exception("this is a exception");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();						
						System.out.println("异常名字："+e.getClass().getName());
					}				
			}
		}).start();
	}
	
	private void thread2(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
					try {
//						throw new ThreadExceptionTest(); //抛出的对象必须是一种异常
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();						
						System.out.println("异常名字："+e.getClass().getName());
					}				
			}
		}).start();
	}
	
	/**
	 * 测试打印一个抛出异常的堆栈跟踪
	 * 打印出Throwable所在的行
	 */
	private void tracePrint(){
		Throwable t = new Throwable();		
		t.printStackTrace();		
	}
	
	/**
	 * 测试打印一个抛出异常的堆栈跟踪
	 * 没有打印出来
	 */
	private void tracePrint2(){
		Throwable t = new Throwable();		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream print = new PrintStream(out);
		t.printStackTrace(print);
		String description = print.toString();
		System.out.println("打印："+description);
	}
	
	/**
	 * 打印堆栈跟踪信息：通过getStackTrace方法
	 * 打印的是Throwable所在的行，以及调用含有Throwable方法的调用所在行
	 * 
	 */
	private void tracePrint3(){
		Throwable t = new Throwable();		
		StackTraceElement[] elements = t.getStackTrace();
		for (StackTraceElement stackTraceElement : elements) {
			
			System.out.println("类名  ："+stackTraceElement.getClassName());
			System.out.println("文件名："+stackTraceElement.getFileName());
			System.out.println("第几行："+stackTraceElement.getLineNumber());
			System.out.println("方法名："+stackTraceElement.getMethodName());			
			System.out.println("打印位置："+stackTraceElement.toString());
			System.out.println(stackTraceElement.toString());
			System.out.println(stackTraceElement);
			
		}

	}
	
	/**
	 * 获取所有线程的堆栈信息
	 */
	private void tracePrint4(){
		Map<Thread,StackTraceElement[]> map = Thread.getAllStackTraces();
		
		for(Thread t:map.keySet()){
			System.out.println("线程的名字****："+t.getName());
			StackTraceElement[] elements = map.get(t);
			for (StackTraceElement stackTraceElement : elements) {				
				System.out.println("类名  ："+stackTraceElement.getClassName());
				System.out.println("文件名："+stackTraceElement.getFileName());
				System.out.println("第几行："+stackTraceElement.getLineNumber());
				System.out.println("方法名："+stackTraceElement.getMethodName());			
				System.out.println("打印位置："+stackTraceElement.toString());
				System.out.println(stackTraceElement.toString());				
			}			
		}
	}
	
	
	private int factorial(int n){
		
		System.out.println("factorial("+n+")");
		Throwable t = new Throwable();		
		StackTraceElement[] elements = t.getStackTrace();
		for (StackTraceElement stackTraceElement : elements) {
			System.out.println(stackTraceElement.toString()+":"+n);
		}
		int r;
		if(n <= 1)r = 1;
		else r = n* factorial(n-1);
		System.out.println("return"+r);
		return r;		
	}

}
