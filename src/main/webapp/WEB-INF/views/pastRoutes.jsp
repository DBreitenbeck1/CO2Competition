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
<%@ include file="partials/header.jsp"%>
</head>
<body>

	<div>

		<h2>Carpools You Have Used:</h2>
		<table class="table">
			<tr>
				<th>Id</th>
				<th>Date</th>
				<th>Passengers</th>
				<th>CO2 Saved</th>
			</tr>
			<c:forEach var="cp" items="${carpools}">
				<tr>
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
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>