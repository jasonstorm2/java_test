package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.Method;

public class MethodInvoke {
	public static void main(String[] args){		
		try {
			Class<?> clz = Class.forName("Chapter18.MethodBase");
			Object obj = clz.newInstance();
			Method m = clz.getMethod("fool", String.class);
			/**invoke**/
			m.invoke(obj, "jason");//Method.invoke(class,²ÎÊý);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
}
