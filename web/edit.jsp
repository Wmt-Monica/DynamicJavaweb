<%--
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
</head>
<body>
<form class="layui-form layui-form-pane1" action="shopEdit" id="subform" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">商品名称</label>
        <div class="layui-input-block">
            <input type="text" name="commodityName" lay-verify="required|title" required placeholder="请输入商品名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品类型</label>
        <div class="layui-input-block">
            <select name="commodityType" lay-filter="adminRole">
                <option value="">请选择商品类型</option>
                <option value="果蔬">果蔬</option>
                <option value="服饰">服饰</option>
                <option value="糖果">糖果</option>
                <option value="电子">电子</option>
                <option value="鞋子">鞋子</option>
                <option value="化妆品}">化妆品</option>
                <option value="家具">家具</option>
                <option value="文具">文具</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品售价</label>
        <div class="layui-input-block">
            <input type="number" name="commodityPrice" lay-verify="required|number" required placeholder="请输入商品销售价格" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品介绍</label>
        <div class="layui-input-block">
            <input type="text" name="commodityIntroduce" lay-verify="required|title" required placeholder="请输入商品介绍" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品库存</label>
        <div class="layui-input-block">
            <input type="number" name="commodityStock" lay-verify="required|number" required placeholder="请输入商品库存数量" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上架时间</label>
        <div class="layui-input-block">
            <input type="text" name="commodityTime" id="createTime" lay-verify="required|title" required placeholder="请选择商品上架时间" autocomplete="off" class="layui-input">
        </div>
    </div>
    <%--  此处创建一个 hidden 属性的 input 是为了将修改商品的编号ID能够传给服务器  --%>
    <input type="hidden" name="deleteID" value="${pageContext.request.getParameter("deleteID")}" checked="checked" >
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