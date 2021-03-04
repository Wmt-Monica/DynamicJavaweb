<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="cn.dreamplume.project.shopping.util.JDBCUtil" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="cn.dreamplume.project.shopping.domain.ShoppingObject" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="cn.dreamplume.project.shopping.dao.ConnectionJDBC" %><%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/2/19
  Time: 8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
    <script src="scripts/jquery.min.js"></script>

    <title>商品信息编辑</title>
    <style>
        body{padding: 10px;}
    </style>
    <script>
        function refresh() {
            window.refresh();
        }
    </script>
</head>
<%
    // 将编辑商品信息的页面中保留原先商品的信息
    try {
        // 创建筛选出的 SQL 语句
        String sql = "select name, type, selling_price,introduce, stock from commodity where id = "+request.getParameter("editID");
        System.out.println("sql = "+sql);
        // 创建预编译Sql语句对象
        PreparedStatement pre = null;
        pre = new JDBCUtil().getConnection().prepareStatement(sql);
        // 执行SQL语句并实例化结果集对象
        ResultSet resultSet = pre.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int colCount = metaData.getColumnCount();
        resultSet.next();
        ShoppingObject obj = new ShoppingObject();
        for (int i = 1; i <= colCount; i ++) {
            String colName = metaData.getColumnLabel(i);
            Object colValue = resultSet.getObject(i);
            Field field = null;
            try {
                field = ShoppingObject.class.getDeclaredField(colName);
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            try {
                field.set(obj, colValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("obj = "+obj.toString());
        // 将编辑的待修改商品信息的存入 request 域容器中
        request.setAttribute("obj", obj);
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<body onload="selectDefault()">
    <form class="layui-form layui-form-pane1" action="shopEdit" id="subform" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-block">
                <input type="text" name="commodityName" value="${requestScope.obj.name}" lay-verify="required|title" required placeholder="请输入商品名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <script>
            function selectDefault() {
                var selects = document.getElementById("selectType");
                for (var i = 0; i < selects.options.length; i ++) {
                    console.log("筛选值："+selects.options[i].value)
                    console.log("默认值："+"${requestScope.obj.type}")
                    if (selects.options[i].value === ("${requestScope.obj.type}")) {
                        selects.options[i].selected = true;
                        break;
                    }
                }
            }
        </script>
        <%
            // 获取数据库中商品类型enum中的所有值再通过 <c:forEach> 语句来循环进行遍历
            String[] types = new ConnectionJDBC().getTypes();
            request.setAttribute("types",types);
        %>
        <div class="layui-form-item">
            <label class="layui-form-label">商品类型</label>
            <div class="layui-input-block">
                <select name="commodityType" lay-filter="adminRole" id="selectType" onselect="selectDefault()">
                    <option value="${requestScope.obj.type}">${requestScope.obj.type}</option>
                    <c:forEach items="${requestScope.types}" var="type">
                        <option value="${type}">${type}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品售价</label>
            <div class="layui-input-block">
                <input value="${requestScope.obj.selling_price}" type="number" name="commodityPrice" lay-verify="required|number" required placeholder="请输入商品销售价格" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品介绍</label>
            <div class="layui-input-block">
                <input value="${requestScope.obj.introduce}" type="text" name="commodityIntroduce" lay-verify="required|title" required placeholder="请输入商品介绍" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品库存</label>
            <div class="layui-input-block">
                <input value="${requestScope.obj.stock}" type="number" name="commodityStock" lay-verify="required|number" required placeholder="请输入商品库存数量" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">上架时间</label>
            <div class="layui-input-block">
                <input type="text" name="commodityTime" id="createTime" lay-verify="required|title" required placeholder="请选择商品上架时间" autocomplete="off" class="layui-input">
            </div>
        </div>
        <%--  此处创建一个 hidden 属性的 input 是为了将修改商品的编号ID能够传给服务器  --%>
        <input type="hidden" name="deleteID" value="${pageContext.request.getParameter("editID")}" checked="checked" >
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="submit" class="layui-btn" lay-submit lay-filter="*" value="提交">
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
    <br><br><br>

    <script>
        //加载layui信息
        layui.use('form', function(){
            var form = layui.form;
            //layui日期选择
            layui.use('laydate', function(){
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({
                    elem: '#createTime', //指定元素
                    value: new Date()
                });
            });
        });
    </script>
</body>
</html>