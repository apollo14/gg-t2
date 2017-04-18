<%--
  Created by IntelliJ IDEA.
  User: apollo14@o2.pl
  Date: 22-02-2017
  Time: 15:22
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <g:render template="../layouts/head"/>
</head>

<body>

<div class="container-fluid">
    <h3>Add operation for: ${id}</h3>
    <g:form name="addOperationForm" controller="register" params="[id: id]" action="saveOperation" >
        <div class="form-group row">
            <label class="col-sm-2" for="date">Date:</label>
            <g:datePicker class="col-sm-10" name="date" value="${new java.util.Date()}" precision="minute"></g:datePicker>
        </div>

        <div class="form-group row">
            <label class="col-sm-2" for="volume">Volume:</label>
            <g:textField class="col-sm-10" name="volume" value="0"/>
        </div>
        <div class="form-group row">
            <label class="col-sm-2" for="price">Price:</label>
            <g:textField class="col-sm-10" name="price" value="0.0"/>
        </div>
        <div class="form-group row">
            <label class="col-sm-2" for="operationType">Operation Type:</label>
            <g:select name="operationType" from="${model.EOperationType.values()}" value="${params.operationType}"/>
        </div>
        <div class="form-group row">
            <label class="col-sm-2" for="operationType">Broker:</label>
            <g:select name="operationType" from="${model.EBroker.values()}" value="${params.broker}"/>
        </div>

        <g:submitButton name="Save" class="btn btn-default"/>
    </g:form>
</div>
</body>
</html>