<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*" import="domain.Blog"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All blogs</title>
</head>
<body>
    <form action="./PostBlog" method="post">
        <div>
          <label for="name">Title </label> <input type="text" id="name" name="title">
        </div>
        <div>
          <label for="mail">Author Id </label> <input type="number" id="mail" name="authorId">
        </div>
        <div>
          <label for="msg">Body </label> <textarea id="msg" name="content"></textarea>
        </div>
        <div class="button">
          <button type="submit">Post your blog</button>
        </div>
    </form>
    <a href="blog">Blogs</a>
    <br>
    <br>
    <% List<Blog> blogs = (List<Blog>)request.getAttribute("blogs"); %>
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
      
</body>
</html>