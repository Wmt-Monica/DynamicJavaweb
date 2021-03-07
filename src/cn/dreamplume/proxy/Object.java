package cn.dreamplume.proxy;

/**
 * @Classname Object
 * @Description TODO
 * @Date 2021/3/7 11:55
 * @Created by 翊
 */
public class Object implements ProxyInterface {

    @Override
    public void method1() {
        System.out.println("method1 is running....");
    }

    @Override
    public String method2() {
        System.out.println("method2 is running....");
        return "Monica";
    }

    @Override
    public String method3(String str) {
        System.out.println("method3 is running.....");
        return str;
    }
}
