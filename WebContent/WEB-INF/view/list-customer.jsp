<!-- Add support for JSTL Core tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ---------------------- -->
<!-- Spring forms tag -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- ---------------------- -->
<!DOCTYPE html>
<html>

<head>

	<title>List Customers</title>
	
	<!-- reference our style sheet -->
	
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
			
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- Add Button: Add customer -->
			
			<input type="button" value="Add Customer"
					onclick="window.location.href='showFormForAdd'; return false;"
					class="add-button"
			/>
			
			<!-- Add form for searching a customer -->
			<div id="search-header">
			<form:form action="search" method="GET">
				<label>Search customer:</label>
				<input type="text" name="theSearchName" required/>
				<input type="submit" value="Search" class="search-button"/>
				<a id="reset-search" href="${pageContext.request.contextPath}/customer/list">reset search</a>
			</form:form>
			</div>
			
			
			<!--  add our html table here -->
			
			<table>
			
				<tr>					
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- Loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${customers}">
				
					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url>
								
					<!-- Construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url>
					<tr>
						<td> ${tempCustomer.firstName} </td>
						<!-- It will call tempCustomer.getFirstName() for us-->
						<td> ${tempCustomer.lastName} </td>
						<td> ${tempCustomer.email} </td>
						
						<!-- Display the update and delete link -->
						<td>
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>
					</tr>
				
				</c:forEach>
			
			</table>
		
		</div>
	
	</div>


</body>

</html>