<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Menu</title>
</head>
<body>
<input type="checkbox" id="check">
<label for="check">
<i class="fas fa-bars" id="btn"></i>
<i class="fas fa-times" id="cancel"></i>
</label>
	<div class="sidebar">
		<header>StrongBody</header>
		<ul>
			<li><a href="${pageContext.request.contextPath}/app/memberList"><i class="fas fa-list-alt"></i>Member List</a></li>
			<li><a href="${pageContext.request.contextPath}/app/equipmentList"><i class="fas fa-dumbbell"></i>Equipment List</a></li>
			<li><a href="${pageContext.request.contextPath}/app/createMember"><i class="fas fa-user-plus"></i>Add New Member</a></li>	
			<li><a href="${pageContext.request.contextPath}/app/createEquipment"><i class="fas fa-plus"></i>Add New Equipment</a></li>
			<li><a href="${pageContext.request.contextPath}/app/activeMembership"><i class="fas fa-stopwatch"></i></i>Active Memberships</a></li>
			<li><a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i>Sign Out</a></li>
			
		</ul>
	</div>
	<section></section>

</body>
</html>