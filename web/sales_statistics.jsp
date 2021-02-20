<%@ page import="java.util.List" %>
<%@ page import="cn.dreamplume.project.shopping.domain.ShoppingObject" %>
<%@ page import="cn.dreamplume.project.shopping.dao.ConnectionJDBC" %>
<%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/2/18
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />

    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/xadmin.css">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <link rel="stylesheet" href="css/amazeui.min.css"/>

    <script src="scripts/jquery.min.js"></script>
    <script src="layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>
    <script src="js/echarts.min.js"></script>
    <title>商品销量统计</title>
</head>
<%
    List<ShoppingObject> shoppingObjectList = new ConnectionJDBC().getShoppingObjects();
    request.setAttribute("shoppingObjectList", shoppingObjectList);
%>
<body>
    <div class="x-body">
        <div class="layui-row">
            <form class="layui-form layui-col-md12 x-so" action="" id="searchByInfo"  method="post">
                <input class="layui-input" placeholder="商品类型" name="testweekly" id="testweekly">
                <input class="layui-input" placeholder="商品名称" name="stuclass" id="stuclass">
                <button class="layui-btn" onclick="searchByInfo()"><i class="layui-icon">&#xe615;</i>
                </button>
            </form>
        </div>
        <script>
            function refreshCommodity () {
                window.location.reload();
            }
        </script>
        <style>
            .refresh {
                float: right;
                width: 50px;
                height: 30px;
                color: white;
            }
            .shopObj span {
                cursor: pointer;
            }
        </style>
        <input type="button" value="刷新" onclick="refreshCommodity()" class="refresh" style="background-color: #009688" >
        <hr>
        <table class="layui-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>商品名称</th>
                <th>商品类型</th>
                <th>库存</th>
                <th>销售价格</th>
                <th>总销售额</th>
                <th>月销售额</th>
                <th>商品介绍</th>
                <th>操作</th>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.shoppingObjectList}" var="obj">
                    <tr class="shop${obj.id}">
                        <td>
                                ${obj.id}
                        </td>
                        <td>
                                ${obj.name}
                        </td>
                        <td>
                                ${obj.type}
                        </td>
                        <td>
                                ${obj.stock}
                        </td>
                        <td>
                                ${obj.selling_price}
                        </td>
                        <td>
                                ${obj.total_sales}
                        </td>
                        <td>
                                ${obj.total_monthly_sales}
                        </td>
                        <td>
                                ${obj.introduce}
                        </td>
                        <td class="td-manage shopObj">
                            <span title="编辑"  onclick="editObj(this,${obj.id})" href="javascript:;">
                                <span>编辑</span><i class="layui-icon">&#xe642;</i>
                            </span>
                            <span title="删除" href="shopDelete?deleteShopID=${obj.id}" id="delete">
                                <span>删除</span><i class="layui-icon">&#xe640;</i>
                            </span>
                        </td>
                    </tr>
                </c:forEach>
            </tr>
            </tbody>
        </table>
    </div>
    <hr class="layui-bg-green">

    <script>
        //编辑操作
        function editObj(obj,getid){
            layer.open({
                type: 2,
                area: ['700px', '500px'],
                fixed: false, //不固定
                shadeClose: true,
                maxmin: true,
                //跳出相对路劲,指定显示的页面路径
                content: 'edit.jsp?deleteID='+getid,
                // content : '',
                end: function(){
                    // 如果是通过单击关闭按钮关闭弹出层，父画面没有此表单
                    if($("#subform").length === 1){
                        $("#subform").submit();
                    }
                }
            });
        }

        //搜索操作
        function searchByInfo(){
            var getBookName = $("#bookname").val();
            var getBookauthor = $("#author").val();
            var form = document.getElementById("searchByInfo");
            form.onsubmit = function(form){
                if (getBookName == "" && getBookauthor == ""){
                    layer.msg('至少填写一个查询信息', {icon: 0.5});
                    return false;
                }else{
                    $("#searchByInfo").attr("").submit();
                }
            }
        }
    </script>
</body>
</html>

