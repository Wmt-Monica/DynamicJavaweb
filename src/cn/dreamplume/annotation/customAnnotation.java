package cn.dreamplume.annotation;

/**
 * @Classname customAnnotation
 * @Description TODO
 * @Date 2021/3/6 23:40
 * @Created by 翊
 * 自定义接口 customAnnotation
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // 元注解，是注解的注解，@Target 定义该注解的使用范围，此为在方法上面使用
@Retention(RetentionPolicy.RUNTIME) // @Retention 定义注解在该程序的生命周期
/*
    SOURCE:注解在源码级别可见
    CLASS:注解在字节码文件级别可见
    RUNTIME:注解子整个运行阶段都可见
    SOURCE < CLASS < RUNTIME
 */
public @interface customAnnotation {
    String name();  // 接口的属性 name,要使用 ()，未设置默认值的接口属性在使用的时候需要给该属性赋值
    int age() default 18;  // 可以在接口属性后面加上 default 后面可以添加这个属性的默认值
}
