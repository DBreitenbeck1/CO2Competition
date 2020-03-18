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
<form action ="/submit-carpool-back/${id }">
<section class="jumbotron">
<table class="table table-striped">
<thead>
  <tr>
    <th>Name</th>
    <th>City</th>
    <th>Street</th>
    <th>Zip code</th>
    <th>Distance to your address</th>
    <th>Distance to their own</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="m" items="${employees }" varStatus="mloop">
  
  <tr>
    <td><input type="radio" name="carpool" value="${m.username }"/>${m.name }</td>
    <td>${cCity }</td>
    <td>${cStreet }</td>
    <td>${cZip }</td>
    <td>${distanceFY [mloop.index].text}</td>
    <td>${distanceFT [mloop.index].text}</td>
  </tr>
  </c:forEach>
  </tbody>
  
</table>
<input type="hidden" name="date" value="${date }"/>
<input type="hidden" name="time" value="${time }"/>
<input type="hidden" name="id" value="${id }" />




</section>
<button class="btn btn-primary" type="submit">Submit your request</button>

</form>
</main>
</body>
</html>