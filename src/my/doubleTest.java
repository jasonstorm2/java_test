package my;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * double保留几位小数点
 * @author Administrator
 *
 */
public class doubleTest {
	public static void main(String[] args) {
//		double f = 1/(3*1.0);
//		BigDecimal b = new BigDecimal(f);
//		double f1 = b.setScale(20, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//		System.out.println(f1);
		
		char[] cha = new char[3200];
		for(int i = 0;i<cha.length;i++){
			cha[i]= 'a';
		}
		System.out.println(cha);
		 
	}

}
