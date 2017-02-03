package newstructor;

import java.lang.reflect.Field;// field 的导入包
import java.lang.reflect.Method;//method的导入包
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//编写操作数据库的dao类
public class NetJavaSession {

    /**
     * 解析出保存对象的sql语句
     * 构造sql语句
     * @param object
     *            ：需要保存的对象
     * @return：保存对象的sql语句
     */
	public String getSaveObjectSql(Object object){
        // 定义一个sql字符串
        String sql = "insert into ";
        Class<? extends Object> c = object.getClass();
        Method[] methods = c.getMethods();
        String cName = c.getName();
        
        // 类名==》表名
        String tableName = cName.substring(cName.lastIndexOf(".") + 1,  //newstructor.UserInfo
                cName.length());
        
        sql += tableName + "("; 
        
        List<String> mList = new ArrayList<>();
        List<Object> vList = new ArrayList<Object>();

        for (Method method : methods){
            String mName = method.getName();
            
            if (mName.startsWith("get") && !mName.startsWith("getClass")){
            	//方法名去掉get之后可以代表数据库表的字段名
                String fieldName = mName.substring(3, mName.length());
                mList.add(fieldName);
                System.out.println("字段名字----->" + fieldName);
                
                try {
                    Object value = method.invoke(object);
                    System.out.println("执行方法返回的值：" + value);
                    if (value instanceof String) {
                        vList.add("\"" + value+ "\"");
                        System.out.println("字段值------>" + value);
                    } else {
                        vList.add(value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //逐一取出mList里面的元素
        for (int i = 0; i < mList.size(); i++) {
            if (i < mList.size() - 1) {
                sql += mList.get(i) + ",";
            } else {
                sql += mList.get(i) + ") values(";
            }
        }
        //逐一取出vList里面的元素
        for (int i = 0; i < vList.size(); i++) {
            if (i < vList.size() - 1) {
                sql += vList.get(i) + ",";
            } else {
                sql += vList.get(i) + ")";
            }
        }
 
        return sql;
    }
 
    public static List getDatasFromDB(String tableName, int Id) {
 
        return null;
 
    }
 
    /**
     * 将对象保存到数据库中
     *
     * @param object
     *            ：需要保存的对象
     * @return：方法执行的结果;1:表示成功，0:表示失败
     */
    public int saveObject(Object object) {
    	String databaseName = "blogSystem";
    	//链接数据库
        Connection con = Connect2DBFactory.getDBConnection(databaseName);      
       
        // 类名==表名
        String cName = object.getClass().getName();
        String tableName = cName.substring(cName.lastIndexOf(".") + 1,
                cName.length());
        //判断表是否存在，不存在则创建        
        Connect2DBFactory.createTable(tableName, databaseName);
        //获得sql语句
        String sql = getSaveObjectSql(object);
        //操作数据库
        try {
            // Statement statement=(Statement) con.createStatement();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
 
    /**
     * 从数据库中取得对象
     *
     * @param arg0
     *            ：对象所属的类
     * @param id
     *            ：对象的id
     * @return:需要查找的对象
     */
    public Object getObject(String className, int Id) {
        // 得到表名字
        String tableName = className.substring(className.lastIndexOf(".") + 1,
                className.length());
        // 根据类名来创建Class对象
        Class c = null;
        try {
            c = Class.forName(className);
 
        } catch (ClassNotFoundException e1) {
 
            e1.printStackTrace();
        }
        // 拼凑查询sql语句
        String sql = "select * from " + tableName + " where Pwd=" + Id;
        System.out.println("查找sql语句：" + sql);
        // 获得数据库链接
        String databaseName = "blogSystem";
        Connection con = Connect2DBFactory.getDBConnection(databaseName);
        // 创建类的实例
        Object obj = null;
        try { 
            Statement stm = con.createStatement();
            // 得到执行查寻语句返回的结果集
            ResultSet set = stm.executeQuery(sql);
            // 得到对象的方法数组
            Method[] methods = c.getMethods();
            int i=1;
            while (set.next()) {
            	System.out.println("while遍历多少遍："+i);
            	i++;
                obj = c.newInstance();
                
                for (Method method : methods) {
                    String methodName = method.getName();
                    if (methodName.startsWith("set")) {
                        String columnName = methodName.substring(3,
                                methodName.length());
                        Class[] parmts = method.getParameterTypes();
                        if (parmts[0] == String.class) {
                            // 该set方法，给对象赋值
                            method.invoke(obj, set.getString(columnName));                            
                        }
                        if (parmts[0] == int.class) {
                            method.invoke(obj, set.getInt(columnName));
                        }
                    }
 
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
