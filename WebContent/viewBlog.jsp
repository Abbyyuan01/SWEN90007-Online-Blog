<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="domain.Blog" import="domain.User"
    import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Blog</title>
</head>
<body>
	<div class = "container">
	
		 <% Blog blog = (Blog)request.getAttribute("blog"); %>
		 <% List<User> users = (List<User>)request.getAttribute("users"); %>
		 <% SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); %>
		
		<h1 class="mt-5"><%= blog.getTitle() %></h1>
		<p class="lead">Created by: <%= blog.getAuthor().getFirstName() + " " + blog.getAuthor().getLastName() %></p>
		<p class="lead">Updated by: <%= blog.getUpdatedAuthor().getFirstName() + " " + blog.getUpdatedAuthor().getLastName() %></p>
		<p class="lead">Created at: <%= df.format(blog.getPostDate()) %></p>
		<p class="lead">Updated at: <%= df.format(blog.getLastEditDate()) %></p>
			
		<p class="lead"><%= blog.getContent() %></p>
		
		<form action="./EditBlog?blogId=<%= blog.getId() %>" method="post">
	        <div>
	          <label for="edit-title">Title </label> <input type="text" id="edit-title" name="title">
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
	          <label for="edit-content">Content </label> <textarea id="edit-content" name="content"></textarea>
	        </div>
	        <div class="button">
	          <button type="submit">Edit</button>
	        </div>
	    </form>
	   
	    <form action="./DeleteBlog?blogId=<%= blog.getId() %>" method="post">
	        <div class="button">
	          <button type="submit">Delete</button>
	        </div>
    </form>
    
    <a href="blog">Return to homepage</a>
    
	</div>

</body>
</html>