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
    	<h6><%= blog.getTitle() %></h6>
    	<%  if (blog.getAuthor() != null) { %>
    	<%=		blog.getAuthor().getFirstName() + " " + blog.getAuthor().getLastName() %><br>
    	<%	} else { %>
    	<%=		"This blog has invalid author id" %><br>
    	<%	} %>
    	
    	    
	    <form action="./viewBlog" method="get">
	        <input type="hidden" name="blogId" value=<%= blog.getId() %> />
		  	
	        <div class="button">
	          <button type="submit">view more</button>
	        </div>
	    </form>
    
        ------------------------
        <br>
    <%} %>
    <%} %>
      </div>
</body>
</html>