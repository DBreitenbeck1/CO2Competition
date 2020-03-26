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

			<c:if test="${employee eq null}">
				<div class="btn-div">
					<a href="/login"><button id="btn-login" class="btn btn-primary"
							type="submit">Login</button></a>
				</div>
			</c:if>
		</div>
		<div class="score-div">
			<h3 class="h3-t">Our Top Clients</h3>
			<!-- <table class="table-t">
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
			</table>-->


		</div>
	</section>
<div class="container-chart">
	<canvas id="myChart"></canvas>
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
//var frameworks = ['React', 'Angular', 'Vue', 'Hyperapp', 'Omi'];
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: frameworks,
        datasets: [{
            label: 'Green on the Go',
            backgroundColor: "rgba(86, 199, 15, 1)",
            borderColor: "rgba(255,99,132,1)",
            borderWidth: 2,
            hoverBackgroundColor: "rgba(255,99,132,0.4)",
            hoverBorderColor: "rgba(255,99,132,1)",
            data: stars
        }]
    }
});</script>


</body>
</html>