package cn.dreamplume.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/5 21:18
 * @Created by 翊
 * 转发和重定向的区别，request 中的域对象的范围：一次请求的范围
 */
@WebServlet(name = "Forward2")
public class Forward2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 向 request 的域对象中存储 name 名称的数据赋值为 燔哥哥
        request.setAttribute("name","燔哥哥");

        // 使用转发的形式向 Java 服务器类对象要数据呈现给页面
        // 1.创建转发器 RequestDispatcher 对象
        RequestDispatcher dispatcher = request.getRequestDispatcher("/forward3");
        // 2.执行转发方法
        // 实行转发的过程，其中时程序这边实行了请求数据的向 forward3 对象转向，而请求方只发出了一次请求
        // 故应此在此设置的域对象 name 属性值可以访问到为 燔哥哥
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
