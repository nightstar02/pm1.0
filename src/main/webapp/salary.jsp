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
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.html">首页</a></li>
                <li><a href="#" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">管理员</a></li>
                <li><a href="user?actionName=userUpdatePassword">修改密码</a></li>
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
                        <%-- <li><a href="salary?actionName=list"><i class="icon-font">&#xe008;</i>工资单查询1</a></li>
                         <li><a href="user?actionName=list"><i class="icon-font">&#xe008;</i>工资单查询2</a></li>--%>
                        <li><a href="user?actionName=list"><i class="icon-font">&#xe008;</i>工资单查询2</a></li>



                        <%--<c:if test="${sessionScope.user.QX=='0'}">
                            <li><a href="design.html"><i class="icon-font">&#xe005;</i>工资单更新</a></li>
                            <li><a href="type?actionName=list"><i class="icon-font">&#xe005;</i>种类更新</a></li>
                        </c:if>--%>

                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>系统管理</a>
                    <ul class="sub-menu">
                        <li><a href="system.html"><i class="icon-font">&#xe037;</i>清理缓存</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->




    <div class="main-wrap" style="margin-left: 10px;margin-top: 10px;margin-right: 10px;margin-top: 10px; width: 1000px">





<%--

        <c:if test="${empty changePage}">
            &lt;%&ndash;   <jsp:include page="user/updatePassword.jsp"></jsp:include>&ndash;%&gt;
            <h2>无资源</h2>
        </c:if>


        &lt;%&ndash;如果获取到后台设置的只，则显示；如果未获取，设置默认&ndash;%&gt;
        <c:if test="${!empty changePage}">
            <jsp:include page="${changePage}"></jsp:include>
        </c:if>
--%>

    <div style="width: 100%;margin-left: 200px;padding: inherit">

        <div class="crumb-wrap" >
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">作品管理</span></div>
        </div>

        <%--搜索栏--%>
<%--        <div class="search-wrap">
            <div class="search-content">
                <form action="#" method="post">
                    <table class="search-tab">
                        <tr>
                            <th>选择时间:</th>
                            <td>
                                <select name="search-sort" id="">
                                    <option value="">全部</option>
                                    <option value="19">精品界面</option><option value="20">推荐界面</option>
                                </select>
                            </td>
                            <th>工号/姓名:</th>
                            <td><input class="common-text" placeholder="关键字" name="keywords" value="" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>--%>


        <div class="result-wrap" id="myDiv"  style="text-align: center" >
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <%--
                                        <li><a href="type?actionName=insert"><i class="icon-font">&#xe005;</i>新增种类</a></li>

                        --%>

                        <c:if test="${sessionScope.user.QX=='0'}">
                            <td><input style="width: 100px;margin-right: 20px;float: right;background-color: #9acfea"  onclick="openAddS()" class="btn btn-primary btn2" name="sub" value="新增类型" type="button"></td>
                        </c:if>
                        <%--<li><a data-toggle="modal" data-target="#myModal" id="addBtn" type="button"><i class="icon-font">&#xe005;</i>新增种类</a></li>--%>

                    </div>
                </div>

                <c:if test="${empty salaryList}"><h2>暂未查询到数据</h2></c:if>

                <c:if test="${!empty salaryList}">
                <div id="myDivS" class="result-content">
                    <table id="myTableS" class="result-tab"  style="overflow:visible;text-align: center;min-width: 500px;width: 100%">
                        <tr>

                            <th style="text-align: center">时间</th>
                            <th style="text-align: center">工号</th>
                            <th style="text-align: center">类型</th>
                            <th style="text-align: center">金额</th>
                            <c:if test="${sessionScope.user.QX=='0'}">
                                <th style="text-align: center">操作</th>
                            </c:if>

                        </tr>

                        <c:forEach items="${salaryList}" var="item">

                            <tr id="tr_${item.SID}">

                                <td hidden="hidden" >${item.SID}</td>

                                <td>${item.MONTH_ID}</td>
                                <td>${item.EMPEE_ACCT}</td>
                                <td>${item.CNAME}</td>
                                <td>${item.SALARY}</td>
                                <c:if test="${sessionScope.user.QX=='0'}">
                                    <td>
                                        <button class="btn btn-primary btn2" type="button" value="修改"  onclick="openUpdatesDialog(${item.SID})">修改</button>
                                        <button class="btn btn-primary btn2" type="button" onclick="deleteTypeS(${item.SID})" value="删除">删除${item.SID}</button>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>

                    </table>
                    </c:if>
                  <%--  <div class="list-page"> 2 条 1/1 页</div>--%>
                </div>
            </form>
            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"></span></button>
                            <h4 class="modal-title" id="myModalLabel"></h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group" style="padding-bottom: 10px">

                                <input type="hidden" name="SID" id="SID">
                                <input type="hidden" name="user" id="user" value="${sessionScope.user.EMPEE_ACCT}">
                                <input type="hidden" name="mkt_area_id" id="mkt_area_id" value="${sessionScope.user.MKT_AREA_ID}">
                                <label for="" style="float: left">时间</label>
                                <input type="text" name="MONTH_ID" value="${item.MONTH_ID}" class="form-control" id="MONTH_ID" >

                                <label for="" style="float: left">工号</label>
                                <input type="text" name="EMPEE_ACCT" value="${item.EMPEE_ACCT}" class="form-control" id="EMPEE_ACCT" >

                                <label for="" style="float: left">类型名字</label>
                                <select class="form-control" id="cSelect" name="cSelect">
                                    <option   class="form-control" selected="selected">--请选择类型--</option>
                                    <%-- <option value="1001">岗位工资</option>
                                     <option value="1017">公积金</option>--%>
                                    <c:forEach items="${typeList}" var="type">
                                        <option value="${type.CID}">${type.CNAME}</option>
                                        <%--<option value="1017">公积金</option>--%>

                                    </c:forEach>
                                </select>
                                <label for="" style="float: left">金额</label>
                                <input type="text" name="SALARY" class="form-control" id="SALARY" placeholder="请输入金额">

                            </div>
                        </div>
                        <div class="modal-footer">
                            <span id="msg" style="font-size: 12px; color: red"></span>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" id="UpdateBtn_submit" onclick="UpdateOrAddS()" class="btn btn-primary">保存</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    </div>
    <!--/main-->
</div>
</body>
</html>