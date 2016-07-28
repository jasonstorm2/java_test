package JavaCoreLearn;

public class ThreadFirst extends Thread{
	private int i ;
	
	@Override
	public void run() {
		super.run();
		for(;i<100;i++){
			System.out.println(this.getName()+"自定义线程"+i);

		}
	}
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
//			System.out.println("当前线程名："+Thread.currentThread().getName()+"i="+i);
			if(i == 20){
				ThreadFirst one = new ThreadFirst();//启动第一个线程
				ThreadFirst two = new ThreadFirst();//启动第二个线程
				one.setName("ONE");
				two.setName("TWO");
				one.start();
				two.start();
			}
		}
	}

}
