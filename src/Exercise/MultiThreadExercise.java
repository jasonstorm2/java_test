package Exercise;


class Print{

	boolean begin;
	int count=1;
	
	public synchronized void printNum() {
		if(!begin){
			for (; count < 53; count++) {
				System.out.println(count);	
				if(count%2==0){
					begin = true;
					try {
	    				notifyAll();
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}else{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}	
    public synchronized void printLetter(){
    	char a = 'A';
		/*  输出字母的方法
		for (int i = 65; i < 91; i++) {
		    System.out.printf("%c\n",i);
			System.out.println((char)i);
		}
		*/
    	if(begin){    		
    		for (; a < 91; a++) {    			
    			System.out.println(a);
    			if(a==90){
    				a = 'A';
    			}
    			begin = false;
    			try {    				
    				notifyAll();
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
    	}else{
       		try {
    				wait();
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	}		
	}
}
class num extends Thread{	
	private Print pt;
	public num(Print pt){
		this.pt = pt;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		pt.printNum();
	}
}

class letter extends Thread{
	
	private Print pt;
	public letter(Print pt){
		this.pt = pt;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		pt.printLetter();
	}
}

public class MultiThreadExercise {
	public static void main(String[] args) {
		Print pt = new Print();
		new num(pt).start();
		new letter(pt).start();		
	}
}
