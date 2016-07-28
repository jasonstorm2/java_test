package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** 
* 针对eclipse是否支持java8小测试 
* MainClass
* @author An Hui 
* */
public class MainClass {
	
	
	
  public static void main(String[] args) {
	// 获取源文件夹下的所有类
        /*
         * 尝试输入一个简单的lambda表达式来测试是否可以编译和运行java8的代码
         */
        new Thread(() -> System.out.println("这是一个java8的小例子,可以使用lambda表达式")).start();
        
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        Collections.sort(names, (a, b) -> b.compareTo(a));
    }
}
