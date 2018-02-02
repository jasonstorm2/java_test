package my;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * JSON技术有各种包，主要是四种：
 *  
 * 1.json-lib 
 * 
 * json-lib最开始的也是应用最广泛的json解析工具，json-lib
 * 不好的地方确实是依赖于很多第三方包，
 * 包括commons-beanutils.jar，commons-collections-3.2.jar，commons
 * -lang-2.6.jar，commons-logging-1.1.1.jar，ezmorph-1.0.6.jar，
 * 对于复杂类型的转换，json-lib对于json转换成bean还有缺陷
 * ，比如一个类里面会出现另一个类的list或者map集合，json-lib从json到bean的转换就会出现问题。
 * json-lib在功能和性能上面都不能满足现在互联网化的需求。 
 * 
 * 2.开源的Jackson
 * 
 * 相比json-lib框架，Jackson所依赖的jar包较少，简单易用并且性能也要相对高些。 而且Jackson社区相对比较活跃，更新速度也比较快。
 * Jackson对于复杂类型的json转换bean会出现问题，一些集合Map，List的转换出现问题。
 * Jackson对于复杂类型的bean转换Json，转换的json格式不是标准的Json格式 
 * 
 * 3.Google的Gson
 * 
 * Gson是目前功能最全的Json解析神器，Gson当初是为因应Google公司内部需求而由Google自行研发而来，
 * 但自从在2008年五月公开发布第一版后已被许多公司或用户应用。
 * Gson的应用主要为toJson与fromJson两个转换函数，无依赖，不需要例外额外的jar，能够直接跑在JDK上。
 * 而在使用这种对象转换之前需先创建好对象的类型以及其成员才能成功的将JSON字符串成功转换成相对应的对象。
 * 类里面只要有get和set方法，Gson完全可以将复杂类型的json到bean或bean到json的转换，是JSON解析的神器。
 * Gson在功能上面无可挑剔，但是性能上面比FastJson有所差距。 
 * 
 * 4.阿里巴巴的FastJson
 * 
 * Fastjson是一个Java语言编写的高性能的JSON处理器,由阿里巴巴公司开发。 无依赖，不需要例外额外的jar，能够直接跑在JDK上。
 * FastJson在复杂类型的Bean转换Json上会出现一些问题，可能会出现引用的类型，导致Json转换出错，需要制定引用。
 * FastJson采用独创的算法，将parse的速度提升到极致，超过所有json库。
 * 
 * @author Administrator
 *
 */
public class JSONToBeanTest {
	public static void main(String[] args) {
		String json = "{id:'1001',name:'张三',age:'22'}";
		Student stu = JSONToBeanTest.ToBeanTest(json);
		System.out.println(stu.getAge());
		
	}	
	
	public static Student ToBeanTest(String json){
		Student stu = new Student();
		return stu = (Student) JSON.parseObject(json, new TypeReference<Student>() {});	      
	}
	public static class Student{
		private int id ;
	    private String name;
	    private int age;
	    
	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public int getAge() {
	        return age;
	    }
	    public void setAge(int age) {
	        this.age = age;
	    }
	    
	    public String toString(){
	        return this.id + ", " + this.name + ", " + this.age;
	    }
		
	}

}


