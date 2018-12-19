package Chapter15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author LiZhenhua
 *
 */
public class FileInputStreamTest {
	/*
	 * 一个换行符，占两个字节
	 * 一个英文字母，占一个字节
	 */
	public static void main(String[] args) throws IOException  {
		//创建字节输入流
		FileInputStream fis = new FileInputStream("FileInputStreamTest.txt");//相对地址，即，根地址是：System.getProperty("user.dir")
		//创建长度为1024的竹筒
		byte[] bbuf = new byte[100];
		//用于保存实际读取的字节数
		int hasRead = 0;
		//循环取水的过程
		while((hasRead = fis.read(bbuf))>0){
			System.out.println(new String(bbuf,0,hasRead));
		}
		fis.close();		
		
	}

}
