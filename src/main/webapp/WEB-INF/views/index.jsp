<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/cerulean/bootstrap.min.css" rel="stylesheet" integrity="sha384-LV/SIoc08vbV9CCeAwiz7RJZMI5YntsH8rGov0Y2nysmepqMWVvJqds6y0RaxIXT" crossorigin="anonymous">
<link href='https://fonts.googleapis.com/css?family=Merienda' rel='stylesheet'>

<title>CO2 Competition</title>
<style>

.carpool-div{
margin-top: 150px;
    margin-left: 80px;
    font-weight:bold;
}

h1{
font-weight:bold;
font-size:50px
}
body {
 font-family: 'Merienda';font-size: 45px;
 font-weight:bold;
}
</style>
</head>
<body>
	<%@ include file="partials/header.jsp"%>
	<c:if test="${message ne null}">
		<div class="alert alert-warning">
			<h4 class="alert-heading">Warning!</h4>
			<p class="mb-0">${message}</p>
		</div>

	</c:if>
	<!-- <h1>${distance.text}</h1>
	<h2>${distance.value}</h2>
	<h3>${start}</h3>
	<h3>${midway}</h3>
	<h3>${dest}</h3>
	<h3>${co2savings}</h3> -->
	<section class="carpool">
	<div class="carpool-div">
	 <h1>Make your ride to work</h1>
	 <h1>mean more.</h1>
	 </div>
</section>

</body>
</html>