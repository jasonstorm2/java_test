package JavaCoreLearn;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest2 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int arr[]	= new int[100];
		Random rand = new Random();		
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			int tmp = rand.nextInt(20);
			
			total +=(arr[i] = tmp);			
		}
		System.out.println("total= "+total);
		//创建一个通用池
		ForkJoinPool pool = ForkJoinPool.commonPool();
		Future<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
		System.out.println(future.get());		
		pool.shutdown();
		
	}

}

class CalTask extends RecursiveTask<Integer>{
	//每个小任务 最多 累加20个数
	private static final int THRESHOLD = 20;
	private int arr[];
	private int start;
	private int end;
	
	//累加从start 到end 的数组元素
	public CalTask(int[] arr,int start,int end){
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		int sum = 0;
		if(end - start <THRESHOLD){
			for (int i = start;i < end; i++) {
				sum += arr[i];
			}
			return sum;
		}else{
			//当end与start 之间的差距大于THRESHOLD，即要累加的数超过20个时
			//将大任务 分解成两个 小任务
			int middle = (start+end)/2;
			CalTask left = new CalTask(arr,start,middle);
			CalTask right = new CalTask(arr,middle,end);
			//并行执行两个 小任务
			left.fork();
			right.fork();
			//把 两个小任务 累加的结果 合并起来
			return left.join()+right.join();			
		}	
	}
	
}
