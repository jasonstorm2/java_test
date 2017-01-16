package my;

import Library.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;



public class JsonStringTest {
	static String str = "{'def':18,'hpMax':1192}";
	public static void main(String[] args) {
		/**取出 一个没有储存的键名 是否报错？		----不存在的键名，值默认为0 **/
		JSONObject obj = Utils.toJSONObject(str);
		int numSurplus = obj.getIntValue("112");
		int  def = Integer.valueOf(obj.getString("def"));	

		System.out.println("取出 一个没有储存的键名 是否报错？:"+numSurplus);
		System.out.println("取出 一个没有储存的键名 是否报错？:"+def);
		
		/**判断键名是否存在***/
		obj.put("jiba", "la");
		System.out.println(obj.toJSONString());
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


	}

}
