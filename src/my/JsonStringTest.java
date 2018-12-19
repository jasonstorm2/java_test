package my;

import Library.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;



/**
 * 测试 取出一个没有储存的键名 是否报错--给默认值了
 * 
 * list map jsonobje 打印出来的效果
 * 
 * 测试jsonobject 放入数组int[] 打印的效果是怎么样的
 * @author LiZhenhua
 *
 */
public class JsonStringTest {
	static String str = "{'def':18,'hpMax':1192}";
	public static void main(String[] args) {
		/**取出 一个没有储存的键名 是否报错？		----不存在的键名，值默认为0 **/
		JSONObject obj = Utils.toJSONObject(str);
		int numSurplus = obj.getIntValue("112");
		Object numSurplus2 = obj.get("112");
		int  def = Integer.valueOf(obj.getString("def"));	

		System.out.println("取出 一个没有储存的键名 是否报错？:"+numSurplus);
		System.out.println("取出 一个没有储存的键名 是否报错？:"+numSurplus2);
		System.out.println("取出 一个没有储存的键名 是否报错？:"+def);
		
		/**判断键名是否存在***/
		obj.put("jiba", "la");
		System.out.println("放入一个键值对："+obj.toJSONString());
		System.out.println("是否包含键jiba:"+obj.containsKey("jiba"));
		System.out.println("是否包含键jiba2:"+obj.containsKey("jiba2"));
		
		
		Map<String,Map<String,String>> mapM = new HashMap<String,Map<String,String>>();
		Map<String,List<Map<String,String>>> mapL = new HashMap<String,List<Map<String,String>>>();
		
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("key", "123456");
		map.put("value", "5356");
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("key", "1");
		map2.put("value", "2");
		Map<String,Integer> map3 = new HashMap<String,Integer>();
		map3.put("size", 1);
		map3.put("heigh", 150);
		
		mapM.put("FIRST",map);
		mapM.put("sec",map2);
		
		list.add(map);
		list.add(map2);
		
		mapL.put("wat", list);
		
		List<Map<String,String>> listmap = new ArrayList<Map<String,String>>();
		listmap.add(map);
		listmap.add(map2);
//		listmap.add(map3);
		
		System.out.println("Map<String,String>鐨勮浆鍖栦负json鐨勬牸寮忥細                    "+JSON.toJSONString(map));	
		System.out.println("Map<String,Map<String,String>>鐨勮浆鍖栦负json鐨勬牸寮忥細        "+JSON.toJSONString(mapM));	
		System.out.println("List<Map<String,String>>鐨勮浆鍖栦负json鐨勬牸寮忥細              "+JSON.toJSONString(listmap));	
		System.out.println("List<Map<String,String>>  toString锛�                      "+listmap.toString());	
		System.out.println("Map<String,List<Map<String,String>>>鐨勮浆鍖栦负json鐨勬牸寮忥細  "+JSON.toJSONString(mapL));	

		/*测试jsonobject 放入数组*/
		int[] lg = new int[]{1,2,3};
		JSONObject jb = new JSONObject();
		jb.put("a", 1);
		jb.put("b", "lala");
		jb.put("c", lg);
		
		System.out.println("jsonobject 放入数组："+jb.toJSONString());

	}

}
