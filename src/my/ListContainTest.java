package my;

import java.util.ArrayList;
import java.util.List;

/**
 * ListµÄ contain ·½·¨²âÊÔ
 * @author Administrator
 *
 */
public class ListContainTest {
	public static void main(String[] args) {
		containTest();
		
	}	
	public static void containTest(){
		List<Integer> baseList =  new ArrayList<Integer>();
		baseList.add(1);
		baseList.add(2);
		baseList.add(3);
		baseList.add(4);
		baseList.add(5);
		
		List<Integer> norList =  new ArrayList<Integer>();
		norList.add(1);
		norList.add(2);
		norList.add(5);
		norList.add(6);
		norList.add(7);
		
		List<Integer> conList =  new ArrayList<Integer>();
		
		for(Integer i : norList){
			if(!baseList.contains(i)){
				conList.add(i);
			}
		}
		
		System.out.println(conList.toString());
		
		
	}

}
