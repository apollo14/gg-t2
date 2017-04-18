<%--
  Created by IntelliJ IDEA.
  User: apollo14@o2.pl
  Date: 27-02-2017
  Time: 11:31
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <g:render template="../layouts/head"/>
</head>

<body>
    <div class="container-fluid">
        <g:link controller="register" action="index">index</g:link>
        <h3>Operations:</h3>
        <table class="table">
            <thead>
            <th>Date created</th>
            <th>Operation type</th>
            <th>Volume</th>
            <th>Price</th>
            <th>Broker</th>
            <th>Commission</th>
            </thead>
            <tbody>
            <g:each in="${operations}" var="operation">
                <tr>
                    <td>${operation.dateCreated.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"))}</td>
                    <td>${operation.type}</td>
                    <td>${operation.volume}</td>
                    <td>${operation.price}</td>
                    <td>${operation.broker}</td>
                    <td>${operation.commission}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</body>
</html>