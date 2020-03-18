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
<H2>Available Routes</H2>

<table class ="table">
  <tr>
    <th>Employee Name</th>
    <th>Starting Address</th>
    <th>Distance from Office</th>
    <th>Distance from You</th>
  </tr>
 
  <c:forEach var="emp" items="${employees }" varStatus="empLoop"  >
  
  <tr>
    <td>${emp.name }</td>
    <td>${emp.address }</td>
     <td>${distanceC[empLoop.index].text }</td>
      <td>${distanceY[empLoop.index].text }</td>
  </tr>
    </c:forEach>

</table>


<h2>Existing Carpools</h2>
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


</div>

</body>
</html>