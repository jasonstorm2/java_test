package Chapter18;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericTypeTesy {
	
	private Map<String,Integer> score;  //这个Map是泛型：Map<K,V>
	private String str;
	
	public static void main(String[] args) throws Exception {
		Class<GenericTypeTesy> clazz = GenericTypeTesy.class;
		
		Field f = clazz.getDeclaredField("score");		
		Class<?> a = f.getType();		//只能获取 普通类型成员变量的 数据类型	
		System.out.println("score的类型是："+a);
		
		Type gType = f.getGenericType();//泛型成员变量 用这个方法取
		System.out.println("gType:"+gType);
		if(gType instanceof ParameterizedType){
			ParameterizedType pType = (ParameterizedType)gType; //将gType类型强制转换为ParameterizedType类型
			//获取原始类型
			Type rType = pType.getRawType();
			System.out.println("原始类型是："+rType);
			//取得泛型类型的 泛型参数
			Type[] tArgs = pType.getActualTypeArguments();//---一个数组
			System.out.println("泛型信息是：");
			
			for (int i = 0; i < tArgs.length; i++) {
				System.out.println("第"+i+"个泛型类型是："+tArgs[i]);
			}			
		}else{
			System.out.println("获取泛型类型出错！");
		}
					
	}
}
