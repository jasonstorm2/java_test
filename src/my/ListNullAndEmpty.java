package my;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList 空和empty的比较
 * @author LiZhenhua
 *
 */
public class ListNullAndEmpty {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list2 = null;
		
		System.out.println(list);
		System.out.println(list2);
		if(list.isEmpty()){
			System.out.println("list是isEmpty的");
		}else{
			System.out.println("list不是isEmpty的");
		}
		
//		if(list2.isEmpty()){ // null 没有对象，无法调用List的方法
//			System.out.println("list2是isEmpty的");
//		}else{
//			System.out.println("list2不是isEmpty的");
//		}
		
		if(list2 == null){
			System.out.println("list2是null的");
		}
		
	}

}
