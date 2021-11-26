<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Edit Membership</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>

	<%--name tags are used by the servlets doPost to get parameters(value tag) --%>
	<div class="container mt-5 pt-5">
		<c:if test="${not empty membership }">
			<div class="d-flex justify-content-center">
				<h4>${member }</h4>
			</div>
			<form class="needs-validation" novalidate method="POST"
				action="${pageContext.request.contextPath}/app/updateMembership">
				<input type="hidden" name="id" value="${membership.id }" />
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
						<label for="serviceLevel">Service Level</label> <select
							class="custom-select" name="serviceLevel" required>
							<c:choose>
								<c:when test="${membership.serviceLevel eq '1x Week' }">
									<option value="1x Week" selected>1x Week</option>
									<option value="2x Week">2x Week</option>
									<option value="3x Week">3x Week</option>
									<option value="4x Week">4x Week</option>
									<option value="5x Week">5x Week</option>
									<option value="6x Week">6x Week</option>
									<option value="Unlimited">Unlimited</option>
								</c:when>
								<c:when test="${membership.serviceLevel eq '2x Week' }">
									<option value="1x Week">1x Week</option>
									<option value="2x Week" selected>2x Week</option>
									<option value="3x Week">3x Week</option>
									<option value="4x Week">4x Week</option>
									<option value="5x Week">5x Week</option>
									<option value="6x Week">6x Week</option>
									<option value="Unlimited">Unlimited</option>
								</c:when>
								<c:when test="${membership.serviceLevel eq '3x Week' }">
									<option value="1x Week">1x Week</option>
									<option value="2x Week">2x Week</option>
									<option value="3x Week" selected>3x Week</option>
									<option value="4x Week">4x Week</option>
									<option value="5x Week">5x Week</option>
									<option value="6x Week">6x Week</option>
									<option value="Unlimited">Unlimited</option>
								</c:when>
								<c:when test="${membership.serviceLevel eq '4x Week' }">
									<option value="1x Week">1x Week</option>
									<option value="2x Week">2x Week</option>
									<option value="3x Week">3x Week</option>
									<option value="4x Week" selected>4x Week</option>
									<option value="5x Week">5x Week</option>
									<option value="6x Week">6x Week</option>
									<option value="Unlimited">Unlimited</option>
								</c:when>
								<c:when test="${membership.serviceLevel eq '5x Week' }">
									<option value="1x Week">1x Week</option>
									<option value="2x Week">2x Week</option>
									<option value="3x Week">3x Week</option>
									<option value="4x Week">4x Week</option>
									<option value="5x Week" selected>5x Week</option>
									<option value="6x Week">6x Week</option>
									<option value="Unlimited">Unlimited</option>
								</c:when>
								<c:when test="${membership.serviceLevel eq '6x Week' }">
									<option value="1x Week">1x Week</option>
									<option value="2x Week">2x Week</option>
									<option value="3x Week">3x Week</option>
									<option value="4x Week">4x Week</option>
									<option value="5x Week">5x Week</option>
									<option value="6x Week" selected>6x Week</option>
									<option value="Unlimited">Unlimited</option>
								</c:when>
								<c:when test="${membership.serviceLevel eq 'Unlimited' }">
									<option value="1x Week">1x Week</option>
									<option value="2x Week">2x Week</option>
									<option value="3x Week">3x Week</option>
									<option value="4x Week">4x Week</option>
									<option value="5x Week">5x Week</option>
									<option value="6x Week">6x Week</option>
									<option value="Unlimited" selected>Unlimited</option>
								</c:when>
							</c:choose>
						</select> <input type="hidden" value="${membership.serviceLevel }" />
						<div class="invalid-feedback">Please choose a service level!</div>
					</div>

					<div class="col-lg-6 mt-3 mb-4">
						<label for="membershipType">Membership Type</label> <select
							class="custom-select" name="membershipType" required>
							<c:choose>
								<c:when test="${membership.membershipType eq 'Daily' }">
									<option value="Daily" selected >Daily</option>
									<option value="Weekly">Weekly</option>
									<option value="Monthly">Monthly</option>
									<option value="Trimester">Trimester</option>
									<option value="Biannual">Biannual</option>
									<option value="Annual">Annual</option>
								</c:when>
								<c:when test="${membership.membershipType eq 'Weekly' }">
									<option value="Daily">Daily</option>
									<option value="Weekly" selected >Weekly</option>
									<option value="Monthly">Monthly</option>
									<option value="Trimester">Trimester</option>
									<option value="Biannual">Biannual</option>
									<option value="Annual">Annual</option>
								</c:when>
								<c:when test="${membership.membershipType eq 'Monthly' }">
									<option value="Daily">Daily</option>
									<option value="Weekly">Weekly</option>
									<option value="Monthly" selected >Monthly</option>
									<option value="Trimester">Trimester</option>
									<option value="Biannual">Biannual</option>
									<option value="Annual">Annual</option>
								</c:when>
								<c:when test="${membership.membershipType eq 'Trimester' }">
									<option value="Daily">Daily</option>
									<option value="Weekly">Weekly</option>
									<option value="Monthly">Monthly</option>
									<option value="Trimester" selected >Trimester</option>
									<option value="Biannual">Biannual</option>
									<option value="Annual">Annual</option>
								</c:when>
								<c:when test="${membership.membershipType eq 'Biannual' }">
									<option value="Daily">Daily</option>
									<option value="Weekly">Weekly</option>
									<option value="Monthly">Monthly</option>
									<option value="Trimester">Trimester</option>
									<option value="Biannual" selected >Biannual</option>
									<option value="Annual">Annual</option>
								</c:when>
								<c:when test="${membership.membershipType eq 'Annual' }">
									<option value="Daily">Daily</option>
									<option value="Weekly">Weekly</option>
									<option value="Monthly">Monthly</option>
									<option value="Trimester">Trimester</option>
									<option value="Biannual">Biannual</option>
									<option value="Annual" selected >Annual</option>
								</c:when>
							</c:choose>
						</select> <input type="hidden" value="${membership.membershipType }" /> <input
							type="hidden" name="membershipTypeGet"
							value="${membership.membershipType }" />
						<div class="invalid-feedback">Please choose a membership
							type!</div>
					</div>
				</div>

				<button class="btn btn-secondary" type="submit">Update</button>
			</form>
		</c:if>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/validation.js"></script>

</body>
</html>