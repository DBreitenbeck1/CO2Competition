<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Routes || Green on the Go</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<%@ include file="../partials/header.jsp"%></head>
<body>

	<div class="route-title">
		<H2>Available Carpools</H2>
	</div>
	<div id="carpool-table">

		<div class="container">
			<div class="row">
				<div class="col">
					<h5>Employee Name</h5>
				</div>
				<div class="col">
					<h5>Starting Address</h5>
				</div>
				<div class="col">
					<h5>Distance from Office</h5>
				</div>
				<div class="col">
					<h5>Distance from You</h5>
				</div>
				<div class="col"></div>
			</div>

			<c:forEach var="emp" items="${employees}" varStatus="empLoop">
				<div class="row">
					<div class="col">${emp.name}</div>
					<div class="col">${emp.address}</div>
					<div class="col">
						<a href="#"
							onClick="window.open('https://www.google.com/maps/dir/${emp.address}/${company.address}/', '_blank', 'width=900%,height=500%')">
							${distanceC [empLoop.index].text}</a>
						</td>
					</div>

					<div class="col">
						<a href="#"
							onClick="window.open('https://www.google.com/maps/dir/${emp.address}/${employ.address}/', '_blank', 'width=900%,height=500%')">
							${distanceY[empLoop.index].text}</a>
					</div>
					<div class="col">
						<form method="post" action="/ride" class="form-inline">
							<input type="hidden" value="${emp.username}" name="username">
							<input type="hidden" value="${distanceC[empLoop.index].value}"
								name="distanceFromCom"> <input type="hidden"
								value="${distanceY[empLoop.index].value}" name="distanceFromYou">
							<input type="submit" name="method" value="To Work"> <input class="in-bh"
								type="submit" name="method" value="Back Home">
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>