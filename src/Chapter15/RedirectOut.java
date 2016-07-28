package Chapter15;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class RedirectOut {
	public static void main(String[] args) {
		try(PrintStream ps = new PrintStream(new FileOutputStream("outlog.txt"));
		    PrintStream out = System.out){
			//将 标准输出流 重新定向到 ps输出流
			System.setOut(ps);
			System.out.println("打印输出");
			System.out.println(new RedirectOut());
			
			//在定向为输出---为什么直接用System.out不行呢。非要声明一个PrintStream
			System.setOut(out);
			System.out.println("再次测试");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
