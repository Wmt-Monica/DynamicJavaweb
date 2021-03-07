package cn.dreamplume.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname Mytest
 * @Description TODO
 * @Date 2021/3/7 10:55
 * @Created by 翊
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest {
    // 自定义单元测试方法不需要多余的属性
    /*
        类似 @Test 的单元测试是不能带参数的方法并且也不能有返回值，
        所以使用了单元测试的方法的方法名称不能相同
     */
}
