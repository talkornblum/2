<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>
	<%  if (((String)request.getAttribute("UserNameDontExist")).compareTo("UserNameDontExist")==0){ %>	
<h1>the username incorrect </h1>
	<form name="tologin" action="/TalAndMatanGym/controller/tologin" method="POST">
		       	<input type="submit" value="go back to login" style="background-color: coral;">
		       	<%}%> 
		   
		       	
<% if (((String)request.getAttribute("ErrorPassWord")).compareTo("ErrorPassWord")==0){ %>	
<h1>false password</h1>
	<form name="tosingup" action="/TalAndMatanGym/controller/tosingup" method="POST">
		       	<input type="submit" value="go back to singup" style="background-color: coral;">
		       	<%} %>
		       		
<% if (((String)request.getAttribute("UserNameExist")).compareTo("UserNameExist")==0){ %>	
<h1>the username already exists</h1>
	<form name="tosingup" action="/TalAndMatanGym/controller/tosingup" method="POST">
		       	<input type="submit" value="go back to singup" style="background-color: coral;">
		       	<%} %>		       			       		       	
	
<% if (((String)request.getAttribute("ErrorActivity")).compareTo("ErrorActivity")==0){ %>	
<h1>false activity</h1>
	<form name="tologin" action="/TalAndMatanGym/controller/backtohome" method="POST">
		       	<input type="submit" value="go back to home page" style="background-color: coral;">
		       	<%} %>

<%if (((String)request.getAttribute("ErrorUpDate")).compareTo("ErrorUpDate")==0){ %>	
<h1>the username already exists</h1>
	<form name="movetoupdate" action="/TalAndMatanGym/controller/movetoupdate" method="POST">
		       	<input type="submit" value="go back to update" style="background-color: coral;">
		       	<%} %>		       	
</body>
</html>