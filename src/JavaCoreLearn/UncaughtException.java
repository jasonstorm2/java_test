package JavaCoreLearn;

public class UncaughtException {
	public static void main(String[] args) {
		Thread.currentThread().setName("主线程");
		//设置 主线程 的 异常处理器:
		Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());//指定线程的实例 设置 异常处理器
//		Thread.setDefaultUncaughtExceptionHandler(new MyExHandler());//该线程的所有实例 设置 默认异常处理器
		

		try {
			int a = 5/0;

		} catch (Exception e) {      //try catch 不上抛错误，程序继续向下运行
			// TODO: handle exception
			System.out.println("计算错误");
		}
		int a = 5/0;                 // 异常处理器 上抛异常，程序停止运行。
		System.out.println("线程正常结束");
		
	}

}

//定义自己的异常处理器

class MyExHandler implements Thread.UncaughtExceptionHandler{

	//实现uncaughtException方法，该方法将处理线程的 未处理的异常
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		System.out.println(t+"线程出现了异常："+e);		
	}	
}
