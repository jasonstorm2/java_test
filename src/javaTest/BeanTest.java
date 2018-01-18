package javaTest;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.hdgf.streams.Stream;

import my.JavaBean;


/**
 * 内省(Introspector) 是Java 语言对 JavaBean 类属性、事件的一种缺省处理方法。
 * Java JDK中提供了一套 API 用来访问某个属性的 getter/setter 方法，这就是内省。
 * 这些API存放于包java.beans 中。
 * @author Administrator
 * 
 * 一般的做法是通过类Introspector的getBeanInfo方法获取某个对象的BeanInfo 信息,
 * 然后通过BeanInfo来获取属性的描述器(PropertyDescriptor),
 * 通过这个属性描述器就可以获取某个属性对应的getter/setter方法,然后我们就可以通过反射机制来调用这些方法。
 */

/*
*　	Introspector类:
	*	将JavaBean中的属性封装起来进行操作。在程序把一个类当做JavaBean来看，就是调用Introspector.getBeanInfo()方法，
*	得到的BeanInfo对象封装了把这个类当做JavaBean看的结果信息，即属性的信息。
	*	getPropertyDescriptors()，获得属性的描述，可以采用遍历BeanInfo的方法，来查找、设置类的属性
*/

/*　PropertyDescriptor类:

	PropertyDescriptor类表示JavaBean类通过存储器导出一个属性。主要方法：
	1. getPropertyType()，获得属性的Class对象;
	2. getReadMethod()，获得用于读取属性值的方法；getWriteMethod()，获得用于写入属性值的方法;
	3. hashCode()，获取对象的哈希值;
	4. setReadMethod(Method readMethod)，设置用于读取属性值的方法;
	5. setWriteMethod(Method writeMethod)，设置用于写入属性值的方法。
* 
*/
public class BeanTest {
	public static void main(String[] args) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		ABean ab = new ABean();
		ab.setCock("几把");
		ab.setFrequence(new int[]{1,3,4});
		ab.setHowbig(20);
		ab.setNotget("没有get方法");
		ab.notset = "没有set方法";
		ab.field = "没有setget的域";
		/**
		 * Introspector.getBeanInfo()内省，得到bean类的相关信息
		 * BeanInfo.getMethodDescriptors(); 只能得到public类型的方法，protected和private无法获取（在程序把一个类当做JavaBean来看，bean没有私有类）
		 */
//		BeanInfo bi = Introspector.getBeanInfo(ab.getClass(),Object.class);//得到javabean的信息。得到javabean 的所有方法，不统计父类Object.class，共13个
		BeanInfo bi = Introspector.getBeanInfo(ab.getClass(),JavaBean.class);//得到javabean 的所有方法，不统计父类javaBean.class，共6个
//		BeanInfo bi = Introspector.getBeanInfo(ab.getClass());//得到javabean 的所有方法，包括父类的方法，总共22个
//		BeanInfo bi = Introspector.getBeanInfo(Object.class);
		
		//see how many methods the class "ab"have
		MethodDescriptor[] md = bi.getMethodDescriptors();//得到ABean类的方法的描述符，即ABean的方法有几个。从中可以获得Method对象
		PropertyDescriptor[] pd = bi.getPropertyDescriptors();//尼玛，只要有一个getter或者setter就算是有一个属性值了。！！！如果两者都没有，那么就没有属性值
		System.out.println("****PropertyDescriptor的个数："+pd.length);		
		System.out.println("class 'ab' has "+md.length+" Methods");
		//遍历各个方法名，方法参数
		for(MethodDescriptor m : md){
			System.out.println("方法名："+m.getName());				//方法名：getClass
			Method method = m.getMethod();							//取出Method对象就可以利用反射啦。。。。
			Class<?>[] paramtypes = method.getParameterTypes();//返回方法的参数类对象数组
			
			if(paramtypes.length>0){
				System.out.println("方法有参数");
				for(Class<?> p : paramtypes){
					System.out.println("方法参数所表示的类型的类的名字："+p.getSimpleName());
				}
			}else{
				System.out.println("方法无参数");
			}
			System.out.println("………………………………………………");
		}
		
		System.out.println("******************遍历PropertyDescriptor******************");

		for(PropertyDescriptor p : pd){
			System.out.println("元素的名字："+p.getName());				//元素的名字：howbig
			System.out.println("元素的类型："+p.getPropertyType());		//元素的类型：int
			if(p.getPropertyType().isArray()){
				if(p.getName().equals("frequence")){
					int[] ss = (int[]) p.getReadMethod().invoke(ab);	//反射调用元素的getter方法
					System.out.print("元素的值：");						//元素的值    ：20
					for(int s :ss){
						System.out.print(s+",");
					}
					System.out.println();
				}
			}else{
				//notget变量没有get方法，此处会报错
				System.out.println("元素的值--    ："+p.getReadMethod().invoke(ab));
			}			
			System.out.println("&&&&&&&&&&&&&&");
		}
		
		//use the setter method
		for(PropertyDescriptor p : pd){
			if(p.getName().equals("cock")){
				p.getWriteMethod().invoke(ab,"dick");					//反射调用元素的setter方法
			}else if(p.getName().equals("frequence")){
				p.getWriteMethod().invoke(ab,new int[]{11,22,33});
			}else if(p.getName().equals("howbig")){
				p.getWriteMethod().invoke(ab,50);
			}else if(p.getName().equals("notset")){
				p.getWriteMethod().invoke(ab,"ll"); //变量没有set方法，此处会报错
			}
			
			System.out.println(p.getName()+" 改变后的值："+p.getReadMethod().invoke(ab));
		}
		//反射的一点小应用
		System.out.println("有setget的域的数量："+pd.length);
		Field[] fields = ab.getClass().getFields();
		System.out.println("bean中域的数量："+fields.length);			  //获得本类和父类中所有的field的数量
		System.out.println("*****各个域的名字****");
		for(Field f : fields){
			System.out.println(f.getName());
		}
		System.out.println("*****各个域的名字****");
		ab.isAlive = true;
		System.out.println("``````````````````");
		for(Field f : fields){			
			if(f.getName().equals("isAlive")){
				System.out.println("isAlive is exist!!");
				System.out.println("isAlive的值是："+f.get(ab));
			}
			if(f.getName().equals("cock")){
				System.out.println("cockjiji的值是："+f.get(ab));
			}
		}
		
	
	}
}

class ABean extends JavaBean{
	/***有setget的域***/
	public int howbig;
	public String cock;
	public int[] frequence;
	/***没有setget的域***/
	public String field;
	public boolean isAlive;
	
	public String notset;
	public String notget;
	
	public String getNotset(){
		return notset;
	}
	public void setNotget(String str) {
		this.notget = str;
	}
	
	
	public int getHowbig() {
		return howbig;
	}
	public void setHowbig(int howbig) {
		this.howbig = howbig;
	}
	public String getCock() {
		return cock;
	}
	public void setCock(String cock) {
		this.cock = cock;
	}
	public int[] getFrequence() {
		return frequence;
	}
	public void setFrequence(int[] frequence) {
		this.frequence = frequence;
	}	
	
	public void non(){
		
	}

}
