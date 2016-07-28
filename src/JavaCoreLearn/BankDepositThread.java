package JavaCoreLearn;

public class BankDepositThread extends Thread{
	private BankAccount account;
	private double drawAmount;
	
	
	public BankDepositThread(String name,BankAccount account,double drawAmount){
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;		
	}
	
	public BankDepositThread(BankAccount account,double drawAmount){
		this.account = account;
		this.drawAmount = drawAmount;		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 20; i++) {
//			System.out.println("deposit i="+i);
			account.deposit3(drawAmount);
		}
	}

}
