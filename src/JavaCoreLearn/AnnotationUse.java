package JavaCoreLearn;

import java.lang.annotation.Annotation;

public class AnnotationUse {
	public static void main(String args[]){
		System.out.println(AnnotationUse.class.isAnnotationPresent(AnnotationTest.class));
		System.out.println(first.class.isAnnotationPresent(AnnotationTest.class));
		System.out.println(second.class.isAnnotationPresent(AnnotationTest.class));
		
		try {
			Annotation[] arr = Class.forName("JavaCoreLearn.second").getAnnotations();
			for(Annotation a : arr){
				System.out.println("annotation:"+a);
				if(a instanceof AnnotationTest){
					System.out.println("a is:"+a);
					System.out.println(((AnnotationTest) a).name());
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

@AnnotationTest(name="ji")
class first{
	
}

class second extends first{
	
	
}
