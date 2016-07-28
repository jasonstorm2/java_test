package Annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Administrator
 * 自定义的一个annotation注释，
 * 前面三个使用的是系统的注释。
 * 第一个说明，生成的注释会在javadoc文档上
 * 第二个说明，生成的注释 应用在域，也就是成员变量，或者data member上
 * 第三个说明，在源码、编译好的.class文件中保留信息，在执行的时候会把这一些信息加载到JVM中去的
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldUse {
	String  value();
	String  theOther();

}
