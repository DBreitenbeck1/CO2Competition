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
<c:if test="${message ne null}">
<div class="alert alert-warning">
  <h4 class="alert-heading">Warning!</h4>
  <p class="mb-0">${message}</p>
</div>
</c:if>
<h4>Please enter the starting address:</h4>

<form method="post" action="/tripdetails">
<label for="street">Street Address:<input class="form-control" name="street" required></label>
<label for="city">City:<input class="form-control" name="city" required></label>
<label for="zip">Zip Code:<input class="form-control" name="zip" required></label>
<button class="btn btn-primary" action="submit">Click here to submit address</button>
</form>
</body>
</html>