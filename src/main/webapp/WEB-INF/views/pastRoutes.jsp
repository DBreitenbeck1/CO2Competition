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
</head>
<body>

<div>

<h2>Carpools You Have Used:</h2>
<table class="table">
  <tr>
    <th>Id</th>
    <th>Date</th>
    <th>Passengers</th>
    <th>CO2 Saved</th>
  </tr>
  <c:forEach var="cp" items="${carpools }">
  <tr>
    <td>${cp.carpoolId }</td>
    <td>${cp.date }</td>
    <td>
    <ul>
    <c:forEach var="pass" items ="${cp.employees }">
    <li>${pass.name}</li>
    </c:forEach>
    </ul>
    </td>
     <th>${cp.co2}</th>
  </tr>
  </c:forEach>
</table>

 <a href="/employee/${emId }" class="btn btn-primary"
				type="submit">Back to Employee Page</a> 

</div>

</body>
</html>