package JavaCoreLearn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


public class ThreadThird{
	public static void main(String[] args) throws InterruptedException {		
		
		//某一个代码段 包装成 futuretask,可以随时被线程调用
		FutureTask<Integer> task = new FutureTask<Integer>(
				(Callable<Integer>)()->{
					int i = 0;
					for(;i<100;i++){
						System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
					}
					return i;
				}
			);	
		
		FutureTask<Integer> task2 = new FutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println("返回负值");
				return -1;
			}
		});
		
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);			
			if(i == 20){
				new Thread(task,"有返回值的线程").start();
			}
		}
		
		try {
			System.out.println("打印");
			if(task.isDone()){
				System.out.println("task结束了");
			}else{
				System.out.println("task正在继续");
			}
			System.out.println("阻塞！！！");
			System.out.println("子线程的返回值："+task.get());
			System.out.println("主线程阻塞！！！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(10000);
		System.out.println("睡眠10秒");
		
	}
}
