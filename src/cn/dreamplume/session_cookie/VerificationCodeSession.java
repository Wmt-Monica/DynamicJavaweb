package cn.dreamplume.session_cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/8 15:33
 * @Created by 翊
 * 验证码验证，将验证码中的信息存储于 Session 对象中
 */
@WebServlet(name = "VerificationCodeSession")
public class VerificationCodeSession extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        // 获取页面用户输入的验证码
        String checkCode = request.getParameter("VerificationCode");
        writer.write("用户输入的验证码："+checkCode+"<br>");
        // 获取图片的文字的验证码
        String checkCode_Session = (String)request.getSession().getAttribute("checkcode_session");
        writer.write("图片验证码："+checkCode_Session+"<br>");
        // 比较用户输入的验证码和图片的验证码是否一致
        if (!checkCode.equals(checkCode_Session)) {
           writer.write("验证码输入错误");
        }else {
            writer.write("验证码输入正确");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
