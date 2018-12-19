package my;

/**
 * 测试main线程是否是最后一个退出的线程
 * 
 * @author LiZhenhua
 * 
 *         JVM会在所有的非守护线程（用户线程）执行完毕后退出； 
 *         main线程是用户线程；
 *         仅有main线程一个用户线程执行完毕，不能决定JVM是否退出，也即是说main线程并不一定是最后一个退出的线程。
 *
 */
public class mainThreadTest {

	public static void main(String[] args) {
		System.out.println("开始程序");
		// 内部类与实例一样，静态方法不能直接调用非静态类
		// 内部类是动态的（无static关键字修饰），
		// 而main方法是静态的，普通的内部类对象隐含地保存了一个引用，
		// 指向创建它的外围类对象，所以要在static方法（类加载时已经初始化）调用内部类的必须先创建外部类。
		// 在一个类的静态成员中去访问非静态成员之所以会出错是因为在类的非静态成员不存在的时候静态成员就已经存在了，
		// 访问一个内存中不存在的东西当然会出错
		new mainThreadTest().new testThread().start();        
		System.out.println("结束程序");		
	}	
	
	
	class testThread extends Thread{
		public testThread() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			super.run();
			int i=10;
			System.out.println("当前线程名字："+Thread.currentThread().getName());			
			Thread.currentThread().setName("myDefineThread");
			while(true){
				
//				try {
//					System.out.println(i);
//					Thread.sleep(1000);					
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				i--;
//				if(i == 0){
//					System.out.println("程序结束退出");
//					break;
//				}	
			}
			
		}
		
	}

}
