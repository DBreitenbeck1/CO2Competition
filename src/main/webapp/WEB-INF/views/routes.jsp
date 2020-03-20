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
<%@ include file="partials/header.jsp"%></head>
<body>
<div>
<H2>Available Carpools</H2>

<div class="container">
	<div class="row">
	    <div class="col">Employee Name</div>
	    <div class="col">Starting Address</div>
	    <div class="col">Distance from Office</div>
	    <div class="col">Distance from You</div>
    </div>
  
  <c:forEach var="emp" items="${employees}" varStatus="empLoop"  >
	 <div class="row">
		  <form method="post" action="/ride" class="form-inline">
			  <div class="form-group mr-2">
  		   		<input type="hidden" value="${emp.username}" name="username">
					  ${emp.name}</div>
		      <div class="form-group mr-2">${emp.address}</div>
		      <div class="form-group mr-2">
		   		<input type="hidden" value="${distanceC[empLoop.index].value}" name="distanceFromCom">
			     		${distanceC[empLoop.index].text}
		      </div>
		      <div class="form-group mr-2"> 
		   		<input type="hidden" value="${distanceY[empLoop.index].value}" name="distanceFromYou">
			     		${distanceY[empLoop.index].text}
		     </div>
		     <div class="form-group mr-2"><input type="submit" name="method" value="To Work"></div>
		     <div class="form-group mr-2"><input type="submit" name="method" value="Back Home"></div>
	      </form>
	  </div>
  </c:forEach>
</div>


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