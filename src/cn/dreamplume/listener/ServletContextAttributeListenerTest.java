package cn.dreamplume.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * @Classname ServletContextAttributeListener
 * @Description TODO
 * @Date 2021/3/4 16:03
 * @Created by 翊
 */
public class ServletContextAttributeListenerTest implements ServletRequestAttributeListener {
    // 监听 context 对象被创建时执行的 attributeAdded() 方法
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("context 域对象 "+srae.getName()+" : "+srae.getValue()+" 被创建");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("context 域对象 "+srae.getName()+" : "+srae.getValue()+" 被修改");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("context 域对象 "+srae.getName()+" : "+srae.getValue()+" 被移除");

    }
}
