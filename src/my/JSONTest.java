package my;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
 * @author Administrator
 *
 */
public class JSONTest {
	public static void main(String[] args) {
		String s = "[ { \"what\"  :  500,   \"sn\":   1,\"tit\":\"attact\"},   {\"country\":\"america\",\"sn\":2,\"male\":\"girl\"}]";
		String s2 = "[[1000,1000,0,0,\"common\"]]";
		String s3 = "jb";
		if(isJSONAraay(s)){
			JSONArray ja = JSON.parseArray(s);
			JSONObject jb = JSON.parseObject(ja.getString(0));

			System.out.println("直接打印JSONArray内容：        "+ja);
			System.out.println("toJSONString打印JSONArray内容: "+ja.toJSONString());
			//转换为jsonobj
			System.out.println("转换成jsonobject:，toJSONString:  "+jb.toJSONString());
			System.out.println("转换成jsonobject,直接打印:        "+jb);
			System.out.println("打印jsonobject的内容key == sn："+jb.getIntValue("sn"));
			
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

}
