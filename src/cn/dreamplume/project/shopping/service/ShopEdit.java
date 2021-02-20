package cn.dreamplume.project.shopping.service;

import cn.dreamplume.project.shopping.util.JDBCUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/18 21:55
 * @Created by 翊
 */
@WebServlet(name = "ShopEdit")
public class ShopEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        // 获取表单修改商品信息的数据
        String commodityName = request.getParameter("commodityName");
        String commodityType = request.getParameter("commodityType");
        String commodityPrice = request.getParameter("commodityPrice");
        String commodityStock = request.getParameter("commodityStock");
        String commodityIntroduce = request.getParameter("commodityIntroduce");
        String deleteID = request.getParameter("deleteID");

        System.out.println("commodityName = "+commodityName);
        System.out.println("commodityType = "+commodityType);
        System.out.println("commodityPrice = "+commodityPrice);
        System.out.println("commodityStock = "+commodityStock);
        System.out.println("commodityIntroduce = "+commodityIntroduce);
        System.out.println("deleteID = "+deleteID);

        String sql = "update commodity set name = "+"\'"+commodityName+"\', "+"type = "+"\'"+commodityType+"\', "+
                "selling_price = "+commodityPrice+", stock = "+commodityStock+", introduce = "+"\'"+commodityIntroduce+"\'"+
                "where id = "+deleteID;
        try {
            PreparedStatement pre = new JDBCUtil().getConnection().prepareStatement(sql);  // 创建预编译SQL语句对象
            pre.execute();  // 执行SQL语句
            pre.close();  // 释放资源
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 转发
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditSuccess.html");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
