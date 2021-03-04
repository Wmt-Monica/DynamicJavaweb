package cn.dreamplume.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Classname ServletContextListenerTest
 * @Description TODO
 * @Date 2021/3/3 23:29
 * @Created by 翊
 * 使用 ServletContextListener 对象根据其中监听事件来进行定时闹钟
 */
// ServletContextListener 对象要实现 ServletContextListener 接口
public class ServletContextListenerTest implements ServletContextListener {

    // 监听context域对象的创建时执行的方法（用户初始化web应用的一些初始化配置或者是数据，数据库，连接池等）
    public void contextInitialized(ServletContextEvent sce) {
        // 获取当下的时间，不使用 SimpleDateFormat 对象的过时的方法，使用日历对象的获取方法
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("year = "+year);
        System.out.println("month = "+month);
        System.out.println("day = "+day);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = "2021-3-4 14:35:00";
        try {
            Date startRunTime = format.parse(currentTime);  // 创建表示web应用定时器启动开始的时间 Date 对象
            // web 应用一启动就开启任务调度
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(  /// 使用 Timer 对象的 scheduleAtFixedRate() 方法
                    new TimerTask() {  // 创建 TimerTask 对象，其中有定时器定时启动的 run() 方法
                        @Override
                        public void run() {
                            System.out.println("计时开始");
                        }
                    },
                    startRunTime,  // 定时器起始时间
                    1000*60*60  // 定时器定时的运行的相隔时间 (单位 ms)
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("ServletContext监听被创建");

    }

    // 监听context对象被销毁时执行的方法
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听被销毁....");
    }
}
