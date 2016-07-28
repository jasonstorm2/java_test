package JavaCoreLearn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Account{
	//定义一个ThreadLocal类型的变量，该变量将是 一个线程局部变量
	private ThreadLocal<String> name = new ThreadLocal<String>();//线程并不是共享此变量，而是每个线程都有这个副本
	private int count = 0;
	
	public Account(String name){
		this.name.set(name);
		//用于访问 当前线程的 name副本的值
		System.out.println("----"+this.name.get());
	}
	
	
	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);;
	}
}

class myTest extends Thread{
	
	private Account account;
	
	public myTest(Account account,String name){
		super(name);
		this.account = account;
	}
	
	@Override
	public void run() {
		super.run();
		
		for (int i = 0; i < 10; i++) {
			if(i==6){
				account.setName(getName());
			}
			account.setCount(account.getCount()+1);
			System.out.println(account.getName() + "账户的i 值 "+ i);
			System.out.println("COUNT的值"+ account.getCount());

		}		
	}
}

public class ThreadLocalTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		//使用Collections 的 synchronizedMap静态方法 将 一个普通的HashMap 包装成线程安全的类
		Map<String, String> map1 = Collections.synchronizedMap(map);
		Map<String, String> map2 = Collections.synchronizedMap(new HashMap<String, String>());
		
		
		Account ac = new Account("初始名");
		System.out.println("主线程ac名："+ac.getName());

		new myTest(ac, "线程1").start();
		new myTest(ac, "线程2222").start();
		
		System.out.println("主线程ac名2："+ac.getName());

		
	}

}
