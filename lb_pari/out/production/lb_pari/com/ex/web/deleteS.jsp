<%--
  Created by IntelliJ IDEA.
  User: Tigran
  Date: 04.12.2023
  Time: 4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<body>

<div class="wrapper">
  <form action="deleteW" method="post">
    <h3>Удалить?</h3>
    <input type="submit" value="Submit">
  </form>

  <table>
    <tr>
      <th>id</th>
      <th>бренд</th>
      <th>тип</th>
      <th></th>
    </tr>

    <tr>
      <td name="id"><c:out value="${id}"/></td>
      <td name="name"><c:out value="${name}"/></td>
      <td name="dayOfWeek"><c:out value="${dayOfWeek}"/></td>
      <td name="classrooms"><c:out value="${classrooms}"/></td>

      <td>

      </td>
    </tr>

  </table>

  <a href="/example">назад</a>
</div>



</body>
</html>
