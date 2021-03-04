package cn.dreamplume.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @Classname HttpSerssionPeople
 * @Description TODO
 * @Date 2021/3/4 17:10
 * @Created by 翊
 */
public class HttpSessionPeople implements HttpSessionBindingListener {
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

    // 对象被存入 session 域对象中，被绑定
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("people 对象被绑定");
    }

    // 对象被移除 session 域对象中，解除绑定
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("people 对象解除绑定");
    }
}
