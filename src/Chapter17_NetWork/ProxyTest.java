package Chapter17_NetWork;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ProxyTest {
	final String PROXY_ADDR = "117.90.2.193";
    final int PROXY_PORT = 3128;
    String urlStr = "https://www.baidu.com/";
    
    public void init() throws Exception{
    	URL url = new URL(urlStr);
    	
    	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_ADDR, PROXY_PORT));
    	
    	URLConnection conn = url.openConnection(proxy);    	
    	conn.setConnectTimeout(3000);
    	try(
    			Scanner scan = new Scanner(conn.getInputStream());
    			PrintStream ps = new PrintStream("index.htm");
    			){
    		while(scan.hasNextLine()){
    			System.out.println("гаЪ§Он");
//    			String line = scan.nextLine();
//    			System.out.println(line);
//    			ps.println(line);
    		}    		
    	}    	
    }
    public static void main(String[] args) throws Exception {
		new ProxyTest().init();
	}
    
    
}
