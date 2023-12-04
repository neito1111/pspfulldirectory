<%--
  Created by IntelliJ IDEA.
  User: Tigran
  Date: 04.12.2023
  Time: 4:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Subject</title>
</head>

<link rel="stylesheet" type="text/css" href="css/style.css"/>
<body>
<div class="wrapper">
  <form action="example" method="post">


    <label for="date">Дата</label>
    <input type="date"  id="date" name="date">
    <br>

    <input type="submit" value="Submit">
  </form>

  <table>
    <tr>
      <th>id</th>
      <th>Название предмета</th>
      <th>время проведения </th>
      <th>аудитория</th>
      <th></th>
    </tr>
    <c:forEach var="subj" items="${subject}">
      <tr>
        <td><c:out value="${subj.getId()}" /></td>
        <td><c:out value="${subj.getName()()}" /></td>
        <td><c:out value="${subj.getDayOfWeek()}" /></td>
        <td><c:out value="${subj.getClassrooms()}" /></td>
        <td><c:out value="${subj.getTeacher().getName()}, ${subj.getTeacher().getSubjects()}" /></td>
        <td>
          <a href='/editS?id=${subj.getId()}'>&#x270E;</a>
          <a href='/deleteS?id=${subj.getId()}'>&#x2718;</a>
        </td>
      </tr>
    </c:forEach>
  </table>

  <a href="/brand">Преподаватели</a>
  <a href="/editW">добавить</a>
</div>

</body>
</html>
