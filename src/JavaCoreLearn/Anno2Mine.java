package JavaCoreLearn;

import java.awt.event.ActionListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义一个 annotation注释
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)   //元 annotation
public @interface Anno2Mine {
	//定义一个成员变量，用于设置元数据
	//该listener 成员变量用于保存监听器实现类
	Class<? extends ActionListener> listener();  // 变量：返回值 + 方法名()---使用此注释的 域或方法，必须给listener赋值
	String test() default "";					   // 变量：---使用此注释的域或方法，可以不用给test赋值，因为它有一个默认值“”

}