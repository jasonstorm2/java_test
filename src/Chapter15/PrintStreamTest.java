package Chapter15;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamTest {
	public static void main(String[] args) {
		/*
		 * 关闭处理流输入输出资源后，处理流 里面的 节电流将被关闭
		 */
		try(
				FileOutputStream fos = new FileOutputStream("test.txt");//节点流，直接以物理IO节点作为构造器参数
				PrintStream ps = new PrintStream(fos)){                 //处理流，以一个以存在的流作为构造器参数
			
			//使用PrintStream执行输出
			ps.println("普通的字符串");
			//直接使用 PrintStream输出对象
			ps.println(new PrintStreamTest());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
