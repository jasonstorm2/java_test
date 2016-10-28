package Chapter18_ClassLoadAndReflection;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * 参考：：
 * http://blog.csdn.net/is_zhoufeng/article/details/26602689
 * 
 * http://blog.csdn.net/zhangdaiscott/article/details/23378023   热部署
 */
class ComClassLoader extends ClassLoader{
	@SuppressWarnings("deprecation")
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("重写的findClass方法");
		
		String classPath = ComClassLoader.class.getResource("/").getPath(); //得到classpath  D:/WorkSpace1/Java_Test/bin/
        String fileName = name.replace(".", "/") + ".class" ;  //Chapter18/Hello.class
        File classFile = new File(classPath , fileName); //把两个路径结合起来，形成一个新路径
        if(!classFile.exists()){                                //D:\WorkSpace1\Java_Test\bin\Chapter18\Hello.class
            throw new ClassNotFoundException(classFile.getPath() + " 不存在") ;  
        } 
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream() ;  
        ByteBuffer bf = ByteBuffer.allocate(1024) ;  
        FileInputStream fis = null ;  
        FileChannel fc = null ;  
        try {  
            fis = new FileInputStream(classFile) ;  
            fc = fis.getChannel() ;  
            while(fc.read(bf) > 0){  
                bf.flip() ;  
                bos.write(bf.array(),0 , bf.limit()) ;  
                bf.clear() ;  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                fis.close() ;  
                fc.close() ;  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return defineClass(bos.toByteArray() , 0 , bos.toByteArray().length) ;  
	}
}
public class CompileClassLoader extends ClassLoader{
	
	
//	//读取一个文件 的 内容
//	private byte[] getBytes(String filename) throws IOException {
//		File file = new File(filename+"q");
//		long len = file.length();
//		byte[] raw = new byte[(int)len];
//		
//		try (FileInputStream fin = new FileInputStream(file)){
//			//一次读取Class文件的全部二进制数据
//				int r = fin.read(raw);
//				if(r!=len) throw new IOException("无法读取全部文件："+r+"!="+len);
//				return raw;
//			}		
//	}
//	
//	
//	//定义编译指定JAVA文件的方法
//	private boolean compile(String javaFile) throws IOException{
//		System.out.println("myClassLoader:正在编译"+javaFile+"..........");
//		//调用系统的javac命令
//		Process p = Runtime.getRuntime().exec("javac"+javaFile+"jiba");
//		try {
//			//其他线程都等待这个线程完成
//			p.waitFor();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e);
//		}
//		//获取javac线程的退出值
//		int ret = p.exitValue();
//		//返回编译是否成功
//		return ret == 0;
//		
//	}
//	
//	//重写ClassLoader的findClass方法
//	protected Class<?> findClass(String name) throws ClassNotFoundException{
//		Class clazz = null;
//		//将包路径中的点(.)替换成斜线（/）
//		String fileStub = name.replace(".","/");
//		String javaFilename = fileStub+".java";
//		String classFilename = fileStub + ".class";
//		
//		File javaFile = new File(javaFilename);
//		File classFile = new File(classFilename);
//		//当指定Java源文件存在，且，Class文件不存在，或者Java源文件的修改时间比class文件的修改时间更晚时，重新编译
//		if(javaFile.exists() && (!classFile.exists()||javaFile.lastModified()>classFile.lastModified())){
//			try {
//				//如果编译失败，或者该class文件不存在
//				if(!compile(javaFilename)||!classFile.exists()){
//					throw new ClassNotFoundException("ClassNotFoundException:"+javaFilename);
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//		//如果class文件存在，系统负责将该文件转换成class对象
//		if(classFile.exists()){
//			try {
//				//将class文件的二进制数据读入数组				
//				byte[] raw = getBytes(classFilename);
//				//调用ClassLoader的defineClass 方法将二进制数据转换成class对象
//				clazz = defineClass(name,raw,0,raw.length);
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//		
//		//如果clazz为null，表明加载失败，则抛出异常
//		if(clazz == null){
//			throw new ClassNotFoundException(name);
//		}
//		
//		return clazz;	
//	}

	
	//定义一个主方法
	public static void main(String[] a) throws Exception{
		//如果运行改程序时没有参数，即没有目标类
//		if(a.length<1){
//			System.out.println("缺少目标类，请按照如下格式运行Java源文件：");
//			System.out.println("java myClassLoader ClassName");
//			return;
//		}
//
//		//第一个参数是需要运行的类
//		String progClass = a[0];
//		//剩下的参数将作为运行目标类时的参数
//		//将这些参数赋值到一个新数组中
//		String[] progArgs = new String[a.length-1];
//		//复制array数组，从 a 复制到 progArgs;从a 的下标1开始复制，到progArgs的下标0.长度是progArgs.length
//		//复制元素的内容是： a 下标index所对应的元素 到 a 下标 index+length-1所对应的元素 之间的内容
//		System.arraycopy(a, 1, progArgs,0,progArgs.length);
		
		String ss = "Chapter18.Hello";
		int i = 0 ;  
        
        while(true){  
        	ComClassLoader mcl = new ComClassLoader() ;  
            System.out.println(mcl.getParent());  
            Class<?> personClass =  mcl.findClass(ss);  
              
            try {  
                Object person =  personClass.newInstance() ;  
                Method sayHelloMethod = personClass.getMethod("sayHello") ;  
                sayHelloMethod.invoke(person) ;  
                System.out.println(++i);  
            } catch (InstantiationException e1) {  
                e1.printStackTrace();  
            } catch (IllegalAccessException e1) {  
                e1.printStackTrace();  
            } catch (SecurityException e) {  
                e.printStackTrace();  
            } catch (NoSuchMethodException e) {  
                e.printStackTrace();  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (InvocationTargetException e) {  
                e.printStackTrace();  
            }  
              
            try {  
                Thread.sleep(1000) ;  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } 
		
//		ClassLoader cl = ClassLoader.getSystemClassLoader();
//		cl.loadClass("Chapter18.Hello").newInstance();  //用到了 Tester类   加载一个类，并不会导致一个类的 初始化
		
		
		
//		CompileClassLoader mc = new CompileClassLoader();
		
//		//加载需要运行的类
//		Class<?> clazz = mc.loadClass("Chapter18.Tester");
//		clazz.newInstance();
//		System.out.println(value);
//		//获取需要运行的类的主方法
//		Method main = clazz.getMethod("main", (new String[0].getClass()));
//		Object argsArray[] = {progArgs};
//		main.invoke(null, argsArray);	
		
	}
}
	
}
