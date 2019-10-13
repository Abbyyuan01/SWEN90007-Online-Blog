<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" import="domain.Blog" import="domain.User"
    import="java.text.SimpleDateFormat" import="session.AppSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Blog</title>
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

	<% if (AppSession.isAuthenticated()) {%>

	<% User user = AppSession.getUser(); %>
	<% Blog blog = (Blog)request.getAttribute("blog"); %>
	

	<form action="./EditBlog?blogId=<%= blog.getId() %>" method="post">
        <div>
          <label for="edit-title">Title </label> 
          <input type="text" class="form-control" id="edit-title" name="title" value="<%=blog.getTitle()%>">
        </div>
	    <input type="hidden" class="form-control" name="updateAuthorId" value=<%=user.getId()%>>
		 
	  	<div>
          <label for="edit-content">Content </label> 
          <textarea id="edit-content" class="form-control" name="content" style="height:200px"><%=blog.getContent()%></textarea>
        </div>
        <div class="button">
          <button type="submit" class="btn btn-secondary">Edit</button>
        </div>
    </form>
    <br>
    <form action="./CancelEditBlog?blogId=<%= blog.getId() %>" method="post">
    	 <button type="submit" class="btn btn-secondary">Cancel</button>
    </form>
    
    <%} %>
    

</body>
</html>