<%@ page import="cn.dreamplume.jsp.People" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/2/13
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL的使用</title>
</head>
<style>
    .all {
        float: left;
        width: 100%;
        height: auto;
    }
    .peo {
        float: left;
        width: 25%;
    }
</style>
<body>
    <%
        request.setAttribute("ID","1");
        request.setAttribute("name","喵小咪");
        request.setAttribute("age","18");
        request.setAttribute("position","奶茶服务员");
    %>
    <div class="all">
        <div class="peo">
            <div>
                <img src="content1.jpg" style="width: 300px">
            </div>
            <div>
                <%--
                    使用EL中的requestScope对象，寻找的对象使用在'.'之后，后面直接写入寻找的对应的名称

                    EL最主要的作用是获得四大域中的数据，格式${EL表达式}
                    EL获得pageContext域中的值：${pageScope.key};
                    EL获得request域中的值：${requestScope.key};
                    EL获得session域中的值：${sessionScope.key};
                    EL获得application域中的值：${applicationScope.key};
                 --%>
                ID号：${requestScope.ID}<br>
                姓名：${requestScope.name}<br>
                年龄：${requestScope.age}<br>
                职业：${requestScope.position}<br>
            </div>
        </div>
        <%
            pageContext.setAttribute("ID","2");
            pageContext.setAttribute("name","喵小萌");
            pageContext.setAttribute("age","19");
            pageContext.setAttribute("position","画家");
        %>
        <div class="peo">
            <div>
                <img src="content2.jpg" style="width: 300px">
            </div>
            <%--
                EL从四个域中获得某个值${key};
                同样是依次从pageContext域，request域，session域，application域中获取属性，
                在某个域中获取后将不在向后寻找
            --%>
            <div>
                ID号：${ID}<br>
                姓名：${name}<br>
                年龄：${age}<br>
                职业：${position}<br>
            </div>
        </div>
        <%
            List<People> peopleList = new ArrayList<>();

            People people1 = new People();
            people1.setId((String) request.getAttribute("ID"));
            people1.setName((String) request.getAttribute("name"));
            people1.setAge((String) request.getAttribute("age"));
            people1.setPosition((String) request.getAttribute("position"));

            People people2 = new People();
            people2.setId("2");
            people2.setName("喵小萌");
            people2.setAge("19");
            people2.setPosition("画家");

            People people3 = new People();
            people3.setId("3");
            people3.setName("喵小樱");
            people3.setAge("20");
            people3.setPosition("服装设设计师");

            People people4 = new People();
            people4.setId("4");
            people4.setName("喵小兰");
            people4.setAge("21");
            people4.setPosition("网红");

            peopleList.add(people1);
            peopleList.add(people2);
            peopleList.add(people3);
            peopleList.add(people4);

            request.setAttribute("peopleList",peopleList);
        %>
        <div class="peo">
            <div>
                <img src="content3.jpg" style="width: 300px">
            </div>
            <%-- 使用集合类时使用[]括号中间填入适当的下标参数在'.'之后填入对象的属性名称，实则调用的是该属性的get()方法 --%>
            <div>
                ID号：${requestScope.peopleList[2].id}<br>
                姓名：${requestScope.peopleList[2].name}<br>
                年龄：${requestScope.peopleList[2].age}<br>
                职业：${requestScope.peopleList[2].position}<br>
            </div>
        </div>
        <div class="peo">
            <div>
                <img src="content4.jpg" style="width: 300px">
            </div>
            <div>
                ID号：${requestScope.peopleList[3].id}<br>
                姓名：${requestScope.peopleList[3].name}<br>
                年龄：${requestScope.peopleList[3].age}<br>
                职业：${requestScope.peopleList[3].position}<br>
            </div>
        </div>
    </div>
</body>
</html>
