<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript">
	$("#loading-text").text('Fetching Consent Details');
</script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="container-fluid">

		<h4>Show Consent Details</h4>

		<br>
		<div class="row">

		<table
			class="table table-hover table-striped table-bordered mpayTable">
			<thead>
				<tr>
					<td>Bank Name</td>
					<td>User Name</td>
					<td>Consent Id</td>
					<td>Created Date</td>
					<td>Expiry Date</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="consentDetails" items="${showConsentDetails}">
					<tr>
						<td><c:out value="${consentDetails.bankName}" /></td>
						<td><c:out value="${consentDetails.userName}" /></td>
						<td><c:out value="${consentDetails.consentId}" /></td>
						<td><c:out value="${consentDetails.createdDate}" /></td>
						<td><c:out value="${consentDetails.expiryDate}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>