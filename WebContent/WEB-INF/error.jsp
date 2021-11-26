<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<style>
html{
	background-color: #e9ecef;
	}
</style>
<title>Error</title>
</head>
<body>

<div class="jumbotron">
  <h1 class="display-4">Oops...</h1>
  <p class="lead">Something went wrong!</p>
  <hr class="my-4">
  <p>Please return to menu and try again!</p>
  <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/app/menu" role="button">Menu</a>
</div>


</body>
</html>