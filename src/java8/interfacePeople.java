package java8;

public interface interfacePeople {
	default void get(){
		System.out.println("get default method");
	}
	
	default void get2(){
		System.out.println("get2 default method");
	}

}
