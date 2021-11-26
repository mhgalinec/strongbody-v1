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
<title>Update Service</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
	<div class="container mt-5 pt-5">
		<c:if test="${not empty scheduledService }">
			<div class="d-flex justify-content-center">
				<h4>${equipmentName }</h4>
			</div>
			<form class="needs-validation" novalidate method="POST"
				action="${pageContext.request.contextPath}/app/updateScheduledService">
				<input type="hidden" name="id" value="${scheduledService.id }" />

				<div class="form-row">
					<div class="form-group col-lg-6 mt-3 mb-3">
						<label for="serviceType">Service Type</label> <select
							class="custom-select" name="serviceType" required>
							<c:choose>
								<c:when
									test="${scheduledService.serviceType eq 'Scheduled Maintenance'}">

									<option value="Scheduled Maintenance" selected>Scheduled
										Maintenance</option>
									<option value="Equipment Upgrade">Equipment Upgrade</option>
									<option value="Parts Exchange">Parts Exchange</option>
								</c:when>
								<c:when
									test="${scheduledService.serviceType eq 'Equipment Upgrade'}">

									<option value="Scheduled Maintenance">Scheduled
										Maintenance</option>
									<option value="Equipment Upgrade" selected>Equipment
										Upgrade</option>
									<option value="Parts Exchange">Parts Exchange</option>
								</c:when>
								<c:when
									test="${scheduledService.serviceType eq 'Parts Exchange'}">

									<option value="Scheduled Maintenance">Scheduled
										Maintenance</option>
									<option value="Equipment Upgrade">Equipment Upgrade</option>
									<option value="Parts Exchange" selected>Parts Exchange</option>
								</c:when>
							</c:choose>
						</select> <input type="hidden" value="${scheduledService.serviceType }" />
						<div class="invalid-feedback">Please choose a service type!</div>
					</div>

					<div class="col-lg-6 mt-3">
						<label for="validationServiceDate">Service Date</label> <input
							type="date" name="serviceDate" class="form-control"
							id="validationServiceDate" placeholder=""
							value="${scheduledService.serviceDate }" required>
						<div class="invalid-feedback">Please choose a date!</div>
					</div>

					<div class="form-group col-lg-6 mt-3">
						<label for="warranty">Warranty</label> <select
							class="custom-select" name="warranty" required>
							<c:choose>
								<c:when test="${scheduledService.warranty eq 'Yes' }">

									<option value="Yes" selected>Yes</option>
									<option value="No">No</option>
								</c:when>
								<c:when test="${scheduledService.warranty eq 'No' }">
									<option value="Yes">Yes</option>
									<option value="No" selected>No</option>
								</c:when>
							</c:choose>
						</select> <input type="hidden" value="${scheduledService.warranty }" />
						<div class="invalid-feedback">Please choose a warranty
							option!</div>
					</div>

					<div class="col-lg-6 mt-3">
						<label for="validationCustomPrice">Price</label>
						<div class="input-group">
							<input type="number" step="any" min="0" name="price"
								class="form-control" id="validationCustomPrice"
								placeholder="Set 0 if warranty is active!"
								aria-describedby="inputGroupAppend"
								value="${scheduledService.price }" required>
							<div class="input-group-append">
								<span class="input-group-text" id="inputGroupAppend">$</span>
							</div>
							<div class="invalid-feedback">Please enter the price!</div>
						</div>
					</div>

					<div class="col-lg-6 mb-4 mt-3">
						<label for="validationServiceCompany">Service Company</label> <input
							type="text" name="serviceCompany" class="form-control"
							id="validationServiceCompany" placeholder="Service Company"
							value="${scheduledService.serviceCompany }" required>
						<div class="invalid-feedback">Please enter the service
							company!</div>
					</div>
					<div class="w-100"></div>
				</div>
				<button class="btn btn-secondary" type="submit">Update</button>
			</form>
		</c:if>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/validation.js"></script>

</body>
</html>