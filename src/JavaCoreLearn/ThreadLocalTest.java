package JavaCoreLearn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal类型的变量测试
 * 
 * 多个线程对同一个对象的处理时，该对象中 ThreadLocal类型的变量和普通变量的 区别
 * ThreadLocal类型的变量：多线程时，每个线程都有该变量的一个副本，如果没有重写初始方法initialValue，则，默认值为null
 * 普通变量:多线程时，该变量为共有变量。除非加锁，否则该变量线程不安全。
 * 
 * 
 * Thread 在内部是通过ThreadLocalMap来维护ThreadLocal变量表， 在Thread类中有一个threadLocals
 * 变量，是ThreadLocalMap类型的，它就是为每一个线程来存储自身的ThreadLocal变量的，
 * ThreadLocalMap是ThreadLocal类的一个内部类，这个Map里面的最小的存储单位是一个Entry，
 * 它使用ThreadLocal作为key， 变量作为 value，这是因为在每一个线程里面，可能存在着多个ThreadLocal变量
 * 
 * 初始时，在Thread里面，threadLocals为空，当通过ThreadLocal变量调用get()方法或者set()方法，
 * 就会对Thread类中的threadLocals进行初始化
 * ，并且以当前ThreadLocal变量为键值，以ThreadLocal要保存的副本变量为value，存到threadLocals。
 * 然后在当前线程里面，如果要使用副本变量，就可以通过get方法在threadLocals里面查找
 * 
 * ThreadLocalMap并不是为了解决线程安全问题， 而是提供了一种将实例绑定到当前线程的机制，
 * 类似于隔离的效果，实际上自己在方法中new出来变量也能达到类似的效果。
 * ThreadLocalMap跟线程安全基本不搭边，绑定上去的实例也不是多线程公用的，
 * 而是每个线程new一份，这个实例肯定不是共用的，如果共用了，那就会引发线程安全问题。
 * ThreadLocalMap最大的用处就是用来把实例变量共享成全局变量， 在程序的任何方法中都可以访问到该实例变量而已。
 * 网上很多人说ThreadLocalMap是解决了线程安全问题，其实是望文生义，两者不是同类问题
 * 
 * @author LiZhenhua
 *
 */
class Account{
	//定义一个ThreadLocal类型的变量，该变量将是 一个线程局部变量
	private ThreadLocal<String> name = new ThreadLocal<String>(){
		@Override
		protected String initialValue() {
			// TODO Auto-generated method stub
			return "-默认值-";
		}
	};
	
	//线程并不是共享此变量，而是每个线程都有这个副本
	private ThreadLocal<Integer> value = new ThreadLocal<Integer>();//线程并不是共享此变量，而是每个线程都有这个副本
	
	private int count = 0;
	
	public Account(String name,int value){
		this.name.set(name);
		//用于访问 当前线程的 name副本的值
		this.value.set(value);		
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
    
    public static int getId(){
    	return nextId.getAndIncrement();
    }
}

class myTestThread extends Thread{
	
	private int id = 0;
	
	private Account account;
	
	public myTestThread(Account account,String name,ThreadId threadId){
		super(name);
		this.account = account;
		this.id = ThreadId.get();
	}
	
	@Override
	public void run() {
		super.run();
		
		System.out.println("线程的编号："+id);
		System.out.println("Account的localname:"+account.getName());
		
		for (int i = 0; i < 4; i++) {
			if(i==2){
				System.out.println("ThreadName:"+getName()+"  "+"i==2");
				account.setName(getName());
			}
			account.setCount(account.getCount()+1);
			System.out.println("ThreadName:"+getName()+"  "+account.getName() + "**账号**,账户的i 值 "+ i);
			System.out.println("ThreadName:"+getName()+"  "+"COUNT的值"+ account.getCount());
			System.out.println("ThreadName:"+getName()+"  "+"value的值,设置前："+ account.getValue());
			account.settValue(i);
			System.out.println("ThreadName:"+getName()+"  "+"value的值,设置后："+ account.getValue());
			
		}		
	}
	
	public int getSelfId(){
		return id;
	}
}

public class ThreadLocalTest {
	
	public static void main(String[] args) {
		
		ThreadId threadId = new ThreadId();		
		Account ac = new Account("初始名",100);
		System.out.println("主线程ac名："+ac.getName());

		myTestThread t1 = new myTestThread(ac, "线程1",threadId);
		myTestThread t2 = new myTestThread(ac, "线程2222",threadId);
		t1.start();
		t2.start();
		
		
		System.out.println("主线程ac名2："+ac.getName());
		

		
	}

}
