<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="partials/header.jsp"%>

<meta charset="ISO-8859-1">

<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/cerulean/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-LV/SIoc08vbV9CCeAwiz7RJZMI5YntsH8rGov0Y2nysmepqMWVvJqds6y0RaxIXT"
	crossorigin="anonymous">
<link href='https://fonts.googleapis.com/css?family=Merienda'
	rel='stylesheet'>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>CO2 Competition</title>
<style>
.carpool-div {
	margin-top: 150px;
	margin-left: 100px;
	font-weight: bold;
}

<
title>CO2 Competition </title> <style>.carpool-div {
	margin-top: 150px;
	margin-left: 100px;
	font-weight: bold;
}

h1 {
	font-family: 'Merienda';
	font-size: 22px;
	font-weight: bold;
	font-size: 50px
}

body {
	font-family: 'Merienda';
	font-size: 22px;
	font-weight: bold;
}

img {
	border: 0px solid #ddd;
	border-radius: 0px;
	padding: 5px;
	width: 548px;
	margin-left: 970px;
	margin-top: -310px;
	opacity: 0.8;
}

.btn-div {
	margin-left: 343px;
	margin-top: 20px;
}

button {
	width: 22%;
}
</style>
</head>
<body>
	<c:if test="${message ne null}">
		<div class="alert alert-${messageType}">
			<strong>${message}</strong>
		</div>
	</c:if>
	<section class="carpool">
		<div class="carpool-div">
			<h1>Make your ride to work</h1>
			<h1>mean more.</h1>
			
			<c:if test="${employee eq null}">
			<div class="btn-div">
				<a href="/login"><button class="btn btn-primary" type="submit">Login</button></a>
			</div>
			</c:if>
		</div>

		<div class="img-div">
			<img src="Carpool App Image.png" />
		</div>
	</section>

</body>
</html>