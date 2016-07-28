package java8;

public class Sam implements interfacePerson{
	
	public static void main(String[] args) {
		Sam ss = new Sam();
		ss.sayHello();
		ss.action();

		
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("实现接口的重写方法");
	}

}
