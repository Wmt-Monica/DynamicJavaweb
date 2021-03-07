package cn.dreamplume.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Classname ProxyObject
 * @Description TODO
 * @Date 2021/3/7 11:55
 * @Created by 翊
 */
public class ProxyObject {
    public static void main(String[] args) {
        // 获得动态代理对象----运行时在内存中动态的为 Object 创建一个虚拟的代理对象
        // proxyObject 是代理对象，根据参数确定是谁的代理对象
        // 创建一个接口对象 proxyObject，使用代理对象类Proxy的newProxyInstance() 根据强制转换的类型创建对应的代理对象类实例
        ProxyInterface proxyObject = (ProxyInterface) Proxy.newProxyInstance(
            /*
                参数一：与目标对象相同的加载器
                参数二：获取被代理类所实现的所有接口的字节对象的数组
                参数三：InvocationHandler 对象
             */
                Object.class.getClassLoader(),  // 使用被代理对象类的加载器对象
                new Class[]{ProxyInterface.class},  // 被代理类所要被代理方法对应的接口字节对象数组

                // InvocationHandler() 对象是 Proxy 代理实例的代理处理接口
                new InvocationHandler() {  // 通过创建 InvocationHandler 的匿名类实现里面的 invoke() 方法
                    /**
                     * 代理对象处理执行代理对象的方法的原基础之上还可以实现自身对方法的添加自己的逻辑
                     * 例如调用该方法前的逻辑和调用该方法之后的逻辑，具体实例：事务（调用方法前自动提交关闭）（正确执行该方法之后手动提交）
                     * @param proxy 代理类代理的真实代理对象
                     * @param method 我们需要被代理对象的 Method 方法对象
                     * @param args 指代理对象传递的对象方法的参数
                     * @return 返回被代理对象 method 方法执行的返回值
                     * @throws Throwable
                     */
                    @Override
                    public java.lang.Object invoke(java.lang.Object proxy, Method method, java.lang.Object[] args)
                            throws Throwable {
                        System.out.println("目标方法被代理前的逻辑");
                        String returnObject = (String) method.invoke(new Object(),args);
                        System.out.println("目标方法被代理后的逻辑");
                        System.out.println("============================\n");
                        return returnObject;
                    }
                }
        );

        proxyObject.method1();
        proxyObject.method2();
        String returnObject = proxyObject.method3("Hahn");
        System.out.println("returnObject = "+returnObject);
    }
}
