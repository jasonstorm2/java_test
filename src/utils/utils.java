package utils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class utils {
	
	/**
	 * 清理指定位置，指定的文件
	 */
	public static void cleanFile(String address,String suffix){
		File f  =  new File(address);
		if(f.isDirectory()){
			File[] fileList = f.listFiles();
			for (File file :fileList) {
				String fileName = file.getName();
				if(fileName.endsWith(suffix)){
					file.delete();
				}
			}
		}
	}
	
	public static String getProperty(String key){
		URL url;
		String serPath = null;
		try {
			url = new URL("file:///E:/properties.properties");
			InputStream inStream = url.openStream(); 
			Properties pp = new Properties();
			pp.load(inStream);
			serPath = pp.getProperty(key);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		return serPath;
	}

}
