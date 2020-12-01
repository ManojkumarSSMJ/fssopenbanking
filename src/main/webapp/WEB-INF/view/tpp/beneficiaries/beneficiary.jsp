<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript">
	$("#loading-text").text('Fetching Beneficiary Details from Bank');
</script>
<script type="text/javascript">
function transferFund(beneficiaryName, remitterAccountNumber,beneficiaryAccountNumber,ifscCode,clientId) {
	document.fundTransfer.beneficiaryName.value=beneficiaryName;
	document.fundTransfer.remitterAccountNumber.value=remitterAccountNumber;
	document.fundTransfer.beneficiaryAccountNumber.value=beneficiaryAccountNumber;
	document.fundTransfer.ifscCode.value=ifscCode;
	document.fundTransfer.clientId.value=clientId;
	document.fundTransfer.submit();
}
</script>
</head>
<body>
<form:form name="fundTransfer" action="tppTransfer" method="post">
<input type="hidden" name="beneficiaryName"/>
<input type="hidden" name="remitterAccountNumber"/>
<input type="hidden" name="beneficiaryAccountNumber"/>
<input type="hidden" name="ifscCode"/>
<input type="hidden" name="clientId"/>
</form:form>
	<div class="container-fluid">

		<h4>Beneficiary</h4>

		<br>
		
		<c:if test="${responseFlag eq 1}">
<div class="alert alert-success">
    	<strong>Success!</strong> <c:out value="${responseMessage}"/>
  	</div>
</c:if>
		<div class="row">

			<c:if test="${not empty tppBeneficiaryResponse.beneficiaryDetails}">
						<table
							class="table table-hover table-striped table-bordered mpayTable">
							<thead>
								<tr>
									<td>Remitter Account Number</td>
									<td>Beneficiary Name</td>
									<td>Beneficiary Account Number</td>
									<td>IFSC Code</td>
									<td>Action</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="beneficiaryObject"
									items="${tppBeneficiaryResponse.beneficiaryDetails}">
									<tr>
										<td><c:out
												value="${beneficiaryObject.remitterMaskAccountNumber}" /></td>
										<td><c:out value="${beneficiaryObject.beneficiaryName}" /></td>
										<td><c:out
												value="${beneficiaryObject.beneficiaryMaskAccountNumber}" /></td>
										<td><c:out value="${beneficiaryObject.ifscCode}" /></td>
										<td><a href="javascript:transferFund('<c:out value="${beneficiaryObject.beneficiaryName}" />','<c:out value="${beneficiaryObject.remitterAccountNumber}"/>','<c:out value="${beneficiaryObject.beneficiaryAccountNumber}"/>','<c:out value="${beneficiaryObject.ifscCode}"/>', '<c:out value="${beneficiaryObject.clientId}"/>')" class="textLink1">Transfer Fund</a> </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
			</c:if>
			<div style="clear: both"></div>
			<c:if test="${not empty tppBeneficiaryResponse.beneficiaryDetails}">
				<div class="col-lg-9 col-lg-offset-3">
					<a href="showBanks"><strong>Click Here</strong></a> to link other
					bank accounts with FSS Open Banking HUB.
				</div>
			</c:if>
			
			<c:if test="${empty tppBeneficiaryResponse.beneficiaryDetails}">
			<div class="alert alert-danger">
    			<strong>Error!</strong> <c:out value="${tppBeneficiaryResponse.responseMessage}"/> Please <a href="showBanks"><strong>Click Here</strong></a> to update your account consent with FSS Open Banking HUB. 
  			</div>
			</c:if>
		</div>
	</div>
</body>
</html>