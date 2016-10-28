package Chapter18_ClassLoadAndReflection;

public class Hello {
	public String hh = "你好";
	public void sayHello(){
		System.out.println("调用的方法：你好吗");
	}
	static{
		System.out.println("静态代码块：好想你。。。。。。");
	}
	
	public static void main(String[] args) {
		for(String arg : args){
			System.out.println("Helloworld，，，i:"+arg);
		}
	}

}
