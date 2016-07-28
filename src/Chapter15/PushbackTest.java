package Chapter15;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

public class PushbackTest {
	public static void main(String[] args) {
		try(
				//创建对象，设立缓冲区大小64
				PushbackReader pr = new PushbackReader(new FileReader("FileInputStreamTest.txt"), 64);				
				){
			char[] buf = new char[32];
			String lastContent = "";
			int hasRead = 0;
			
			String content = null;
			while((hasRead = pr.read(buf)) > 0){
				content = new String(buf,0,hasRead);
				int targetIndex = 0;
				
				//将上次 读取的 字符串 和本次读取的 字符串 拼接起来
				//查看是否包含目标字符串，如果包含目标字符串
				if((targetIndex = (lastContent+content).indexOf("new PushbackReader")) > 0){
					//将本次内容和上次内容一起 推回 缓冲区
					pr.unread((lastContent+content).toCharArray());
					
//					if(targetIndex > 32){
//						buf = new char[targetIndex];
//					}
					pr.read(buf);
					pr.read(buf);
					System.out.println(new String(buf,0,32));
					pr.read(buf);
					System.out.println(new String(buf,0,32));
//					System.exit(0);
				}else{
					System.out.println(content);
					lastContent = content;
				}
				
			}
			
		}catch(IOException e){
			e.printStackTrace();
			;;;
		}
	}

}
