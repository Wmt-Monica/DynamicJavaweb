package cn.dreamplume.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet")
public class IntroductionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 防止在页面显示的中文汉字成乱码现象，在写入文字前加入 response.setContentType("text.html;charset=UTF-8)
        response.setContentType("text/html;charset=UTF-8");
        // 在form 表单提交完毕后显示的页面写入文字
        response.getWriter().write("石燔傻逼");
        // 在 form 表单提交之后在控制台写入语句
        System.out.println("石燔傻逼");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
