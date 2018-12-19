package my;



/**
 * 测试eclipse java 的 断点功能呢
 * @author LiZhenhua
 * 
 * 1.强制退出当前断点所在的方法： alt+shift+F。退出后，方法内断点后面的部分不执行
 * 
 * 2.条件断点：右键断点，设置参数
 * 
 * 3.variables 视图改变参数值
 * 
 * 4.异常断点
 * 
 * 5.表达式视图的使用 --  提前计算表达式的值。或者改变参数的值
 * 
 * 在程序中断点，断点时，
 * 		1.可以在表达式视图输入相关变量的表达式，计算表达式的值。
 * 		2.也可以在程序中，选中要计算的表达式，右击选择 inspect 选项，或者 ctrl+shift+i
 * 注意： 表达式在执行时生效，测试完后要及时删掉
 *
 */
public class DebugTest {
	/**
	 * 阶乘	
	 * @param value
	 * @return
	 */
	public static int factorial(int value){	
			if(value==1){
				return value;
			}else {	
				return value*factorial(value-1);
			}		
	}	
	
	/**
	 * 条件断点：
	 * 当i==500时，断点生效
	 * 
	 * 1.右键断点，选择 breakpoint.properties
	 * 选择 conditional
	 * 在输入框，设置指定的参数 i==500.
	 * 当i==500时，断点才会生效
	 * 
	 * 2.在 variables 视图，可以右键改变参数的值 同样可以有这个效果
	 */
	public static void forDebug(){
		int value = 0;
		for (int i = 0; i < 1000; i++) {			
			value += i;
		}
		forDebug2();
		System.out.println("累加："+value);
	}
	
	public static void forDebug2(){
		int i=0;
		int s=1;
		int t=5;
		System.out.println("forDebug2:"+(s+t+i));
		System.out.println("forDebug2:"+s+t+i);
	}
	
	/**
	 * 主动抛出异常对象
	 * 
	 * 测试:在抛出指定异常的地方自动断点
	 * 断点视图的 添加异常断点 中，添加指定的异常类型
	 * 
	 * 1.如果不主动添加异常断点。效果还是一样。程序会在异常出断点挂起
	 * @throws MyExceptoin 
	 */
    public static void exceptionDebug(boolean alter) throws MyExceptoin{
    	if(alter){
    		throw new NullPointerException();
    	}else{
    		throw new MyExceptoin();
    	}
    }
	
	public static void main(String[] args) throws MyExceptoin {
		forDebug();
		System.out.println("阶乘："+factorial(10));
		
		utils.utils.PrintLine("抛出异常测试");
		exceptionDebug(true);
	}
}


/**
 * 如何自定义一个异常类 
 * 
 * 1.自定义的异常类继承现有的异常类 
 * 2.提供一个序列号，提供几个重载的构造器 * 
 * @author LiZhenhua
 *
 */

class MyExceptoin extends Exception{

	/**
	 * 自动生成的序列号
	 */
	static final long serialVersionUID = -5028318153622440729L;
	
	public MyExceptoin() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public MyExceptoin(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	
}
