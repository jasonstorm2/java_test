package my;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Method反射：
 * 1，Class对象通过getMethod方法获得Method对象
 * 2，Method对象通过invoke（Class.newInstance）方法调用方法
 * Field反射：
 * 1，Class对象通过getField方法获得Field对象
 * 2，Field对象通过get（Class.newInstance）方法 获得域值
 * @author LiZhenhua
 *
 */
public class MethodInvokeTest {
	public static void main(String[] args){
		
		try {
			Class<?> newoneClass = Class.forName("my.Bird");//在本类中。非public类
			Object obj = newoneClass.newInstance();//通过类对象，实例化
			
			/**
			 * invoke(方法所在的对象，方法的参数);----对象方法
			 * invoke(null,方法参数)；------------类方法
			 */
			if(obj instanceof Bird){
				Method getType = newoneClass.getMethod("getType");//通过类对象，获得方法对象
				getType.invoke(obj);//方法对象invoke类对象实例，即反射调用方法
				
				Method setAge  = newoneClass.getMethod("setAge", int.class);
				setAge.invoke(obj, 121);//对象方法
				
				Method statement = newoneClass.getMethod("statement");
				statement.invoke(null);//类方法
				
				Method statement2 = newoneClass.getMethod("statement2",String.class);
				statement2.invoke(null,"大雁");//类方法
			}
			System.out.println("----------------------------------------------");
			if(newoneClass.isAssignableFrom(Bird.class)){
				Method getType = Bird.class.getMethod("getType");
				getType.invoke(obj);
				
				Method setAge  = Bird.class.getMethod("setAge", int.class);
				setAge.invoke(obj, 122);
				
				Method statement = Bird.class.getMethod("statement");//类方法
				statement.invoke(null);//类方法
				
				Method statement2 = Bird.class.getMethod("statement2",String.class);//类方法
				statement2.invoke(null,"大雁");//类方法
			}
			
			System.out.println("----------------------");
			/**看看反射调用之后，类中Field的值是否改变**/
			//可以改变，因为实例已经生成--newoneClass.newInstance()，程序已经为实例分配的储存空间
			Field age = newoneClass.getField("age");
			Field weight = newoneClass.getField("weight");
			Class<?> clazz= age.getType();
			if(clazz.isAssignableFrom(int.class)){
				int o = (int)age.get(obj);//反射获得域值
				System.out.println("是否改变了域值？："+o);
			}else{
				System.out.println("这并不是一个int数值");
			}
			int i = (int) weight.get(obj);
			System.out.println("重量："+i);
			System.out.println("----------------------");
			/****直接设置Field的值，不通过调用方法：关键是可能不知道Field的类型。强制赋值会出错*****/
			/**private 域值设置**/
			Field type = newoneClass.getDeclaredField("type");
			type.setAccessible(true);
			System.out.println("原来的值："+type.get(obj));
			type.set(obj, "fuckyou");
			System.out.println("改变 后的值："+type.get(obj));
			/**public 域值设置**/
			Field weight2 = newoneClass.getField("weight");
			/**getInt，知道具体类型***/
			System.out.println("weight原值："+weight2.getInt(obj));
			/**get（obj），不知道具体类型***/
			System.out.println("weight原值："+weight2.get(obj));
			weight2.setInt(obj, 50);
			System.out.println("weight改变后的值："+weight2.getInt(obj));
			weight2.set(obj, 51);
			System.out.println("weight改变后的值："+weight2.getInt(obj));

			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class Bird{
	public int age = 10;
	public int weight = 15;
	private String type = "little";
	
	public void getType(){
		System.out.println("方法1getType:"+type);
	}
	
	public void setAge(int age){
		this.age = age;
		System.out.println("方法2age:"+age);
	}
	
	public static void statement(){
		System.out.println("方法3statement:鸟可以飞");
	}
	
	public static void statement2(String str){
		System.out.println("方法4statement:"+str+"可以飞");
	}	
}
