package my;

import java.sql.*;

public class DataBaseTest {
	ResultSet results;
	ResultSetMetaData rsmd;
	DatabaseMetaData dma;
	Connection conn;
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/blogsystem";
	    String user = "root";
        String password = "root";
		
		
		
	}

	public void JdbcOdbc_test() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/blogsystem";
	    String user = "root";
        String password = "root";
		try {
			// 加载 JDBC-ODBC 桥驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);// 连接数据库
			
			
			DatabaseMetaData metadata = conn.getMetaData();   
            System.out.println("数据库已知的用户: "+ metadata.getUserName());   
            System.out.println("数据库的系统函数的逗号分隔列表: "+ metadata.getSystemFunctions());   
            System.out.println("数据库的时间和日期函数的逗号分隔列表: "+ metadata.getTimeDateFunctions());   
            System.out.println("数据库的字符串函数的逗号分隔列表: "+ metadata.getStringFunctions());   
            System.out.println("数据库供应商用于 'schema' 的首选术语: "+ metadata.getSchemaTerm());   
            System.out.println("数据库URL: " + metadata.getURL());   
            System.out.println("是否允许只读:" + metadata.isReadOnly());   
            System.out.println("数据库的产品名称:" + metadata.getDatabaseProductName());   
            System.out.println("数据库的版本:" + metadata.getDatabaseProductVersion());   
            System.out.println("驱动程序的名称:" + metadata.getDriverName());   
            System.out.println("驱动程序的版本:" + metadata.getDriverVersion());   
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}