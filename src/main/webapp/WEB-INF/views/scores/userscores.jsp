<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<title>Scoreboard || Green on the Go</title>


</head>
<body>
	<div class="user-score">

		<h1 class="Jumbotron">User Scores</h1>
	</div>
	<div class="weekly-table">
		<div class="container">
			<div class="row">
				<div class="col-4">
					<h3 class="text-center">All Time</h3>
					<section class="lead">
						<table class="text-muted table">
							<thead>
								<tr>
									<th scope="col">User</th>
									<th scope="col">Score</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="emp" items="${empscoresAT}" end ="9">
									<tr scope="row">
										<td>${emp.name}</td>
										<td><fmt:formatNumber type="number" value="${emp.score}" /></td>
									</tr>
								</c:forEach>
								<tr scope="row">
									<th scope="row">Company Total:</th>
									<th scope="row"><fmt:formatNumber type = "number" value = "${totalAT}" /></th>
								</tr>
							</tbody>

						</table>

					</section>
				</div>

				<div class="col-4">
					<h3 class="text-center">Monthly</h3>

					<table class="text-muted table">
						<thead>
							<tr>
								<th scope="col">User</th>
								<th scope="col">Score</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="emp" items="${empscoresMN}" end ="9">
								<tr scope="row">
									<td>${emp.key}</td>
									<td><fmt:formatNumber type="number" value="${emp.value}" /></td>
								</tr>
							</c:forEach>
							<tr scope="row">
								<th scope="row">Company Total:</th>
								<th scope="row"><fmt:formatNumber type = "number" value = "${totalMN}" /></th>
							</tr>
						</tbody>

					</table>

				</div>
				<div class="col-4">
					<h3 class="text-center">Weekly</h3>
					<table class="text-muted table">
						<thead>
							<tr scope="row">
								<th scope="col">User</th>
								<th scope="col">Score</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="emp" items="${empscoresWK}" end ="9">
								<tr scope="row">
									<td>${emp.key}</td>
									<td><fmt:formatNumber type="number" value="${emp.value}" /></td>
								</tr>
							</c:forEach>
							<tr scope="row">
								<th scope="row">Company Total:</th>
								<th scope="row"><fmt:formatNumber type = "number" value = "${totalWK}" /></th>
							</tr>
						</tbody>

					</table>



				</div>
			</div>

		</div>

	</div>
</body>
</html>