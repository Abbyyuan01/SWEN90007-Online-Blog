
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="domain.User"
    import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View User</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style type="text/css">
body {
  	padding: 2rem 10rem 2rem 10rem;
  
}
.blog {
  text-align: center;
}
</style>
</head>
<body>
	 <form action="./listUser" method="get">
    	<!-- <div class="button">
          <button type="submit" class="btn btn-info">List all users</button>
        </div> -->
    
     <br><br>
    <% List<User> users = (List<User>)request.getAttribute("users"); %>
    <table class="table">
	  <thead>
	    <tr>
	      <th scope="col">Id</th>
	      <th scope="col">FirstName</th>
	      <th scope="col">LastName</th>
	      <th scope="col">Email</th>
	      <th scope="col">Password</th>
	    </tr>
	  </thead>
  <% if (users != null) { %>
    <% for (User user : users) { %>
	  <tbody>
	    <tr>
	      <th scope="row"><%=user.getId()%></th>
	      <td><%=user.getFirstName()%></td>
	      <td><%=user.getFirstName()%></td>
	      <td><%=user.getEmail()%></td>
	      <td><%=user.getPassword()%></td>
	    </tr>
	  </tbody>  	
     <%} %>
	 <%} %>
	</table>
</form>

</body>
</html>