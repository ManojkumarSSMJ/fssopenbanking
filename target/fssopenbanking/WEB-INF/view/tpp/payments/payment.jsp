<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<script type="text/javascript">

function validateOtp() {

	$("#accountNumberError").text(""); 
	$("#providerError").text(""); 
	$("#mobileNumberError").text(""); 
	$("#amountError").text("");
	 
	var accountNumber = $("#accountNumber").val();
	var provider = $("#provider").val();
	var mobileNumber = $("#mobileNumber").val();
	var amount = $("#amount").val();
	var errorCode = null;
	var numberExpression = /^[0-9]*$/;
	
	if(accountNumber == -1) {
		errorCode = 'Please select the Account Number';
		$("#accountNumberError").text(errorCode); 
		return false;
	}else if(provider == -1) {
		errorCode = 'Please select the Provider';
		$("#providerError").text(errorCode); 
		return false;
	}else if(mobileNumber == '' || mobileNumber == null) {
		errorCode = 'Mobile Number should not be empty';
		$("#mobileNumberError").text(errorCode); 
		return false;
	}
	else if (!numberExpression.test(mobileNumber.substring(1, mobileNumber.length))) {
		errorCode = 'Mobile Number should be numeric';
		$("#mobileNumberError").text(errorCode); 
		return false;
	} else if(!mobileNumber.startsWith("+1"))
	{
		errorCode = 'Mobile Number should start with +1';
		$("#mobileNumberError").text(errorCode); 
		return false;
	}
	else if(mobileNumber.length != 12)
	{
		errorCode = "Mobile Number length ahould be 12";
		$("#mobileNumberError").text(errorCode); 
		return false;
	}else if(amount == '' || amount == null) {
		errorCode = 'Amount should not be empty';
		$("#amountError").text(errorCode); 
		return false;
	}
	else if (!numberExpression.test(amount)) {
		errorCode = 'Amount should be numeric';
		$("#amountError").text(errorCode); 
		return false;
	}

	return true;
}

$(document).ready(function(){
	$("#recharge").bind('click', function(){
		
		var ValidationMessage = validateOtp();
       
		if(ValidationMessage)
		{ 
			document.paymentForm.accountNumber.value=$("#accountNumber").val();
			document.paymentForm.provider.value=$("#provider").val();
			document.paymentForm.amount.value=$("#amount").val();
			document.paymentForm.mobileNumber.value=$("#mobileNumber").val();
			document.paymentForm.submit();
		}
	});
});

</script>
</head>
<body>

<div class="container-fluid">

  <h4>Payments / Recharge</h4>	
   
<br>
<form:form method="post" action="tppRecharge" name="paymentForm">
<input type="hidden" name="accountNumber"/>
<input type="hidden" name="provider"/>
<input type="hidden" name="mobileNumber"/>
<input type="hidden" name="amount"/>
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
    <p> Please <a href="tppPayments">click here</a> to do New Recharge
  	</div>
</c:if>
<c:if test="${responseFlag eq 0}">
<div class="alert alert-danger">
		<p><strong>Oh no, your recharge failed</strong></p>
    	<br>
    	Service Provider : <c:out value="${provider}"/><br>
    	Mobile Number : <c:out value="${mobileNumber}"/><br>
    	Account Number : <c:out value="${accountNumber}"/><br>
    	Amount : <c:out value="${amount}"/><br>
    	<p> Please <a href="tppPayments">click here</a> to do New Recharge
   	</div>
</c:if>

<c:if test="${responseFlag eq 2}">
   <strong>Error!</strong> <c:out value="${responseMessage}"/> Please <a href="showBanks"><strong>Click Here</strong></a> to link your accounts with FSS Open Banking HUB. 
</c:if>
<c:if test="${responseFlag ne 1 && responseFlag ne 0 && responseFlag ne 2}">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="graybox col-lg-8 col-lg-offset-2">
				
					
						<div class="col-lg-6">
							<span class="col-lg-6"><strong>Account Number</strong><span class="must">*</span></span>
							<span class="col-lg-2"></span> <span class="col-lg-4">
							<select name="accounts" id="accountNumber" required="required">
							<option value="-1">Select</option>
							<c:forEach var="account" items="${tppPaymentsResponse.accountsResponseList}">
								<option value="<c:out value="${account.accountId}"/>|<c:out value="${account.bankName}"/>|<c:out value="${account.logoUrl}"/>|<c:out value="${account.iconUrl}"/>|<c:out value="${account.bankId}"/>"><c:out value="${account.bankName}"/> - <c:out value="${account.accountId}"/></option>
							 </c:forEach>
							</select>
							</span>
						</div>
				<div style="clear:both; height:5px"></div>
						<div class="col-lg-8">
							<span class="col-lg-6"></span>
							<span id="accountNumberError" style="color: red"></span>
						</div>
				<div style="clear:both; height:5px"></div>
					
				
						<div class="col-lg-6">
							<span class="col-lg-6"><strong>Provider</strong><span class="must">*</span></span>
							<span class="col-lg-2"></span> <span class="col-lg-4">
							<select name="provider" id="provider" required="required">
							<option value="-1">Select</option>
							<option value="airtel">Airtel</option>
							<option value="idea">Idea</option>
							<option value="jio">JIO</option>
							</select></span>
						</div>
						<div style="clear:both; height:5px"></div>
						<div class="col-lg-8">
							<span class="col-lg-6"></span>
							<span id="providerError" style="color: red"></span>
						</div>
					<div style="clear:both; height:5px"></div>
					
					
					
						<div class="col-lg-6">
							<span class="col-lg-6"><strong>Mobile Number</strong><span class="must">*</span></span>
							<span class="col-lg-2"></span> <span class="col-lg-4">
							<input class="textbox" required="required" type="text" id="mobileNumber"/></span>
						</div>
					<div style="clear:both; height:5px"></div>
						<div class="col-lg-8">
							<span class="col-lg-6"></span>
							<span id="mobileNumberError" style="color: red"></span>
						</div>
					
						<div style="clear:both; height:5px"></div>
						<div class="col-lg-6">
							<span class="col-lg-6"><strong>Amount</strong><span class="must">*</span></span>
							<span class="col-lg-2"></span> <span class="col-lg-4">
							<input class="textbox" required="required" type="text" id="amount" /></span>
						</div>
					<div style="clear:both; height:5px"></div>
						<div class="col-lg-8">
							<span class="col-lg-6"></span>
							<span id="amountError" style="color: red"></span>
						</div>
				
				<div style="clear:both; height:5px"></div>
					<p class="text-center">
						<input name="" type="button" class="btn btn-primary"
							value="Recharge" id="recharge">
					
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