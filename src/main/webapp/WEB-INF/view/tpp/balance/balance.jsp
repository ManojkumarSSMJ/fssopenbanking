<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<script type="text/javascript">
$("#loading-text").text('Fetching Account Details from Bank');
</script>
<script type="text/javascript">
</script>
</head>
<body>
<div class="col-lg-5">
     <h4>Accounts Balance</h4>
             
        <div class="graybox" style = " margin: auto;width: 80%;border: 3px solid green;padding: 10px;">
             <c:choose>
             <c:when test="${not empty tppBalanceResponse}">
              	<div class="col-lg-3">Bank Name</div>  <div class="col-lg-1"> :</div><div class="col-lg-7"><c:out value="${tppBalanceResponse.bankName}"/></div><br>
			    <div class="col-lg-3">Account Id</div>  <div class="col-lg-1"> :</div><div class="col-lg-7"><c:out value="${tppBalanceResponse.maskAccountId}"/></div><br>
			<c:forEach var="tppBalanceList" items="${tppBalanceResponse.tppBalanceList}" varStatus="loop">
			    <div class="col-lg-3">Balance <c:out value="${loop.index + 1}"/></div>  <div class="col-lg-1"> :</div><div class="col-lg-7"><c:out value="${tppBalanceList.currency}"/> <c:out value="${tppBalanceList.balance}"/> (<c:out value="${tppBalanceList.type}"/>)</div><br>
             </c:forEach>
             </c:when>
             <c:otherwise>
             <div class="alert alert-danger">
	      		<strong>Error!</strong> <c:out value="${tppBalanceResponse.responseMessage}"/> Please <a href="tppAccounts"><strong>Click Here</strong></a> to update your account transaction details with FSS Open Banking HUB.
			</div>
             </c:otherwise>
             </c:choose>
          </div>
          <div class="col-lg-9 col-lg-offset-3"><label></label></div>
				<div class="col-lg-9 col-lg-offset-3">
					<a href="showBanks"><strong>Click Here</strong></a> to link other bank accounts with FSS Open Banking HUB.
					<br>
					<a href="tppAccounts"><strong>Click Here</strong></a> to go Accounts page.
				</div>
 </div>
</body>
</html>