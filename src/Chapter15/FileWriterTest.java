package Chapter15;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
	/*
	 * Java中\n和\r的区别如下：
	 * 1.\r 叫回车 Carriage Return
	 * 2.\n 叫新行 New Line
	 * 但是都会造成换行,使用System.getProperty("line.separator")来获取当前OS的换行符
	 * 各系统应当是\r Mac ,\n Unix ,Linux\r\n Windows
	 * 
	 * 不同操作平台，不同输出，一般下，二者结合使用\r\n
	 */
	public static void main(String[] args) {
		try(FileWriter fw = new FileWriter("poem.txt")){
			fw.write("锦瑟-李商隐\r\n");
			fw.write("锦瑟无端五十弦，一弦一柱思华年\r\r");
			fw.write("庄生晓梦迷蝴蝶，望帝春心托杜鹃\r\n");			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
