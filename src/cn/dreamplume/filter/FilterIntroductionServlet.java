package cn.dreamplume.filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/3/5 15:46
 * @Created by 翊
 * filter 快速入门
 */
@WebServlet(name = "FilterIntroductionServlet")
public class FilterIntroductionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FilterIntroductionServlet is  running.....");
        response.getWriter().write("FilterIntroductionServlet is  running.....");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
