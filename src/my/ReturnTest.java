package my;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class ReturnTest {

	boolean ret(int a) {
		return (a & 2) == 2;
	}
	
	public  int isSameType(int baseType, int itemType) {
		int ss= itemType & baseType;
		return ss;
	}

	public static void main(String[] args) {

		ReturnTest re = new ReturnTest();

		System.out.println(re.ret(2));
		long cutime = System.currentTimeMillis();
		SimpleDateFormat sim = new SimpleDateFormat("ddHHmm");//Ê±¼ä×ª»»Æ÷£¬yyyyMMdd is the FORMAT to transform
		String timeformat=sim.format(cutime);
		String timefor2 =sim.format(new Date(cutime));
		
		System.out.println(timeformat);
		
		System.out.println(timefor2);
		
		
		System.out.println(StringUtils.isEmpty("."));
		
		
		System.out.println("what is that:"+re.isSameType(16777216, 16777728));
		
		System.out.println("what is that:"+re.isSameType(9, 5));
		

	}

}
