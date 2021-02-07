package cn.dreamplume.session_cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/7 16:35
 * @Created by 翊
 * 获取 Cookie 对象
 */
@WebServlet(name = "GetCookie")
public class GetCookie extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        // 创建 Cookie 对象
        Cookie c1 = new Cookie("name","ShiFan");
        Cookie c2 = new Cookie("age","22");
        Cookie c3 = new Cookie("identity","boyfriend");
        Cookie c4 = new Cookie("character","tender");


        // 这里不适用 setMaxAge() 方法设置 Cookie 对象携带时间
        // 同样不使用 setPath() 方法设置 Cookie 对象的携带路径，默认同级服务器均可访问

        //  将 Cookie 对象储存在客户端发送到响应头中
        response.addCookie(c1);
        response.addCookie(c2);
        response.addCookie(c3);
        response.addCookie(c4);

        // 使用 request 的 getCookies() 方法获取 Cookie 数组对象
        Cookie[] cookies = request.getCookies();

        // 创建写入响应器 response 的 PrintWriter 对象
        PrintWriter writer = response.getWriter();

        // 在页面显示所有的 Cookie 对象
//        for (int i = 0; i < cookies.length; i ++) {
//            writer.write(cookies[i].getName()+" ----> "+cookies[i].getValue()+"<br>");
//        }

        // 在页面显示指定的 Cookie 对象
        for(Cookie c : cookies) {
            /**
             * Cookie 对象使用 getName() 方法可以获取参数名
             * 使用 getValue() 方法可以获取参数值
             */
            if (c.getName().equals("identity")) {
                writer.write(c.getName()+" ----> "+c.getValue()+"<br>");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
