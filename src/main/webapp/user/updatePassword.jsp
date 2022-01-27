<%--
  Created by IntelliJ IDEA.
  User: 伊卌
  Date: 2021-11-27
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="../static/js/jquery-2.1.0.min.js" type="text/javascript"></script>

<div class="crumb-wrap">
    <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">作品管理</a><span class="crumb-step">&gt;</span><span>新增作品</span></div>
</div>
<div class="result-wrap">
    <div class="result-content">
        <form action="/jscss/admin/design/add" method="post" id="myform" name="myform" enctype="multipart/form-data">
            <table class="insert-tab" width="100%">
                <tbody><tr>


                </tr>
                <tr>
                    <th><i class="require-red">*</i>工号：</th>
                    <td>
                            <input class="common-text required" id="userName" name="userName" size="50" value="${user.EMPEE_ACCT}" type="text">
                    </td>
                </tr>
                <tr>
                    <th><i class="require-red">*</i>名字：</th>
                    <td><input class="common-text" name="ename" id="ename" size="50" value="${user.EMPEE_NAME}" type="text"></td>
                </tr>
                <tr>
                    <th><i class="require-red">*</i>密码：</th>
                    <td><input class="common-text" name="pwd" id="pwd" size="50" placeholder="请输入密码" type="text"></td>

                    <td>    <span id="msg1" style="color: red; font-size: 12px">${resultInfo.msg}</span></td>
                </tr>
                <tr>
                    <th><i class="require-red">*</i>确认新密码：</th>
                    <td><input class="common-text" name="newPwd" id="newPwd"  size="50" placeholder="请输入新密码" type="text"></td>
                    <td>    <span id="msg2" style="color: red; font-size: 12px">${resultInfo.msg}</span></td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input class="btn btn-primary btn6 mr10" value="提交" type="button" onclick="checkUpdate()"  />
                        <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
                    <td>    <span id="msg3" style="color: red; font-size: 12px">${resultInfo.msg}</span></td>

                    </td>
                </tr>
                </tbody></table>
        </form>
    </div>
</div>