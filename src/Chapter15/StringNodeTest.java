package Chapter15;

import java.io.StringReader;
import java.io.StringWriter;

public class StringNodeTest {
	public static void main(String[] args) {
		String src = "从明天起，做一个幸福的人\n喂马,劈柴,周游世界\n";
		char[] buffer = new char[32];
		int hasRead = 0;
		try(StringReader sr = new StringReader(src)){// 以 字符串 为物理节点
			while((hasRead = sr.read(buffer))>0){
				System.out.println(new String(buffer,0,hasRead));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			}
		
		//创建StringWriter时，实际上是以一个StringBuffer作为输出节点的
		//下面指定的20就是StringBuffer的初始长度
		try(StringWriter sw = new StringWriter(20)){
			sw.write("美丽新世界-伍佰\n有一个美丽的新世界\n她在远方等我\n那里有天真的孩子\n还有姑娘的酒窝\n");
			System.out.println("----------下面是sw字符串节点里的内容");
			System.out.println(sw.toString());
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
