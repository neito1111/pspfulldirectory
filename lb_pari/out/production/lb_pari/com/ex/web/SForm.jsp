<%--
  Created by IntelliJ IDEA.
  User: Tigran
  Date: 04.12.2023
  Time: 4:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<body>
<h3>${title}</h3>

<div>
  <form action="editW" method="post">

    <input type="text" value='${name}' name="name" required>

    <select name="selectedType" required>
      <option disabled="true" >Выберите предмет</option>
      <c:forEach var="name" items="${name}">
        <option value=${name}><c:out value="${name}" /></option>
      </c:forEach>
    </select>

    <input name="kol" type="number" step="1" min="0" value='${kol}' required>
    <input name="stud" type="number" step="1" min="0" value='${stud}' required>

    <input type="submit">
  </form>

  <a href="/example">назад</a>
</div>


</body>
</html>