package my;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileInOutStremTest{
	   public static void main(String[] args) throws Exception {
		   /*
		    * "/"符号等价于"\\"
		    * 如果输出流 FileOutputStream（路径）路径指示的文件不存在，那么，将生成这个文件
		    */
	      FileOutputStream out = new FileOutputStream("C:/Users/Administrator/Documents/JavaTest/hellowddd.txt");
	      out.write("how to set yourself cool down".getBytes()); //把字符串 转化为 字节数组 并写入到流中
	      out.close();//关闭输出流
	 
	      byte[] buf = new byte[1024];
	      File f = new File("C:/Users/Administrator\\Documents\\JavaTest/hellowddd.txt");
	      FileInputStream in = new FileInputStream(f);
	      int len = in.read(buf); //读取内容到字节数组中，返回读入的字节数
	      System.out.println(new String(buf,0,len)); //String构造函数把字节数组转化为字符串
	      in.close();//关闭输入流
	      
	   }
	}