package my;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
	public static void main(String[] args) {
		List<Object> l = new ArrayList<>();
		String two = new String("bb");
		Integer d = new Integer(5);
		
		 l.add("aa");
		 l.add("aa");
		 l.add(two);
		 l.add("cc");
		 l.add(d);
		 l.add(d);
		 
		 for (Iterator iter = l.iterator(); iter.hasNext();) {
			  Object str =iter.next();
			  System.out.println(str);
			 }
		 
		 
		 Iterator iter = l.iterator();
		 while(iter.hasNext()){
			 Object str = iter.next();
		  if(str=="bb"){
			  iter.remove();
			  continue;
		  }
		  System.out.println("使用迭代器Iterator:"+str);
		 }
		 for(int i=0;i<l.size();i++){
			 System.out.println("使用list的下标index："+l.get(i));
			 
		 }
		 
		
	}
}
