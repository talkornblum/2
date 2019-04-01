<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<meta charset="ISO-8859-1">
<title>Singup</title>
</head>
<body>
  <div data-role="page" data-theme="a">
    <form action ="/TalAndMatanGym/controller/singup" method="POST">
      <input type="text" name="username">
      <input type="password" name="password">
      <input type="submit" value="singup" style="background-color: blue">
      <p class="message" style="color:red">registered? <a href="/TalAndMatanGym/controller/tologin">go to login page</a></p>
    </form>
    </div>
</body>
</html>