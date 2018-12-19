package JavaCoreLearn;

/**
 * 守护进程（后台进程）
 * @author LiZhenhua
 *
 */
public class ThreadDaemon extends Thread{
	
	public  ThreadDaemon(String name){
		super(name);
	}
	
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 1000; i++) {
			System.out.println(getName()+""+i);
		}
	}
	
	public static void main(String[] args) {
		ThreadDaemon td = new ThreadDaemon("自定义后台程序");
		td.setDaemon(true);//设置为后台程序
		
		td.start();
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+i);
		}
		
		
	}

}
