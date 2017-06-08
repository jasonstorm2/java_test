package Chapter15;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class FileReaderTest {
	/*
	 * try-with-resources语句:java 7的新功能。所有的IO资源类都实现了 AutoCloseable接口。。try语句能自动关闭流
	 * 
	 * 方式：
	 * 
	 * try(要关闭的流,可以多个){//try-with-resources语句中资源的关闭释放顺序和它们创建的顺序相反。
	 *    操作语句
	 * }
	 * 
	 * try(){
	 * 
	 * }catch(){//既然有try，可以顺便捕获下异常
	 *  
	 * }
	 * 
	 */
	public static void main(String[] args) {
		//文件默认的编码格式是 gbk
		try(FileReader fis = new FileReader("FileInputStreamTest.txt");
				/*
				 * 问题出在FileReader读取文件的过程中，FileReader继承了InputStreamReader，
				 * 但并没有实现父类中带字符集参数的构造函数，所以FileReader只能按系统默认的字符集来解码，
				 * 然后在UTF-8 -> GBK -> UTF-8的过程中编码出现损失，造成结果不能还原最初的字符
				 * 
				 * Windows下简体中文默认使用GBK字符集，而Linux下则默认使用UTF-8字符集。。。。。。
				 */
		    //此方法可以解决 乱码问题
		    InputStreamReader isr = new InputStreamReader(new FileInputStream("FileInputStreamTest.txt"),"GBK");
				
				
			FileInputStream fisss = new FileInputStream("FileInputStreamTest.txt")){
			//创建长度为1024的竹筒
			char[] bbuf = new char[1024];
			//用于保存实际读取的字节数
			int hasRead = 0;
			//循环取水的过程
			while((hasRead = fis.read(bbuf))>0){
//				System.out.println("是否支持mark："+fis.markSupported());
//				fis.skip(2);
				String print = new String(bbuf,0,hasRead);
				System.out.println(new String(print.getBytes(),"GBK"));
				System.out.println(new String(print.getBytes(),"UTF-8"));//编码不一致，中文出错

				System.out.println("@@@@@-------------------------------------------------------------------");

				System.out.println(print); //本文件的编码 也是GBK
				System.out.println("#####-------------------------------------------------------------------");

			}
			
			while((hasRead = isr.read(bbuf))>0){
//				System.out.println("是否支持mark："+fis.markSupported());
//				fis.skip(2);
				System.out.println(new String(bbuf,0,hasRead));
				System.out.println("$$$$$-------------------------------------------------------------------");

			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
