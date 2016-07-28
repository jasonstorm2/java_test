package Chapter18;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ObjectPoolFactory {
	/*
	 * 定义一个对象池，前面是对象名，后面是是即对象
	 */
	private Map<String, Object> objectPool = new HashMap<String, Object>();
	private Properties props = new Properties();
	
	/*根据类名，反射实例化一个类对象*/
	private Object createObject(String clazzName) throws Exception{
		/*
		 * 使用装在当前类（ObjectPoolFactory）的装在器、调用了Class.forName(className,true, this.getClass().getClassLoader())
		 * loading,linking,initializing
		 * 第二个参数true，说明要initializing这个类
		 */
		Class<?> clazz = Class.forName(clazzName);//--使用的是各种导入的包的文件的全名，或者classpath文件的全名，可以是绝对路径吗？
		/*
		 * ClassLoader.loadClass(className)方法内部调用ClassLoader.loadClass(name,false)方法，第二个参数指出Class在load之后，是否进行link操作。
         * 区别就出来了。Class.forName(className)装载的class已经被实例化，而ClassLoader.loadClass(className)装载的class还没有被link，所以就更谈不上实例化了。
		 */
	    //ClassLoader cl = new  ClassLoader();  //--使用一个自己指定的装在器
		//Class c1= cl.loadClass(String className, boolean resolve );  
		return clazz.newInstance();
	}
	
	/*
	 * 该方法 根据指定文件 来初始化 对象池
	 * 它会根据 配置文件 来创建对象
	 */
	
	public void init(String fileName) throws Exception{
		try(FileInputStream fis = new FileInputStream(fileName)){
			//直接从收入文件流获取属性对象
			props.load(fis);

		}catch(IOException ex){
			System.out.println("读取"+fileName+"异常");
		}
	}
	/*
	 * 这种方式要求properties文件 在项目下的该类所对应的包中--相对路径1
	 */
	public void getPropertiesFromHome(){
		try {
			InputStream in = getClass().getResourceAsStream("properties.properties");
//			FileInputStream in = new FileInputStream("properties.properties"); //两个方法不一样
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 这种方式要求properties文件 在项目下--相对路径2
	 */
	public void getPropertiesFromProject(){
		try {
			FileInputStream in = new FileInputStream("properties.properties");
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void printNames() throws Exception{
		for (String name : props.stringPropertyNames()) {
			System.out.println(name);
		}
	}
	/*
	 * 每取出一对key-value对，就根据value创建一个对象
	 * 调用createObject创建对象，并将对象添加到对象池中
	 */
	public void initPool() throws Exception{
		for (String name : props.stringPropertyNames()) {
			if(!name.contains("%")){
				objectPool.put(name, createObject(props.getProperty(name)));
			}
		}
	}
	
	/*
	 * 该方法将会根据 属性文件 来调用 指定对象的 setter方法
	 */
	public void intProperty() throws Exception{
		for (String name : props.stringPropertyNames()) {
			if(name.contains("%")){
				String[] strArr = name.split("%");
				
				Object target = getObject(strArr[0]);
				
				String mtdName = "set"+strArr[1].substring(0, 1).toUpperCase() + strArr[1].substring(1);
				
				Class<?> targetClass = target.getClass();
				
				Method mtd = targetClass.getMethod(mtdName, String.class);//返回指定名称，参数类型的 public Method 对象
				//invoke需要程序有 访问的权限 ，调用方法setAccessible(boolean b)来访问 private 方法
				mtd.invoke(target, props.getProperty(name));	//target方法所属的对象，方法所需的参数
			}			
		}
	}
	
	
	public Object getObject(String name){
		return objectPool.get(name);
	}
	
	public static void main(String[] args) throws Exception {
		/***绝对路径测试--开始***/
//		String str = "E:/my/myl/moon.class";
//		Class<?> clazz = Class.forName(str);//--不能用绝对路径，会出错
		/***绝对路径测试--结束***/
		ObjectPoolFactory pf = new ObjectPoolFactory();
		pf.init("E:/properties.properties");//加载属性文件--绝对路径
		pf.initPool();//
		pf.intProperty();
		System.out.println("pf************:"+pf);
		System.out.println("a: "+pf.getObject("a"));
		System.out.println("b: "+(JFrame)pf.getObject("b"));
		System.out.println("c: "+((Hello)pf.getObject("c")).hh);
		JFrame j = (JFrame)pf.getObject("b");
		JLabel b = (JLabel)pf.getObject("d");
		j.add(b);
		j.setSize(500, 500);
		j.setVisible(true);
		j.setTitle("测试");		
		
		/**方法比较**/
//		pf.getPropertiesFromProject();--相对路径1
//		pf.printNames();
//		System.out.println("---------------------------");
//		pf.getPropertiesFromHome();--相对路径2
//		pf.printNames();
	}

}
