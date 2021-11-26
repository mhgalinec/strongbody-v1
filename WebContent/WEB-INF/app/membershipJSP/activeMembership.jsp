<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>Active Membership</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
	<div class="container mt-5 pt-5">
		<div class="table table-responsive">
			<table class="table table-hover">
				<caption class="cap">List of active memberships</caption>
				<thead class="thead-dark text-center">
					<tr>
						<th>ID</th>
						<th>Registration Date</th>
						<th>Valid From      </th>
						<th>Valid Through</th>
						<th>Service Level</th>
						<th>Membership Type</th>
						<th>Active For</th>
						<th>Member Name</th>
						<th colspan="2"></th>

					</tr>
				</thead>
				<tbody class="text-center">
					<c:forEach items="${activeMembership}" var="membership">

						<fmt:formatDate var="time" value="${membership.validThrough }"
							pattern="yyyy-MM-dd" />
						<tr>
							<th scope="row">${membership.id }</th>
							<td>${membership.registrationDate }</td>
							<td>${membership.validFrom }</td>
							<td>${membership.validThrough }</td>
							<td>${membership.serviceLevel }</td>
							<td>${membership.membershipType }</td>
							<td id="${membership.id }"></td>
							<td>${membership.memberName }</td>
							<td><a href="deleteMembership?id=${membership.id }">Delete</a>
							</td>
							<td><a href="updateMembership?id=${membership.id }&amp;member=${membership.memberName}">Update</a>
							</td>
						</tr>
						<script type="text/javascript">
							var countDate = new Date("${time}").getTime();

							function isActive() {
								var now = new Date().getTime();
								var day = 1000 * 60 * 60 * 24;
								var hour = 1000 * 60 * 60;

								gap = countDate - now + day;

								var d = Math.floor(gap / day);
								var h = Math.floor((gap % day) / hour);

								if (d == 0) {
									document.getElementById('${membership.id}').innerHTML = h
											+ ' hours';
									if (h <= 1) {
										document.getElementById('${membership.id}').innerHTML = 'Less than an hour';
									}
								} else if (d < 0) {
									document.getElementById('${membership.id}').innerHTML = 'Expired';
								} else if (d == 1) {
									document.getElementById('${membership.id}').innerHTML = d
											+ ' day';
								} else {
									document.getElementById('${membership.id}').innerHTML = d
											+ ' days';
								}

							}
							isActive();
						</script>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>