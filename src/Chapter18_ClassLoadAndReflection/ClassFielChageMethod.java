package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.Field;

public class ClassFielChageMethod {
	/**
	 * 反射动态改变域field
	 */
	public static void resetField(Object obj) throws IllegalArgumentException, IllegalAccessException{		

	    Class<? extends Object> cl = obj.getClass();
			
			for (Field f : cl.getDeclaredFields()) {
				//将该成员变量设置成可自由访问			
				f.setAccessible(true);
				if(f.getType() == String.class ){//修改制定类型的数据
					f.set(obj, "Jessica");
				}			
				
				f.setAccessible(false);
		    }		
		}

}
