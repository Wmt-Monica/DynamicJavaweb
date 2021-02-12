<%@ page import="cn.dreamplume.jsp.People" %>
<%@ page import="java.util.List" %>
<%@ page import="sun.misc.Request" %><%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/2/12
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>人物介绍</title>
    </head>
    <link rel="shortcut icon" href="logo.ico" type="favicon" />
    <link rel="stylesheet" href="Css.css" />
    <body>
        <div class="all">
            <!--导航栏-->
            <div class="logo">
                <span>人物列表</span>
            </div>
            <!--人物具体内容-->
            <div class="content">
                <!--人物头像-->
                <div class="row">
                    <div class="lop">
                        <img src="content1.jpg"/>
                    </div>
                    <div class="lop">
                        <img src="content2.jpg" />
                    </div>
                    <div class="lop">
                        <img src="content3.jpg" />
                    </div>
                    <div class="lop">
                        <img src="content4.jpg" />
                    </div>
                </div>
                <%
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html;charset=utf-8");
                    // 获取人物 peopleList 对象
                    List<People> peopleList = (List<People>) request.getAttribute("peopleList");
                    int i = 0;
                %>
                <!--人物介绍-->
                <div class="introduce">
                    <div class="int_lop peo_1"><br>
                        姓名：
                        <span class="name">
                            <%= peopleList.get(i).getName()%>
                        </span><br>
                        年龄：
                        <span class="age">
                            <%= peopleList.get(i).getAge() %>
                        </span><br>
                        职业：
                        <span class="position">
                            <%= peopleList.get(i).getPosition() %>
                        </span>
                        <% i++; %><br>
                    </div>
                    <div class="int_lop peo_2"><br>
                        姓名：
                        <span class="name">
                            <%= peopleList.get(i).getName() %>
                        </span><br>
                        年龄：
                        <span class="age">
                            <%= peopleList.get(i).getAge() %>
                        </span><br>
                        职业：
                        <span class="position">
                            <%= peopleList.get(i).getPosition()%>
                        </span>
                        <% i++; %><br>
                    </div>
                    <div class="int_lop peo_3"><br>
                        姓名：
                        <span class="name">
                            <%= peopleList.get(i).getName() %>
                        </span><br>
                        年龄：
                        <span class="age">
                            <%= peopleList.get(i).getAge() %>
                        </span><br>
                        职业：
                        <span class="position">
                            <%= peopleList.get(i).getPosition()%>
                        </span>
                        <% i++; %><br>
                    </div>
                    <div class="int_lop peo_4"><br>
                        姓名：
                        <span class="name">
                            <%= peopleList.get(i).getName() %>
                        </span><br>
                        年龄：
                        <span class="age">
                            <%= peopleList.get(i).getAge() %>
                        </span><br>
                        职业：
                        <span class="position">
                            <%= peopleList.get(i).getPosition()%>
                        </span>
                        <% i++; %><br>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

