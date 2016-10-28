package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.Array;
import java.util.Date;

public class GenericTypeFactory {
	
	//原始方法
	/*
	 * 通过类名，反射得到类对象，在反射得到类实例
	 */
	public static Object getInstance(String clsName){
		try{
			Class cls = Class.forName(clsName);
			return cls.newInstance();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//原始方法
	/*
	 * 通过类名，反射得到类对象，在反射得到类实例
	 */
	public static Object getInstance2(String clsName){
		try{
			Class<?> cls = Class.forName(clsName);
			return cls.newInstance();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//加入泛型
	/*
	 * 直接通过 类对象，反射得到类实例
	 */
	public static <F> F getInstance3(Class<F> cls){
		try{			
			return cls.newInstance();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//对 Array 的 newInstance方法进行包装
	@SuppressWarnings("unchecked")
	public static <T> T[] newInstance(Class<T> componentType,int length){
		
		return(T[])Array.newInstance(componentType, length);
		
	}
	public static void main(String[] args) {
		Date d = (Date) GenericTypeFactory.getInstance("java.util.Date");
		Date d2 = GenericTypeFactory.getInstance3(Date.class);
		
		//使用newInstance静态方法 创建 一维数组
		String[] arr = GenericTypeFactory.newInstance(String.class, 10);
		//使用newInstance静态方法 创建 二维数组
		int[][] intArr = GenericTypeFactory.newInstance(int[].class, 5);
		
		arr[5] = "测试插入";
		
		intArr[1] = new int[]{23,12	};//二维数组的元素 必须是 一维数组
		
		System.out.println(arr[5]);
		System.out.println(intArr[1][1]);
		System.out.println(arr[4]);
		
	}

}
