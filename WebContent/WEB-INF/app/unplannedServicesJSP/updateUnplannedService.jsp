<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<c:if test="${not empty unplannedService }">
		<div class="d-flex justify-content-center"><h4>${equipmentName }</h4></div>
		<form class="needs-validation" novalidate method="POST"
			action="${pageContext.request.contextPath}/app/updateUnplannedService">
			<input type="hidden" name="id" value="${unplannedService.id }" />
			<div class="form-row ">
				<div class="col-lg-6 mb-3 mt-3">
					<label for="validationFaultType">Fault Type</label> <input
						type="text" name="faultType" class="form-control"
						id="validationFaultType" placeholder="Fault Type"
						value="${unplannedService.faultType }" required>
					<div class="invalid-feedback">Please enter the fault type!</div>
				</div>

				<div class="col-lg-6 mb-3 mt-3">
					<label for="validationFaultDate">Fault Date</label> <input
						type="date" name="dateOfFault" class="form-control"
						id="validationFaultDate" placeholder=""
						value="${unplannedService.dateOfFault }" required>
					<div class="invalid-feedback">Please choose a date!</div>
				</div>

				<div class="form-group col-lg-6 mt-3">
				<label for="warranty">Warranty</label>
					<select class="custom-select" name="warranty" required>
						<c:choose>
								<c:when test="${unplannedService.warranty eq 'Yes' }">

									<option value="Yes" selected>Yes</option>
									<option value="No">No</option>
								</c:when>
								<c:when test="${unplannedService.warranty eq 'No' }">
									<option value="Yes">Yes</option>
									<option value="No" selected>No</option>
								</c:when>
							</c:choose>
					</select> <input type="hidden" value="${unplannedService.warranty }" />
					<div class="invalid-feedback">Please choose a warranty
						option!</div>
				</div>
				<div class="w-100"></div>

				<div class="col-lg-6 mb-3">
					<label for="validationCustomPrice">Price</label>
					<div class="input-group">
						<input type="number" step="any" min="0" name="price"
							class="form-control" id="validationCustomPrice"
							placeholder="Set 0 if warranty is active!"
							aria-describedby="inputGroupAppend"
							value="${unplannedService.price }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">$</span>
						</div>
						<div class="invalid-feedback">Please enter the price!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationServiceCompany">Service Company</label> <input
						type="text" name="serviceCompany" class="form-control"
						id="validationServiceCompany" placeholder="Service Company"
						value="${unplannedService.serviceCompany }" required>
					<div class="invalid-feedback">Please enter the service
						company!</div>
				</div>
			</div>
			<button class="btn btn-secondary" type="submit">Update</button>
		</form>
		</c:if>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>

</body>
</html>