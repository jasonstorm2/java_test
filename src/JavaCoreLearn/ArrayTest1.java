package JavaCoreLearn;
import java.lang.reflect.Array;

/**
 * 反射中的 数组类，Array
 * Array对象可以代表所有的数组
 * 
 * 动态创建并操作 数组
 * @author LiZhenhua
 *
 */
public class ArrayTest1 {
	public static void main(String[] args) {
		try{
			//创建一个元素类型为String，长度10的数组
			Object arr = Array.newInstance(String.class, 10);
			//setXXX(Object obj,int index,xxx value)
			Array.set(arr, 5, "疯子");
			Array.set(arr, 6, "水电费");
			
			//getXXX(Object ob,int index) 返回array数组第index个元素，
			Object ob1 = Array.get(arr, 5);
			Object ob2 = Array.get(arr, 6);
			Object ob3 = Array.get(arr, 0);
			
			System.out.println("ob1:"+ob1);
			System.out.println("ob2:"+ob2); 
			System.out.println("ob3:"+ob3); 
			System.out.println(Array.get(arr,9));
			
		}catch(Throwable e){
			System.err.println(e);
		}
	}

}
