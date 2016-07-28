package Chapter15;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterTest {
	public static void main(String[] args) {
		File file = new File(".");
		System.out.println(file.getAbsolutePath());
		//lamda方法：使用lamda表达式，目标类型为FilenameFilter,实现文件过滤器
		String[] nameList = file.list((dir,name) -> name.endsWith(".java")||new File(name).isDirectory());
		
		for (String string : nameList) {
			System.out.println(string);
		}
		
		//普通方法：
		String[] nameList2 = file.list(new FilenameFilter(){//匿名内部类  实现了对象，重写了对象的方法，是否类似于给 对象的变量赋值？？
			@Override
			public boolean accept(File dir, String name) {//重写了方法
				// TODO Auto-generated method stub				
				return name.endsWith(".java")||new File(name).isDirectory();
			}			
		});
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		for (String string : nameList2) {
			System.out.println(string);
		}
		
		//自己写的 函数式接口
		new FilenameFilterTest().bool(stringla->stringla.endsWith("la"));//重写了接口的方法，并实例化一个接口对象。一次对象最为bool的参数
		

	}
	
	public void bool(testLamdaInteface in){
		int i=0;
		if(in.judge("tonglad")){
			System.out.println("正确的");
		}else{
			System.out.println("错误的");
		}
	}

}
