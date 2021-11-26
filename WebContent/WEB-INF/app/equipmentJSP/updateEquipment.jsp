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
<title>Equipment Update</title>
</head>
<body>
	<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
	<div class="container mt-5 pt-5">
		<div class="d-flex justify-content-center">
			<h4>${equipment.name }</h4>
		</div>
		<c:if test="${not empty equipment}">
			<form class="needs-validation" novalidate method="POST"
				action="${pageContext.request.contextPath}/app/updateEquipment">
				<input type="hidden" name="id" value="${equipment.id }" />
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
						<label for="category">Category</label> <select
							class="custom-select" name="category" required>
							<c:choose>
								<c:when test="${equipment.category eq 'Neck' }">
									<option value="Neck" selected>Neck</option>
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
								</c:when>
								<c:when test="${equipment.category eq 'Shoulders' }">
									<option value="Neck">Neck</option>
									<option value="Shoulders" selected>Shoulders</option>
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
								</c:when>
								<c:when test="${equipment.category eq 'Triceps' }">
									<option value="Neck">Neck</option>
									<option value="Shoulders">Shoulders</option>
									<option value="Triceps" selected>Triceps</option>
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
								</c:when>
								<c:when test="${equipment.category eq 'Biceps' }">
									<option value="Neck">Neck</option>
									<option value="Shoulders">Shoulders</option>
									<option value="Triceps">Triceps</option>
									<option value="Biceps" selected>Biceps</option>
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
								</c:when>
								<c:when test="${equipment.category eq 'Forearms' }">
									<option value="Neck">Neck</option>
									<option value="Shoulders">Shoulders</option>
									<option value="Triceps">Triceps</option>
									<option value="Biceps">Biceps</option>
									<option value="Forearms" selected>Forearms</option>
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
								</c:when>
								<c:when test="${equipment.category eq 'Upper Back' }">
									<option value="Neck">Neck</option>
									<option value="Shoulders">Shoulders</option>
									<option value="Triceps">Triceps</option>
									<option value="Biceps">Biceps</option>
									<option value="Forearms">Forearms</option>
									<option value="Upper Back" selected>Upper Back</option>
									<option value="Mid Back">Mid Back</option>
									<option value="Lower Back">Lower Back</option>
									<option value="Chest">Chest</option>
									<option value="Abs">Abs</option>
									<option value="Gluteus">Gluteus</option>
									<option value="Quadriceps">Quadriceps</option>
									<option value="Hamstrings">Hamstrings</option>
									<option value="Calves">Calves</option>
									<option value="Cardio">Cardio</option>
								</c:when>
								<c:when test="${equipment.category eq 'Mid Back' }">
									<option value="Neck">Neck</option>
									<option value="Shoulders">Shoulders</option>
									<option value="Triceps">Triceps</option>
									<option value="Biceps">Biceps</option>
									<option value="Forearms">Forearms</option>
									<option value="Upper Back">Upper Back</option>
									<option value="Mid Back" selected>Mid Back</option>
									<option value="Lower Back">Lower Back</option>
									<option value="Chest">Chest</option>
									<option value="Abs">Abs</option>
									<option value="Gluteus">Gluteus</option>
									<option value="Quadriceps">Quadriceps</option>
									<option value="Hamstrings">Hamstrings</option>
									<option value="Calves">Calves</option>
									<option value="Cardio">Cardio</option>
								</c:when>
								<c:when test="${equipment.category eq 'Lower Back' }">
									<option value="Neck">Neck</option>
									<option value="Shoulders">Shoulders</option>
									<option value="Triceps">Triceps</option>
									<option value="Biceps">Biceps</option>
									<option value="Forearms">Forearms</option>
									<option value="Upper Back">Upper Back</option>
									<option value="Mid Back">Mid Back</option>
									<option value="Lower Back" selected>Lower Back</option>
									<option value="Chest">Chest</option>
									<option value="Abs">Abs</option>
									<option value="Gluteus">Gluteus</option>
									<option value="Quadriceps">Quadriceps</option>
									<option value="Hamstrings">Hamstrings</option>
									<option value="Calves">Calves</option>
									<option value="Cardio">Cardio</option>
								</c:when>
								<c:when test="${equipment.category eq 'Chest' }">
									<option value="Neck">Neck</option>
									<option value="Shoulders">Shoulders</option>
									<option value="Triceps">Triceps</option>
									<option value="Biceps">Biceps</option>
									<option value="Forearms">Forearms</option>
									<option value="Upper Back">Upper Back</option>
									<option value="Mid Back">Mid Back</option>
									<option value="Lower Back">Lower Back</option>
									<option value="Chest" selected>Chest</option>
									<option value="Abs">Abs</option>
									<option value="Gluteus">Gluteus</option>
									<option value="Quadriceps">Quadriceps</option>
									<option value="Hamstrings">Hamstrings</option>
									<option value="Calves">Calves</option>
									<option value="Cardio">Cardio</option>
								</c:when>
								<c:when test="${equipment.category eq 'Abs' }">
									<option value="Neck">Neck</option>
									<option value="Shoulders">Shoulders</option>
									<option value="Triceps">Triceps</option>
									<option value="Biceps">Biceps</option>
									<option value="Forearms">Forearms</option>
									<option value="Upper Back">Upper Back</option>
									<option value="Mid Back">Mid Back</option>
									<option value="Lower Back">Lower Back</option>
									<option value="Chest">Chest</option>
									<option value="Abs" selected>Abs</option>
									<option value="Gluteus">Gluteus</option>
									<option value="Quadriceps">Quadriceps</option>
									<option value="Hamstrings">Hamstrings</option>
									<option value="Calves">Calves</option>
									<option value="Cardio">Cardio</option>
								</c:when>
								<c:when test="${equipment.category eq 'Gluteus' }">
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
									<option value="Gluteus" selected>Gluteus</option>
									<option value="Quadriceps">Quadriceps</option>
									<option value="Hamstrings">Hamstrings</option>
									<option value="Calves">Calves</option>
									<option value="Cardio">Cardio</option>
								</c:when>
								<c:when test="${equipment.category eq 'Quadriceps' }">
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
									<option value="Quadriceps" selected>Quadriceps</option>
									<option value="Hamstrings">Hamstrings</option>
									<option value="Calves">Calves</option>
									<option value="Cardio">Cardio</option>
								</c:when>
								<c:when test="${equipment.category eq 'Hamstrings' }">
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
									<option value="Hamstrings" selected>Hamstrings</option>
									<option value="Calves">Calves</option>
									<option value="Cardio">Cardio</option>
								</c:when>
								<c:when test="${equipment.category eq 'Calves' }">
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
									<option value="Calves" selected>Calves</option>
									<option value="Cardio">Cardio</option>
								</c:when>
								<c:when test="${equipment.category eq 'Cardio' }">
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
									<option value="Cardio" selected>Cardio</option>
								</c:when>
								</c:choose>
						</select> <input type="hidden" value="${equipment.category }" />
						<div class="invalid-feedback">Please choose a category!</div>
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