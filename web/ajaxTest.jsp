<%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/3/2
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>ajax测试Html页面</title>
</head>
<script>
    window.onload = function () {
        document.getElementById("tongBu").getElementsByTagName("li")[0].innerHTML = "动态的改变页面中指定的内容";
    }

    function fun1() {
        // ajax 的基本步骤
        // 1.创建 ajax 引擎对象 ----> 所有的操作都是通过引擎对象
        var xmlHttp;
        // XMLHttpRequest 浏览器对象的兼容
        if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        }else {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        // 2.绑定监听 ----> 监听服务器是否已经返回相应的数据
        xmlHttp.onreadystatechange = function () {
            if(xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                // 5.接受相应的数据
                var sendResult = xmlHttp.responseText;
                document.getElementById("tongBu").getElementsByTagName("li")[1].innerHTML = sendResult;
                console.log(sendResult);
            }
        }
        // 3.绑定地址
        /*
            参数一：访问服务器的方式
            参数二：访问服务器的服务器地址
            参数三：是否为异步
            http://localhost:8080/ajaxServlet
         */
        xmlHttp.open("GET","/jsAjaxServlet?condition=get发送请求的参数内容", true);
        // 4.发送请求
        xmlHttp.send();
    }

    function fun2() {
        // ajax 的基本步骤
        // 1.创建 ajax 引擎对象 ----> 所有的操作都是通过引擎对象
        var xmlHttp;
        // XMLHttpRequest 浏览器对象的兼容
        if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        }else {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        // 2.绑定监听 ----> 监听服务器是否已经返回相应的数据
        xmlHttp.onreadystatechange = function () {
            if(xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                // 5.接受相应的数据
                var sendResult = xmlHttp.responseText;
                document.getElementById("fun2").innerHTML = sendResult;
                console.log(sendResult);
            }
        }
        // 3.绑定地址
        /*
            参数一：访问服务器的方式
            参数二：访问服务器的服务器地址
            参数三：是否为异步
            http://localhost:8080/ajaxServlet
         */
        xmlHttp.open("GET","/jsAjaxServlet?condition=get发送请求的参数内容", true);
        // 4.发送请求
        xmlHttp.send();
    }

    function fun3() {
        // ajax 的基本步骤
        // 1.创建 ajax 引擎对象 ----> 所有的操作都是通过引擎对象
        var xmlHttp;
        // XMLHttpRequest 浏览器对象的兼容
        if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        }else {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        // 2.绑定监听 ----> 监听服务器是否已经返回相应的数据
        xmlHttp.onreadystatechange = function () {
            if(xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                // 5.接受相应的数据
                var sendResult = xmlHttp.responseText;
                document.getElementById("fun3").innerHTML = sendResult;
                console.log(sendResult);
            }
        }
        // 3.绑定地址
        /*
            参数一：访问服务器的方式
            参数二：访问服务器的服务器地址
            参数三：是否为异步
         */
        xmlHttp.open("POST","/jsAjaxServlet", false);
        // 4.发送请求
        // 如果发送方式是 post 类型，那么添加响应头的参数要添加如下代码
        xmlHttp.setRequestHeader("content-type" , "application/x-www-form-urlencoded");
        xmlHttp.send("condition=Post发送请求的参数");
    }
</script>
<body>
<h3>js原生Ajax</h3>
<div id="tongBu">
    <!--
        同步现象︰客户端发送请求到服务器端，当服务器返回响应之前，客户端都处于等待卡死状态
        异步现象︰客户端发送请求到服务器端，无论服务器是否返回响应，客户端都可以随意做其他事情，不会被卡死
    -->
    <li>同步代码</li>
    <li>异步代码</li>
    <input type="button" onclick="fun1()" value="异步获取服务器中的相应数据">
    <br><br>
    <h3>同异步的区别</h3>
    <input type="button" onclick="fun2()" value="异步"><span id="fun2"></span><br>
    <input type="button" onclick="fun3()" value="同步"><span id="fun3"></span><br>
    <input type="button" onclick="alert()" value="测试按钮">
</div>
<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
<script type="text/javascript">
    function fun4() {
        $.get(
            "/jQueryAjaxServlet",  // url 地址
            {"name":"王梦婷","age":22},  // 请求参数
            function (data) {  // 执行成功后的回调函数
                document.getElementById("getButton").innerHTML = data.name;
            },
            "json"
        );
    };

    function fun5() {
        $.post(
            "/jQueryAjaxServlet",  // url 地址
            {"name":"石燔","age":22},  // 请求参数
            function (data) {  // 执行成功后的回调函数
                document.getElementById("putButton").innerHTML = data.name;
            },
            "json"
        );
    };

    function fun6() {
        $.ajax({
            url:"/jQueryAjaxServlet",  // url 请求服务器方地址
            async:true,  // 是否为异步(默认是true)
            type:"POST",  // 请求方式 GET/POST
            data:{"name":"Monica","age":20},  // 请求方发送出去的参数
            success:function (data) {  // 请求成功后执行的回执函数
                document.getElementById("ajaxButton").innerHTML = data.name;
            },
            error:function(){
                alert("请求发送失败");
            },
            dataType:"json"  // 服务器发送回来的数据类型 text/json...
        });
    };
</script>
<h3>jQuery版本Ajax</h3>
<div>
    <input type="button" onclick="fun4()" value="get异步访问"><span id="getButton"></span><br>
    <input type="button" onclick="fun5()" value="put异步访问"><span id="putButton"></span><br>
    <input type="button" onclick="fun6()" value="ajax访问"><span id="ajaxButton"></span><br>
</div>
</body>
</html>