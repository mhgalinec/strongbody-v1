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
<title>Create Equipment</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
	<div class="container mt-5 pt-5">
	<div class="d-flex justify-content-center"><h4>Add New Equipment</h4></div>
		<form class="needs-validation" novalidate method="POST"
			action="${pageContext.request.contextPath}/app/createEquipment">
			<div class="form-row">

				<div class="col-lg-6 mb-3 mt-3">
					<label for="validationEquipment">Equipment Name</label> <input
						type="text" name="name" class="form-control"
						id="validationEquipment" placeholder="Equipment Name"
						value="${equipment.name }" required>
					<div class="invalid-feedback">Please enter a name!</div>
				</div>
				<div class="col-lg-6 mb-3 mt-3">
					<label for="validationSerial">Serial Number</label> <input
						type="text" name="serialNo" class="form-control"
						id="validationSerial" placeholder="Serial Number"
						value="${equipment.serialNo }" required>
					<div class="invalid-feedback">Please enter a serial number!</div>
				</div>
				<div class="col-lg-6 mb-3">
					<label for="validationManufacturer">Manufacturer</label> <input
						type="text" name="manufacturer" class="form-control"
						id="validationManufacturer" placeholder="Manufacturer"
						value="${equipment.manufacturer }" required>
					<div class="invalid-feedback">Please enter the manufacturer!</div>
				</div>
				<div class="col-lg-6 mb-3">
					<label for="validationCustomWidth">Width</label>
					<div class="input-group">
						<input type="number" name="width" class="form-control"
							id="validationCustomWidth" placeholder="Width"
							aria-describedby="inputGroupAppend" value="${equipment.width }"
							required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">mm</span>
						</div>
						<div class="invalid-feedback">Please enter the width!</div>
					</div>
				</div>
				<div class="col-lg-6 mb-3">
					<label for="validationCustomLength">Length</label>
					<div class="input-group">
						<input type="number" name="length" class="form-control"
							id="validationCustomLength" placeholder="Length"
							aria-describedby="inputGroupAppend" value="${equipment.length }"
							required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">mm</span>
						</div>
						<div class="invalid-feedback">Please enter the length!</div>
					</div>
				</div>
				<div class="col-lg-6 mb-3">
					<label for="validationCustomHeigth">Heigth</label>
					<div class="input-group">
						<input type="number" name="heigth" class="form-control"
							id="validationCustomHeigth" placeholder="Heigth"
							aria-describedby="inputGroupAppend" value="${equipment.heigth }"
							required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">mm</span>
						</div>
						<div class="invalid-feedback">Please enter the heigth!</div>
					</div>
				</div>
				<div class="col-lg-6 mb-3">
					<label for="validationCustomWeight">Weight</label>
					<div class="input-group">
						<input type="number" name="weight" class="form-control"
							id="validationCustomHeigth" placeholder="Weight"
							aria-describedby="inputGroupAppend" value="${equipment.weight }"
							required>
						<div class="input-group-append">
							<span class="input-group-text" id="inputGroupAppend">kg</span>
						</div>
						<div class="invalid-feedback">Please enter the weight!</div>
					</div>
				</div>
				<div class="w-100"></div>
				<div class="form-group col-lg-12 mt-3 mb-4">
				<label for="category">Category</label>
					<select class="custom-select" name="category" required>
						<option value="">Category</option>
						<option value="Neck">Neck</option>
						<option value="Shoulders">Shoulders</option>
						<option value="Triceps">Triceps</option>
						<option value="Biceps">Biceps</option>
						<option value="Forearms">Forearms</option>
						<option value="Upper Back">Upper Back</option>
						<option value="Mid Back">Mid Back</option>
						<option value="Lower Back">Lower Back</option>
						<option value="Chest">Chest</option>
						<option value="Abs">Abs</option>
						<option value="Gluteus">Gluteus</option>
						<option value="Quadriceps">Quadriceps</option>
						<option value="Hamstrings">Hamstrings</option>
						<option value="Calves">Calves</option>
						<option value="Cardio">Cardio</option>
					</select> <input type="hidden" value="${equipment.category }" />
					<div class="invalid-feedback">Please choose a category!</div>
				</div>
			</div>
			<button class="btn btn-secondary" type="submit">Submit form</button>
		</form>
	</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>

</body>
</html>