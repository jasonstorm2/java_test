package Chapter15;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KeyinTest {
	/*
	 * 字节流 转换成 字符流
	 */
	public static void main(String[] args) {
		try(
				//将system.in对象转换成Reader对象,
				InputStreamReader ir = new InputStreamReader(System.in);
				//将普通的reader对象包装成BufferedReader
				BufferedReader br = new BufferedReader(ir);
				){
			String line =null;
			while ((line = br.readLine()) != null) {
				if(line.equals("exit")){
					System.exit(1);
				}
				System.out.println("输入内容为："+line);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
