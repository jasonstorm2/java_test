package my;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * 尽量不要使用相对于System.getProperty("user.dir")当前用户目录的相对路径。这是一颗定时炸弹，随时可能要你的命。
 * 
 * 尽量使用URI形式的绝对路径资源。它可以很容易的转变为URI,URL，File对象
 * 
 * 尽量使用相对classpath的相对路径。不要使用绝对路径。
 * 
 * 绝对不要使用硬编码的绝对路径。因为，我们完全可以使用ClassLoader类的getResource("")方法得到当前classpath的绝对路径。
 * 
 * @author Administrator 
 * 		   URL的一般格式为：
 * 
 *         scheme://host:port/path?query#fragment
 * 
 *         例如：
 * 
 *         http://www.imailtone.com:80/WebApplication1/WebForm1.aspx?name=tom&;
 *         amp;age=20#resume
 * 
 *         scheme，通信协议方案，下表列出 Scheme 属性的有效方案名称。
 * 
 *         file 资源是本地计算机上的文件。格式file://
 * 
 *         ftp 通过 FTP访问资源。格式 FTP://
 * 
 *         gopher 通过 Gopher 协议访问该资源。
 * 
 *         http 通过 HTTP 访问该资源。 格式 http:// https 通过安全的 HTTPS 访问该资源。 格式
 *         target=_blank>https://
 * 
 *         mailto 资源为电子邮件地址，通过 SMTP 访问。 格式 mailto:
 * 
 *         MMS 通过 支持MMS（流媒体）协议的播放该资源。（代表软件：Windows Media Player）格式 MMS://
 * 
 *         ed2k 通过 支持ed2k（专用下载链接）协议的P2P软件访问该资源。（代表软件：电驴） 格式 ed2k://
 * 
 *         Flashget 通过 支持Flashget:（专用下载链接）协议的P2P软件访问该资源。（代表软件：快车） 格式 Flashget://
 * 
 *         thunder 通过 支持thunder（专用下载链接）协议的P2P软件访问该资源。（代表软件：迅雷） 格式 thunder://
 * 
 *         news 通过 NNTP 访问该资源。
 * 
 *         host，主机，指定的服务器的域名系统 (DNS) 主机名或 IP 地址。
 * 
 *         port，端口号，整数，可选，省略时使用方案的默认端口，如http的默认端口为80。
 * 
 *         path，路径，由零或多个“/”符号隔开的字符串，一般用来表示主机上的一个目录或文件地址。
 * 
 *         query，查询，可选，用于给动态网页（如使用CGI、ISAPI、PHP/JSP/ASP/ASP.NET等技术制作的网页）传递参数，
 *         可有多个参数，用“&”符号隔开，每个参数的名和值用“=”符号隔开。
 * 
 *         fragment，信息片断，字符串，用于指定网络资源中的片断。例如一个网页中有多个名词解释，可使用fragment直接定位到某一名词解释。
 * 
 *         注意，Windows 主机不区分 URL 大小写，但是，Unix/Linux 主机区分大小写。
 */
public class FileAndUrlAddressTest {
	public static void main(String[] args) throws Exception {
		File file = new File("D:/我的批处理/2.bat");
		System.out.println("File文件路径："+file.getAbsolutePath());
		URL url0 = file.toURL();//废弃，建议用下面的方式
		URL url = file.toURI().toURL();
		System.out.println("URL文件路径: "+url.getPath());	
		FileAndUrlAddressTest ft = new FileAndUrlAddressTest();
		URL url2 = ft.getURL();
		System.out.println("URL2文件路径: "+url2.getPath());	
		
		URL w3 = new URL("http://www.baidu.com");		
		System.out.println(w3.getContent());
	}
	
	/**
	 * 获得该类的 class文件的路径，当前类FileAndUrlAddressTest.class文件的URI目录
	 * /D:/WorkSpace1/Java_Test/bin/my/
	 * @return
	 * 
	 */
	public URL getURL(){
		URL base = this.getClass().getResource("");//写入具体文件，就可以返回该文件的地址
		return base;
	}
	
	/**
	 * 获得该类的 class文件的上一级路径，当前的classpath的绝对URI路径
	 * /D:/WorkSpace1/Java_Test/bin/
	 * @return
	 */
	public URL getURL2(){
		URL base = this.getClass().getResource("/");//写入具体文件，就可以返回该文件的地址
		return base;
	}
	
	/**
	 * 获得该类的 class文件的上一级路径，当前的classpath的绝对URI路径
	 * /D:/WorkSpace1/Java_Test/bin/
	 * 
	 * 推荐用这个方法来获取classpath的绝对路径的URI
	 * @return
	 */
	public URL getURL3(){
		URL base = Thread.currentThread().getContextClassLoader().getResource("");
		return base;
	}
	
	/**
	 * 获得该类的 class文件的上一级路径，当前的classpath的绝对URI路径
	 * /D:/WorkSpace1/Java_Test/bin/
	 * @return
	 */
	public URL getURL4(){
		URL base = FileAndUrlAddressTest.class.getClassLoader().getResource("");
		return base;
	}
	
	/**
	 * 获得该类的 class文件的上一级路径，当前的classpath的绝对URI路径
	 * /D:/WorkSpace1/Java_Test/bin/
	 * @return
	 */
	public URL getURL5(){
		URL base = ClassLoader.getSystemResource("");
		return base;
	}
}
