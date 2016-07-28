package Chapter18;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;



public class URLClassLoaderTest {
	private static Connection conn;
	
	private String url;
	private String driver;
	
	//定义一个获取数据库 连接的方法
	public static Connection getConn(String url,String user,String pass) throws Exception{
		if(conn==null){
			//创建一个URL数组,只有一个元素
			//封装了路径，成为一个对象
			//当前路径下的mysql-connector-java-5.1.30-bin.jar文件
			//file 表名是从 本地文件系统加载，
			//http:为前缀，表明从互联网通过HTTP来访问
			URL[] urls = {new URL("file:mysql-connector-java-5.1.30-bin.jar")};
			
			//以 默认的 ClassLoader 作为父 ClassLoader,创建 URLClassLoader
			URLClassLoader myClassLoader = new URLClassLoader(urls);
			
			//加载MYSQL的JDBC驱动， 并创建默认实例-------与 普通的通过 new 生成实例的方法 对比
			Driver driver = (Driver)myClassLoader.loadClass("com.mysql.jdbc.Driver").newInstance();
			//创建一个 设置JDBC连接属性的 Properties对象
			Properties props = new Properties();			
			//至少需要为改对象传入 user和password两个属性
			props.setProperty("user", user);
			props.setProperty("password", pass);
			//调用Driver 对象的 connect方法来取得数据库连接
			conn = driver.connect(url, props);
		}
		return conn;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(getConn("jdbc:mysql://localhost:3306/gamedb", "root", "root"));
		
		Statement st = conn.createStatement();
		
		ResultSet re = st.executeQuery("SELECT NAME,LEVEL, COMBAT,actvaluemax FROM human WHERE NAME = '闻人半雪'");
		while(re.next()){
			System.out.println("NAME："+re.getString(1)+"\n"
			+"LEVEL:"+re.getString(2)+"\n\n"
			+"COMBAT:"+re.getString(3)+"\n"
			+"actvaluemax:"+re.getString(4));
		}
		
		System.out.println("lala");
	}

}
