
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="domain.*"
    import="java.text.SimpleDateFormat" import="session.AppSession"
    import="service.BlogService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User</title>
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
<main>

    <a href="blog">Return to homepage</a>

	<% if (AppSession.isAuthenticated()) {%>

	<% User user = AppSession.getUser(); %>
	<% SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); %>
	
           
    <div class="row">
      <div class="col-2">Firstname</div>
      <div class="col-3"><%= user.getFirstName() %></div>
    </div>

    <br>
    <div class="row">
      <div class="col-2">Lastname</div>
      <div class="col-3"><%= user.getLastName() %></div>
    </div>

    <br>
    <div class="row">
      <div class="col-md-2">Email</div>
      <div class="col-md-10"><%= user.getEmail() %></div>
    </div>
    <br>
	    
    
    <!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal">
	  Edit details
	</button>
	
	<!-- Modal -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Edit details</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      
	      <form action="./EditUser?userId=<%= user.getId() %>" method="post">
	      <div class="modal-body">
	        <label for="edit-first">Firstname </label> 
	        <input type="text" class="form-control" id="edit-first" name="firstname" value=<%=user.getFirstName()%>>
	        
	       	<label for="edit-last">Lastname </label> 
	        <input type="text" class="form-control" id="edit-last" name="lastname" value=<%=user.getLastName()%>>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>
	      </form>
	    </div>
	  </div>
	</div>
    
    <br>
    <br>
    <% List<Blog> blogs = BlogService.searchBlogByUser(user.getId()); %>
    <% if (blogs != null) { %>
    <% for (Blog blog : blogs) { %>
    	
	    <h4><a href="./viewBlog?blogId=<%=blog.getId()%>"><%= blog.getTitle() %></a></h4>
    	<p class="text-muted"><%= df.format(blog.getPostDate()) %> by 
    	<%=	blog.getAuthor().getFirstName() + " " + blog.getAuthor().getLastName() %></p>    
    	    
    <%} %>
    <%} %>
    
    
   	<form class="form-signin" name= "form" action="./LogoutServlet" method="post">       
	  <button class="btn btn-primary " type="submit">Log out</button>
    </form>
    
   	<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">
	  Delete my account
	</button>
	
	<!-- Modal -->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Delete my account</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      
	      <form action="./DeleteUser?userId=<%= user.getId() %>" method="post">
	      <div class="modal-body">
	        Are you sure you want to delete your account?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-danger">Delete</button>
	      </div>
	      </form>
	    </div>
	  </div>
	</div>
    
    
   	<%} %>
   	</main>
   	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
     <script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
	
    <script src="https://getbootstrap.com/docs/4.1/assets/js/vendor/popper.min.js"></script>
    <script src="https://getbootstrap.com/docs/4.1/dist/js/bootstrap.min.js"></script>
</body>
</html>