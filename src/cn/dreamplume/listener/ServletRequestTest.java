package cn.dreamplume.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @Classname ServletRequestTest
 * @Description TODO
 * @Date 2021/3/4 15:39
 * @Created by 翊
 */
public class ServletRequestTest  implements ServletRequestListener {

    // ServletRequestListener 监听对象被创建
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("ServletRequestListener 监听对象被创建");
    }

    // ServletRequestListener 监听对象被销毁
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("ServletRequestListener 监听对象被销毁");
    }
}
