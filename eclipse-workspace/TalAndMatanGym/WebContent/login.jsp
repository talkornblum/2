<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<!-- script  graph -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>


<title>login</title>
</head>
<body >
  <div>
    <form action ="/TalAndMatanGym/controller/login" method="POST">
      <input type="text" name="username" placeholder="Username" >
      <input type="password" name="password" placeholder="Password">
      <input type="submit" value="login" style="background-color: blue">
      <p class="message" style="color:red">Not registered? <a href="/TalAndMatanGym/controller/tosingup">Create an account</a></p>
    </form>
 </div>
 
</body>
</html>