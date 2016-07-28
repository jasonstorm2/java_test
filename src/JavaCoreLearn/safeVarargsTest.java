package JavaCoreLearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class safeVarargsTest {
	public static void main(String[] args) {
//		List list = new ArrayList<Integer>();
//		list.add(20);
//		
//		List<String> ls = list;
//		System.out.println(ls.get(0));
		
		List<String > s1 = new ArrayList<String>();
		s1.add("yi");
		
		List<String > s2 = new ArrayList<String>();
		s1.add("er");
		
//		safeVarargsTest.faultyMethod(s1,s2);
		safeVarargsTest.faultyMethod(Arrays.asList("Hello"),Arrays.asList("World"));
	}
	
	@SafeVarargs
	public static void faultyMethod(List<String>...paramlists ){
		List<String>[] methodList = paramlists;
		List<String> myList = new ArrayList<String>();
		myList.add("lala");
		
//		methodList[0] = myList;
		
		String s = paramlists[0].get(0);
		System.out.println("sµÄÖµ£º"+s);
		System.out.println(paramlists[0]+" "+paramlists[1]);
		System.out.println(paramlists[0].get(0)+" "+paramlists[1].get(0));
		
	}

}
