package JavaCoreLearn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited()
public @interface AnnotationTest {
	//定义带两个成员变量的annotation
	//annotation 中的成员变量以方法的形式来定义
	String name() default "jason";//无形参的方法
	int age() default 20;//方法名和返回值 定义了 该成员变量的名字和类型
}
