package JavaCoreLearn;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTest {
	public static void main(String[] args) throws InterruptedException {

		ForkJoinPool pool = new ForkJoinPool();
		//提交可分解的printtask任务
		pool.submit(new PrintTask(0, 300));
		pool.awaitTermination(2, TimeUnit.SECONDS);
		//关闭线程池
		pool.shutdown();
		
	}
}

class PrintTask extends RecursiveAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//每个 小任务 最多 只打印 50 个数
	private static final int THRESHOLD = 50;
	private int start;
	private int end;
	
	public PrintTask(int start,int end){
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		System.out.println("线程："+Thread.currentThread().getName());

		// TODO Auto-generated method stub
		if(end - start<THRESHOLD){
			for (int i = start; i < end; i++) {
				System.out.println(Thread.currentThread().getName()+"的i值"+i);
			}
		}else{
			int times = 1;
			System.out.println();
			int middle = (start+end)/2;
			PrintTask left = new PrintTask(start,middle);
			PrintTask right = new PrintTask(middle,end);
			//并行两个任务
			left.fork();
			right.fork();			
		}		
	}	
}
