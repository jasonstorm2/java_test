
public class StringLastIndexOf_Test {
	//substring(index)，表示 截取 从字符串的index下标开始，直到字符串结束 的字符串，形成新的字符串
	public static void main(String[] args) {
//		String s = "C:\\myfile\\jspfile\\example.jsp";
		String s =".0.1.2.3啦啦啦";
		//最后一个符号“.”出现的位置 的下标
		int index = s.lastIndexOf(".");
		String s1=s.substring(index);
		String s2=s.substring(index,index+1);

		
		System.out.println(index);
		System.out.println(s1);
		System.out.println(s2);
		
	}

}
