package cn.dreamplume.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
 * @Classname HttpSessionActivationListenerPeople
 * @Description TODO
 * @Date 2021/3/4 17:53
 * @Created by 翊
 */
// 要实现对象的钝化和活化其对象类要实现 HttpSessionActivationListener,Serializable 两个接口
public class HttpSessionActivationListenerPeople implements HttpSessionActivationListener, Serializable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 钝化：是将 Session 内存中的对象持久化（序列化）到磁盘
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("People 对象被钝化....");
    }

    // 活化：是将磁盘山的对象再次恢复到 session 内存中
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("People 对象被活化....");
    }
}
