<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*" import="domain.Blog" import="domain.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>All blogs</title>
</head>
<body>
<div class='container'>

	<br>
	<% List<User> users = (List<User>)request.getAttribute("users"); %>
   
    <form action="./viewBlog" method="get">

        <div>
          <label for="msg">Blog id </label> <textarea id="msg" name="blogId"></textarea>
        </div>
        <div class="button">
          <button type="submit">Post your blog</button>
        </div>
    </form>
    
    <form action="./PostBlog" method="post">
        <div>
          <label for="name">Title </label> <input type="text" id="name" name="title">
        </div>
        
        <div class="form-group">
	    <label for="selectAuthor">Select Author</label>
		    <select class="form-control" id="SelectAuthor" name="authorId">
		   		    <% for (User user : users) { %>
				    	<option value=<%= user.getId()%>><%= user.getFirstName() %> <%= user.getLastName() %></option>
				    <%} %>
		    </select>
	  	</div>
	  	
        <div>
          <label for="msg">Body </label> <textarea id="msg" name="content"></textarea>
        </div>
        <div class="button">
          <button type="submit">Post your blog</button>
        </div>
    </form>
    
    <br>
    <br>
    
    <form action="./SearchBlogByUser" method="get">
        
        <div class="form-group">
	    <label for="selectAuthor">Select Author</label>
		    <select class="form-control" id="SelectAuthor" name="authorId">
		   		    <% for (User user : users) { %>
				    	<option value=<%= user.getId()%>><%= user.getFirstName() %> <%= user.getLastName() %></option>
				    <%} %>
		    </select>
	  	</div>
	  	
        <div class="button">
          <button type="submit">Search blog by user</button>
        </div>
    </form>
    
    <br>
    
    <a href="blog">List all blogs</a>
    
    <br>
    <br>
    
    <% List<Blog> blogs = (List<Blog>)request.getAttribute("blogs"); %>
    <% if (blogs != null) { %>
    <% for (Blog blog : blogs) { %>
    	<h4><%= blog.getTitle() %></h4>
    	<%  if (blog.getAuthor() != null) { %>
    	<%=		blog.getAuthor().getFirstName() + " " + blog.getAuthor().getLastName() %><br>
    	<%	} else { %>
    	<%=		"This blog has invalid author id" %><br>
    	<%	} %>
        <p> <%= blog.getContent() %></p>
        <form action="./EditBlog?blogId=<%= blog.getId() %>" method="post">
	        <div>
	          <label for="edit-title">Title </label> <input type="text" id="edit-title" name="title">
	        </div>
	        <div class="button">
	          <button type="submit">Edit title</button>
	        </div>
	    </form>
	    <form action="./EditBlog?blogId=<%= blog.getId() %>" method="post">
	        <div>
	          <label for="edit-content">Content </label> <textarea id="edit-content" name="content"></textarea>
	        </div>
	        <div class="button">
	          <button type="submit">Edit content</button>
	        </div>
	    </form>
	    <form action="./DeleteBlog?blogId=<%= blog.getId() %>" method="post">
	        <div class="button">
	          <button type="submit">Delete</button>
	        </div>
	    </form>
        ------------------------
        <br>
    <%} %>
    <%} %>
      </div>
</body>
</html>