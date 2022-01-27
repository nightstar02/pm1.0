<%--
  Created by IntelliJ IDEA.
  User: 伊卌
  Date: 2021-11-27
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../static/js/util.js" type="text/javascript"></script>
<script src="../static/js/config.js" type="text/javascript"></script>
<%--
<script src="../static/js/jquery-2.1.0.min.js" type="text/javascript"></script>
--%>

<script src="../static/js/type.js"></script>
<div class="main-wrap">


    <div class="result-wrap">
        <div class="result-content">
            <form action="type" method="post" id="insertTypeForm">
                <table class="insert-tab" width="100%">
                    <tbody>
                    <tr>  <input  type="hidden" name="actionName" value="insertType"></tr>
                    <tr>
                        <th><i class="require-red">*</i>类型名称：</th>
                        <td>
                            <input class="common-text required" id="typeName" name="typeName" size="50" value="" type="text">
                        </td>
                    </tr>

                    <tr>
                        <th></th>
                        <td>
                            <input type="button" tabindex="3" value="提交" class="btn btn-primary" onclick="dnmd()" />
                            <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
                        </td>
                    </tr>
                    </tbody></table>
            </form>
        </div>
    </div>

