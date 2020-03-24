<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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