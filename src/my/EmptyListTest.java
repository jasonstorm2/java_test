package my;

import java.util.Collections;
import java.util.List;

/**
 * 测试 空集合。
 * 空集合的值不能改变，也就是说无法添加新的值
 * @author LiZhenhua
 *
 */
public class EmptyListTest {
	public static void main(String[] args) {
		List<String> list = Collections.emptyList();
//		list.add("a"); // 报错不能添加
		System.out.println(list.size());
		System.out.println(list);
		for (String string : list) {
			System.out.println(string);
		}
		if(list != null){
			System.out.println("集合不为空");
//			list.get(0); //报错
		}
		
	}

}
