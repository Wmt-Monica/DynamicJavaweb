package cn.dreamplume.proxy;

/**
 * @Classname ProxyInterface
 * @Description TODO
 * @Date 2021/3/7 11:50
 * @Created by 翊
 * 实现对象代理共同实现的接口
 */
public interface ProxyInterface {
    // 不带参数和返回值的方法
    public void method1();

    // 不带参数带返回值的方法
    public String method2();

    // 带参数和返回值的方法
    public String method3(String str);
}
