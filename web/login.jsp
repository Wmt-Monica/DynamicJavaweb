<%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/2/18
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1">
    <!--basic_css-->
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">

    <style type="text/css">
        body {
            background-image:url('img/lg-bg.jpg');
            background-position:14px 14px;
            background-repeat:no-repeat;
            background-position: center center;
            background-attachment: fixed;
            background-size: cover;
        }
        .layui-input{
            height:45px;
            width:87%;
            padding-left: 5px;
            font-size:16px;
            display:inline-block;
        }

        .layui-btn {
            height:45px;
        }

        .layui-form {
            width:30%;
            height:60%;
            margin:0 auto;
            margin-top:7%;
            padding:15px 28px 0px;
            background:#fff;
        }

        .layui-input-block {
            margin-left:0;
        }

        .layui-input-block a {
            color:blue;
            float:right;
            line-height:30px;
            font-size:14px;
        }

        h1 {
            text-align:center;
            color:#1d598e;
        }

        #canvas {
            float:right;
            margin-right:1%;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 5px;
            cursor: pointer;
        }

        .input-val {
            width: 60%;
            background: #ffffff;
            height: 2.8rem;
            border-radius: 5px;
            border: none;
            border: 1px solid rgba(0,0,0,.2);
            padding-left: 5px;
        }
        .error {
            color: red;
            margin-left: 15%;
        }
    </style>
</head>

<body>
    <form class="layui-form" action="judgeLogin" id="loginForm" method="post">
        <div class="layui-form-item">
            <h1>Java-office操作</h1>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <span class="decrib">账号：</span>
                <input type="text" name="userName" placeholder="请输入账号" autocomplete="off" class="layui-input" autofocus required>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <span class="decrib">密码：</span>
                <input type="password" name="userPassword" placeholder="请输入密码" autocomplete="off" class="layui-input" required>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <span class="decrib">类型：</span>
                <input type="text" name="userType" placeholder="请选择账号类型" autocomplete="off" class="layui-input" list="list" required>
                <datalist id="list">
                    <option>管理员</option>
                    <option>商家</option>
                </datalist>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <span class="decrib">验证：</span>
                <input type="text" value="" placeholder="请输入验证码（不区分大小写）" class="input-val">
                <canvas id="canvas" width="100" height="43">
                </canvas>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="submit" class="layui-btn layui-btn-fluid" value="登录" />
            </div>
        </div>
        <div>
            <span class="error">
                ${requestScope.error}
            </span>
        </div>
        <br>
        <br>
    </form>
</body>

<script src="layui/layui.js"></script>
<script src="layui/jquery.min.js"></script>
<!--验证码-->
<script>
    $(function(){
        var show_num = [];
        draw(show_num);

        // 点击验证码刷新
        $("#canvas").on('click',function(){
            draw(show_num);
        })

        $(".layui-btn").on('click',function(){
            var val = $(".input-val").val().toLowerCase();  // 获取输入的验证码的具体内容，全部转成小写的，不区分大小写
            var num = show_num.join("");  // 将随即生成的验证码添加空字符串转成字符串的形式赋值给 num
            // 验证码输入为空
            if(val==''){
                alert('请输入所有信息！');
                return false;  // 返回 false 防止跳转页面
            }else if(val == num){  // 验证码输入正确
                $(".input-val").val('');
                draw(show_num);
            }else{  // 验证码输入错误
                alert('验证码错误！请重新输入！');
                $(".input-val").val('');
                draw(show_num);
                return false;  // 返回 false 防止跳转页面
            }
        })
    })

    function draw(show_num) {
        var canvas_width=$('#canvas').width();
        var canvas_height=$('#canvas').height();
        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");
        var aLength = aCode.length;//获取到数组的长度

        for (var i = 0; i <= 3; i++) {
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
            var deg = (Math.random() * 30 * Math.PI / 180); //产生0~30之间的随机弧度
            /**
             * 此处的 txt 是验证码的内容,用于之后的验证功能,该
             * 功能一般由前端 js 带啊吗完成,减轻后端程序的繁琐
             */
            var txt = aCode[j];//得到随机的一个内容
            /**
             * 将获取到的随机生成的验证码内容全部转成小写赋值给 show_num 用于之后对验证码的验证
             */
            show_num[i] = txt.toLowerCase();
            var x = 10 + i * 20;//文字在canvas上的x坐标
            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
            context.font = "bold 23px 微软雅黑";

            context.translate(x, y);
            context.rotate(deg);

            context.fillStyle = randomColor();
            context.fillText(txt, 0, 0);

            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
    }

    function randomColor() {//得到随机的颜色值
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }
</script>

</html>
