<%--
  Created by IntelliJ IDEA.
  User: Q1O1
  Date: 23-02-2017
  Time: 15:12
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <g:render template="../layouts/head"/>
</head>

<body>
<div class="container-fluid">
    <h3>Add register:</h3>
    <g:form name="addOperationForm" controller="register" params="[id: id]" action="saveRegister" >
        <div class="form-group row">
            <label class="col-sm-2" for="id">Volume:</label>
            <g:textField class="col-sm-10" name="id" value=""/>
        </div>
        <div class="row">
            <g:submitButton name="Save" class="btn btn-default"/>
        </div>
    </g:form>
</div>
</body>
</html>