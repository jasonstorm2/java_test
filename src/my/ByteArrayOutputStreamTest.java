package my;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 字节数组输出流在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中。 
 * 创建字节数组输出流对象有以下几种方式。
 * 下面的构造方法创建一个32字节（默认大小）的缓冲区 OutputStream bOut = new ByteArrayOutputStream();
 * 
 * 另一种方式，指定大小n字节的缓冲区：OutputStream bOut = new ByteArrayOutputStream(int n)
 * 
 * @author Administrator
 *
 */
public class ByteArrayOutputStreamTest {
	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);
		while (bOutput.size() != 10) {
			// 获取用户输入
			bOutput.write(System.in.read());
		}
		byte b[] = bOutput.toByteArray();//创建一个新分配的字节数组。数组的大小和当前输出流的大小，内容是当前输出流的拷贝。把内存中的数据读到字节数组中
		System.out.println("Print the content");
		for (int x = 0; x < b.length; x++) {
			// 打印字符
			System.out.print((char) b[x] + "   ");
		}
		System.out.println("   ");
		int c;
		ByteArrayInputStream bInput = new ByteArrayInputStream(b);//又把字节数组中的字节以流的形式读出
		System.out.println("Converting characters to Upper case ");
		for (int y = 0; y < 1; y++) {
			while ((c = bInput.read()) != -1) {
				System.out.println(Character.toUpperCase((char) c));
			}
			bInput.reset();//将此字节数组输出流的 count 字段重置为零，从而丢弃输出流中目前已累积的所有数据输出。
		}
	}
}
