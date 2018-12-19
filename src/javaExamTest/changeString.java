package javaExamTest;

/**
 * 修改字符串String对象
 * @author LiZhenhua
 *
 */
//判断输出的值是什么。。。
public class changeString {
	 
    public static void changeStr(String str){
        str="welcome";
    }
    public static void main(String[] args) { 
        String str="1234";
        changeStr(str);
        System.out.println(str);
    }
}