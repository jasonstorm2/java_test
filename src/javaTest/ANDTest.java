package javaTest;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ANDTest {
	public static void main(String[] args) {
//		int a =-1;
//		a = getSubType3(16777475);
//		System.out.println(a);
//		initEquipPosJSON();
	    
		System.out.println(getRateSucc(100));
		String s = "[{\"1\":7,\"2\":0,\"3\":0,\"4\":0,\"5\":0,\"6\":0,\"7\":0,\"8\":0},{\"1\":5,\"2\":0,\"3\":0,\"4\":0,\"5\":0,\"6\":0,\"7\":0,\"8\":0}]";

		System.out.println(setEquipState(s, 1, 11));
		
		int sss = (int) Math.ceil(500 * (11100 / 10000.0) -500);// 向上取整	
		int sss2 = (int) Math.ceil(500 * (1100 / 10000.0));// 向上取整		

		System.out.println(sss);
		System.out.println(sss2);
	}
	
	public static int getSubType3(int type) {
		return type & 0Xffffff00;
	}
	
	public static String setEquipState(String str,int position,int lev){
		System.out.println(str);
		JSONArray ja = JSONArray.parseArray(str);
		JSONObject jb = ja.getJSONObject(0);
		jb.replace(String.valueOf(position), lev);
		return ja.toJSONString();
	}
	
	public static boolean getRateSucc(int rate){
		boolean through = false;
		int value = (int)(Math.random()*100);
		if(rate>=value){
			through = true;
		}
		System.out.println("value:"+value);
		return through;
	}
	
	public static void initEquipPosJSON(){
		JSONArray ja = new JSONArray();
		JSONObject jb = new JSONObject();
		jb.put(String.valueOf(1), 0);
		jb.put(String.valueOf(2), 0);
		jb.put(String.valueOf(3), 0);
		jb.put(String.valueOf(4), 0);
		jb.put(String.valueOf(5), 0);
		jb.put(String.valueOf(6), 0);
		jb.put(String.valueOf(7), 0);
		jb.put(String.valueOf(8), 0);
		
		JSONObject jb2 = new JSONObject();
		jb2.put(String.valueOf(1), 0);
		jb2.put(String.valueOf(2), 0);
		jb2.put(String.valueOf(3), 0);
		jb2.put(String.valueOf(4), 0);
		jb2.put(String.valueOf(5), 0);
		jb2.put(String.valueOf(6), 0);
		jb2.put(String.valueOf(7), 0);
		jb2.put(String.valueOf(8), 0);
		
		ja.add(jb);
		ja.add(jb2);
		
		
		System.out.println(ja.toJSONString());
		
	}
}
