<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
		<link rel="stylesheet" href="css/siteName.css"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="css/login.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login Form</title>
	</head>
	
	
	<body>
	<div class="wholePage">
		<span id="siteName"><a href="index.jsp">GeoQ</a></span>
		<div class="wrapper">
			<form class="form-signin">
	     		<h2 class="form-signin-heading">Please login</h2>
				<input type="text" class="form-control" name="username" placeholder="Username" /><br>
				<input type="password" class="form-control" name="password" placeholder="Password">  <br>
				<button class="btn btn-md btn-primary btn-block" type="submit">Login</button>
	    	</form>
	  	</div>
	</div>
	<!--Fix chrome bug with CSS transitions and forms-->
	<script> </script>
	<!--Yes, really.-->
	</body>
</html>