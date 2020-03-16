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
<form method="post" action="/tripdetails/${emId }">

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
<!-- <h4>Please enter the destination address:</h4>
<label for="street1">Street Address:<input class="form-control" name="street1" required></label>
<label for="city1">City:<input class="form-control" name="city1" required></label>
<label for="zip1">Zip Code:<input class="form-control" name="zip1" required></label> -->
<section>
<h5>Choose your company</h5>
<c:forEach var="c" items="${company }">

<label><input type="radio" name="co" value="${c.name }"/>${c.name }</label>

</c:forEach>
</section>
</section>

<button class="btn btn-primary" type="submit">Click here to submit address</button>
</form>
</main>
</body>
</html>