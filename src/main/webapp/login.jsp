<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="static/css/admin_login.css"/>
    <script src="static/js/util.js" type="text/javascript"></script>
    <script src="static/js/config.js" type="text/javascript"></script>
    <script src="static/js/jquery-2.1.0.min.js" type="text/javascript"></script>
</head>
<body>
<div class="admin_login_wrap">
    <h1>后台管理</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form action="user" method="post" id="loginForm">
                <%--actionName表示用户行为--%>
                <input  type="hidden" name="actionName" value="login">
                    <input  type="hidden" name="qx" value="${resultInfo.result.QX}">

                    <ul class="admin_items">
                    <li>
                        <label for="userName">用户名：</label>
                        <input type="text" name="userName" value="${resultInfo.result.EMPEE_ACCT}" id="userName" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="passWord">密码：</label>
                        <input type="passWord" name="passWord" value="${resultInfo.result.PASSWORD}" id="passWord" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <input name="rem" type="checkbox" value="1" class="inputcheckbox">&nbsp;&nbsp;记住密码
                    </li>
                    <li>
                        <span id="msg" style="color: red; font-size: 12px">${resultInfo.msg}</span>
                    </li>
                    <li>
                        <input type="button" tabindex="3" value="登录" class="btn btn-primary" onclick="checkLogin()" />

                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>
</html>