package my;

import java.lang.Enum;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;

/**
 * 枚举类测试：
 * 通过反射，获得某类的所有枚举。
 * Class对象 getEnumConstants()可以获得该类的所有枚举，然后可以遍历枚举啦。。
 * @author LiZhenhua
 *
 */
public class EnumClassTest {
	public static void main(String[] args) {
		ArrayList<Enum<?>> result = new ArrayList<Enum<?>>();//枚举类--转换为--数组
		try {
			Class<?> myEnum = Class.forName("my.myEnum");
			Enum<?>[] enums = (Enum[])myEnum.getEnumConstants();
			System.out.println("枚举长度："+enums.length);
			for(Enum<?> e : enums){
				System.out.println("-----------------");
				System.out.println("e:"+e);
				System.out.println("e.name:"+e.name());
				myEnum myEnum1 = (myEnum)e;
				myEnum1.printName();
				myEnum1.printSomeThing();
				
				System.out.println("枚举所在类的名字："+e.getClass().getName());
				result.add(e);

			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/**
 * 一个普通类
 * @author LiZhenhua
 * 枚举的 switch应用
 */
class aClass{
	
	aEnum e = aEnum.a;
	public void change(){
		switch(e){
		case a:
			e = aEnum.b;
			break;
		case b:
			break;
		case c:
			break;
		case d:
			break;		
		}
		
	}
}

/**
 * 一个普通类的继承
 * @author LiZhenhua
 *
 */
class bClass extends aClass{
	
}

interface Food {
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }

    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}
/**
 * 一个枚举类
 * @author LiZhenhua
 *
 */
enum aEnum {
	a,
	b,
	c,
	d
}

enum myEnum {
	PEOPLE1("JSON"){
		@Override
		public void printName() {
			//实现自定义的方法
			System.out.println(name());
		}
	},
	PEOPLE2("ANDROW"){
		@Override
		public void printName() {
			System.out.println(name());
		}
	},
	PEOPLE3("ELIZABER"){
		@Override
		public void printName() {
			System.out.println(myEnum.PEOPLE3.name);
		}
	};
	private String name;

	public abstract void printName();

	public void printSomeThing(){
		System.out.println("haha");
	}

	private myEnum(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return "myEnum{" +
				"name='" + name + '\'' +
				'}';
	}
}
