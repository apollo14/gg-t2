<%--
  Created by IntelliJ IDEA.
  User: Q1O1
  Date: 07-12-2016
  Time: 14:57
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <g:render template="../layouts/head"/>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-9">
            <g:render template="filter"/>
        </div>
        <div class="col-sm-1">
            <g:link controller="register" action="addRegister">
                <input type="button" value="Add register" class="btn" style="width: 100%"/>
            </g:link>
        </div>
        <div class="col-sm-1">
            <g:link controller="register" action="loadData">
                <input type="button" value="Load" class="btn" style="width: 100%"/>
            </g:link>
        </div>
        <div class="col-sm-1">
            <g:link controller="register" action="saveData">
                <input type="button" value="Save" class="btn" style="width: 100%"/>
            </g:link>
        </div>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#gpw">GPW</a></li>
        <li><a data-toggle="tab" href="#tfi">TFI</a></li>
    </ul>
    <div class="tab-content">
        <div id="gpw" class="tab-pane fade in active">
            <g:render template="total"/>
            <table class="table">
                <tbody>
                <g:each in="${registers}" var="register">
                    <tr>
                        <td><g:link controller="register" action="show" id="${register.value?.id}">${register.value?.id}</g:link></td>
                        <td><g:render template="registerDetails" bean="${register}"/></td>
                        <td><g:link controller="register" action="operationsList" id="${register.value?.id}">O</g:link> </td>
                        <td><g:link controller="register" action="pricesList" id="${register.value?.id}">P</g:link> </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
        <div id="tfi" class="tab-pane fade">
            <h3>TFI ready</h3>
        </div>
    </div>
</div>
</body>
</html>