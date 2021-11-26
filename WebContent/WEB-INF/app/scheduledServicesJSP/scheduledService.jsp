<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:ital,wght@1,600&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
<title>Scheduled Service</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
	<div class="container mt-5 pt-5">
		<div class="table table-responsive">
			<table class="table table-hover">
				<caption class="cap">Scheduled Service for ${equipmentName }</caption>
				<thead class="thead-dark text-center ">
					<tr>
						<th>ID</th>
						<th>Service Type</th>
						<th>Service Date</th>
						<th>Warranty</th>
						<th>Price ($)</th>
						<th>Service Company</th>
					</tr>
				</thead>
				<tbody class="text-center">
					<tr>
						<td>${scheduledService.id }</td>
						<td>${scheduledService.serviceType }</td>
						<td>${scheduledService.serviceDate }</td>
						<td>${scheduledService.warranty }</td>
						<td>${scheduledService.price }</td>
						<td>${scheduledService.serviceCompany }</td>
					</tr>
				</tbody>

			</table>
			<a class="btn btn-secondary"
				href="updateScheduledService?id=${scheduledService.id }&amp;equipment=${equipmentName }"
				role="button">Update</a> <a class="btn btn-danger"
				href="deleteScheduledService?id=${scheduledService.id }"
				role="button">Delete All</a>
		</div>
	</div>


</body>
</html>