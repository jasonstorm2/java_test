package Chapter15;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {
	public static void main(String[] args) {
		try (
		// 创建字节输入流
		FileInputStream fis = new FileInputStream("FileInputStreamTest.txt");
				// 创建字节输出流
				FileOutputStream fos = new FileOutputStream("OutputStream.txt");) {
			byte[] bt = new byte[32];
			int hasRead = 0;
			while((hasRead = fis.read(bt))>0){
				//每读取一次，即写入文件输入流，读了多少，就写多少
				fos.write(bt, 2, hasRead-2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
