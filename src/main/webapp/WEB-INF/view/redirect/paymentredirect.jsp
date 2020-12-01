<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<script type="text/javascript">
$("#loading-text").text('Redirecting you to '+'<c:out value="${bankName}"/>');	
</script>
<script type="text/javascript">

	function callThisOnLoad()
	{
		
		setTimeout(function(){
			//$('#loading').hide();
			$('#loading').show();
			setTimeout(function(){
			//document.redirectPage.action = document.redirectPage.url.value ;
			document.redirectPage.submit();}, 3000)
		}, 800);
		
	}
</script>
<script type="text/javascript">
$(document).ready(function(){
	$('#loading').hide();
	
});
</script>


<script>
javascript:callThisOnLoad();
</script>
</head>
<body>

<form:form name="redirectPage" method="post" action="tppRedirect">
	<input type="hidden" name="bankName" value="<c:out value="${bankName}"/>"/>
	<input type="hidden" name="accountNumber" value="<c:out value="${accountNumber}"/>"/>
	<input type="hidden" name="userId" value="<c:out value="${userId}"/>"/>
	<input type="hidden" name="provider" value="<c:out value="${provider}"/>"/>
	<input type="hidden" name="mobileNumber" value="<c:out value="${mobileNumber}"/>"/>
	<input type="hidden" name="amount" value="<c:out value="${amount}"/>"/>
	<input type="hidden" name="moduleName" value="<c:out value="${moduleName}"/>"/>
	<input type="hidden" name="logoUrl" value="<c:out value="${logoUrl}"/>"/>
	<input type="hidden" name="iconUrl" value="<c:out value="${iconUrl}"/>"/>
	<input type="hidden" name="bankId" value="<c:out value="${bankId}"/>"/>
	<input type="hidden" name="transactionId" value="<c:out value="${transactionId}"/>"/>
	
</form:form>

<div class="container">
<div class="row">



	<div class="col-lg-8 col-lg-offset-2 text-center padding_up" style="padding-top:5%;">
	<h4>Transferring you to <c:out value="${bankName}"/></h4>
	<p>You are now leaving FSS Open Banking HUB and we are securely transferring you over to <c:out value="${bankName}"/></p>
	
	</div>

</div>
</div>
</body>
</html>