package my;

import java.io.IOException;

/**
 * java 安全管理类测试
 * 
 * SecurityManager应用场景
 * 当运行未知的Java程序的时候，该程序可能有恶意代码（删除系统文件、重启系统等），为了防止运行恶意代码对系统产生影响，
 * 需要对运行的代码的权限进行控制，这时候就要启用Java安全管理器。
 * 
 * 启动安全管理有两种方式，建议使用启动参数方式。
 * 1.
 * 启动程序的时候通过附加参数启动安全管理器：
 * -Djava.security.manager
 * 
 * 若要同时指定配置文件的位置那么示例如下：
 * -Djava.security.manager -Djava.security.policy="E:/java.policy"
 * 
 * 2.
 * 也可以通过编码方式启动，不过不建议：
 * System.setSecurityManager(new SecurityManager());
 * 
 * Runtime.exec("cmd命令");
 * Runtime类封装了运行时的环境。每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
 * 
 * @author LiZhenhua
 *
 */
public class SecurityManagerTest {
	public static void main(String[] args) {
		
		   System.out.println("SecurityManager: " + System.getSecurityManager());
		
		try {//rmdir /s/q 222  删除整个目录			
			
			/**
			 * 
			 * rmdir/rd  删除文件夹
			 * del 删除文件
			 * 
			 * 为什么要加上 /c或/k 才能删除文件？
			 * 
			 * /s即是将目标目录下的所有文件及子目录文件删除 
			 * /q是无须确认删除 
			 * 
			 * cmd /c dir：是执行完dir命令后关闭命令窗口；	
			 * cmd /k dir：是执行完dir命令后不关闭命令窗口。
			 * /F 强制
			 * 
			 */
			Runtime.getRuntime().exec("cmd /k rmdir  /S /Q D:\\java安全管理测试\\aa");
			// 删除指定的文件：/k del /f/s/q dirname> nul
//			Runtime.getRuntime().exec("cmd /k del /F /S /Q D:\\java安全管理测试\\aa\\dd.txt");
//			Runtime.getRuntime().exec("cmd /k rmdir  /S /Q D:\\java安全管理测试\\aa");
//			Runtime.getRuntime().exec("cmd /k rmdir  /S /Q D:\\java安全管理测试\\aa");
			Runtime.getRuntime().exec("Shutdown.exe -r -t 5");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
