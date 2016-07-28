package my;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaBeanTest {
	public static void main(String[] args) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		JavaBean jb = new JavaBean();
		jb.setAge(18);
		jb.setName("张静姝");
		String[] childs=new String[]{"收到","滴定法"};
		jb.setWtf(childs);
		
		BeanInfo bi=Introspector.getBeanInfo(jb.getClass(), Object.class);
//		BeanInfo bi=Introspector.getBeanInfo(jb.getClass(), jb.getClass().getSuperclass());
		//开始自省        
        
	    /*     
	    * BeanInfo.getMethodDescriptors()     
	    * 用于获取该Bean中的所有允许公开的成员方法，以MethodDescriptor数组的形式返回     
	    *     
	    * MethodDescriptor类     
	    * 用于记载一个成员方法的所有信息     
	    * MethodDescriptor.getName()     
	    * 获得该方法的方法名字     
	    * MethodDescriptor.getMethod()     
	    * 获得该方法的方法对象（Method类）     
	    *     
	    * Method类     
	    * 记载一个具体的的方法的所有信息     
	    * Method.getParameterTypes()     
	    * 获得该方法所用到的所有参数，以Class数组的形式返回     
	    *     
	    * Class..getName()     
	    * 获得该类型的名字     
	    */		
		MethodDescriptor[] methodDescriptor = bi.getMethodDescriptors();
		System.out.println("bean类方法的个数："+methodDescriptor.length);
		for(MethodDescriptor m : methodDescriptor){
			//获得一个成员方法描述器所代表的方法的名字
			String methodName = m.getName();
			//獲得該方法的對象method類的。。。。對象
			Method methodObj = m.getMethod();			
			
			//通过方法对象获得该方法的所有参数，以Class数组的形式返回   
			Class[] paramters = methodObj.getParameterTypes();
			if(paramters.length>0){
				//获得参数的类型的名字
				System.out.println("方法=0的参数Class类型:"+paramters[0].getName());
				for(int j=1;j<paramters.length;j++){
					System.out.println("方法内所有>=1的参数Class类型："+paramters[j].getName());
				}
			}
			System.out.println("方法名methodName："+methodName);
			System.out.println("方法参数个数："+paramters.length);				
			System.out.println("***********************");
		}
		
		  /*     
		    * BeanInfo.getPropertyDescriptors()     ---域，有set和get的方法。必然不属于成员属性
		    * 用于获取该Bean中的所有允许公开的成员属性，以PropertyDescriptor数组的形式返回     
		    *     
		    * PropertyDescriptor类     
		    * 用于描述一个成员属性     
		    *     
		    * PropertyDescriptor.getName()     
		    * 获得该属性的名字     
		    *     
		    * PropertyDescriptor.getPropertyType()     
		    * 获得该属性的数据类型，以Class的形式给出     
		    *     
		    */ 
		
	      /*     
		    * BeanInfo.getEventSetDescriptors()     
		    * 用于获取该Bean中的所有允许公开的成员事件，以EventSetDescriptor数组的形式返回     
		    *     
		    * EventSetDescriptor类     
		    * 用于描述一个成员事件     
		    *     
		    * EventSetDescriptor.getName()     
		    * 获得该事件的名字     
		    *     
		    * EventSetDescriptor.getListenerType()     
		    * 获得该事件所依赖的事件监听器，以Class的形式给出     
		    *     
		    */ 
		PropertyDescriptor[] pd=bi.getPropertyDescriptors();
		
		 System.out.println("pd.length="+pd.length);
		  //演示如何get
	      for (int i = 0; i < pd.length; i++) {
	    	  
	         if(pd[i].getPropertyType().isArray())  //getPropertyType得到属性类型。
	         {
	            //getReadMethod()得到此属性的get方法----Method对象，然后用invoke调用这个方法
	            String[] result=(String[]) pd[i].getReadMethod().invoke(jb);
	            System.out.println("String[]数组属性的名字："+pd[i].getName()+":");//getName得到属性名字
	            for (int j = 0; j < result.length; j++) {
	               System.out.println("String[]数组的元素："+result[j]);
	            }
	         }
	         else
	         {
	            System.out.println(pd[i].getName()+":"+pd[i].getReadMethod().invoke(jb));
	            System.out.println("3");
	         }
	         System.out.println("………………………………………………………………………………");
	      }
	      for(PropertyDescriptor p :pd){
	    	  System.out.println("属性的名字.getName():"+p.getName());
	    	  System.out.println("属性的类型.getPropertyType():"+p.getPropertyType());
	    	  System.out.println("属性的值，调用invoke:"+p.getReadMethod().invoke(jb));
	    	  
	    	  System.out.println("---------------------------");
	    	   
	      }
	      
	      //set的應用
	      JavaBean pb0=new JavaBean();
	      //模拟一个数据（数据名字和javabean的属性名一致）
	      String name="luonan";
	      int age = 19;
	      String[] childname=new String[]{"xing","xian"};
	 
	      BeanInfo bi0=Introspector.getBeanInfo(pb0.getClass(), Object.class);
	      PropertyDescriptor[] pd0=bi0.getPropertyDescriptors();
	 
	      for (int i = 0; i < pd0.length; i++) {
	         if(pd0[i].getPropertyType().isArray()){
	            if(pd0[i].getName().equals("wtf")){ 
	            if(pd0[i].getPropertyType().getComponentType().equals(String.class))
	               {//getComponentType()可以得到数组类型的元素类型
	                  //getWriteMethod()得到此属性的set方法---Method对象，然后用invoke调用这个方法
	                  pd0[i].getWriteMethod().invoke(pb0,new Object[]{childname});
	               }
	            }
	         }
	         else{
	            if(pd0[i].getName().equals("name"))
	            {
	               pd0[i].getWriteMethod().invoke(pb0,name);
	            }
	            if(pd0[i].getName().equals("age")){
	            	 pd0[i].getWriteMethod().invoke(pb0,age);
	            }
	         }
	      }
	 
	      System.out.println("the name:"+pb0.getName());
	      String[] array=pb0.getWtf();
	      for (int i = 0; i < array.length; i++) {
	         System.out.println(array[i]);
	      }
	      System.out.println("the age:"+pb0.getAge());

	}
}
