package JavaCoreLearn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	private String accountNo;
	private double balance;
	
	private final ReentrantLock lock = new ReentrantLock();//线程锁
	private final Condition cond = lock.newCondition();	 //线程锁的 解锁对象
	
	public BankAccount(String accountNo,double balance){
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	//因为账户余额不允许随意修改，所以只为balance提供getter方法
	public double getBalance() {
		return balance;
	}

//	public void setBalance(double balance) {
//		this.balance = balance;
//	}
	
	/*
	 * 同步方法。此方法的同步监视器是this，也就是本类的实例
	 */
	public synchronized void draw(double drawAmount){
		if(balance >= drawAmount){
			System.out.println(Thread.currentThread().getName()+" 取钱成功，取钞："+drawAmount);
			//修改余额
			balance -= drawAmount;
			System.out.println("\t 余额为："+balance);
		}else{
			System.out.println(Thread.currentThread().getName() + " 取钱失败！余额不足！");
		}
		
	}
	
	public  void drawlock(double drawAmount){
		lock.lock();
		try{
			if(balance >= drawAmount){
				System.out.println(Thread.currentThread().getName()+" 取钱成功，取钞："+drawAmount);
				//修改余额
				balance -= drawAmount;
				System.out.println("\t 余额为："+balance);
			}else{
				System.out.println(Thread.currentThread().getName() + " 取钱失败！余额不足！");
			}
		}finally{
			lock.unlock();
		}	
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return accountNo.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this ==obj)
			return true;
		if(obj!=null&& obj.getClass() == BankAccount.class){
			BankAccount target = (BankAccount)obj;
			return target.getAccountNo().equals(accountNo);
		}
		return false;
	}
	
	public boolean haveMoney;
	
	public synchronized void draw2(double money) {//取款
//		System.out.println("取款，当前线程名："+Thread.currentThread().getName());

			if(haveMoney==false){
//				System.out.println("取款等待，开始*******************");
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
//				System.out.println("取款等待，结束*******************");
			}else{
				balance -= money;
				System.out.println("取款"+money+"元");
//				System.out.println("余额："+balance);
//				System.out.println();	
				haveMoney = false;
				notifyAll();
				}
	}
	
	public synchronized void deposit2(double money){//存款
//		System.out.println("存款,当前线程名："+Thread.currentThread().getName());

		if(haveMoney == true){
			try {
//				System.out.println("存库等待，开始*******************");
				wait();
//				System.out.println("存库等待，结束*******************");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			balance += money;
			System.out.println("存款"+money+"元");
//			System.out.println("余额："+balance);
//			System.out.println();
			haveMoney = true;
			notifyAll();			
		}
		
	}
	
	public  void draw3(double money) {//取款
		lock.lock();
		try{
			if(!haveMoney){
				cond.await(); //等待	
			}else{
				balance -= money;
				System.out.println("取款"+money+"元");
				haveMoney = false;
				cond.signalAll();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}
	
	public  void deposit3(double money){//存款
		lock.lock();
		try{
			if(haveMoney){
			  cond.await();	
			}else{
				balance += money;
				System.out.println("存款"+money+"元");
				haveMoney = true;
				cond.signalAll();		
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			
		}
		
	}
	


}
