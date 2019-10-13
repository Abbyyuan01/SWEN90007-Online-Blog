<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*" import="domain.Blog" import="domain.User"
    import="java.text.SimpleDateFormat" import="session.AppSession"
    import="service.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.blog-post-meta {
  margin-bottom: 1.25rem;
  color: #999;
}
</style>
<title>Admin page</title>
</head>
<body>
<div class='container'>

	<% User admin = AppSession.getUser(); %>
	
	<% List<User> users = UserService.getAllUser(); %>
	
	<div class="row">
      <div class="col-2">Firstname</div>
      <div class="col-3"><%= admin.getFirstName() %></div>
    </div>

    <br>
    <div class="row">
      <div class="col-2">Lastname</div>
      <div class="col-3"><%= admin.getLastName() %></div>
    </div>

    <br>
    <div class="row">
      <div class="col-md-2">Email</div>
      <div class="col-md-10"><%= admin.getEmail() %></div>
    </div>
    <br>
	
    
    <form action="./SearchBlogByUser" method="get">
          	
	  	<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <label class="input-group-text" for="selectAuthor">Select Author</label>
		  </div>
		  <select class="custom-select" id="selectAuthor" name="authorId" required>
	    		<% if (users != null) { %>
	   		    <% for (User user : users) { %>
			    	<option value=<%= user.getId()%>><%= user.getFirstName() %> <%= user.getLastName() %></option>
			    <%} %>
			    <%} %>
		  </select>
		</div>
	  	
        <div class="button">
          <button type="submit" class="btn btn-secondary">Search blog by user</button>
        </div>
    </form>
    
    <br>
    
      </div>
</body>
</html>