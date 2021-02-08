package cn.dreamplume.session_cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/7 21:44
 * @Created by 翊
 * JSESSIONID 的持久化
 */
@WebServlet(name = "JSESSIONID")
public class JSESSIONID extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 通过 request 对象创建 Session 对象
        HttpSession session = request.getSession();
        // 获取 Session 对象的 id
        String id = session.getId();
        // 手动设置一个存储 JSESSIONID 的 Cookie 为该 cookie 设置持久化
        Cookie cookie = new Cookie("JSESSIONID",id);
        // 设置该 Cookie 对象的携带路径
        cookie.setPath("/JSESSIONID");
        // 为 response 对象添加 Cookie 对象
        response.addCookie(cookie);
        // 为该 Cookie 对象设置存在时间(单位：秒)
        cookie.setMaxAge(60*10);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
