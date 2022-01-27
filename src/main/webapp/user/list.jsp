<%--
  Created by IntelliJ IDEA.
  User: 伊卌
  Date: 2021-11-26
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    div{_width:100%; }
</style>


<script src="../static/js/jquery-2.1.0.min.js"></script>
<link href="../static/css/bootstrap.min.css" rel="stylesheet">
<!-- 2. jQuery导入，建议使用1.9以上的版本 -->

<!-- 3. 导入bootstrap的js文件 -->
<script src="../static/js/bootstrap.min.js"></script>

<script type="text/javascript">
</script>
<script src="../static/js/type.js"></script>
<script src="../static/js/salary.js"></script>
<script src="../static/js/util.js"></script>
<script src="../static/js/user.js"></script>




<div style="height: auto!important;">
 <div style="width: 100%;margin-left: 200px;padding: inherit; height:auto !important;">

<%--搜索栏--%>
<div class="search-wrap">
    <div class="search-content">

        <form action="salary" method="post" id="cx">
            <input  type="hidden" name="actionName" value="listdt">
            <table class="search-tab">

                <tr>
                    <th>选择时间:</th>

                    <th>年:</th>
                    <td>
                        <select class="form-control" name="year" id="year">
                            <option   class="form-control" selected="selected"></option>
                            <c:forEach items="${sessionScope.years}" var="years">
                                <option  value="${years.year}">${years.year}年</option>
                            </c:forEach>
                        </select>

                    </td>

                    <th>月:</th>
                    <td>
                        <select class="form-control" name="months" id="months">
                            <option   class="form-control" selected="selected"></option>
                            <c:forEach items="${sessionScope.months}" var="months">
                                <option value="${months.month}">${months.month}月</option>
                            </c:forEach>
                        </select>

                    </td>
                        <td id="msg" style="color: red;font-size: 12px"></td>

               <%-- <td><a href="salary?actionName=listdt&MONTH_ID=${year1}&YEAR_ID=${month1}">ass</a></td>--%>
                   <%-- <td ><a onclick="CxDgByTime()">ss</a> </td>--%>
                    <td><input style="width: 100px;margin-right: 20px;float: right;background-color: #9acfea" onclick="CxDgByTime()"class="btn btn-primary btn2" name="sub" value="查询" type="button"></td>
                    <%--<input onclick="CxDgByTime()" type="button" value="1" >--%>

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

<div class="result-wrap" style="text-align: center" >
    <form name="myform" id="myform" method="post">


        <div class="result-content"  >
            <table class="result-tab" width="100%" style="text-align: center">
                <tr style="text-align: center">
                    <th style="text-align: center">时间</th>
                    <th style="text-align: center">工号</th>
                    <th style="text-align: center">姓名</th>
                    <th style="text-align: center">实发</th>
                    <th style="text-align: center">应发</th>
                    <th style="text-align: center">操作</th>
                </tr>
                <c:if test="${empty salaryDList}">
                    <h2>无数据</h2>
                </c:if>
                <c:if test="${!empty salaryDList}">


                    <%--  <c:forEach items="${userList}" var="items">--%>
                <c:forEach items="${salaryDList}" var="items">
                    <tr>
                        <td>${items.MONTH_ID}</td>
                        <td>${items.EMPEE_ACCT}</td>
                        <td>${items.EMPEE_NAME}</td>
                        <td>${items.RMONEY}</td>
                        <td>

                                  ${items.AMONEY}
                        </td>
                        <td>
                        <li><a href="salary?actionName=list&EMPEE_ID=${items.EMPEE_ID}&MONTH_ID=${items.MONTH_ID}"><i class="icon-font"></i>工资单查询1</a></li>
                    </tr>

                </c:forEach>



            </table>
           <%-- <div class="list-page"> 2 条 1/1 页</div>--%>
        </div>  </c:if>
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
                        <%--<input type="hidden" name="mkt_area_id" id="mkt_area_id" value="${sessionScope.user.MKT_AREA_ID}">--%>
                        <label for="" style="float: left">时间</label>
                        <input type="text" name="MONTH_ID" value="${item.MONTH_ID}" class="form-control" id="MONTH_ID" >

                        <label for="" style="float: left">工号</label>
                        <input type="text" name="EMPEE_ACCT" value="${item.EMPEE_ACCT}" class="form-control" id="EMPEE_ACCT" >

                        <label for="" style="float: left">类型名字</label>
                        <select class="form-control" id="cSelect" name="cSelect">
                            <option   class="form-control" selected="selected">--请选择类型--</option>
                            <c:forEach items="${typeList}" var="type">
                                <option value="${type.CID}">${type.CNAME}</option>
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
 </div></div>