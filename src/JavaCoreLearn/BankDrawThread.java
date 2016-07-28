package JavaCoreLearn;

public class BankDrawThread extends Thread{
	private BankAccount account;
	private double drawAmount;
	//取钱线程
	public BankDrawThread(String name,BankAccount account,double drawAmount){
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;		
	}
	
	public BankDrawThread(BankAccount account,double drawAmount){
		this.account = account;
		this.drawAmount = drawAmount;		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
//		account.draw(drawAmount);
//		account.drawlock(drawAmount);
		
		for (int i = 0; i < 20; i++) {
			account.draw3(drawAmount);		
		}

	
		/*
		 * 同步代码块
		 */
//		synchronized(account){
//			if(account.getBalance() >= drawAmount){
//				System.out.println(getName()+" 取钱成功，取钞："+drawAmount);
//				//修改余额
//				account.setBalance(account.getBalance() - drawAmount);
//				System.out.println("\t 余额为："+account.getBalance());
//			}else{
//				System.out.println(getName() + " 取钱失败！余额不足！");
//			}
//		}
		
	}

}
