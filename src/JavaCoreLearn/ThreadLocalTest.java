package JavaCoreLearn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal类型的变量测试
 * 
 * 多个线程对同一个对象的处理时，该对象中 ThreadLocal类型的变量和普通变量的 区别
 * 
 * ThreadLocalMap并不是为了解决线程安全问题，
 * 而是提供了一种将实例绑定到当前线程的机制，
 * 类似于隔离的效果，实际上自己在方法中new出来变量也能达到类似的效果。
 * ThreadLocalMap跟线程安全基本不搭边，绑定上去的实例也不是多线程公用的，
 * 而是每个线程new一份，这个实例肯定不是共用的，如果共用了，那就会引发线程安全问题。
 * ThreadLocalMap最大的用处就是用来把实例变量共享成全局变量，
 * 在程序的任何方法中都可以访问到该实例变量而已。
 * 网上很多人说ThreadLocalMap是解决了线程安全问题，其实是望文生义，两者不是同类问题
 * 
 * @author Administrator
 *
 */
class Account{
	//定义一个ThreadLocal类型的变量，该变量将是 一个线程局部变量
	private ThreadLocal<String> name = new ThreadLocal<String>();//线程并不是共享此变量，而是每个线程都有这个副本
	private ThreadLocal<Integer> value = new ThreadLocal<Integer>();//线程并不是共享此变量，而是每个线程都有这个副本
	
	private int count = 0;
	
	public Account(String name){
		this.name.set(name);
		//用于访问 当前线程的 name副本的值
		System.out.println("主线程ThreadLocal name值："+this.name.get());
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
		this.name.set(name);
	}
	
	public Integer getValue() {
		return value.get();
	}
	public void settValue(Integer value) {
		this.value.set(value);
	}
}

 class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
        new ThreadLocal<Integer>() {
            @Override protected Integer initialValue() {
                return nextId.getAndIncrement();
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
}

class myTestThread extends Thread{
	
	private int id = 0;
	
	private Account account;
	
	public myTestThread(Account account,String name,ThreadId threadId){
		super(name);
		this.account = account;
		this.id = threadId.get();
	}
	
	@Override
	public void run() {
		super.run();
		
		System.out.println("线程id"+id);
		
		for (int i = 0; i < 10; i++) {
			if(i==6){
				System.out.println("ThreadName:"+getName()+"  "+"i==6");
				account.setName(getName());
			}
			account.setCount(account.getCount()+1);
			System.out.println("ThreadName:"+getName()+"  "+account.getName() + "账户的i 值 "+ i);
			System.out.println("ThreadName:"+getName()+"  "+"COUNT的值"+ account.getCount());
			account.settValue(i);
			System.out.println("ThreadName:"+getName()+"  "+"value的值"+ account.getValue());
			
		}		
	}
	
	public int getSelfId(){
		return id;
	}
}

public class ThreadLocalTest {
	
	public static void main(String[] args) {
		
		ThreadId threadId = new ThreadId();
		Map<String, String> map = new HashMap<String, String>();
		//使用Collections 的 synchronizedMap静态方法 将 一个普通的HashMap 包装成线程安全的类
		Map<String, String> map1 = Collections.synchronizedMap(map);
		Map<String, String> map2 = Collections.synchronizedMap(new HashMap<String, String>());
		
		Map<Thread, String> Threadmap2 = Collections.synchronizedMap(new HashMap<Thread, String>());
		
		
		Account ac = new Account("初始名");
		System.out.println("主线程ac名："+ac.getName());

		myTestThread t1 = new myTestThread(ac, "线程1",threadId);
		myTestThread t2 = new myTestThread(ac, "线程2222",threadId);
		t1.start();
		t2.start();
		
		
		System.out.println("主线程ac名2："+ac.getName());
		

		
	}

}
