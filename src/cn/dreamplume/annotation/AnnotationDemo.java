package cn.dreamplume.annotation;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Classname Annotation
 * @Description TODO
 * @Date 2021/3/7 10:20
 * @Created by 翊
 */
public class AnnotationDemo {

    public static void main(String[] args) {
        try {
            new AnnotationDemo().getMethodAnnotation();
            new AnnotationDemo().finishMyTestAnnotationMethod();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @customAnnotation(name = "Monica")
    public void method() {

    }

    /*
        类似 @Test 的单元测试是不能带参数的方法并且也不能有返回值，
        所以使用了单元测试的方法的方法名称不能相同
     */
    @Test
    public void method1() {
        System.out.println("method1 is running....");
    }

    @MyTest
    public void method2() {
        System.out.println("method2 is running....");
    }

    @MyTest
    public void method3() {
        System.out.println("method3 is running....");
    }

    // 解析 AnnotationDemo 类中的 method() 方法上面的注解的参数
    public void getMethodAnnotation() throws NoSuchMethodException {
        // 获取 AnnotationDemo 的字节对象
        Class clazz = AnnotationDemo.class;
        // 获取 AnnotationDemo 字节对象中的方法对象
        Method method = clazz.getDeclaredMethod("method",null);
        // 获取该方法上面的注解对象 注意参数里面注解对象前面不需要添加 @ ，并且用于存储注解的对象是要获取注解的类型
        // customAnnotation 类型
        customAnnotation annotation = method.getAnnotation(customAnnotation.class);
        String name = annotation.name();
        int age = annotation.age();
        System.out.println("name = "+name+"\tage = "+age);
    }

    // 创建调用完成 AnnotationDemo 类中使用了 @MyTest 注解的方法
    public void finishMyTestAnnotationMethod() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        // 获取 AnnotationDemo 类的字节码对象
        Class clazz = AnnotationDemo.class;
        // 获取该类对象下面的所有方法对象
        Method[] methods = clazz.getDeclaredMethods();
        // 如果该类中的方法对象不为空
        for (Method method : methods) {
            // 使用方法对象的 isAnnotationPresent() 方法判断该方法是否使用了 @MyTest 注解
            // 注意传入注解 MyTest 字节对象时不需要再注解前面加入 @
            boolean flag = method.isAnnotationPresent(MyTest.class);
            // 如果该方法使用了 @MyTest 注解就使用 invoke() 来调用该方法
            if (flag) {
                // 第一个参数是调用该方法的类对象实例。可以使用该类的字节码对象调用 newInstance() 来创建一个实例
                // 第二个参数在单元测试中是不能又参数和返回值的，所以这里的方法参数补充上 null
                method.invoke(clazz.newInstance(), null);
            }
        }
    }
}
