package Chapter15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/*
 * windows路径分隔符是反斜线\,java的路径分隔符是斜线/，也可以是双反斜线\\
 * 相对地址，即跟地址是：System.getProperty("user.dir")，即项目的地址，此项目地址是：D:\WorkSpace1\Java_Test
 */
public class FileTest {
	public static void main(String[] args) throws IOException {
		//以当前路径来创建一个File对象--项目的路径
		File file = new File(".");  //-----------------------------------创建对象并非创建了一个文件或文件夹？
		/****注意相对地址的区别****/
		FileInputStream inputFile = new FileInputStream("./FileInputStreamTest.txt");			//******根目录下，与("FileInputStreamTest.txt")结果一样
		InputStream inputFile2 = FileTest.class.getResourceAsStream("./FileInputStreamTest.txt");//******.class的当前目录
		InputStream inputFile3 = FileTest.class.getResourceAsStream("FileInputStreamTest.txt");//******.class的当前目录
		System.out.println(inputFile);
		System.out.println(inputFile2);
		System.out.println(inputFile3);
		byte[] b = new byte[1024];
		int hasRead = 0;
		if((hasRead = inputFile3.read(b))>0){
			System.out.println("从文件中读取的内容:"+new String(b,0,hasRead));  
		}
		
//		总结一下，可能只是两种写法
//		第一：前面有 “   / ”
//		“ / ”代表了工程的根目录，例如工程名叫做myproject，“ / ”代表了myproject
//		me.class.getResourceAsStream("/com/x/file/myfile.xml");
//		第二：前面没有 “   / ”
//		代表当前类的目录
//		me.class.getResourceAsStream("myfile.xml");
//		me.class.getResourceAsStream("file/myfile.xml");
		
		//直接获取文件名，输出一点
		System.out.println("文件名"+file.getName());
		//获取相对路径的父 路径可能出错，下面代码输出null
		System.out.println("父文件夹名"+file.getParent());
		//获取绝对路径
		System.out.println("绝对路径："+file.getAbsoluteFile());
		//获取上一级绝对路径
		System.out.println("上一级的绝对路径："+file.getAbsoluteFile().getParent());
		
		//在当前路径下创建一个临时文件
		File tmpFile = File.createTempFile("aaaaaaaaaaaaaa", ".txt",file);
		//当JVM退出时，删除该文件
//		tmpFile.deleteOnExit();
		
		//以系统当前时间 作为新文件名来创建 新文件
		File newFile = new File(System.currentTimeMillis() + "time2.txt");
		System.out.println("newFile是否存在："+newFile.exists());
		//以指定 newFile对象来创建一个文件
		newFile.createNewFile();  //---------------------------------------------通过对象创建一个文件！！
		System.out.println("newFile是否存在："+newFile.exists());
		
		//以 newFile 对象来创建一个目录(即文件夹)，因为newFile已经存在
		//所以下面方法返回false，即无法创建该目录		
		newFile.mkdir();          //---------------------------------------------通过对象创建目录（即文件夹），不能与文件同名
		
		//使用list()方法列出 当前路径下 的所有文件和路径
		String[] fileList = file.list();
		System.out.println("**********当前路径下的所有文件和路径***********");
		for(String f : fileList){
			System.out.println(f);
		}
		
		
		//listRoots()静态方法列出所有的磁盘根路径
		File[] roots = File.listRoots();
		System.out.println("========系统所有根路径如下=========");
		for (File f : roots) {
			System.out.println(f);
		}
	}
}
