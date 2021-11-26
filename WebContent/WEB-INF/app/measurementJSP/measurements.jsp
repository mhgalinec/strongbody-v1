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
<title>Measurements</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
	<div class="container mt-5 pt-5">
		<div class="table table-responsive">
			<table class="table table-hover">
				<caption class="cap">Measurements of ${member }</caption>
				<thead class="thead-dark text-center ">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Measurement Date</th>
						<th scope="col">Height</th>
						<th scope="col">Weight</th>
						<th scope="col">Body Fat</th>
						<th scope="col">Shoulders</th>
						<th scope="col">Torso</th>
						<th scope="col">Waist</th>
						<th scope="col">Upper Arm</th>
						<th scope="col">Lower Arm</th>
						<th scope="col">Upper Leg</th>
						<th scope="col">Lower Leg</th>
						<th scope="col">Resting Hearth Rate</th>
					</tr>
				</thead>
				<tbody class="text-center">
					<tr>
						<th scope="row">${measurements.id }</th>
						<td>${measurements.measurementDate }</td>
						<td>${measurements.height }cm</td>
						<td>${measurements.weight }kg</td>
						<td>${measurements.bodyFat }%</td>
						<td>${measurements.shoulders }cm</td>
						<td>${measurements.torso }cm</td>
						<td>${measurements.waist }cm</td>
						<td>${measurements.upperArm }cm</td>
						<td>${measurements.lowerArm }cm</td>
						<td>${measurements.upperLeg }cm</td>
						<td>${measurements.lowerLeg }cm</td>
						<td>${measurements.restingHeartRate }bpm</td>
					</tr>
				</tbody>
			</table>
		</div>
		<a class="btn btn-secondary"
			href="updateMeasurements?id=${measurements.id }&amp;name=${member }" role="button">Update</a>
		<a class="btn btn-danger"
			href="deleteMeasurements?id=${measurements.id }" role="button">Delete
			All</a>
	</div>
</body>
</html>