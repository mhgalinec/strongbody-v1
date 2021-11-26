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
<title>Update Measurements</title>
</head>
<body>
<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
	<div class="container mt-5 pt-5">
	<c:if test="${not empty measurements}">
		<div class="d-flex justify-content-center"><h4>${member }</h4></div>
		<form class="needs-validation" novalidate method="POST"
			action="${pageContext.request.contextPath }/app/updateMeasurements">
			<input type="hidden" name="id" value="${measurements.id }"/>

			<div class="form-row">
				<div class="col-lg-6 mb-4 mt-3">
					<label for="validationDate">Measurement Date</label> <input
						type="date" name="measurementDate" class="form-control"
						id="validationDate" placeholder=""
						value="${measurements.measurementDate }" required>
					<div class="invalid-feedback">Please choose a date!</div>
				</div>

				<div class="col-lg-6 mb-4 mt-3">
					<label for="validationCustomHeight">Height</label>
					<div class="input-group">
						<input type="number" name="height" step="any" min="0"
							class="form-control" id="validationCustomHeight"
							placeholder="Height" aria-describedby="inputGroupAppend"
							value="${measurements.height }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">cm</span>
						</div>
						<div class="invalid-feedback">Please enter the width!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationCustomWeight">Weight</label>
					<div class="input-group">
						<input type="number" name="weight" step="any" min="0"
							class="form-control" id="validationCustomHeigth"
							placeholder="Weight" aria-describedby="inputGroupAppend"
							value="${measurements.weight }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">kg</span>
						</div>
						<div class="invalid-feedback">Please enter the weight!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationCustomBodyFat">Body Fat</label>
					<div class="input-group">
						<input type="number" name="bodyFat" step="any" min="0"
							class="form-control" id="validationCustomBodyFat"
							placeholder="Body Fat" aria-describedby="inputGroupAppend"
							value="${measurements.bodyFat }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">%</span>
						</div>
						<div class="invalid-feedback">Please enter a body fat
							percentage!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationCustomShoulders">Shoulders</label>
					<div class="input-group">
						<input type="number" name="shoulders" step="any" min="0"
							class="form-control" id="validationCustomShoulders"
							placeholder="Shoulder Circumference"
							aria-describedby="inputGroupAppend"
							value="${measurements.shoulders }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">cm</span>
						</div>
						<div class="invalid-feedback">Please enter a circumference!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationCustomTorso">Torso</label>
					<div class="input-group">
						<input type="number" name="torso" step="any" min="0"
							class="form-control" id="validationCustomTorso"
							placeholder="Torso Circumference"
							aria-describedby="inputGroupAppend"
							value="${measurements.torso }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">cm</span>
						</div>
						<div class="invalid-feedback">Please enter a circumference!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationCustomWaist">Waist</label>
					<div class="input-group">
						<input type="number" name="waist" step="any" min="0"
							class="form-control" id="validationCustomWaist"
							placeholder="Waist Circumference"
							aria-describedby="inputGroupAppend"
							value="${measurements.waist }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">cm</span>
						</div>
						<div class="invalid-feedback">Please enter a circumference!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationCustomUpperArm">Upper Arm</label>
					<div class="input-group">
						<input type="number" name="upperArm" step="any" min="0"
							class="form-control" id="validationCustomUpperArm"
							placeholder="Upper Arm Circumference"
							aria-describedby="inputGroupAppend"
							value="${measurements.upperArm }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">cm</span>
						</div>
						<div class="invalid-feedback">Please enter a circumference!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationCustomLowerArm">Lower Arm</label>
					<div class="input-group">
						<input type="number" name="lowerArm" step="any" min="0"
							class="form-control" id="validationCustomLowerArm"
							placeholder="Lower Arm Circumference"
							aria-describedby="inputGroupAppend"
							value="${measurements.lowerArm }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">cm</span>
						</div>
						<div class="invalid-feedback">Please enter a circumference!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationCustomUpperLeg">Upper Leg</label>
					<div class="input-group">
						<input type="number" name="upperLeg" step="any" min="0"
							class="form-control" id="validationCustomUpperLeg"
							placeholder="Upper Leg Circumference"
							aria-describedby="inputGroupAppend"
							value="${measurements.upperLeg }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">cm</span>
						</div>
						<div class="invalid-feedback">Please enter a circumference!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationCustomLowerLeg">Lower Leg</label>
					<div class="input-group">
						<input type="number" name="lowerLeg" step="any" min="0"
							class="form-control" id="validationCustomLowerLeg"
							placeholder="Lower Leg Circumference"
							aria-describedby="inputGroupAppend"
							value="${measurements.lowerLeg }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">cm</span>
						</div>
						<div class="invalid-feedback">Please enter a circumference!</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">
					<label for="validationCustomHeartRate">Resting Heart Rate</label>
					<div class="input-group">
						<input type="number" name="restingHeartRate" step="any" min="0"
							class="form-control" id="validationCustomHeartRate"
							placeholder="Resting Heart Rate"
							aria-describedby="inputGroupAppend"
							value="${measurements.restingHeartRate }" required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">bpm</span>
						</div>
						<div class="invalid-feedback">Please enter a circumference!</div>
					</div>
				</div>
			</div>
			<button class="btn btn-secondary" type="submit">Update</button>
		</form>
		</c:if>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>

</body>
</html>