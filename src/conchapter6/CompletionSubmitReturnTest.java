package conchapter6;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * submit提交的线程的两种返回方式：
 * 
 * CompletionService.submit(Callable);有返回值 		使用Callable
 * CompletionService.submit(Runnable,V v);有返回值	使用Runnable,必须加返回的类型
 * 
 * @author Administrator
 *
 */
public class CompletionSubmitReturnTest {

	public static void main(String[] args) throws Exception {
		CompletionSubmitReturnTest thisClass =  new CompletionSubmitReturnTest();
//		thisClass.submintReturnTest();
		thisClass.submintReturnTest2();
		
	}
	
	class MyObj{
		private String name;
		
		public void setName(String name){
			this.name = name;
			
		}
		public String getName(){
			return name;
		}
		
	}
	
	class MyObj2{
		private final String name = "initjiba";		

		public String getName(){
			return name;
		}
		
	}
	
	class MyRunnable implements Runnable{
		
		private MyObj obj;
		
		
		public MyRunnable(MyObj obj){
			this.obj = obj;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(System.currentTimeMillis()+" "+"正在运行");	
			try {
				Thread.sleep(1000);
				obj.setName("jiba");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis()+" "+"运行完毕");
		}		
	}
	
	class MyRunnable2 implements Runnable{
		
		private String str;
		
		
		public MyRunnable2(String str){
			this.str = str;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(System.currentTimeMillis()+" "+"正在运行");	
			try {
				Thread.sleep(1000);
				str = "jiba";
				str = str.replace("mimi", "jiba");
				str = new String("fuck");
				str = str.substring(0, 1);
				System.out.println("操作后的值："+str);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis()+" "+"运行完毕");
		}		
	}
	
	/**
	 * 返回值即对 返回对象的处理
	 * @throws Exception
	 * @throws ExecutionException
	 */
	public void submintReturnTest() throws Exception, ExecutionException{
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		
		CompletionService<MyObj> csRef = new ExecutorCompletionService<MyObj>(executor);		
		
		MyObj obj = new MyObj();
		obj.setName("mimi");
		MyRunnable runnable = new MyRunnable(obj);		
		Future<MyObj> future = csRef.submit(runnable, obj);		
		
		System.out.println(future.get().getName());
	}
	
	/**
	 * String,Integer,Double等immutable类型没有提供修改自身的函数，每次操作都是生成一个新的对象
	 * @throws Exception
	 * @throws ExecutionException
	 */
	public void submintReturnTest2() throws Exception, ExecutionException{
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);		
		
		String str = new String("mimi");
		MyRunnable2 runnable = new MyRunnable2(str);		
		Future<String> future = csRef.submit(runnable, str);		
		
		System.out.println(future.get());
	}
	
}
