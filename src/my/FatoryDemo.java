package my;

interface car{
	public void drive();
}
class Benz implements car{

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("this is benz");
	}
	
}

class BMW implements car{

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("this is BMW");
		
	}
	
}

class CarFatory{
	public static car makeCar(String str){
		if(str.equalsIgnoreCase("benz")){
			return new Benz();
		}else if(str.equalsIgnoreCase("bmw")){
			return new BMW();
		}else{
			return null;
		}
		
	}
}

public class FatoryDemo {
	public static void main(String[] args) {
		car c = CarFatory.makeCar("BENZ");
		if(c!=null){
			c.drive();
		}
	}	
}

