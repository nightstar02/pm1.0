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

<%--<div class="crumb-wrap" >
    <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">作品管理</span></div>
</div>--%>

<%--搜索栏--%>
  <%--  <div class="search-wrap">
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
       <td><input style="width: 100px;margin-right: 20px;float: right;background-color: #9acfea"  onclick="openAdd()" class="btn btn-primary btn2" name="sub" value="新增类型" type="button"></td>


    <%--<li><a data-toggle="modal" data-target="#myModal" id="addBtn" type="button"><i class="icon-font">&#xe005;</i>新增种类</a></li>--%>

            </div>
        </div>

        <c:if test="${empty typeList}"><h2>暂未查询到数据</h2></c:if>

        <c:if test="${!empty typeList}">
        <div id="myDiv" class="result-content">
            <table id="myTable" class="result-tab"  style="overflow:visible;text-align: center;min-width: 500px;width: 100%">
                <tr>
                    <th style="text-align: center">编号</th>
                    <th style="text-align: center">类型</th>
                    <th style="text-align: center">更新时间</th>
                    <th style="text-align: center">备注</th>
                    <th style="text-align: center">更新人</th>
                    <th style="text-align: center">类型</th>
                    <th style="text-align: center">操作</th>
                </tr>

                <c:forEach items="${typeList}" var="item">

                    <tr id="tr_${item.CID}">

                        <td>${item.CID}</td>
                        <td>${item.CNAME}</td>
                        <td>${item.UPTIME}</td>
                        <td>${item.MESSAGE}</td>
                        <td>${item.UPTOR}</td>
                        <td>
                            <c:if test="${item.typeType=='1'}">实发</c:if>
                            <c:if test="${item.typeType=='0'}">应发</c:if>
                        </td>

                        <td>
                            <button class="btn btn-primary btn2" type="button" value="修改"  onclick="openUpdateDialog(${item.CID})">修改</button>
                            <button class="btn btn-primary btn2" type="button" onclick="deleteType(${item.CID})" value="删除">删除 </button>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            </c:if>
           <%-- <div class="list-page"> 2 条 1/1 页</div>--%>
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
                       <label for="" style="float: left">类型名字</label>
                       <input type="hidden" name="CID" id="CID">
                       <input type="hidden" name="user" id="user" value="${sessionScope.user.EMPEE_ACCT}">
                       <input type="text" name="CNAME"  class="form-control" id="CNAME" placeholder="请输入添加类型">
                       <label for="" style="float: left">备注</label>
                       <input type="text" name="MESSAGE" class="form-control" id="MESSAGE" placeholder="请输入备注">
                       <label for="" style="float: left">类型</label>
                      <%-- <input type="text" name="typeType" class="form-control" id="typeType" placeholder="请输入类型">--%>
                       <select id="typeType" name="typeType" class="form-control">
                           <option value="">--请选择类型--</option>
                           <option value="0">应发</option>
                           <option value="1">实发</option>
                       </select>
                   </div>
                </div>
                <div class="modal-footer">
                    <span id="msg" style="font-size: 12px; color: red"></span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="UpdateBtn_submit" onclick="UpdateOrAdd()" class="btn btn-primary">保存</button>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
