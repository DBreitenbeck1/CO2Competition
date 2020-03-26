<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weekly Scores || Green on the Go</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel ="stylesheet" href ="/MyStyle.css"/>

<%@ include file="../partials/header.jsp"%>
</head>
<body>


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
						<td>${emp.key}</td>
						<td>${emp.value}</td>
					</tr>
				</c:forEach>
				<tr class="table-primary">
					<td><b>Company Total:</b></td>
					<td><b>${total}</b></td>
				</tr>
			</tbody>

		</table>

	</section>
</body>
</html>