<!-- Add support for JSTL Core tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ---------------------- -->

<!DOCTYPE html>
<html>

<head>
	<title>Single Customer View</title>	
	
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
	
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/view.css"/>
	
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="view-container"">
		<div id="page-title-header">
			<h3>Customer Information Page</h3>
		</div>
		<div id="content">
			<section>
				<h4 class="title">Full Name:</h4>
				<p>${customers.firstName}  ${customer.lastName}</p>
			</section>
			<section>
				<h4 class="title">Email Address:</h4>
				<p>${customers.email}
			</section>
			<section>
				<h4 class="title">Age:</h4>
				<p>${customers.customerDetail.age}</p>
			</section>
			<section>
				<h4 class="title">Hobby:</h4>
				<p>${customers.customerDetail.hobby}</p>
			</section>
		</div>
	</div>
	
	<a id="viewPage-back-to-list" href="${pageContext.request.contextPath}/customer/list">Back to List</a>

</body>

</html>