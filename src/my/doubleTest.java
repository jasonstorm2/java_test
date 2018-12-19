package my;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * double保留几位小数点
 * 
 * float和double类型的主要设计目标是为了科学计算和工程计算。
 * 在大多数的商业计算中，一般采用java.math.BigDecimal类来进行精确计算。
 * 
 * BigDecimal构造方法 1.public BigDecimal(double val) 将double表示形式转换为BigDecimal
 * *不建议使用 -- 不可预知，0.1--0.1000000000000000055511151231257827021181583404541015625
 * 
 * 2.public BigDecimal(int val)　　将int表示形式转换成BigDecimal
 * 
 * 3.public BigDecimal(String val)　　将String表示形式转换成BigDecimal
 * 
 * 当double必须用作BigDecimal的源时，请使用Double.toString(double)转成String，
 * 然后使用String构造方法，或使用BigDecimal的静态方法valueOf
 * 
 * 
 * 加减乘除 public BigDecimal add(BigDecimal value); //加法
 * 
 * public BigDecimal subtract(BigDecimal value); //减法
 * 
 * public BigDecimal multiply(BigDecimal value); //乘法
 * 
 * public BigDecimal divide(BigDecimal value); //除法
 * 
 * BigDecimal a = new BigDecimal("4.5"); BigDecimal b = new BigDecimal("1.5");
 * 
 * System.out.println("a + b =" + a.add(b)); System.out.println("a - b =" +
 * a.subtract(b)); System.out.println("a * b =" + a.multiply(b));
 * System.out.println("a / b =" + a.divide(b));
 * 
 * 有一点需要注意的是除法运算divide.
 * 
 * BigDecimal除法可能出现不能整除的情况，比如 4.5/1.3， 这时会报错java.lang.ArithmeticException:
 * Non-terminating decimal expansion; no exact representable decimal result.
 * 
 * 其实divide方法有可以传三个参数
 * 
 * public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)
 * 第一参数表示除数， 第二个参数表示小数点后保留位数， 第三个参数表示舍入模式，只有在作除法运算或四舍五入时才用到舍入模式
 * 
 * ROUND_CEILING //向正无穷方向舍入
 * 
 * ROUND_DOWN //向零方向舍入
 * 
 * ROUND_FLOOR //向负无穷方向舍入
 * 
 * ROUND_HALF_DOWN //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向下舍入, 例如1.55 保留一位小数结果为1.5
 * 
 * ROUND_HALF_EVEN
 * //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，如果保留位数是奇数，使用ROUND_HALF_UP，如果是偶数
 * ，使用ROUND_HALF_DOWN
 * 
 * ROUND_HALF_UP //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入,(四舍五入采用 ) 1.55保留一位小数结果为1.6
 * 
 * ROUND_UNNECESSARY //计算结果是精确的，不需要舍入模式
 * 
 * ROUND_UP //向远离0的方向舍入
 * 
 * 
 * @author LiZhenhua
 *
 */
public class doubleTest {
	public static void main(String[] args) {
		// 普通的 计算，得出的值不精确
		/**
		 * 原因在于我们的计算机是二进制的。
		 * 浮点数没有办法是用二进制进行精确表示。
		 * 我们的CPU表示浮点数由两个部分组成：指数和尾数，这样的表示方法一般都会失去一定的精确度，有些浮点数运算也会产生一定的误差。
		 * 如：2.4的二进制表示并非就是精确的2.4。
		 * 反而最为接近的二进制表示是 2.3999999999999999。浮点数的值实际上是由一个特定的数学公式计算得到的。
		 */
		System.out.println(0.2+0.1);// 0.30000000000000004
		double f = 1/(3*1.0);
		System.out.println("f:"+f);
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(20, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		System.out.println("f1:"+f1);
		
		double r = 1/(3*1.0);				
		BigDecimal bb = new BigDecimal(r);
		double rate = bb.setScale(15, BigDecimal.ROUND_DOWN).doubleValue(); 
		System.out.println("rate:"+rate);
		
		char[] cha = new char[3200];
		for(int i = 0;i<cha.length;i++){
			cha[i]= 'a';
		}
		System.out.println(cha);
		 
	}

}
