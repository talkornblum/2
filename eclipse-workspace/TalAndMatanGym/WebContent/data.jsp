<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="Gym.Model.Activity" %>
<%@ page import="Gym.Model.User" %>
<html>


<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>


<meta charset="ISO-8859-1">
<title>My Training</title>
</head>
<body>
	<div>
		<font size="1"><table data-role="table" id="table-column-toggle" data-mode="columntoggle" class="ui-responsive table-stroke" >	
		<%
		        List<Activity> activities = (List<Activity>)request.getAttribute("activities");
		%>
		 <thead>
		 <!-- main columns -->
                
		      	<th data-priority="1">muscle</font></th>
		      	<th data-priority="1">sets</th>
		      	<th data-priority="1">repetition</th>
		      	<th data-priority="1">weight</th>
		      	<th data-priority="1">time</th>
		      	<th data-priority="1">speed</th> 
		      	<th data-priority="1">Date</th>
		      </tr>
		    </thead>
		    <tbody>
		      <%
		      for(int j=0;j<activities.size();j++) { // put all activities in the data table 
		      %>
		        <tr>
		    		<th><%= activities.get(j).getMuscle() %></th>
		      	    <th><%= activities.get(j).getSets() %></th>
		            <th><%= activities.get(j).getRepetition() %></font></th>
		      	    <th><%= activities.get(j).getWeight() %></th>
		      	    <th><%= activities.get(j).getRunning_time() %></th> 
		      	    <th><%= activities.get(j).getAvg_speed() %></th> 
		      	    <th><%= activities.get(j).getdate().getTime().toLocaleString()%></th> <!-- current date -->
		      	    <th> <p class="message" style="color:red"><a href="/TalAndMatanGym/controller/deleteinfomydata/<%=activities.get(j).getId()%>">delete</a></p><br>
		      	    <p class="message" style="color:blue"><a href="/TalAndMatanGym/controller/movetoupdateactivity/<%=activities.get(j).getId()%>">change</a></p></th>
		    	</tr> 
		   <%} %>
		   </tbody>
		</table></font>
		<form name="home" action="/TalAndMatanGym/controller/backtohome" method="POST">
		       	<input type="submit" value="go back to home page" style="background-color: coral;">
		</form>
	</div>
</body>
</html>