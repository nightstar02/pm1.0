<%--
  Created by IntelliJ IDEA.
  User: 伊卌
  Date: 2021-11-26
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../static/js/type.js"></script>
<script src="../static/js/util.js"></script>
<!-- 1. 导入CSS的全局样式 -->
<link href="../static/css/bootstrap.min.css" rel="stylesheet">
<!-- 2. jQuery导入，建议使用1.9以上的版本 -->
<script src="../static/js/jquery-2.1.0.min.js"></script>
<!-- 3. 导入bootstrap的js文件 -->
<script src="../static/js/bootstrap.min.js"></script>

<script type="text/javascript">
</script>


<div style="width: 100%;margin-left: 200px;padding: inherit">

    <div class="crumb-wrap" >
        <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">作品管理</span></div>
    </div>

    <%--搜索栏--%>
    <div class="search-wrap">
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
    </div>


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
                <div class="list-page"> 2 条 1/1 页</div>
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
