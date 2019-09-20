<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="domain.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>login</title>
<style type="text/css">
	.bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
}
</style>
<link href="https://getbootstrap.com/docs/4.3/examples/floating-labels/floating-labels.css" rel="stylesheet">
</head>
<body>

	    <form class="form-signin" name= "form" action="<%=request.getContextPath()%>/login" method="post">       
	      <div class="text-center mb-4">
		    <img class="mb-4" src="https://getbootstrap.com/docs/4.3/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
		    <h1 class="h3 mb-3 font-weight-normal">Login in</h1>
		   </div>
		
		  <div class="form-label-group">
		    <input type="email" id="inputEmail" class="form-control" name="email" placeholder="Email address" required autofocus>
		    <label for="inputEmail">Email address</label>
		  </div>
		
		  <div class="form-label-group">
		    <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required>
		    <label for="inputPassword">Password</label>
		  </div>
		
		  <div class="checkbox mb-3">
		    <label>
		      <input type="checkbox" value="remember-me"> Remember me
		    </label>
		  </div>
		  <div class="form-label-group">
		  	<span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
		  </div>
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
	    </form>
 
</body>
</html>