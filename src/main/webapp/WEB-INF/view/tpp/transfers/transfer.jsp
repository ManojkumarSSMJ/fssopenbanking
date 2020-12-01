<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	$("#transfer").bind('click', function(){
		if(validateAmount() && validateRemarks()) {
				document.fundTransfer.remitterAccountNumber.value=$("#remitterAccountNumber").val();
				document.fundTransfer.beneficiaryAccountNumber.value=$("#beneficiaryAccountNumber").val();
				document.fundTransfer.ifscCode.value=$("#ifscCode").val();
				document.fundTransfer.beneficiaryName.value=$("#beneficiaryName").val();
				document.fundTransfer.amount.value=$("#amount").val();
				document.fundTransfer.remarks.value=$("#remarks").val();
				document.fundTransfer.submit();
		} else {
			validateAmount();
			validateRemarks();
		}
		
	});
	
	function validateAmount() {
		var amount = $("#amount").val();
		if(amount == '') {
			$("#amountError").text('Please enter the amount');
			return false;
		}
		$("#amountError").text('');
		return true;
	}
	
	function validateRemarks() {
		var remarks = $("#remarks").val();
		if(remarks == '') {
			$("#remarksError").text('Please enter the remarks');
			return false;
		}
		$("#remarksError").text('');
		return true;
	}
});

</script>
</head>
<body>

<div class="container-fluid">

  <h4>Fund Transfer</h4>	
   
<br>
<form:form method="post" action="submitFundTransfer" name="fundTransfer">
<input type="hidden" name="remitterAccountNumber"/>
<input type="hidden" name="beneficiaryAccountNumber"/>
<input type="hidden" name="ifscCode"/>
<input type="hidden" name="clientId" value="<c:out value="${hubTransferRequest.clientId}"/>"/>
<input type="hidden" name="beneficiaryName"/>
<input type="hidden" name="amount"/>
<input type="hidden" name="remarks"/>
</form:form>
<c:if test="${responseFlag eq 1}">
<div class="alert alert-success">
<p><strong>Recharge has been completed successfully</strong></p>
    <br>
    	Transaction Id : <c:out value="${transactionId}"/><br>
    	Service Provider : <c:out value="${provider}"/><br>
    	Mobile Number : <c:out value="${mobileNumber}"/><br>
    	Account Number : <c:out value="${accountNumber}"/><br>
    	Amount : <c:out value="${amount}"/><br>
    <p> Please <a href="hubPayments">click here</a> to do New Recharge
  	</div>
</c:if>
<c:if test="${responseFlag eq 0}">
<div class="alert alert-danger">
    	<strong>Error!</strong> <c:out value="${responseMessage}"/> Please <a href="showBanks"><strong>Click Here</strong></a> to link your accounts with FSS Open Banking HUB. 
  	</div>
</c:if>

<c:if test="${responseFlag ne 1}">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="graybox col-lg-8 col-lg-offset-2">
				
					
						<div class="col-lg-6">
							<span class="col-lg-6"><strong>Remitter Account Number</strong><span class="must">*</span></span>
							<span class="col-lg-2"></span> <span class="col-lg-4">
							<input type="text" id="remitterAccountNumber" value="<c:out value="${hubTransferRequest.remitterAccountNumber}"/>" readonly="readonly"/>
							
							</span>
						</div>
				<div style="clear:both; height:5px"></div>
					
				
						<div class="col-lg-6">
							<span class="col-lg-6"><strong>Beneficiary Name</strong><span class="must">*</span></span>
							<span class="col-lg-2"></span> <span class="col-lg-4">
							<input type="text" id="beneficiaryName" value="<c:out value="${hubTransferRequest.beneficiaryName}"/>"/>
							</span>
						</div>
					<div style="clear:both; height:5px"></div>
					
					
					
						<div class="col-lg-6">
							<span class="col-lg-6"><strong>Beneficiary Account Number</strong><span class="must">*</span></span>
							<span class="col-lg-2"></span> <span class="col-lg-4">
							<input type="text" id="beneficiaryAccountNumber" value="<c:out value="${hubTransferRequest.beneficiaryAccountNumber}"/>" readonly="readonly"/></span>
						</div>
				
				<div style="clear:both; height:5px"></div>
					
					
					
						<div class="col-lg-6">
							<span class="col-lg-6"><strong>IFSC Code</strong><span class="must">*</span></span>
							<span class="col-lg-2"></span> <span class="col-lg-4">
							<input type="text" id="ifscCode" value="<c:out value="${hubTransferRequest.ifscCode}"/>" readonly="readonly"/></span>
						</div>
					
						<div style="clear:both; height:5px"></div>
						<div class="col-lg-6">
							<span class="col-lg-6"><strong>Amount</strong><span class="must">*</span></span>
							<span class="col-lg-2"></span> <span class="col-lg-4"><input
								class="textbox"  type="text" id="amount" value="" /></span>
						</div>
						<br><br>
						<span class="col-lg-offset-4 red" id="amountError" class="red"></span>
						
						<div style="clear:both; height:5px"></div>
						<div class="col-lg-6">
							<span class="col-lg-6"><strong>Remarks</strong><span class="must">*</span></span>
							<span class="col-lg-2"></span> <span class="col-lg-4"><input
								class="textbox"  type="text" id="remarks" value="" /></span>
						</div>
							<br><br>
						<span  class="col-lg-offset-4 red" id="remarksError"></span>
						
				<div style="clear:both; height:5px"></div>
					<p class="text-center">
						<input name="" type="button" class="btn btn-primary"
							value="Transfer" id="transfer">
					
						&nbsp;&nbsp;&nbsp; <input name="" type="reset"
							class="btn btn-primary gray-btn" id="cancel"
							value="Cancel">
					</p>
				</div>
			
			</div>
		</div>
	</div>
	</c:if>
</div>
</body>
</html>