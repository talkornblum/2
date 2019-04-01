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
  <div id="mussle">
			<form name="training" action="/TalAndMatanGym/controller/updateactivitymuscle/<%=activity.getId()%>" method="POST">
		      <label for="select-choice-a" class="select">Custom select menu:</label>
				<select name="select-muscle" id="select-muscle" data-native-menu="false">
	   			 <option><%=activity.getMuscle()%></option> <!-- input prev muscle -->
	   			 <option value="arm">arm</option>
	   			 <option value="leg">leg</option>
	   			 <option value="breasts">breasts</option>
	   			 <option value="back">back</option>
				</select>
		       Sets:<input type="text" name="sets" placeholder="<%=activity.getSets()%>"><br>
		       Repetition:<input type="text" name="repetition" placeholder="<%=activity.getRepetition()%>"><br>
		       Weight:<input type="text" name="weight" placeholder="<%=activity.getWeight()%>"><br>
		       <input data-icon="check" type="submit" value="ok">
				</form>
				
   </div>
</div>
</body>
</html>