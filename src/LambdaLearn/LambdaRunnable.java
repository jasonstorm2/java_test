package LambdaLearn;

public class LambdaRunnable {

	public static void main(String[] args) {
		Runnable runn;
		Runnable run = new Runnable(){
			@Override
			public void run() {
				System.out.println("一个线程接口");
			}			
		};
		
		Runnable ru=()->{System.out.println("lamba");};
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println("线程接口");
			}
			
		}).start();
		
		new Thread(runn=()->{System.out.println("lamba,定义");}).start();
		new Thread(()->{System.out.println("lamba,定义缺省对象名字");}).start();
		
		new Thread(ru).start();
		
		
	}
	
	

}
