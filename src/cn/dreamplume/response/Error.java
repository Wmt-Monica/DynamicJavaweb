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
 * @Date 2021/2/3 10:54
 * @Created by 翊
 */
@WebServlet(name = "Error")
public class Error extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * response 细节点：
         *      1）：response获取的流不需要手动关闭， Tomcat 容器会帮我们关闭
         *      2）：getWriter 和 getOutputStream 不能同时调用
         */
        response.setCharacterEncoding("UTF-8");
        response.getWriter();
        response.getOutputStream();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
