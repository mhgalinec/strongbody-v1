<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Create New Member</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
	<div class="container mt-5 pt-5">
	<div class="d-flex justify-content-center"><h4>Add New Member</h4></div>
		<form class="needs-validation" novalidate method="POST"
			action="${pageContext.request.contextPath}/app/createMember">
			<div class="form-row">

				<div class="col-lg-6 mb-3 mt-3">
					<label for="validationName">Full Name</label> <input type="text"
						name="fullName" class="form-control" id="validationName"
						placeholder="Full Name" value="${member.fullName }" required>
					<div class="invalid-feedback">Please enter a name!</div>
				</div>
				<div class="col-lg-6 mb-3 mt-3">
					<label for="validationDOB">Date of Birth</label> <input type="date"
						name="dateOfBirth" class="form-control" id="validationDOB"
						placeholder="" value="${member.dateOfBirth }" required>
					<div class="invalid-feedback">Please choose a date!</div>
				</div>
				<div class="col-lg-6 mb-3">
					<label for="validationCustomEmail">Email</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroupPrepend">@</span>
						</div>
						<input type="email" name="email" class="form-control"
							id="validationCustomEmail" placeholder="Email"
							aria-describedby="inputGroupPrepend" value="${member.email }"
							required>
						<div class="invalid-feedback">Please enter an Email.</div>
					</div>
				</div>

				<div class="col-lg-6 mb-3">
					<label for="validationContact">Contact Number - Example:
						+123456789</label> <input type="tel" name="contactNumber"
						class="form-control" id="validationContact"
						placeholder="Contact Number" value="${member.contactNumber }"
						pattern="[+]{1}[0-9]{4,16}" required>
					<div class="invalid-feedback">Please provide a valid contact
						number.</div>
				</div>
			</div>

			<div class="custom-control custom-radio">
				<input type="radio" name="sex" class="custom-control-input"
					id="customControlValidationM" name="radio-stacked" value="M"
					required> <label class="custom-control-label"
					for="customControlValidationM">M</label>
			</div>
			<div class="custom-control custom-radio mb-3">
				<input type="radio" name="sex" class="custom-control-input"
					id="customControlValidationF" name="radio-stacked" value="F"
					required> <label class="custom-control-label"
					for="customControlValidationF">F</label>
				<div class="invalid-feedback">Please choose a sex!</div>
			</div>

			<div class="form-row">
				<div class="form-group col-lg-6">
				<label for="diet">Diet</label>
					<select class="custom-select" name="diet" required>
						<option value="">Diet</option>
						<option value="Vegetarian">Vegetarian</option>
						<option value="Omnivore">Omnivore</option>
						<option value="Vegan">Vegan</option>
						<option value="Paleo">Paleo</option>
					</select> <input type="hidden" value="${member.diet }" />
					<div class="invalid-feedback">Please choose a diet!</div>
				</div>
			</div>

			<button class="btn btn-secondary" type="submit">Submit form</button>
		</form>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>

</body>
</html>