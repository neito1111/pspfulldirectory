<%--
  Created by IntelliJ IDEA.
  User: Tigran
  Date: 04.12.2023
  Time: 4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Teacher</title>
</head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<body>

<div class="wrapper">
  <form action="name" method="post">

    <select name="selectedType">
      <option disabled="true" selected="true">Выберите тип часов</option>
      <c:forEach var="type" items="${types}">
        <option value=${type}><c:out value="${type}" /></option>
      </c:forEach>
    </select>
    <br>

    <select name="selectedCountry">
      <option disabled="true" selected="true">Выберите предмет</option>
      <c:forEach var="subject" items="${subject}">
        <option value=${subject}><c:out value="${subject}" /></option>
      </c:forEach>
    </select>
    <br>
    <input type="submit" value="Submit">
  </form>

  <table>
    <tr>
      <th>id</th>
      <th>name</th>
      <th>country</th>

      <td>
      </td>

    </tr>

    <c:forEach var="Teachers" items="${teachers}">

      <tr>
        <td><c:out value="${Teachers.getId()}" /></td>
        <td><c:out value="${Teachers.getName()}" /></td>
        <td><c:out value="${Teachers.getweeklySubjects()}" /></td>
        <td><c:out value="${Teachers.getweeklyClasses()}" /></td>
        <td><c:out value="${Teachers.getStudentsPerClass()}" /></td>

        <td>
          <a href='/editT?id=${Teachers.getId()}'>&#x270E;</a>
          <a  href='/deleteT?id=${Teachers.getId()}'>&#x2718;</a>
        </td>

      </tr>
    </c:forEach>
  </table>

  <a href="/example">расписание</a>
  <a href="/editM">добавить</a>

</div>


</body>
</html>