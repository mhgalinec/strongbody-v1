<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.strongbody.beans.Members"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	href="${pageContext.request.contextPath}/css/style.css">">
<title>Membership</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
	<div class="container mt-5 pt-5">
		<div class="table table-responsive">
			<table class="table table-hover">
				<caption class="cap">Membership of ${member }</caption>
				<thead class="thead-dark text-center ">
					<tr>
						<th>ID</th>
						<th>Registration Date</th>
						<th>Valid From</th>
						<th>Valid Through</th>
						<th>Service Level</th>
						<th>Active For</th>
						<th>Membership Type</th>
						<%-- Adding multiple parameters to URL --%>
						<th><a
							href="membershipFee?membershipType=${membership.membershipType }&amp;serviceLevel=${membership.serviceLevel }&amp;id=${membership.id}&amp;paymentStatus=${membership.paymentStatus}">Payment
								Status</a></th>
					</tr>
				</thead>
				<tbody class="text-center">

					<tr>
						<td>${membership.id}</td>
						<td>${membership.registrationDate }</td>
						<td>${membership.validFrom}</td>
						<td>${membership.validThrough }</td>
						<td>${membership.serviceLevel }</td>
						<td id="${membership.id }"></td>
						<td>${membership.membershipType }</td>
						<td id="status">${membership.paymentStatus }</td>
					</tr>
				</tbody>
			</table>
			<a class="btn btn-secondary"
				href="updateMembership?id=${membership.id }&amp;membershipTypeGet=${membership.membershipType}&amp;member=${member}"
				role="button">Update</a> <a class="btn btn-danger"
				href="deleteMembership?id=${membership.id }" role="button">Delete
				All</a>
		</div>
	</div>
	<fmt:formatDate var="time" value="${membership.validThrough }"
		pattern="yyyy-MM-dd" />
	<script type="text/javascript">
		var countDate = new Date("${time}").getTime();

		function isActive() {
			var now = new Date().getTime();
			var day = 1000 * 60 * 60 * 24;

			gap = countDate - now + day;

			var d = Math.floor(gap / day);

			if (d < 0 || d == 0) {
				document.getElementById('${membership.id}').innerText = 'Expired';
			} else if (d == 1) {
				document.getElementById('${membership.id}').innerText = d
						+ ' day';
			} else {
				document.getElementById('${membership.id}').innerText = d
						+ ' days';
			}

			if (document.getElementById('status') == null) {
				document.getElementById('status').innerText = "Awaiting Payment";
			}

		}
		isActive();
	</script>

</body>
</html>