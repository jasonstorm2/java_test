package newstructor;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//编写获得数据库连接的工厂类：
public class Connect2DBFactory {
    public static Connection getDBConnection(String databaseName) {
        Connection conn = null;
        try {
        	//加载数据库
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "root";            
            //连接
            conn = DriverManager.getConnection(url, user, password);           
            
    		if (conn != null) {// 如果数据库不存在 就创建该库
    			Statement smt = conn.createStatement();
    			smt.executeUpdate("CREATE DATABASE IF NOT EXISTS  `" + databaseName+ "`  DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci");
    			smt.close();
    		}
    		conn = DriverManager.getConnection(url+databaseName, user, password);          
            
            
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
 
        return conn;
    }
	
    /**
     * 创建数据库 和 创建数据库表格
     * @param databasename
     */
	public static void createDatabase(String databasename){		
        try {  
        	//加载数据库
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "root";            
            //连接
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st =  con.createStatement(); 
            st.execute("create database "+databasename);            
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    } 
	
	/**
	 * 创建表格
	 * 
	 * 判断表格是否存在，存在则退出，不存在则创建
	 * 
	 * @param tableName
	 */
	public static void createTable(String tableName, String databaseName) {
		String sqlStr = "create table " + tableName + "( id int not null auto_increment primary key,"
				+ "name varchar(25)," + "pwd varchar(25),"
				// + "age int(11))engine=innodb auto_increment=7 default
				// charset=latin1;"
				+ "age int(11));"
		;

		try {
			// 加载数据库
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/" + databaseName;
			String user = "root";
			String password = "root";
			// 得到连接对象
			Connection con = DriverManager.getConnection(url, user, password);
			DatabaseMetaData metadata = con.getMetaData();
			ResultSet rs = metadata.getTables(null, null, tableName, null);
			if (rs.next()) {
				System.out.println("表 " + tableName + " 存在，退出");
				return;
				/*
				 * 判断表中是否有 某名字的列 rs = metadata.getColumns(null, null, tableName,
				 * columnName);
				 * 
				 * if (rs.next()) { System.out.println("Table " + tableName +
				 * " exist in Table " + tableName); } else { System.out.println(
				 * "Column " + tableName + " not exist in Table " + tableName);
				 * }
				 */
			} else {
				System.out.println("表 " + tableName + " 不存在，那么创建该表");
				Statement st = con.createStatement();
				// 操作数据库
				st.execute(sqlStr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 检查某个字段是否存在
//	 * @param id
//	 * @param conn
//	 * @return
//	 * @throws Exception
//	 */
//	public static boolean isIdExist(int id,Connection conn) throws Exception{
//		boolean isExist = false;
//		DatabaseMetaData metadata = conn.getMetaData();
//		ResultSet rs = metadata.getColumns(null, null, id, null);
//		if (rs.next()) {
//			isExist = true;
//		}
//		return isExist;
//	}
	
}