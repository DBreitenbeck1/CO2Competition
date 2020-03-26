<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Home || Green on the Go</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<%@ include file="../partials/header.jsp"%>
</head>
<body>
	<main class="container">
			<c:if test="${message ne null}">
			<div class="alert alert-${messageType}">
				<strong>${message}</strong>
			</div>
		</c:if>
		<section class="jumbotron">

			<h1>Welcome ${employee.name}!</h1>
			<p>You are currently working at ${employee.company.name}.</p>
			<p>Your current score is ${score } points!</p>

<a href="/routes" class="btn btn-success" type="submit"
									>Find a Carpool!
										</a>


<div class="p-routes">
		
	</div>

	<div class="p-routes-t">
	<c:choose>
	
	<c:when test ="${empty carpools }">
	<h2>${noCP }</h2>
	
	</c:when>
	<c:when test="${not empty carpools }">
	
<h2>Your Past Carpools:</h2>
		<div class="container-fluid">
			<table class="table">
				<tr>	
					<th>Date</th>
					<th>Passengers</th>
					<th>CO2 Saved</th>
					<th>User Score</th>
					<th></th>
				</tr>
				<c:forEach var="cp" items="${carpools}" varStatus="sl">
					<c:choose>
						<c:when test="${cp.date>today }">
							<tr bgcolor=lightyellow>
								<td>${cp.date}</td>
								<td>
									<ul>
										<c:forEach var="pass" items="${cp.employees}">
											<li>${pass.name}</li>
										</c:forEach>
									</ul>
								</td>
								<td>${cp.co2}</td>
								<td>${userScore[sl.index] }</td>
								
								<td><a href="/cancel/${cp.carpoolId}"
									class="btn btn-warning" type="submit"
									onclick="return confirm('Are you sure you want to cancel this carpool?')">Cancel
										carpool</a></td>

							</tr>
						</c:when>

						<c:when test="${cp.date<today }">
							<tr bgcolor=lightgreen>
								<td>${cp.date}</td>
								<td>
									<ul>
										<c:forEach var="pass" items="${cp.employees}" varStatus="scoreLoop">
											<li>${pass.name}</li>
										</c:forEach>
									</ul>
								</td>
								<td>${cp.co2}</td>
								<td>${userScore[sl.index] }</td>
								<th></th>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
			</table>
		</div>
	</div>
		</c:when>
	</c:choose>
	

		</section>
	</main>
</body>
</html>