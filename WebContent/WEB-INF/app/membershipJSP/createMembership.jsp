<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.strongbody.beans.MembershipPrice"%>
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

<title>Create Membership</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

	<div class="container mt-5 pt-5">
		<div class="d-flex justify-content-center"><h4> Create membership for ${member }</h4></div>
		<%-- On submit calls servlet with /app/createMemberhsip annotations --%>
		<form class="needs-validation" novalidate method="POST"
			action="${pageContext.request.contextPath}/app/createMembership">
			<%-- Automatic membership insert Step 4
	 	 Parameter memberID called from MembershipInfo servlet
	 --%>
			<input type="hidden" name="memberID" value="${memberID }" /> <input
				type="hidden" name="memberName" value="${member}" />

			<div class="form-row">
				<div class="col-lg-6 mb-4 mt-3">
					<label for="validationDate">Registration Date</label> <input
						type="date" name="registrationDate" class="form-control"
						id="validationDate" placeholder=""
						value="${membership.registrationDate }" required>
					<div class="invalid-feedback">Please choose a date!</div>
				</div>

				<div class="col-lg-6 mb-4 mt-3">
					<label for="validationValidFrom">Valid From</label> <input
						type="date" name="validFrom" class="form-control"
						id="validationValidFrom" placeholder=""
						value="${membership.validFrom }" required>
					<div class="invalid-feedback">Please choose a date!</div>
				</div>


				<div class="col-lg-6 mt-3 mb-3 ">
					<label for="validationValidThrough">Valid Through</label> <input
						type="date" name="validThrough" class="form-control"
						id="validationValidThrough" placeholder=""
						value="${membership.validThrough }" required>
					<div class="invalid-feedback">Please choose a date!</div>
				</div>
				

				<div class="col-lg-6 mt-3 mb-4 ">
				<label for="serviceLevel">Service Level</label>
					<select class="custom-select" name="serviceLevel" required>
						<option value="">Service Level</option>
						<option value="1x Week">1x Week</option>
						<option value="2x Week">2x Week</option>
						<option value="3x Week">3x Week</option>
						<option value="4x Week">4x Week</option>
						<option value="5x Week">5x Week</option>
						<option value="6x Week">6x Week</option>
						<option value="Unlimited">Unlimited</option>
					</select> <input type="hidden" value="${membership.serviceLevel }" />
					<div class="invalid-feedback">Please choose a service level!</div>
				</div>

				<div class="col-lg-6 mt-3 mb-4  ">
				<label for="membershipType">Membership Type</label>
					<select class="custom-select" name="membershipType" required>
						<option value="">Membership Type</option>
						<option value="Daily">Daily</option>
						<option value="Weekly">Weekly</option>
						<option value="Monthly">Monthly</option>
						<option value="Trimester">Trimester</option>
						<option value="Biannual">Biannual</option>
						<option value="Annual">Annual</option>
					</select> <input type="hidden" value="${membership.membershipType }" />
					<div class="invalid-feedback">Please choose a membership
						type!</div>
				</div>
			</div>

			<button class="btn btn-secondary" type="submit">Submit form</button>
		</form>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>

</body>
</html>