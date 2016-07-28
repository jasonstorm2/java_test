package java8;

public class impleLikeExtends2 implements interfacePeople,interfacePerson{

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("继承");
		
	}
	
//	public void sayHello(){
//		System.out.println("重写了默认的方法");
//		
//	}
	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		interfacePerson.super.sayHello();
		System.out.println("重写了默认的方法");
	}
	public static void main(String[] args) {
		impleLikeExtends2 im = new impleLikeExtends2();
		im.action();
		im.get();
		im.sayHello();
		
		
	}
}
