<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Past Routes || Green on the Go</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<%@ include file="../partials/header.jsp"%>
</head>
<body>

	<div class="p-routes">

		<h2>Carpools You Have Used:</h2>
	</div>
	<div class="p-routes-t">

		<div class="container-fluid">
			<table class="table">
				<tr>
					<th>Id</th>
					<th>Date</th>
					<th>Passengers</th>
					<th>CO2 Saved</th>
					<th></th>
				</tr>
				<c:forEach var="cp" items="${carpools}">
					<c:choose>
						<c:when test="${cp.date>today }">
							<tr bgcolor=lightyellow>
								<td>${cp.carpoolId}</td>
								<td>${cp.date}</td>
								<td>
									<ul>
										<c:forEach var="pass" items="${cp.employees}">
											<li>${pass.name}</li>
										</c:forEach>
									</ul>
								</td>
								<th>${cp.co2}</th>
								<td><a href="/cancel/${cp.carpoolId}"
									class="btn btn-warning" type="submit"
									onclick="return confirm('Are you sure you want to cancel this carpool?')">Cancel
										carpool</a></td>

							</tr>
						</c:when>

						<c:when test="${cp.date<today }">
							<tr bgcolor=lightgreen>
								<td>${cp.carpoolId}</td>
								<td>${cp.date}</td>
								<td>
									<ul>
										<c:forEach var="pass" items="${cp.employees}">
											<li>${pass.name}</li>
										</c:forEach>
									</ul>
								</td>
								<th>${cp.co2}</th>
								<th></th>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>