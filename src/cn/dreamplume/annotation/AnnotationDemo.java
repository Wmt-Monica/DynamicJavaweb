package cn.dreamplume.annotation;

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
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @customAnnotation(name = "Monica")
    public void method() {

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
}
