package WorldSrvTest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ToStringBuildTest {
	public  int a =1;
	public  String b = "lala";
	public  int c = 3;
	public  String d ="fuck";
	
	
	public String toString2() {
		return new ToStringBuilder(this)
					.append("sn", a)
					.append("targetProgress", b)
					.append("nowProgress", c)
					.append("status", d)
					.toString();
	}

	public  String toStringbuild(){
		return new ToStringBuilder(this).append("a",a).append("b",b).append("c",c).toString();
	}
	
	@Override
	public  String toString(){
		return  ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}
	
	public static void main(String[] args) {
		ToStringBuildTest test = new ToStringBuildTest();
		String use=test.toStringbuild();
		System.out.println(use);
		
		System.out.println(test.toString2());
		
		System.out.println(new ToStringBuildTest());

		
	}

}
