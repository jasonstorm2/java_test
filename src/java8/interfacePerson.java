package java8;

public interface interfacePerson {
	//http://www.examw.com/java/jichu/204270/index-2.html
	//接口可以实现一个默认的方法
	default void sayHello(){
		System.out.println("接口的默认方法Hello");
		
	}
	void action();

}
