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
 * @Date 2021/2/18 16:00
 * @Created by 翊
 */
@WebServlet(name = "ShopDelete")
public class ShopDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取删除商品的ID编号
        String deleteShopID = request.getParameter("deleteShopID");
        // 创建删除数据库中指定ID编号商品
        String sql = "delete from commodity where id = "+deleteShopID;
        System.out.println("sql = "+sql);
        try {
            // 创建预编译 PreparedStatement 对象
            PreparedStatement pre = new JDBCUtil().getConnection().prepareStatement(sql);
            // 执行删除SQL语句
            pre.execute();
            // 释放资源
            pre.close();
            // 创建转发器
            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_statistics.jsp");
            // 执行转发
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
