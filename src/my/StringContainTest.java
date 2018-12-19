package my;

/**
 * 测试 String类的contain方法，看看String对象是否包含某个String片段
 * 结果：若是连续片段，则是true
 * @author LiZhenhua
 *
 */
public class StringContainTest {
	public static void main(String[] args) {
		String str = "123,235龙的传人";
		int a = 5;
		String as = a+"";
		
		boolean inContain = StringContainTest.isStrContain(str, as);
		System.out.println("是否包含某字段："+inContain);
		System.out.println("是否包含某字段："+str.contains("35龙"));
		
		int percent = (int)(((3*1.0)/6)*100);
		System.out.println("百分比："+percent);
		
	}
	
	public static boolean isStrContain(String a,String b){
		return a.contains(b);
	}

}
