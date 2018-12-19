package my;

/**
 * 求余和取整，以及double参数的结果
 * 
 *  a/b 斜杠：	如果a和b都是整数，表示求余，
 *  		  	如果有一个参数是带小数的的数，结果是double小数
 *  
 *  
 *  a%b 百分号：表示取整
 * 
 */
public class DivisionAndRemainderTest {
	public static void main(String[] args) {
		System.out.println("0/2.0取整"+0/2);//0
		System.out.println("1/2.0取整"+1/2);//0
		System.out.println("2/2.0取整"+2/2);//1
		System.out.println("3/2.0取整"+3/2);//1
		System.out.println("4/2.0取整"+4/2);//2
		System.out.println("5/2.0取整"+5/2);//2
		utils.utils.PrintLine("取整");
		System.out.println("6/2取整"+6/2);//3
		System.out.println("7/2取整"+7/2);//3		
		System.out.println("0%2求余"+0%2);//0
		System.out.println("1%2求余"+1%2);//1
		System.out.println("2%2求余"+2%2);//0
		System.out.println("3%2求余"+3%2);//1
		System.out.println("4%2求余"+4%2);//0
		System.out.println("5%2求余"+5%2);//1
		utils.utils.PrintLine("double参数");
		System.out.println("0/2.0取整"+0/2);//0
		System.out.println("1/2.0取整"+1.0/2);//0.5
		System.out.println("2/2.0取整"+2/2.0);//1.0
		System.out.println("3/2.0取整"+3/2.0);//1.5
		System.out.println("4/2.0取整"+4/2.0);//2.0
		System.out.println("4/2.0取整"+1/3.0);//0.3333333333333333
		
	}

}
