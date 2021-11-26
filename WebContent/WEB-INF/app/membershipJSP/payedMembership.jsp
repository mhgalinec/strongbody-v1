<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Status</title>
<style>
html,.d-flex{
	background-color: #e9ecef;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
<div class="d-flex fee justify-content-center">
<div class="jumbotron text-center">
<i class="fas fa-check" style="color:#2ECC71"></i>
  <h1 class="display-4">Thank You!</h1>
  <p class="lead">Membership has already been payed for!</p>
  <hr class="my-4">
  <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/app/menu" role="button">Return To Menu</a>
</div>
</div>

</body>
</html>