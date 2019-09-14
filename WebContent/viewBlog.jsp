<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="domain.Blog"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Blog</title>
</head>
<body>
	<div class = "container">
	
		 <% Blog blog = (Blog)request.getAttribute("blog"); %>
		
		<h1 class="mt-5"><%= blog.getTitle() %></h1>
		<p class="lead"><%= blog.getAuthor().getFirstName() + " " + blog.getAuthor().getLastName() %></p>	
		<p class="lead"><%= blog.getContent() %></p>
		
	</div>

</body>
</html>