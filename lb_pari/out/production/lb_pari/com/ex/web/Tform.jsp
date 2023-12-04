<%--
  Created by IntelliJ IDEA.
  User: Tigran
  Date: 04.12.2023
  Time: 4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<body>

<h3>${title}</h3>

<div>
  <form action="editT" method="post">
    <input type="text" name="name" value='${name}' required>
    <input type="text" name="subject" value='${subject}' required>


    <input type="submit">
  </form>
</div>
<a href="/Teachers">Преподаватели</a>
</body>
</html>
