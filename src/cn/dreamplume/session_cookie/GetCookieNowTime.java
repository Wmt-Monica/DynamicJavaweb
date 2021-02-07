package cn.dreamplume.session_cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/7 17:52
 * @Created by 翊
 */
@WebServlet(name = "GetCookieNowTime")
public class GetCookieNowTime extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        // 创建一个返回上一次访问时间的 Cookie 对象
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String nowTime = dateFormat.format(date);
        Cookie newNowTime = new Cookie("nowTime",nowTime);

        // 从该会话 sendCookie 资源中获取所有的 Cookie 数组对象
        Cookie[] cookies = request.getCookies();

        // 创建 response 响应器 PrintWriter 对象
        PrintWriter writer = response.getWriter();

        boolean flag = false;
        for (int i = 0; i < cookies.length; i ++) {
            // 当 sendCookie 资源中含有的 Cookie 对象名为 nowTime 的，说明在该会话中有访问过该页面
            // 将上一次保存访问的时间提示显现在页面中，最后，再将该次访问时间覆盖在名为 nowTime 的 Cookie对象
            if (cookies[i].getName().equals("nowTime")) {
                flag = true;
                // 当不是第一次访问该页面，返回提示上一次访问的时间
                writer.write("上一次访问时间："+cookies[i].getValue()+"<br>");
                // 将当前访问的时间重新覆盖在该会话的 sendCookie 资源中
                response.addCookie(newNowTime);
            }
        }
        // 当 flag == false 时说明在该会话 senCookie 资源中没有 nowTime Cookie 对象
        // 故是该客户端首次访问该页面，先将初次访问记录的时间通过 response 的 addCookie() 方法中添加到 sendCookie 资源中
        if (!flag) {
            response.addCookie(newNowTime);
        }
    }
}
