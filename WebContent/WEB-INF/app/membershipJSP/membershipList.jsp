<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.strongbody.beans.MembershipPrice" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Membership List</title>
</head>
<body>
<jsp:include page="/WEB-INF/navbar.jsp"></jsp:include>
<jsp:useBean id="membershipFee" class="com.strongbody.beans.MembershipPrice"/>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Registration Date</th>
				<th>Valid From</th>
				<th>Valid Through</th>
				<th>Service Level</th>
				<th>Active For</th>
				<th>Membership Type</th>
				<th>Payment Status</th>
				<th>Member Name</th>
			</tr>
			
			<c:forEach items="${membershipList}" var="membership">
			
			<fmt:formatDate var="time" value="${membership.validThrough }" pattern="yyyy-MM-dd"/>
			<tr>
				<td>${membership.id }</td>
				<td>${membership.registrationDate }</td>
				<td>${membership.validFrom }</td>
				<td>${membership.validThrough }</td>
				<td>${membership.serviceLevel }</td>
				<td id="${membership.id }"></td>
				<td>${membership.membershipType }</td>
				<td>${membership.paymentStatus }</td>
				<td>${membership.memberName }</td>
				<td>
					<a href="deleteMembership?id=${membership.id }">Delete</a>
				</td>
			</tr>	
			
			<script type="text/javascript">
			
			var countDate = new Date("${time}").getTime();

			function isActive(){
				var now = new Date().getTime();
				var day = 1000*60*60*24;
				var hour = 1000*60*60;
					
				gap = countDate - now + day;

				var d = Math.floor(gap / day);
				var h = Math.floor((gap % day) / hour);
				
				
				if(d==0){
					document.getElementById('${membership.id}').innerHTML = h + ' hours';
					if(h <= 1){
						document.getElementById('${membership.id}').innerHTML = 'Less than an hour';
					}
				}else if(d<0){
					document.getElementById('${membership.id}').innerHTML ='Expired';
				}else if(d == 1){
					document.getElementById('${membership.id}').innerHTML = d + ' day';
				}else{
					document.getElementById('${membership.id}').innerHTML = d + ' days';
				}	

			}
			isActive();
			</script>
			</c:forEach>	
		</table>
	
		<%-- href used when the file is in the same folder and forwarded by a servlet --%>
		<a href="createMembership">Create Membership</a>
		
		<%-- href used when the file is outside WEB-INF folder --%>
		<a href="${pageContext.request.contextPath}/menu.jsp">Menu1</a>
		<a href="menu">Menu</a>

		<a href="activeMembership">Active Membership</a>
	
</body>
</html>