<%--
  Created by IntelliJ IDEA.
  User: Bruce
  Date: 28.06.2022
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>CostCalculator</title>
</head>
<body>
<h1><%= "Kostenrechner" %></h1>
<form action="./BaseServlet" method="post">
    <input type="String" name="licencePlate">
    <input type="submit">
</form>
<a href="index.jsp">zurück zum Parkhaus</a>
</body>
</html>
