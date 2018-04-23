import java.util.HashMap;

import myl.earth;
import myl.moon;



class AFather
{
	static{
		System.out.println("A里面的静态代码块");
	}
    void aa()
    {
        System.out.println ("A里的");    
        
    }    
    
}
class B extends AFather
{
    void aa()
    {
        System.out.println ("B里的");    
        
    }    
    
}
class C extends AFather
{
    void aa()
    {
        System.out.println ("C里的");    
        
    }    
    
}
//newInstance: 弱类型。低效率。只能调用无参构造。 
//new: 强类型。相对高效。能调用任何public构造。

//下面给一个例子：Class的最大作用就是实现了动态加载类，为多态提供了很好的帮助。
//动态加载和创建Class 对象，比如想根据用户输入的字符串来创建对象
public class ClassDemo
{
    
    public static void main(String[] args)
    {        
 
    	Integer a = new Integer(3);
        Integer b = 3;                  // 将3自动装箱成Integer类型
        int c = 3;
        System.out.println(a == b);     // false 两个引用没有引用同一对象
//        System.out.println(a == c);     // true a自动拆箱成int类型再和c比较
    	
        System.out.println(c == a);     // true a自动拆箱成int类型再和c比较
    	
    	
    	ClassDemo t1=new ClassDemo();
        t1.show("C");//程序类名
        System.out.println("-----------动态加载--------------");
        C showc = new C();
        showc.aa();  //子类继承了父类，先实现父类
        System.out.println("-------------------------");
        
        ClassDemo t=new ClassDemo();
        t.show("C");//程序类名
        t.show("AFather");
        t.show("B");
        
        System.out.println("-------------------------");
        AFather show2 = new AFather();
        show2.aa();
        AFather sh = new AFather();
        sh.aa();
        
        moon ww = new moon();
        earth.descripe("导入的包只能在默认的包中使用吗？？？？");
        
    }
    void show(String name)
    {
        try
        {
        	//Class.forName(xxx.xx.xx) 返回的是一个类, .newInstance() 后才创建一个对象 
        	//Class.forName(xxx.xx.xx)的作用是要求JVM查找并加载指定的类，也就是说JVM会执行该类的静态代码段
             AFather show=(AFather)Class.forName(name).newInstance();
           
        show.aa();
    }
    catch(Exception e)
    {
        System.out.println (e);
        }
            
        
    }
    
    
}