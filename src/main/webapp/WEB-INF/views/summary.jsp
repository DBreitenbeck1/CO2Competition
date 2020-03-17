<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />

</head>
<body>
<main class="container">
<section class="jumbotron">
<p>Carpool: ${cp.carpoolId } From: ${company } Has saved: ${cp.co2 } lbs</p>
<table class="table table-striped">
<thead>
  <tr>
    <th>Date</th>
    <th>CO2 Saved</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="pass" items="${cp.employees }">
  <tr>
    <td>${pass.name }</td>
    <td></td>
  </tr>
  </c:forEach>
  </tbody>
</table>
</section>
</main>



</body>
</html>