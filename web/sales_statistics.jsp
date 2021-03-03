<%@ page import="java.util.List" %>
<%@ page import="cn.dreamplume.project.shopping.domain.ShoppingObject" %>
<%@ page import="cn.dreamplume.project.shopping.dao.ConnectionJDBC" %>
<%@ page import="java.sql.SQLException" %>
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
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />

    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/xadmin.css">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <link rel="stylesheet" href="css/amazeui.min.css"/>

    <script src="scripts/jquery.min.js"></script>
    <script src="layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>
    <script src="js/echarts.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <title>商品销量统计</title>
</head>
<%
    String commodityType = (String) request.getAttribute("commodityType");
    String commodityName = (String) request.getAttribute("commodityName");
    // 防止出现在未搜索前的 commodityType 和 commodityName 未赋值出现 null 的现象
    if (commodityName == null) {
        commodityName = "";
    }
    if (commodityType == null) {
        commodityType = "";
    }
    List<ShoppingObject> shoppingObjectList = null;
    try {
        shoppingObjectList = new ConnectionJDBC().getShoppingObjects(commodityType, commodityName);
    } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
        e.printStackTrace();
    }
    request.setAttribute("shoppingObjectList", shoppingObjectList);
%>
<style>
    .ajaxSearch {
        position: absolute;
        z-index: 1000;
        background-color: white;
        width: 189px;
        height: auto;
        border:#e6e6e6 1px solid;
        color: black;
        -moz-box-shadow: 1px 1px 5px #e6e6e6;
        -webkit-box-shadow: 1px 1px 5px #e6e6e6;
        box-shadow:1px 1px 5px #e6e6e6;
    }
    .searchLeft {
        margin-left: 404px;
    }
    .searchRight {
        margin-left: 598px;
    }
    .searchLeft td, .searchRight td {
        position: relative;
        height: 40px;
        width: 189px;
        padding: 9px;
        min-height: 20px;
        font-size: 14px;
        color: #666666;
    }
    .searchLeft td, .searchRight td:hover {
        background-color: #e6e6e6;
        color: black;
        font-size: 14px;
    }
</style>
<script>
    // 当输入框是去焦点之后推荐搜索的div内容就隐藏
    $(function () {
        $("#commodityType").blur(function () {
            $("#searchTypeContent").css("display","none");
        });
        $("#commodityName").blur(function () {
            $("#searchNameContent").css("display","none");
        });
    });

    function searchTypeType() {
        $.post(
            "/searchType",  // url
            {"type":document.getElementById("commodityType").value},  // 请求参数
            function (data) {  // 执行成功的回调函数
                var types = "";
                for (var i = 0; i < data.length; i ++) {
                    types += "<tr><td>"+data[i].type+"</td></tr>";
                }
                document.getElementById("searchTypeContent").innerHTML = types;
            },
            "json"// 返回参数的类型
        );
    }

    function searchNameType() {
        $.post(
            "/searchName",  // url
            {"name":document.getElementById("commodityName").value},  // 请求参数
            function (data) {  // 执行成功的回调函数
                var names = "";
                for (var i = 0; i < data.length; i ++) {
                    names += "<tr><td>"+data[i].name+"</td></tr>";
                }
                document.getElementById("searchNameContent").innerHTML = names;
            },
            "json"// 返回参数的类型
        );
    }
</script>
<body>
    <div class="x-body">
        <div class="layui-row">
            <form class="layui-form layui-col-md12 x-so" action="search" id="searchByInfo"  method="post" style="position: relative">
                <input onkeyup="searchTypeType()" class="layui-input" placeholder="商品类型" name="commodityType" id="commodityType">
                <div class="ajaxSearch searchLeft">
                    <table id="searchTypeContent"></table>
                </div>
                <input onkeyup="searchNameType()" class="layui-input" placeholder="商品名称" name="commodityName" id="commodityName">
                <div class="ajaxSearch searchRight">
                    <table id="searchNameContent"></table>
                </div>
                <input type="submit" class="layui-btn" onclick="searchByInfo()" value="搜索">
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
                padding-left: 12px;
                line-height: 30px;
            }
            .shopObj span {
                cursor: pointer;
            }
        </style>
        <input type="button" value="刷新" onclick="refreshCommodity()" class="refresh layui-btn" style="background-color: #009688" >
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
                content: 'edit.jsp?editID='+getid,
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
            var commodityType = $("#commodityType").val();
            var commodityName = $("#commodityName").val();
            var form = document.getElementById("searchByInfo");
            form.onsubmit = function(){
                if (commodityType === "" && commodityName === ""){
                    layer.msg('至少填写一个查询信息', {icon: 0.5});
                    return false;
                }else {
                    return true;
                }
            }
        }
    </script>
</body>
</html>

