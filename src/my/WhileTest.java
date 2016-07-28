package my;

public class WhileTest {
	static int s;
	Refer test(int s){
	Refer r = new Refer();
		r.setS(s);
		return r;
	}
	void me(boolean ss){
		for(int i=0;i<10;i++){
			if(i==5){
				ss=true;
				break;
			}
			System.out.println("lala"+i);
		}
	}
	public static void main(String[] args) {		
		boolean ss=false;
		WhileTest w = new WhileTest();
		Refer r =w.test(10);
		System.out.println("r.sµÄÖµ£º"+r.getS());
		while(!ss){		
		  w.me(ss);
	
		}
		
		
	}
	 class Refer{
		private int s;

		public int getS() {
			return s;
		}

		public void setS(int s) {
			this.s = s;
		}
		
		
	}

}
