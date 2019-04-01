<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Gym.Model.Activity" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>update activity</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
</head>
<body>
<% Activity activity=(Activity)request.getAttribute("activity"); %>
<div data-role="page" data-theme="a">
  <div id="running">
  <!-- prev activity info -->
				<form name="training" action="/TalAndMatanGym/controller/updateactivityrunning/<%=activity.getId()%>" method="POST">
		      		Avg_speed:<input type="text" name="avg_speed" placeholder="<%=activity.getAvg_speed()%>"><br> 
		     	 	Running_time:<input type="text" name="running_time" placeholder="<%=activity.getRunning_time()%>"><br>
		      		<input data-icon="check" type="submit" value="ok">
				</form>       
   </div>
</div>
</body>
</html>