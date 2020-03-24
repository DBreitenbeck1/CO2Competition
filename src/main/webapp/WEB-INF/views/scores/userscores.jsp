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
	<h1 class="Jumbotron">User Scores:</h1>

	<section class="lead">
		<table class="text-muted">
			<thead>
				<tr>
					<th>User</th>
					<th>Score</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="emp" items="${empscores}">
					<tr class="table-primary">
						<td>${emp.name}</td>
						<td>${emp.score}</td>
					</tr>
				</c:forEach>
				<tr class="table-primary">
					<td><b>Company Total:</b></td>
					<td><b>${total}</b></td>
				</tr>
			</tbody>

		</table>

	</section>
	<!-- if we want to make a progress bar to a goal stretch 
	<div class="progress">
  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
</div>
-->

</body>
</html>