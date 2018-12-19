package my;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import utils.utils;

/**
 * 测试 jsonarray 转换成 jsonobject
 * 
 * JSON.parseArray（str）方法，将 String 转换成 JSONArray，如果不是则报错，以此写判断String是否能转换成JSONArray的方法
 * JSON.parseObject（str） 把string 转换成 jsonobject
 * 
 * jasonArray.getString(0) 取出具体下标的值，可以转换成 jsonobject
 * 
 * 直接打印JSONArray或JSONObject 和 toJSONString()方法 打印出来的String字符串都一样
 * 
 * JSON.toJSONString(obj) 可以把任意 object转换为 string格式
 * jb 的 toJSONString 和 ja 的toJSONString 是调用父类 JSON 的toJSONString 方法，是同一个方法
 * 
 * @author LiZhenhua
 *
 */
public class JSONTest {
	public static void main(String[] args) {
		String s = "[ { \"what\"  :  500,   \"sn\":   1,\"tit\":\"attact\"},  "
				+ "{ \"what\"  :  400,   \"sn\":   11,\"tit\":\"attact\"},"
				+ "{ \"what\"  :  200,   \"sn\":   2,\"tit\":\"attact\"}"				
				+ "]";
		String s2 = "{ \"what\"  :  500,   \"sn\":   3,\"tit\":\"attact\"}";
		
		
		
		
		String s3 = "{ \"what\"  :  402,   \"sn\":   14,\"tit\":\"attact\"}";
		String s4 = "{ \"what\"  :  430,   \"sn\":   17,\"tit\":\"attact\"}";
		String s5 = "{ \"what\"  :  404,   \"sn\":   10,\"tit\":\"attact\"}";
		
		if(isJSONAraay(s)){
			JSONArray ja = JSON.parseArray(s);
			JSONObject jb = JSON.parseObject(s2);
			JSONObject jb2 = JSON.parseObject(s2);
			JSONObject jb3 = JSON.parseObject(s3);
			JSONObject jb4= JSON.parseObject(s4);
			JSONObject jb5 = JSON.parseObject(s5);
			ja.add(jb2);
			ja.add(jb3);
			ja.add(jb4);
			ja.add(jb5);
			System.out.println("JSONArray 添加JSONObject后，顺序："+ja.toJSONString());


			System.out.println("直接打印JSONArray内容：        "+ja);
			System.out.println("toJSONString打印JSONArray内容: "+ja.toJSONString());
			//转换为jsonobj
			System.out.println("转换成jsonobject:，toJSONString:  "+jb.toJSONString());
			System.out.println("转换成jsonobject,直接打印:        "+jb);
			System.out.println("打印jsonobject的内容key == sn："+jb.getIntValue("sn"));
			
			List<JSONObject> data = new ArrayList<JSONObject>();
			data.add(jb);
			data.add(jb);
			data.add(jb);
			
			System.out.println("jsonlist转换成 string："+set(data));
			
			String str = set(data);
			List<JSONObject> data2 = get(str); 
			data2.get(1).toJSONString();
			
			for(JSONObject j : data2){
				System.out.println("string 转换List<JSONObject> 后，逐一打印："+j.toJSONString());
			}
			
			
		}else{
			System.out.println("string不是jsonarray");
		}		
		if(isJSONAraay(s2)){
			JSONArray ja = JSON.parseArray(s2);
			JSONArray ja2 = JSON.parseArray(ja.getString(0));

			System.out.println("s2内容是：    "+ja);
			System.out.println("ja2(0):"+ja2.getString(0));
		}
		
		if(isJSONObject(s3)){
			System.out.println("s3是 jsonobject");
		}else{
			System.out.println("s3不是jsonobject");
		}
		
		JSONArray jarAll = new JSONArray();
		JSONArray jar1 = new JSONArray();
		jar1.add(1);
		jar1.add(0);
		jar1.add(new int[]{50,100});
		jar1.add(new int[]{4,11});
		
		JSONArray jar2 = new JSONArray();
		jar2.add(2);
		jar2.add(1);
		jar2.add(new int[]{50,100});
		jar2.add(new int[]{4,11});
		
		JSONArray jar3 = new JSONArray();
		jar3.add(1);
		jar3.add(1);
		jar3.add(new int[]{50,100});
		jar3.add(new int[]{4,11});
		
		JSONArray jar4 = new JSONArray();
		jar4.add(1);
		jar4.add(4);
		jar4.add(new int[]{50,100});
		jar4.add(new int[]{4,11});
		
		System.out.println("jar1.toJSONString():"+jar1.toJSONString());
		System.out.println("jar1.toString():"+jar1.toString());
		jarAll.add(jar1);
		jarAll.add(jar2);
		jarAll.add(jar3);
		jarAll.add(jar4);
		System.out.println("jarAll.toString():"+jarAll.toString());
		//{"p":[20],"b":0,"t":[4],"i":1}
		//[1,0,[50,100],[4,11]]
		
		int[] pri = null;
		pri = (int[]) jar3.get(2);
		System.out.println("强制转换："+pri);
		
		isInstanceOf(jar3.get(2));
		
		JSONRemoveTest(s3);
		
	}
	
	public static String isInstanceOf(Object obj){
		if(obj instanceof int[]){
			System.out.println("类型：int[]");
		}else if(obj instanceof String){
			System.out.println("类型：String");
		}else if(obj instanceof Integer){
			System.out.println("类型：Integer");
		}else if(obj instanceof Double){
			System.out.println("类型：Double");
		}else if(obj instanceof Float){
			System.out.println("类型：Float");
		}else if(obj instanceof JSONArray){
			System.out.println("类型：JSONArray");
		}
		return null;		
	}
	
	/**
	 * 判断String 是不是 JSONArray
	 * @param value
	 * @return
	 */
	public static boolean isJSONAraay(String value) {
		try {
			JSON.parseArray(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isJSONObject(String value) {
		try {
		    JSON.parseObject(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String set(List<JSONObject> data){
		 StringBuffer sb = new StringBuffer("[");  
		  
		 for(JSONObject jb :data){
			 if (sb.length() > 1) {  
                sb.append(',');  
            }
            sb.append(jb.toJSONString());
		 }            
        sb.append("]");
        return sb.toString();  
	}
	
	public static List<JSONObject> get(String str){
		List<JSONObject> data = new ArrayList<JSONObject>();
		
		str = str.replace("[", "").replace("]", "");
		String[] array = str.split(",");
		for (int i = 0; i < array.length; i ++) {		
			JSONObject jj = utils.toJSONObject(array[i]);
			data.add(jj);
		}
		return data;		
	}
	
	public static void JSONRemoveTest(String str){
		JSONObject jb = JSON.parseObject(str);
		System.out.println("删除前："+jb);		
		jb.remove("what");
		System.out.println("删除后："+jb);
		
	}

}
