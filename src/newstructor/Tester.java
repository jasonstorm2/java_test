package newstructor;

/**
 * 用 bean对象 和数据库条目 一一对应
 * 
 * 使用 beanObject 操作数据库
 * @author LiZhenhua
 *
 */
public class Tester {
	
	public static final int TESTNUMBER = 0;
	public String str;
	
	public void ss(String sd){
		this.str=sd;
		
		
	}
	
    public static void main(String args[]) {
        //获得NetJavaSession对象
    	NetJavaSession session = new NetJavaSession();
        //创建一个UserInfo对象
        UserInfo user = new UserInfo();
        //设置对象的属性
        user.setId(15);
        user.setAge(45);
        user.setPwd("123456");
        user.setName("jacika");
        
        //将对象保存到数据库中        
        int val=session.saveObject(user);
        System.out.println("是否写入数据库："+val);
        //得到SQL语句
        String sql = session.getSaveObjectSql(user);               
        System.out.println("保存对象的sql语句：" + sql);
        //查找对象
        UserInfo userInfo = (UserInfo) session.getObject(
                "newstructor.UserInfo", 123456);
        System.out.println("获取到的信息：" + userInfo);
 
    }
}