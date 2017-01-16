package java8;

/**
 * 继承接口时，接口中的默认方法 不用重写
 * 必须重写 接口中的抽象方法（接口中的方法默认是抽象的）
 * @author Administrator
 *
 */
public class impleLikeExtends implements interfacePeople, interfacePerson {

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("继承");

	}

	public static void main(String[] arsf) {
		impleLikeExtends im = new impleLikeExtends();
		im.action();
		im.get();
		im.get2();
		im.sayHello();

	}

}
