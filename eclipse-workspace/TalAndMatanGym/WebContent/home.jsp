<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="Gym.Model.Activity" %>
<%@ page import="Gym.Model.User" %>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>


<meta charset="ISO-8859-1">
<title>MyPage</title>
</head>
<body>
<% if(session.getAttribute("username")==null) 
	response.sendRedirect("/TalAndMatanGym/controller/tologin");%>
	<div>
			<div>
		 	  <h1>Welcome <%= session.getAttribute("username")%></h1>
			</div>
			<!--  all buttons -->
			<form name="training" action="/TalAndMatanGym/controller/movegraph" method="POST">
			<input type="submit" value="calorie graph" style="background-color: coral;">
			</form>
			<form name="muscle" action="/TalAndMatanGym/controller/movegraphmuscle" method="POST">
			<input type="submit" value="intensity graph" style="background-color: coral;"></form>
			
			<form name="training" action="/TalAndMatanGym/controller/mydata" method="POST">
			<input type="submit" value="my traninig table" style="background-color: coral;"> </form>
				<form name="training" action="/TalAndMatanGym/controller/movetoupdate" method="POST">
			<input type="submit" value="update user" style="background-color: coral;"> </form>
		  <h1><center>Add Activity</center></h1>
		  <h5><center>Add muscle training</center></h5>
		  <div id="mussle">
			<form name="training" action="/TalAndMatanGym/controller/addactivityanaerobic" method="POST">
		      <label for="select-choice-a" class="select">Custom select menu:</label>
				<select name="select-muscle" id="select-muscle" data-native-menu="false">
	   			 <option>choose muscle</option>
	   			 <option value="arm">arm</option>
	   			 <option value="leg">leg</option>
	   			 <option value="breasts">breasts</option>
	   			 <option value="back">back</option>
				</select>
		       Sets:<input type="text" name="sets"><br>
		       Repetition:<input type="text" name="repetition"><br>
		       Weight:<input type="text" name="weight"><br>
		       <input data-icon="check" type="submit" value="ok" style="background-color: coral;">
				</form>
				
		  </div>
		 <h5><center>Add Aerobic activity</center></h5>
			<div id="running">
				<form name="training" action="/TalAndMatanGym/controller/addactivityaerobic" method="POST">
		      		Avg_speed:<input type="text" name="avg_speed"><br> 
		     	 	Running_time:<input type="text" name="running_time"><br>
		      		<input data-icon="check" type="submit" value="ok" style="background-color: coral;">
				</form>       
			</div>
			<form name="logut" action="/TalAndMatanGym/controller/logout" method="POST">
		       	<input type="submit" value="logout" style="background-color: red;">
			</form>
	</div>
</body>
</html>