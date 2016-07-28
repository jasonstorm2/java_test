package my;

import Library.Utils;

import com.alibaba.fastjson.JSONObject;



public class JsonStringTest {
	static String str = "{'def':18,'hpMax':1192}";
	public static void main(String[] args) {
		JSONObject obj = Utils.toJSONObject(str);
		int  def = Integer.valueOf(obj.getString("def"));	
		obj.put("jiba", "la");
		System.out.println(def);
		System.out.println(obj.toJSONString());
		

	}

}
