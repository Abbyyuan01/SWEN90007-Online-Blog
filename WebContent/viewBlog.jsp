<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="domain.Blog" import="domain.User"
    import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Blog</title>
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
	<div class="blog">
	
		 <% Blog blog = (Blog)request.getAttribute("blog"); %>
		 <% List<User> users = (List<User>)request.getAttribute("users"); %>
		 <% SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); %>
		
		<h1><%= blog.getTitle() %></h1>
		<p><small>Created by: <%= blog.getAuthor().getFirstName() + " " + blog.getAuthor().getLastName() %> ,
			at: <%= df.format(blog.getPostDate()) %></small></p>
		<p><small>Updated by: <%= blog.getUpdatedAuthor().getFirstName() + " " + blog.getUpdatedAuthor().getLastName() %> ,
		at: <%= df.format(blog.getLastEditDate()) %></small></p>
			
		<p class="lead"><%= blog.getContent() %></p>
		
	</div>
		
		<form action="./EditBlog?blogId=<%= blog.getId() %>" method="post">
	        <div>
	          <label for="edit-title">Title </label> 
	          <input type="text" class="form-control" id="edit-title" name="title" value=<%=blog.getTitle()%>>
	        </div>
		    <div class="form-group">
		    	<label for="selectAuthor">Select Author</label>
			    <select class="form-control" id="SelectAuthor" name="updateAuthorId">
			   		    <% for (User user : users) { %>
					    	<option value=<%= user.getId()%>><%= user.getFirstName() %> <%= user.getLastName() %></option>
					    <%} %>
			    </select>
		  	</div>
		  	<div>
	          <label for="edit-content">Content </label> 
	          <textarea id="edit-content" class="form-control" name="content" style="height:200px"><%=blog.getContent()%></textarea>
	        </div>
	        <div class="button">
	          <button type="submit" class="btn btn-secondary">Edit</button>
	        </div>
	    </form>
	   
	    <form action="./DeleteBlog?blogId=<%= blog.getId() %>" method="post">
	        <div class="button">
	          <button type="submit" class="btn btn-danger">Delete</button>
	        </div>
    </form>
    
    <a href="blog">Return to homepage</a>
    
	

</body>
</html>