<%--
  Created by IntelliJ IDEA.
  User: apollo14@o2.pl
  Date: 07-12-2016
  Time: 15:40
--%>

<%@ page import="model.EOperationType" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <g:render template="../layouts/head"/>
</head>

<body>
    <div class="container-fluid">
        <g:link controller="register" action="index">index</g:link>
        <div class="row">
            <div class="col-sm-10"><h3>${register.value.id}</h3></div>
            <div class="col-sm-1">
                <g:link controller="register" action="addOperation" params="[id: register.value.id, operationType: EOperationType.BUY.name()]">
                    <input type="button" value="Buy" class="btn"/>
                </g:link>
            </div>
            <g:if test="${register.value.volume > 0}">
                <div class="col-sm-1">
                    <g:link controller="register" action="addOperation" params="[id: register.value.id, operationType: EOperationType.SELL.name()]">
                        <input type="button" value="Sell" class="btn"/>
                    </g:link>
                </div>
            </g:if>
        </div>
        <g:render template="registerDetails" bean="${register}"/>
        <g:render template="operations"/>
    </div>
</body>
</html>