<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.*" import="domain.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Add User</title>
</head>
<body>
	<div class="container">
		<h3>Create New User</h3>
		<form action="./AddUser" method="post">
		  <div class="form-group">
		    <label for="id">Id</label>
		    <input type="number" class="form-control" id="userid" name="userId">
		  </div>
		   <div class="form-group">
		    <label for="firstName">First Name</label>
		    <input type="text" class="form-control" id="fname" name="firstName">
		  </div>
		  <div class="form-group">
		    <label for="firstName">Last Name</label>
		    <input type="text" class="form-control" id="lname" name="lastName">
		  </div>	 
		  <div class="form-group">
		    <label for="email">Email address</label>
		    <input type="email" class="form-control" id="email" name="email">
		  </div>
		  <div class="form-group">
		    <label for="password">Password</label>
		    <input type="password" class="form-control" id="pwd" name="password">
		  </div> 
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>