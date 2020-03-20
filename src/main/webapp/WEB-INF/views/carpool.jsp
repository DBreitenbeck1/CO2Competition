<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Plan Carpool || Green on the Go</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<%@ include file="partials/header.jsp"%>

</head>

<body>
<main class="container">
<section class="jumbotron">
<h4>Hey ${employee.name}, let's plan your carpool.</h4>
<h4>Where to?</h4>
<a href="/ridetw" class="btn btn-primary" type="submit">Ride to Work</a>
<a href="/ridebh" class="btn btn-primary" type="submit">Ride Home</a>
</section>
</main>
</body>
</html>