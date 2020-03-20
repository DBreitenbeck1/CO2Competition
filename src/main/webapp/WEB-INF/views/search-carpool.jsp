<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Plan Carpool || Green on the Go</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<%@ include file="partials/header.jsp"%>
</head>
<body>
<main class="container">
<form action ="/submit-carpool">
<section class="jumbotron">
<table class="table table-striped">
<thead>

<h4>To: ${employee.company.name}</h4>
<h5>${employee.company.streetAddress}</h5>

  <tr>
    <th>Name</th>
    <th>City</th>
    <th>Street</th>
    <th>Zip Code</th>
    <th>Distance from Work</th>
    <th>Distance from You</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="m" items="${employees}" varStatus="mloop">
  
  <tr>
    <td><input type="radio" name="carpool" value="${m.username}"/>${m.name}</td>
    <td>${m.city}</td>
    <td>${m.streetAddress}</td>
    <td>${m.zipCode}</td>
    <td>${distanceC [mloop.index].text}</td>
    <td>${distanceY [mloop.index].text}</td>
  </tr>
  </c:forEach>
  </tbody>
  
</table>
<input type="hidden" name="date" value="${date}"/>
<input type="hidden" name="time" value="${time}"/>
<input type="hidden" name="id" value="${employee.employeeId}" />
<button class="btn btn-primary" type="submit">Submit your request</button>




</section>
</form>
</main>
</body>
</html>