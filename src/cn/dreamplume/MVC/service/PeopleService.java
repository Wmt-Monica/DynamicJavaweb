package cn.dreamplume.MVC.service;

import cn.dreamplume.MVC.dao.ConnectionJDBC;
import cn.dreamplume.MVC.domain.People;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/14 15:27
 * @Created by 翊
 */
@WebServlet(name = "PeopleService")
public class PeopleService extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 获取数据库中的人物资料清单
            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            List<People> peopleList = connectionJDBC.getObjectList();

            // 将人物资料清单存储到 request 对象中去
            request.setAttribute("peopleList", peopleList);

            // 创建转发器对象
            RequestDispatcher dispatcher = request.getRequestDispatcher("JavaEE_MVC.jsp");

            // 执行转发操作
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
