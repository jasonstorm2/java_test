package my;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 测试putall 方法是否是深复制
 * @author LiZhenhua
 *
 */
public class MapEntryCloneTest {
	public static void main(String[] args) {	
		
		Map<String, Do> map1 =new  HashMap<String, Do>();
		map1.put("1", new Do("a","a"));
		map1.put("2", new Do("b","b"));
		map1.put("3", new Do("ab","ab"));
		map1.put("4", new Do("abb","abb"));
		
		Map<String, Do> map2 = getMap(map1);

		
		map2.remove("1");
		
		for(Entry<String, Do> map : map1.entrySet()){
			System.out.println(map.getKey()+" "+map.getValue().getB());
		}
		System.out.println("--------------");
		
		for(Entry<String, Do> map : map2.entrySet()){
			System.out.println(map.getKey()+" "+map.getValue().getB());
		}
		
	}
	
	public static Map<String, Do> getMap(Map<String, Do> map){
		Map<String, Do> map1 =new  HashMap<String, Do>();
		
		for(Entry<String, Do> a :map.entrySet()){
			map1.put(a.getKey(), a.getValue());
		}		
		return map1;		
	}

}

class Do{
	private String s = null;
	private String b = null;
	 public Do(String s,String b){
		 this.s = s;
		 this.b = b;
		 
	 }
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	
}
