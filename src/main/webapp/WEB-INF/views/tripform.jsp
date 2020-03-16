<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<main class="container">
<form method="post" action="/tripdetails">

<section class="jumbotron">
<c:if test="${message ne null}">
<div class="alert alert-warning">
  <h4 class="alert-heading">Warning!</h4>
  <p class="mb-0">${message}</p>
</div>
</c:if>
<h4>Please enter the starting address:</h4>

<label for="street">Street Address:<input class="form-control" name="street" required></label>
<label for="city">City:<input class="form-control" name="city" required></label>
<label for="zip">Zip Code:<input class="form-control" name="zip" required></label>
<h4>Please enter the destination address:</h4>
<label for="street1">Street Address:<input class="form-control" name="street1" required></label>
<label for="city1">City:<input class="form-control" name="city1" required></label>
<label for="zip1">Zip Code:<input class="form-control" name="zip1" required></label>
</section>

<button class="btn btn-primary" action="submit">Click here to submit address</button>
</form>
</main>
</body>
</html>