package JavaCoreLearn;

import java.lang.reflect.Field;

class Person{
	private String name;
	private int age;
	public String toString(){
		return "Person[name:"+name+",age:"+age+"]";
	}
}
public class FieldTest {
	public static void main(String[] args) throws Exception {
		Person p = new Person();
		Class<Person> personClazz = Person.class;
		
		Field nameField = personClazz.getDeclaredField("name");
		
		System.out.println("nameField的名："+nameField.getName());
		//设置 通过反射访问该变量时 取消访问权限检查
		nameField.setAccessible(true);
		//field.getXXX(Obj) 获取obj对象的 该成员变量， xxx对应8种基本类型，如果是引用类型，XXX则取消
		System.out.println("nameField的值："+nameField.get(p));
		nameField.set(p, "Yeeku");
		
		Field ageField = personClazz.getDeclaredField("age");
		ageField.setAccessible(true);
		//field.setXXX(Obj) 设置obj对象的 该成员变量， xxx对应8种基本类型，如果是引用类型，XXX则取消
		ageField.set(p, 30);
		System.out.println(p);
		
		
	}

}
