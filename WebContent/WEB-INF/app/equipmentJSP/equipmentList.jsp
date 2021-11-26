<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>Equipment List</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
	<div class="container mt-5 pt-5">
		<div class="table table-responsive">


			<table class="table table-hover">
				<caption class="cap">Equipment List</caption>

				<thead class="thead-dark text-center">
					<tr>
						<th>ID</th>
						<th>Equipment Name</th>
						<th>Serial Number</th>
						<th>Category</th>
						<th>Manufacturer</th>
						<th>Width (mm)</th>
						<th>Length (mm)</th>
						<th>Heigth (mm)</th>
						<th>Weight (kg)</th>
						<th colspan="4"></th>
					</tr>
				</thead>

				<tbody class="text-center">
					<c:forEach items="${equipmentList }" var="equipment">
						<tr>
							<td>${equipment.id }</td>
							<td>${equipment.name }</td>
							<td>${equipment.serialNo }</td>
							<td>${equipment.category }</td>
							<td>${equipment.manufacturer }</td>
							<td>${equipment.width }</td>
							<td>${equipment.length }</td>
							<td>${equipment.heigth }</td>
							<td>${equipment.weight }</td>
							<td><a
								href="scheduledService?id=${equipment.id }&amp;equipmentName=${equipment.name}">Scheduled
									Service</a></td>
							<td><a
								href="unplannedService?id=${equipment.id }&amp;equipmentName=${equipment.name}">Unplanned
									Service</a></td>
							<td><a href="updateEquipment?id=${equipment.id}">Update</a></td>
							<td><a
								href="deleteEquipment?id=${equipment.id}&amp;equipmentName=${equipment.name}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>

		</div>
</body>
</html>