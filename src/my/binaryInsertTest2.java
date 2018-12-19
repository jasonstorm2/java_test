package my;

import java.util.ArrayList;
import java.util.List;

/**
 * 假设list是降序
 * 求230在list中插入的位置
 * @author LiZhenhua
 *
 */
public class binaryInsertTest2 {
	
	static List<Integer> list = new ArrayList<Integer>();
	static{		
		list.add(800);//0
		list.add(700);//1
		list.add(600);//2
		list.add(500);//3
		list.add(400);//4
		list.add(300);//5
		//		 230
		list.add(200);//6
		list.add(100);//7
	}
	public static void main(String[] args) {
		int value = 50;
		int i = getMiddle(0, 7, value);
		System.out.println("插入位置："+i);
		list.add(9, value);
		System.out.println(list.toString());
	}
	
	/**
	 * 中间值求法，(start+end)/2 = middle
	 * 
	 * 怎么判断确定了值所在的范围，不用再进行二分计算？
	 * 当start和end分无可分时，即 end - start =1,就不必在二分计算
	 * 
	 * @param start
	 * @param end
	 * @param value
	 */
	static int getMiddle(int start,int end,int value){
		System.out.println("start:"+start + "  end:"+end);		
		if(end - start>1){  //可以区分
			int middle = (start + end)/2;		
			if(compare(list.get(middle), value)){
				return getMiddle(middle, end, value);
			}else{
				return getMiddle(start, middle, value);
			}
		}else{
			if(!compare(list.get(start), value)){
				return start;
			}
			if(!compare(list.get(end), value)){
				return end+1;
			}
			
		}
		return value;
	}
	
	/**
	 * 判断两个值的大小
	 * @param v1
	 * @param v2
	 * @return
	 */
	static boolean compare(int v1,int v2){
		if(v1 > v2){
			return true;
		}else{
		    return false;
		}		
	}
	
	

}
