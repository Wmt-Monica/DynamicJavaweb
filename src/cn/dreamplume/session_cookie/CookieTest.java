package cn.dreamplume.session_cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/7 10:29
 * @Created by 翊
 */
@WebServlet(name = "CookieTest")
public class CookieTest extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 创建 Cookie 对象
        // 注意：Cookie 对象的参数和参数值都不能含有中文
        Cookie cookie = new Cookie("name","Monica");

        // 当客户端关闭浏览器之后再重启，其中的 Cookie 对象存储的信息就会消失。
        // 这里 setMaxAge() 方法设置 Cookie 对象的存储时间（单位：秒），惠子啊该段时间内，无论是否中间关闭浏览器（结束对话）
        // 里面存储的信息均不会消失
        cookie.setMaxAge(60*5);

        /**
         * 如果不设置携带路径，那么该 Cookie 对象的信息会在访问该 Cookie 的 web 资源所在的包下的所在所有路径都携带Cookie 信息
         */
        // 这里指定浏览路径指定页面的 setCookie 资源才携带该 Cookie 对象
        // 设置为前面的 ../../就指定在 ../../路径以下的任何资源都可以访问 sendCookie 资源中携带该 Cookie 对象，
        // 此处设置 / 指：当访问该服务器中的任何资源都可以有该 name 数据及其数据值
//        cookie.setPath("/");
//        cookie.setPath("/CookieTest");  // 设置只有 /CookieTest 路径下才可以有访问 sendCookie 资源才携带该 Cookie 对象

        // 将 Cookie 对象中存储的信息发送给客户端----响应头部分
        response.addCookie(cookie);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
