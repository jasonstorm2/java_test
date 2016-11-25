package my;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 测试 a对象赋值给b对象后，b对象值的改变会不会影响到a对象。
 * 结论：会影响。。对象的赋值其实是引用Reference的传递
 * @author Administrator
 *
 */
public class ObjectReferenceTest {
	public Map<String, String> map = null;
	
	public  ObjectReferenceTest(){
		map = new HashMap<String, String>();
		map.put("1", "jack");
		map.put("2", "jessica");		
	}
	
	public static void main(String[] args) {
		ObjectReferenceTest ob = new ObjectReferenceTest();
		
		Map<String, String> map = ob.map;
		map.put("3", "test");
		for (Entry<String, String> en : map.entrySet()) {
			System.out.println("key:"+en.getKey());
			System.out.println("value:"+en.getValue());			
		}
		System.out.println("------------------------------------");
		for (Entry<String, String> en : ob.map.entrySet()) {
			System.out.println("key:"+en.getKey());
			System.out.println("value:"+en.getValue());			
		}
	}

}
