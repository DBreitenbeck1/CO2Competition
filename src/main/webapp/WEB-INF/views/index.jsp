<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>CO2 Competition</title>

</head>
<body>
	<%@ include file="partials/header.jsp"%>
	<c:if test="${message ne null}">
		<div class="alert alert-warning">
			<h4 class="alert-heading">Warning!</h4>
			<p class="mb-0">${message}</p>
		</div>

	</c:if>
	<h1>${distance.text}</h1>
	<h2>${distance.value}</h2>
	<h3>${start}</h3>
	<h3>${midway}</h3>
	<h3>${dest}</h3>
	<h3>${co2savings}</h3>

	<div>
	<a href="/register" class="btn btn-primary" type="submit">Register Here</a>
	</div>
</body>
</html>