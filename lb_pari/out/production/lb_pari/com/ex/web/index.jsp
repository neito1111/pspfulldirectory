<%--
  Created by IntelliJ IDEA.
  User: Tigran
  Date: 04.12.2023
  Time: 4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<body>

<p>filters:</p>
<form method="POST" action="TextServlet">
  <input name="data" type="date" >
</form>
<h1>Parameter Value: ${param}</h1>

<c:forEach var="product" items="${Subject}">
  <div class="product">
    <h4><c:out value="${product.getName()}" /></h4>
  </div>
</c:forEach>
</body>
</html>
