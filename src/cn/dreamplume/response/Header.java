package cn.dreamplume.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/2 12:35
 * @Created by 翊
 */
@WebServlet(name = "Header")
public class Header extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置状态码 302, post 型的重定向
        response.setStatus(302);

        // 设置响应头
        // addHeader() 方法是添加对应响应头的数据值----(累加数据，不覆盖原来的数据)
        response.setCharacterEncoding("UTF-8");
        response.addHeader("name", "ShiFanShaBi");
        response.addHeader("name","FanGouShi");

        // setHeader() 方法是设置对应响应头的数据值----(会覆盖)
        response.setHeader("age", "20");
        response.setHeader("age","21");

        // 封装成一个重定向的方法 sendRedirect(url)
//        response.sendRedirect("/formVerification");

        // 设置定时刷新响应头----和下面的重定向有着类似的效果不过加上了定时器
        // 注意：上面的重定向和该定时刷新响应头的方法不能叠用
        response.setHeader("refresh","5;url=http://www.baidu.com");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
