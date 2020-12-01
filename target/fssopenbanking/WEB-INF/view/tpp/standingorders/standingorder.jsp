<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript">
	$("#loading-text").text('Fetching Standing Orders from Bank');
</script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="container-fluid">

		<h4>Standing Order</h4>

		<br>
		<div class="row">

			<c:if test="${not empty tppStandingOrderResponse.standingOrderDetailList}">
						<table
							class="table table-hover table-striped table-bordered mpayTable">
							<thead>
								<tr>
									<td>Standing Order Id</td>
									<td>Remitter Account Id</td>
									<td>Frequency</td>
									<td>Beneficiary Name</td>
									<c:forEach var="standingOrderIdentifier" items="${accountsObject.standingOrderIdentifications}">
										<td>Beneficiary <c:out value = "${standingOrderIdentifier.type}" /></td>
									</c:forEach>
									<td>First Payment Date</td>
									<td>First Payment Amount</td>
									<td>Next Payment Date</td>
									<td>Next Payment Amount</td>
									<td>Final Payment Date</td>
									<td>Final Payment Amount</td>
									<td>Status</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="standingOrderObject"
									items="${tppStandingOrderResponse.standingOrderDetailList}">
									<tr>
										<td><c:out
												value="${standingOrderObject.standingOrderId}" /></td>
										<td><c:out value="${standingOrderObject.maskAccountId}" /></td>
										<td><c:out
												value="${standingOrderObject.frequency}" /></td>
										<td><c:out value="${standingOrderObject.beneficiaryName}" /></td>
										<c:forEach var="standingOrderIdentifier" items="${accountsObject.identification}">
										<td><c:out value = "${standingOrderIdentifier.standingOrderId}" /></td>
										</c:forEach>									
										<td><c:out value="${standingOrderObject.firstPaymentDate}" /></td>
										<td><c:out value="${standingOrderObject.firstPaymentAmount}" /></td>
										<td><c:out value="${standingOrderObject.nextPaymentDate}" /></td>
										<td><c:out
												value="${standingOrderObject.nextPaymentAmount}" /></td>
										<td><c:out value="${standingOrderObject.finalPaymentDate}" /></td>
										<td><c:out
												value="${standingOrderObject.finalPaymentAmount}" /></td>
										<td><c:out value="${standingOrderObject.standingOrderStatus}" /></td>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
			</c:if>
			<div style="clear: both"></div>
			<c:if test="${not empty tppStandingOrderResponse.standingOrderDetailList}">
				<div class="col-lg-9 col-lg-offset-3">
					<a href="showBanks"><strong>Click Here</strong></a> to link other
					bank accounts with FSS Open Banking HUB.
					<br>
					<a href="tppAccounts"><strong>Click Here</strong></a> to go Accounts page.
				</div>
			</c:if>
			
			<c:if test="${empty tppStandingOrderResponse.standingOrderDetailList}">
			<div class="alert alert-danger">
    			<strong>Error!</strong> <c:out value="${tppStandingOrderResponse.responseMessage}"/> Please <a href="tppAccounts"><strong>Click Here</strong></a> to update your account standing order details with FSS Open Banking HUB. 
  			</div>
			</c:if>
		
		</div>
	</div>
</body>
</html>