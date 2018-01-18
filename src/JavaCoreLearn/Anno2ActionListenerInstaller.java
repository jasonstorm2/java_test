package JavaCoreLearn;

import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.AbstractButton;

/**
 * 本类的作用是：绑定 成员变量对象 和 成员变量的注释类的参数对象
 * 
 * 通过反射，获取成员变量的注释类对象里面的参数，本例的参数是ActionListener类Class对象
 * 通过反射，获取成员变量的对象，（强制转化(AbstractButton)fObj）
 * 
 * 
 * @author Administrator
 *
 */
public class Anno2ActionListenerInstaller {
	int a = 2;
	int b = 3;
	public static void processAnnotations(Object obj) {

		try {
			Class<? extends Object> cl = obj.getClass();
			for (Field f : cl.getDeclaredFields()) {//遍历类是成员变量
				//将该成员变量设置成可自由访问
				f.setAccessible(true);
				Anno2Mine a = f.getAnnotation(Anno2Mine.class);//获得 成员变量注释（可能没有注释对象）
				Annotation as = f.getAnnotation(Anno2Mine.class);
				Object fObj = f.get(obj);//获得 成员变量对象（以便判断对象类型）
				
				if(a !=null && fObj !=null && fObj instanceof AbstractButton){
					Class<? extends ActionListener> listenerClazz = a.listener();//获取元数据 Class对象					
					ActionListener al = listenerClazz.newInstance();//实例化
					AbstractButton ab = (AbstractButton)fObj;
					
					ab.addActionListener(al);			//给按钮设置监听					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
