<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import="java.util.ArrayList" import="java.util.Calendar"
	import="java.util.Date" import="java.text.DateFormat"
	import="java.text.SimpleDateFormat" import="java.util.Locale"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<title>graph</title>
</head>
<body>
<div data-role="page" data-theme="a">

		<% int[] days= (int[])request.getAttribute("days"); 
		 Calendar d=Calendar.getInstance();
		 Calendar cal=Calendar.getInstance(); 
			//if (caloriesPerDay != null) {
				DateFormat dateFormat = new SimpleDateFormat("EEEEEEE", Locale.ENGLISH);
				String[] Days = new String[7];
				int[] dayTemp=new int[7];
				for (int i = 6; i >= 0; i--) {
					dayTemp[i]=d.get(Calendar.DAY_OF_WEEK)-1;
					Days[i] = dateFormat.format(d.getTime());
					d.add(Calendar.DAY_OF_WEEK, -1);
				}

		%>
	<!-- 	<div>
		<h2 style="color:green;">Aww yeah, you successfully added today workout.</h2>
		<h4>Here is the weekly report:</h4>
		</div>
		<br> -->
		<canvas id="myChart" width="400" height="400"></canvas>
		<script>
			var ctx = document.getElementById("myChart").getContext('2d');
			var myChart = new Chart(ctx, {
				type : 'bar',
				data : {
					labels : [ "<%=Days[0]%>"
						, "<%=Days[1] %>"
						, "<%=Days[2] %>"
						, "<%=Days[3] %>"
						, "<%=Days[4] %>"
						, "<%=Days[5] %>"
						, "<%=Days[6] %>" ],
					datasets : [ {
						label : 'Numbers',
						data : [
		    <%=days[dayTemp[0]] %>
			,
			<%=days[dayTemp[1]] %>
			,
			<%=days[dayTemp[2]] %>
			,
			<%=days[dayTemp[3]] %>
			,
			<%=days[dayTemp[4]] %>
			,
			<%=days[dayTemp[5]] %>
			,
			<%= days[dayTemp[6]]%>
			],
						backgroundColor : [ 'rgba(0,100,0, 0.2)',
								'rgba(0,100,0, 0.2)',
								'rgba(0,100,0, 0.2)',
								'rgba(0,100,0, 0.2)',
								'rgba(0,100,0, 0.2)',
								'rgba(0,100,0, 0.2)',
								'rgba(0,100,0, 0.2)' ],
						borderColor : [ 'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)' ],
						borderWidth : 1
					} ]
				},
				options : {
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							}
						} ]
					}
				}
			});
		</script> 
		<%-- <%
			}
		%> --%>
		
		
	
	<form name="logut" action="/TalAndMatanGym/controller/backtohome" method="POST">
		       	<input type="submit" value="go back to home page" style="background-color: coral;">

    </div>
</body>
</html>