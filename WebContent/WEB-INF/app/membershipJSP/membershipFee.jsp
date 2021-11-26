<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
<title>Membership Fee</title>
</head>
<body>
<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>	
<div class="container mt-5 pt-5">
<div class="d-flex justify-content-center">
	<form method="POST" action="${pageContext.request.contextPath}/app/membershipFee">
		<h3>The total Membership Fee is ${price } kn</h3>
		<input type="hidden" name="id" value="${membership.id }"/>
		<input type="hidden" name="registrationDate" value="${membership.registrationDate }"/>
		<input type="hidden" name="validFrom" value="${membership.validFrom }"/>
		<input type="hidden" name="validThrough" value="${membership.validThrough }"/>
		<input type="hidden" name="serviceLevel" value="${membership.serviceLevel }"/>
		<input type="hidden" name="membershipType" value="${membership.membershipType}"/>
		<input type="hidden" name="payment" value="Payed"/>		
		<button class="btn btn-secondary" type="submit">Pay Membership</button>	
	</form>
	</div>
	</div>
		

</body>
</html>