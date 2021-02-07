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
 * @Date 2021/2/7 11:40
 * @Created by 翊
 */
@WebServlet(name = "RemoveCookie")
public class RemoveCookie extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 注意：由于 /cookieTest 没有设置 Cookie 对象其携带路径，默认
         * web 应用下同一级的服务器可视化，都可以对该 Cookie 对象进行操作
         */
        // 删除同一级的 /cookieTest 下的 Cookie 对象中的 name 参数
        // 1.创建 Cookie 对象，传入要删除的参数，其中值无所谓赋值
        Cookie cookie = new Cookie("name","");

        // 2.清除 Cookie 对象中的 name 参数，实质就是设置其数据存活时间为 0
        cookie.setMaxAge(0);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
