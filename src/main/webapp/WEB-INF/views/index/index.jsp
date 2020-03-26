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

</head>
<body>
<c:if test="${message ne null}">
	<div class="alert alert-${messageType}">
		<strong>${message}</strong>
	</div>
</c:if>

<div class="container p-5">
<div class="row">
	<div class="col">
		<h2>Make your ride to work</h2>
		<h2>mean more.</h2>
		<!-- Fix this make it pretty :D -->
		<c:if test="${employee eq null}">
				<a href="/login"><button id="btn-login" class="btn btn-success"
						type="submit">Login</button></a>
				<a href="/register"><button id="btn-login"
						class="btn btn-success" type="submit">Create account</button></a>

		</c:if>
		
		</div></div><div class="row"><div class="col">
		<hr class="my-4">
			<h3>Our Top Clients</h3>
	<div class="container-chart">
		<canvas id="myChart"></canvas>
	</div>
	</div>
	<div class="col m-2">
		<img class="index-img" src="Carpool App Image.png"/>
	</div>
	</div>
</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.js
"></script>
	<script>var ctx = document.getElementById('myChart');
		var stars = [
		<c:forEach var="c" items="${cc}">
			${c.value}, 
			</c:forEach>
		];
		var frameworks = [
			<c:forEach var="o" items="${cc}">
			"${o.key}", 
		</c:forEach>
		];
		var myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		        labels: frameworks,
		        datasets: [{
		            label: 'Green on the Go',
		            backgroundColor: "rgba(2, 184, 117, 1)",
		            borderColor: "rgba(0, 0, 0, 1)",
		            borderWidth: 2,
		            hoverBackgroundColor: "rgba(43, 202, 106, 0.69)",
		            hoverBorderColor: "rgba(0, 0, 0, 1)",
		            data: stars
		        }]
		    }
		});</script>
</body>
</html>