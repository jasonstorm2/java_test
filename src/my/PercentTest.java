package my;

import java.text.NumberFormat;

public class PercentTest {
	public static void main(String[] args) {
		//这里的数后面加“D”是表明它是Double类型，否则相除的话取整，无法正常使用
		   double percent = 50.5D / 150D;
		   //输出一下，确认你的小数无误
		   System.out.println("小数：" + percent);
		   //获取格式化对象
		   NumberFormat nt = NumberFormat.getPercentInstance();
		   //设置百分数精确度2即保留两位小数
		   nt.setMinimumFractionDigits(2);
		   //最后格式化并输出
		   System.out.println("百分数：" + nt.format(percent));
		   
		   
		   System.out.println(percent * 100);
		   System.out.println((int)(percent * 100));
		   
		   long a = 100l;
		   long b = 300l;
		   System.out.println((int)((double)a/b));
		   System.out.println(      (int)(((double)a/b)*100)    );
			
			
	}

}
