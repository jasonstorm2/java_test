package newstructor;

import java.sql.Connection;
import java.sql.DriverManager;

//编写获得数据库连接的工厂类：
public class Connect2DBFactory {
    public static Connection getDBConnection() {
        Connection conn = null;
        try {
        	//加载数据库
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/blogsystem";
            String user = "root";
            String password = "root";
            //连接
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return conn;
    }
}