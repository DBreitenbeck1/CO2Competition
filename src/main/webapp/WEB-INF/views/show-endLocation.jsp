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
<form action="/find-carpool-back/${id }">
<section class="jumbotron">

<div>Work - ${cCity }, ${cStreet }, ${cZip }</div>
<div>Home - ${eCity }, ${eStreet }, ${eZip }</div>
<div>Select pickup date: <input type="date" name="date" required/></div>
<div>Select pickup time: <input type="time" name="time" required/></div>

</section>
<input type="hidden" name="cCity" value="${cCity }"/>
<button class="btn btn-primary" type="submit">Find carpool</button>
</form>
</main>
</body>
</html>