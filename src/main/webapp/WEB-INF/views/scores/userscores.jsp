<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />

<%@ include file="../partials/header.jsp"%>
<meta charset="ISO-8859-1">
<title>User Scores!</title>


</head>
<body>
	<div class="user-score">

		<h1 class="Jumbotron">User Scores:</h1>
	</div>
	<div class="weekly-table">

		<div class="container">
			<div class="row">
				<div class="col-4">
					<h2>All Time</h2>
					<section class="lead">

						<table class="text-muted">
							<thead>
								<tr>
									<th>User</th>
									<th>Score</th>
								</tr>
							</thead>

							<tbody>

								<c:forEach var="emp" items="${empscoresAT}">
									<tr class="table-primary">
										<td>${emp.name}</td>
										<td>${emp.score}</td>
									</tr>
								</c:forEach>
								<tr class="table-primary">
									<td><b>Company Total:</b></td>
									<td><b>${totalAT}</b></td>
								</tr>
							</tbody>

						</table>

					</section>
				</div>

				<div class="col-4">
					<h2>Monthly</h2>

					<table class="text-muted">
						<thead>
							<tr>
								<th>User</th>
								<th>Score</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="emp" items="${empscoresMN}">
								<tr class="table-primary">
									<td>${emp.key}</td>
									<td>${emp.value}</td>
								</tr>
							</c:forEach>
							<tr class="table-primary">
								<td><b>Company Total:</b></td>
								<td><b>${totalMN}</b></td>
							</tr>
						</tbody>

					</table>

				</div>
				<div class="col-4">
					<h2>Weekly</h2>
					<table class="text-muted">
						<thead>
							<tr>
								<th>User</th>
								<th>Score</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="emp" items="${empscoresWK}">
								<tr class="table-primary">
									<td>${emp.key}</td>
									<td>${emp.value}</td>
								</tr>
							</c:forEach>
							<tr class="table-primary">
								<td><b>Company Total:</b></td>
								<td><b>${totalWK}</b></td>
							</tr>
						</tbody>

					</table>



				</div>
			</div>

		</div>
	</div>
</body>
</html>