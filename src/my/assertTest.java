package my;

/**
 * 
 * 正常发布的代码都是断言无效的，即正常发布的代码中断言语句都不不执行的（或不起作用的）
 * 
 * assert 即断言，判断正确或错误 可以在default vm
 * arguments中填入-ea使断言生效，也可以使用-da选项使断言无效（默认为无效），空也表示无效
 * 
 * 同样，也可以通过在-ea或-da后面指定包名来使一个包的断言有效或无效。 例如，要使一个com.test包中的断言 无效，可以使用：
 * 
 * -da:com.test 要使一个包中的所有子包中的断言能够有效或无效，在包名后加上三个点。
 * 
 * 例如： -ea:com.test... 即可使com.test包及其子包中的断言无效。
 * 
 * 
 * 
 * Java2在1.4中新增了一个关键字：assert。在程序开发过程中使用它创建一个断言(assertion)，它的 语法形式有如下所示的两种形式：
 * 
 * 1、assert <boolean表达式>
 * 
 * 2、assert <boolean表达式> : <错误信息表达式>
 * 
 * 如果表达式为false，则断言失败，则会抛出一个AssertionError对象。这个AssertionError继承于Error对象，
 * 而Error继承于Throwable，Error是和Exception并列的一个错误对象，通常用于表达系统级运行错误。
 * 
 * @author LiZhenhua
 *
 */
public class assertTest {
	public static void main(String[] args) {
		assertTest.assertMethod(true);
		assertTest.assertMethod(false);		
	}
	
	public static void assertMethod(boolean b){
//		assert(b);
		System.out.println("成功执行啦");	
		assert b:"这是一个错误";
	}

}
