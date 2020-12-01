<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<script type="text/javascript">
$("#loading-text").text('Fetching Transaction Details from Bank');
</script>
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
</head>
<body>

<div class="container-fluid">
   
   <h4>Transactions</h4>	
   
<br>
<div class="row">
<c:if test="${tppTransactionResponse.responseFlag eq 0}">
<div class="alert alert-danger">
<strong>Error!</strong> <c:out value="${tppTransactionResponse.responseMessage}"/> Please <a href="tppAccounts"><strong>Click Here</strong></a> to update your account transaction details with FSS Open Banking HUB.  	</div>
</c:if>

<div style="clear: both"></div>
<c:if test="${not empty tppTransactionResponse.transactionDetailList}">
	<table class="table table-hover table-striped table-bordered mpayTable">
	<thead>
	<tr>
	<td>Bank Name</td>
	<td>Account Number</td>
	<td>Transaction Id</td>
	<td>Transaction Reference</td>
	<td>Transaction Date Time</td>
	<td>Transaction Amount</td>
	<td>Currency</td>
	<td>Transaction Status</td>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="transactionObject" items="${tppTransactionResponse.transactionDetailList}">
	<tr>
	<td><c:out value="${transactionObject.bankName}"/></td>
	<td><c:out value="${transactionObject.maskAccountNumber}"/></td>
	<td><c:out value="${transactionObject.transactionId}"/></td>
	<td><c:out value="${transactionObject.transactionReference}"/></td>
	<td><c:out value="${transactionObject.transactionDateTime}"/></td>
	<td><c:out value="${transactionObject.amount}"/></td>
	<td><c:out value="${transactionObject.currency}"/></td>
	<td><c:out value="${transactionObject.transactionStatus}"/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
</c:if>
<div style="clear: both"></div>
	<c:if test="${not empty tppTransactionResponse.transactionDetailList}">
		<div class="col-lg-9 col-lg-offset-3">
			<a href="showBanks"><strong>Click Here</strong></a> to link other bank accounts with FSS Open Banking HUB.
			<br>
			<a href="tppAccounts"><strong>Click Here</strong></a> to go Accounts page.
		</div>
	</c:if>
</div>
</div>
</body>
</html>