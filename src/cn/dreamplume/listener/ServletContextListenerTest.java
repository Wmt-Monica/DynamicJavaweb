package cn.dreamplume.listener;

import org.junit.Test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname ServletContextListenerTest
 * @Description TODO
 * @Date 2021/3/3 23:29
 * @Created by 翊
 * 使用 ServletContextListener 对象根据其中监听事件来进行定时闹钟
 */
// ServletContextListener 对象要实现 ServletContextListener 接口
public class ServletContextListenerTest implements ServletContextListener {

    @Test
    public void test() {
        // 创建定时器功能
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String time = date.toString();
        System.out.println("time = "+time);
        int year = Integer.parseInt(time.substring(24,28)) ;
        int month = Integer.parseInt(time.substring(8,10));
        int day = Integer.parseInt(time.substring(11,13));
        System.out.println("year = "+year+"\tmonth = "+month+"\tday = "+day);
    }

    // 监听context域对象的创建时执行的方法（用户初始化web应用的一些初始化配置或者是数据，数据库，连接池等）
    public void contextInitialized(ServletContextEvent sce) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("ServletContext监听被创建");
//        // 获取被监听的对象
//        ServletContext servletContext = sce.getServletContext();
//        // getSource就是被监听的对象 是通用的方法
//        Source source = (Source) sce.getSource();
//        ServletContext source2 = (ServletContext) sce.getSource();

//        // 创建定时器功能
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date date = new Date();
//        int year = date.getYear();
        // 创建定时器功能
//        System.out.println("=============");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    }

    // 监听context对象被销毁时执行的方法
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听被销毁....");
    }
}
