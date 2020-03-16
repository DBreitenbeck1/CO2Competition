<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />

</head>
<body>
<main class="container">
<section class="jumbotron">

<p>Welcome ${name }</p>
<p>You are currently working at ${company }</p>

</section>
<section class="jumbotron">

<a href="/previous-routes" class="btn btn-primary" type="submit">Previous Routes</a>
<a href="/favorite-routes" class="btn btn-primary" type="submit">Favorite Routes</a>
<a href="/summary" class="btn btn-primary" type="submit">Summary of saved</a>
<a href="/carpool/${emId }" class="btn btn-primary" type="submit">Request a Carpool</a>


</section>

</main>


</body>
</html>