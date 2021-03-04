package cn.dreamplume.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;

/**
 * @Classname HttpSessionListenerTest
 * @Description TODO
 * @Date 2021/3/4 15:00
 * @Created by 翊
 */
public class HttpSessionListenerTest implements HttpSessionListener {

    // 监听器创建执行的方法
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        Enumeration<String> sessionName = se.getSession().getAttributeNames();
        while (sessionName.hasMoreElements()) {
            System.out.println(sessionName.toString());
        }
        System.out.println("sessionID = "+sessionId);
    }

    // 监听器销毁时执行的方法
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
