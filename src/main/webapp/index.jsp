<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台</title>
    <link rel="stylesheet" type="text/css" href="static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/main.css"/>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.28.7/dist/sweetalert2.all.min.js"></script>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="static/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="static/js/bootstrap.min.js"></script>

    <script type="text/javascript">
    </script>
    <script src="static/js/type.js"></script>
    <script src="static/js/salary.js"></script>
    <script src="static/js/util.js"></script>
    <script src="static/js/user.js"></script>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <%--<div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.html">首页</a></li>
                &lt;%&ndash;<li><a href="#" target="_blank">网站首页</a></li>&ndash;%&gt;
            </ul>
        </div>--%>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <%--<li><a href="#">管理员</a></li>--%>
                <%--<li><a href="user?actionName=userUpdatePassword">修改密码</a></li>--%>
                <li><a href="user?actionName=logout">退出</a></li>
            </ul>
        </div>
    </div>
</div>




<div class="container clearfix" style="margin-left: 25px;margin-top: 10px;margin-bottom: 10px;margin-right: 10px;"  >

    <!--左侧菜单栏-->

    <div class="sidebar-wrap" style="margin-left: 10px;margin-top: 10px;margin-right: 10px;margin-top: 10px;">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                       <%-- <li><a href="salary?actionName=list"><i class="icon-font">&#xe008;</i>工资单查询1</a></li>--%>
                        <li><a href="salary?actionName=listd"><i class="icon-font">&#xe008;</i>工资单查询2</a></li>


                    <c:if test="${sessionScope.user.QX=='0'}">
                        <li><a href="type?actionName=list"><i class="icon-font">&#xe005;</i>种类更新</a></li>
                    </c:if>

                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>系统管理</a>
                    <ul class="sub-menu">
                        <li><a href="#"><i class="icon-font">&#xe037;</i>清理缓存</a></li>
                    </ul>
                </li>
            </ul>
        </div>
</div>
    <!--/sidebar-->




    <div class="main-wrap" style="margin-left: 10px;margin-top: 10px;margin-right: 10px;margin-top: 10px; width: 1000px">









        <%--如果获取到后台设置的只，则显示；如果未获取，设置默认--%>
        <c:if test="${!empty changePage}">
            <jsp:include page="${changePage}"></jsp:include>
        </c:if>


            <c:if test="${empty changePage}">
                <%--   <jsp:include page="user/updatePassword.jsp"></jsp:include>--%>
                <h2>无资源</h2>
            </c:if>

    </div>
    <!--/main-->
</div>
</body>
</html>