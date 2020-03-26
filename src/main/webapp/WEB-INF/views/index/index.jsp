<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<%@ include file="../partials/header.jsp"%>
<title>CO2 Competition</title>
<style>
<
title>CO2 Competition </title> <style>.carpool-div {
	margin-top: 150px;
	margin-left: 100px;
	font-weight: bold;
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

			<!-- Fix this make it pretty :D -->

			<c:if test="${employee eq null}">
				<div class="btn-div">
					<a href="/login"><button id="btn-login" class="btn btn-primary"
							type="submit">Login</button></a>
				</div>
				<div class="btn-div">
					<a href="/register"><button id="btn-login"
							class="btn btn-primary" type="submit">Create account</button></a>
				</div>
			</c:if>
		</div>
		<div class="score-div">
			<h3>Our Top Clients</h3>
			<table class="table-t">
				<thead>
					<tr class="table-primary">
						<th>Company</th>
						<th>Score</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${cc}">

						<tr class="table-primary">
							<td>${c.key}</td>
							<td>${c.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<img class="index-img" src="Carpool App Image.png" />

		</div>
	</section>

</body>
</html>