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

<h4>${cName } employees</h4>
</section>
<section class="jumbotron">
<table class="table table-striped">
<thead>
  <tr>
    <th>Employee</th>
    <th>City</th>
    <th>Street</th>
    <th>Zip code</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="d" items="${info }">
  
  <tr>
    <td>${d.name }</td>
    <td>${d.city }</td>
    <td>${d.streetAddress }</td>
    <td>${d.zipCode }</td>
  </tr>
  </c:forEach>
  </tbody>
</table>



</section>


</main>



</body>
</html>