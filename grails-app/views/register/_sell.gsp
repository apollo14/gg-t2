<% if (it != null) { %>
    <td>${it.sell?.dateCreated.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"))}</td>
    <td>${it.sell?.price}</td>
    <td>${it.sell?.volume}</td>
<%} else {%>
    <td></td>
    <td></td>
    <td></td>
<%}%>