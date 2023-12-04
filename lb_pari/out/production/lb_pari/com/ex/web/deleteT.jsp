<%--
  Created by IntelliJ IDEA.
  User: Tigran
  Date: 04.12.2023
  Time: 4:04
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
  <form action="deleteM" method="post">
    <h3>Удалить?</h3>
    <input type="submit" value="Submit">
  </form>



  <table>
    <tr>
      <th>id</th>
      <th>name</th>
      <th>subjects</th>
      <th>weeklyClasses</th>
      <th>studentsPerClass</th>

      <td>
      </td>

    </tr>

    <tr>
      <td name="id"><c:out value="${id}" /></td>
      <td name="name"><c:out value="${name}" /></td>
      <td name="subjects"><c:out value="${subjects}" /></td>
      <td name="weeklyClasses"><c:out value="${weeklyClasses}" /></td>
      <td name="studentsPerClass"><c:out value="${studentsPerClass}" /></td>

      <td>

      </td>

    </tr>
  </table>
  <a href="/brand">назад</a>

</div>


</body>
</html>
