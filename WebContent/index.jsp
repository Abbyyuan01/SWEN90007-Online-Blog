<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*" import="domain.Blog" import="domain.User"
    import="java.text.SimpleDateFormat"%>
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
<title>All blogs</title>
</head>
<body>
<div class='container'>

	<br>
	<% List<User> users = (List<User>)request.getAttribute("users"); %>
    
    <form action="./PostBlog" method="post">
        <div>
          <label for="name">Title </label> 
          <input type="text" class="form-control" id="name" name="title" required>
        </div>
        
        <div class="form-group">
	    <label for="selectAuthor">Select Author</label>
		    <select class="form-control" id="SelectAuthor" name="authorId" required>
		        <% if (users != null) { %>
	   		    <% for (User user : users) { %>
			    	<option value=<%= user.getId()%>><%= user.getFirstName() %> <%= user.getLastName() %></option>
			    <%} %>
			    <%} %>
		    </select>
		   	<a href="newUser.jsp">Create new user</a>
	  	</div>
	  	
        <div>
          <label for="msg">Content </label> 
          <textarea id="msg" name="content" class="form-control" style="height:200px" required></textarea>
        </div>
        
        <div class="button">
          <button type="submit" class="btn btn-primary" style="margin-top: 1rem;">Post</button>
        </div>
    </form>
    
    <br>
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
    
    <form action="./blog" method="get">
    	<div class="button">
          <button type="submit" class="btn btn-info">List all blogs</button>
        </div>
    </form>
        
    <br><br>
    
    <% SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); %>
    <% List<Blog> blogs = (List<Blog>)request.getAttribute("blogs"); %>
    <% if (blogs != null) { %>
    <% for (Blog blog : blogs) { %>
    	
	    <h4><a href="./viewBlog?blogId=<%=blog.getId()%>"><%= blog.getTitle() %></a></h4>
    	<p class="text-muted"><%= df.format(blog.getPostDate()) %> by 
    	<%=	blog.getAuthor().getFirstName() + " " + blog.getAuthor().getLastName() %></p>    
    	    
    <%} %>
    <%} %>
      </div>
</body>
</html>