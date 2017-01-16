package my;

/**
 * 接口 和 继承 对象类型的判断instanceof
 * @author Administrator
 *
 */
interface A{}
class B implements A{
 
}
class C extends B {
 
}

public class InstanceOfTest {
 public static void main(String[] args){
    A a=null;
    B b=null;
    boolean res; 
    
    System.out.println("instanceoftest test case 1: ------------------");
      res = a instanceof A; 
      System.out.println("a instanceof A: " + res);
      
      res = b instanceof B;
      System.out.println("b instanceof B: " + res);
      
    System.out.println("instanceoftest test case 2: ------------------");   
    a=new B();
    b=new B();
    
    res = a instanceof A; 
    System.out.println("a instanceof A: " + res);
    
    res = a instanceof B;
    System.out.println("a instanceof B: " + res);

    res = b instanceof A;
    System.out.println("b instanceof A: " + res);
    
    res = b instanceof B;
    System.out.println("b instanceof B: " + res);
   
    System.out.println("\ninstanceoftest test case 3: ------------------");
    
    /*instanceof判断的是运行时类型*/
    B b2=new C();    
    res = b2 instanceof A;
    System.out.println("b2 instanceof A: " + res);
    
    res = b2 instanceof B;
    System.out.println("b2 instanceof B: " + res);
    
    res = b2 instanceof C;
    System.out.println("b2 instanceof C: " + res);
 }
}

