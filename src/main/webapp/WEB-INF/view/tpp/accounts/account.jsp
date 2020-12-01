<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<script type="text/javascript">
$("#loading-text").text('Fetching Account Details from Bank');
</script>
<script type="text/javascript">
$(document).ready(function(){
	'<c:if test="${not  empty tppBalanceResponse}">'
		$("#balanceModal").modal('show');
	'</c:if>'
	
	$("#closeModal").bind('click', function(){
		window.location = 'tppAccounts';
	});
});

function fire_ajax_submit(inputValue) {

	document.getElementById("updateSucces").style.display = 'none';
	$("#ajaxUpdateSuccess").text("");
	document.getElementById("updateFail").style.display = 'none';
	$("#ajaxUpdateFail").text("");
	
	$("#ajaxUpdateLoading").text("Updating... ");
	document.getElementById("updateLoading").style.display = 'block';
	
	var splitedValue = inputValue.split("/");
    $.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/updateBankDetailsByBank?bankId="+splitedValue[0]+"&bankName="+splitedValue[1]+"&lastUpdateDT="+splitedValue[2],
        success : function(response) {
            if(response == "success")
             {
            	document.getElementById("updateLoading").style.display = 'none';
            	$("#ajaxUpdateLoading").text("");
            	
        		$("#ajaxUpdateSuccess").text("Updated successfully, ");
        		document.getElementById("updateSucces").style.display = 'block';
             }
            else if(response == "uptodate")
            {
	           	document.getElementById("updateLoading").style.display = 'none';
	           	$("#ajaxUpdateLoading").text("");
	           	
	       		$("#ajaxUpdateSuccess").text("You're up-to-date!, ");
	       		document.getElementById("updateSucces").style.display = 'block';
            }
            else if(response == "tknNotFound")
            {
            	document.getElementById("updateLoading").style.display = 'none';
            	$("#ajaxUpdateLoading").text("");
            	
            	document.getElementById("updateSucces").style.display = 'none';
            	$("#ajaxUpdateSuccess").text("");

            	$("#ajaxUpdateFail").text("Consent token not found, ");
            	document.getElementById("updateFail").style.display = 'block';
             
            }
            else
             {
            	document.getElementById("updateLoading").style.display = 'none';
            	$("#ajaxUpdateLoading").text("");
            	
            	document.getElementById("updateSucces").style.display = 'none';
            	$("#ajaxUpdateSuccess").text("");

            	$("#ajaxUpdateFail").text("Failed to update, ");
            	document.getElementById("updateFail").style.display = 'block';
             }
        }
    });
}

function fire_ajax_submit1() {

	document.getElementById("updateSucces").style.display = 'none';
	$("#ajaxUpdateSuccess").text("");
	document.getElementById("updateFail").style.display = 'none';
	$("#ajaxUpdateFail").text("");

	var loading = document.getElementById("ajaxUpdateLoading").value;
	
	$("#ajaxUpdateLoading").text("Updating... ");
	document.getElementById("updateLoading").style.display = 'block';

    $.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/updateBankDetails",
        success : function(response) {
            if(response == "success")
             {
            	document.getElementById("updateLoading").style.display = 'none';
            	$("#ajaxUpdateLoading").text("");
            	
        		$("#ajaxUpdateSuccess").text("The update was successfull, ");
        		document.getElementById("updateSucces").style.display = 'block';
             }
            else
             {
            	document.getElementById("updateLoading").style.display = 'none';
            	$("#ajaxUpdateLoading").text("");

            	$("#ajaxUpdateFail").text("Failed to update, ");
            	document.getElementById("updateFail").style.display = 'block';
             }
        }
    });
}
</script>
</head>
<body>

<div class="container-fluid">
   
   <h4>Accounts</h4>
   	<div id="updateSucces" class="alert alert-success" style="display:none;">
   	   <span id="ajaxUpdateSuccess"></span> Please <a href="tppAccounts"><strong>Click Here</strong></a> to refresh the page. 
   	</div>
   	<div id="updateLoading"class="alert alert-light" style="display:none;">
       <Strong><span id="ajaxUpdateLoading"></span><img src="images/BeanEater.gif"  style="width:30px;height:30px;"/></Strong> 
  </div>
    <div id="updateFail"class="alert alert-danger" style="display:none;">
       <span id="ajaxUpdateFail"></span> Please <a href="tppAccounts"><strong>Click Here</strong></a> to refresh the page.
    </div>
<br>

<div class="row">
<c:if test="${consent eq 'true' and responseFlag eq 1}">
<div class="alert alert-success">
    	<strong>Success!</strong> <c:out value="${responseMessage}"/>
  	</div>
</c:if>

<c:if test="${responseFlag eq 0}">
	<div class="alert alert-danger">
    	<strong>Error!</strong> <c:out value="${responseMessage}"/> Please <a href="showBanks"><strong>Click Here</strong></a> to link your accounts with FSS Open Banking HUB. 
  	</div>
</c:if>

<c:if test="${responseFlag eq 2}">
	<div class="alert alert-danger">
    	<strong>Error!</strong> Please <a onclick="javascript:fire_ajax_submit1()" href="#"><strong>Click Here</strong></a> to Update Account Details with FSS Open Banking HUB.
  	</div>
</c:if>

<c:if test="${not empty tppAccountsResponse.accountsResponseList}">
	<div class="accordion col-lg-offset-1">
		<c:forEach var="institutionDetail" items="${tppAccountsResponse.institutionDetails}">
			<div class="group col-lg-5">
				
				<h2><c:out value="${institutionDetail.bankName}"/> &nbsp;&nbsp;&nbsp;<img src="${institutionDetail.bankIcon}"  style="width:25px;height:25px;"/></h2>
				
				<div class="content">
				
				<div class="graybox">
					<a onclick="javascript:fire_ajax_submit('${institutionDetail.bankId}/${institutionDetail.bankName}/${institutionDetail.lastUpdateDT}/>')" href="#"><strong>Click Here</strong></a> to Update <strong><c:out value="${institutionDetail.bankName}"/></strong> (Last updated on <a><c:out value="${institutionDetail.lastUpdateDT}"/></a>).
				</div>
				
				<c:forEach var="accountsObject" items="${tppAccountsResponse.accountsResponseList}">
					<c:if test="${institutionDetail.bankName eq accountsObject.bankName}">
						<div class="graybox"  style="background-image: url('${institutionDetail.bankLogo}');background-repeat: no-repeat;background-position: center;background-position: top right;background-size: 100px;">
							
							<span class="col-lg-5">Bank Name</span>          <span class="col-lg-1">  :</span> <span class="col-lg-4"><c:out value="${accountsObject.bankName}"/></span> 
							
							<div style="clear: both"></div>
							
							<span class="col-lg-5">Account Holder Name</span>          <span class="col-lg-1">  :</span> <span class="col-lg-4"><c:out value="${accountsObject.accountHolderName}"/></span> 
							
							<div style="clear: both"></div>
							
							<c:forEach var="accountIdentifier" items="${accountsObject.accountIdentification}">
							
								<span class="col-lg-5"><c:out value="${accountIdentifier.type}"/></span>         <span class="col-lg-1">  :</span> <span class="col-lg-4"><c:out value="${accountIdentifier.identification}"/></span>
								
								<div style="clear: both"></div> 
							
							</c:forEach>
							
							<span class="col-lg-5">Account ID</span>             <span class="col-lg-1">  :</span> <span class="col-lg-4"><c:out value="${accountsObject.accountId}"/></span> 
							
							<div style="clear: both"></div>
							
							<span class="col-lg-5">Currency Type </span>           <span class="col-lg-1">  :</span> <span class="col-lg-4"><c:out value="${accountsObject.currencyType}"/></span>
							
							<div style="clear: both"></div>
							
							<span class="col-lg-5">Account Type </span>   <span class="col-lg-1">  :</span><span class="col-lg-4"><c:out value="${accountsObject.accountType}"/></span>
							
							<div style="clear: both"></div>
							
							<span class="col-lg-5">Account Sub Type </span>   <span class="col-lg-1">  :</span><span class="col-lg-4"><c:out value="${accountsObject.accountSubType}"/></span>
							
							<div style="clear:both"></div>
							
							<br>
							
							<form:form name="balanceForm" action="tppBalance" cssStyle="display:inline-block">
								<input type="hidden" name="accountId" value="<c:out value="${accountsObject.accountId}"/>"/>
								<input type="hidden" name="bankName" value="<c:out value="${accountsObject.bankName}"/>"/>
								<input type="hidden" name="bankShortName" value="<c:out value="${accountsObject.bankShortName}"/>"/>
								<input type="hidden" name="bankId" value="<c:out value="${accountsObject.bankId}"/>"/>
								<input type="hidden" name="mobileNumber" value="<c:out value="${accountsObject.mobileNumber}"/>"/>
								<button type="submit" class="btn btn-primary" id="viewBalance">Balance</button>
							</form:form>
							
							<form:form name="transactionForm" action="tppTransactions" cssStyle="display:inline-block">
								<input type="hidden" name="accountId" value="<c:out value="${accountsObject.accountId}"/>"/>
								<input type="hidden" name="bankName" value="<c:out value="${accountsObject.bankName}"/>"/>
								<input type="hidden" name="bankShortName" value="<c:out value="${accountsObject.bankShortName}"/>"/>
								<input type="hidden" name="bankId" value="<c:out value="${accountsObject.bankId}"/>"/>
								<input type="hidden" name="mobileNumber" value="<c:out value="${accountsObject.mobileNumber}"/>"/>
								<button type="submit" class="btn btn-primary" id="viewTransactions">Transactions</button>
							</form:form>
							
							<form:form name="directDebitForm" action="tppDirectDebits" cssStyle="display:inline-block">
								<input type="hidden" name="accountId" value="<c:out value="${accountsObject.accountId}"/>"/>
								<input type="hidden" name="bankName" value="<c:out value="${accountsObject.bankName}"/>"/>
								<input type="hidden" name="bankShortName" value="<c:out value="${accountsObject.bankShortName}"/>"/>
								<input type="hidden" name="bankId" value="<c:out value="${accountsObject.bankId}"/>"/>
								<input type="hidden" name="mobileNumber" value="<c:out value="${accountsObject.mobileNumber}"/>"/>
								<button type="submit" class="btn btn-primary" id="viewDirectDebit">Direct-Debits</button>
							
							</form:form>
								<form:form name="standingOrderForm" action="tppStandingOrders" cssStyle="display:inline-block">
								<input type="hidden" name="accountId" value="<c:out value="${accountsObject.accountId}"/>"/>
								<input type="hidden" name="bankName" value="<c:out value="${accountsObject.bankName}"/>"/>
								<input type="hidden" name="bankShortName" value="<c:out value="${accountsObject.bankShortName}"/>"/>
								<input type="hidden" name="bankId" value="<c:out value="${accountsObject.bankId}"/>"/>
								<input type="hidden" name="mobileNumber" value="<c:out value="${accountsObject.mobileNumber}"/>"/>
								<button type="submit" class="btn btn-primary" id="viewStandingOrder">Standing Orders</button>
							</form:form>
							
						</div>
					</c:if>
				</c:forEach>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>

<div style="clear: both"></div>
<c:if test="${not empty tppAccountsResponse.accountsResponseList}">
<div class="col-lg-9 col-lg-offset-3">
    <a href="showBanks"><strong>Click Here</strong></a> to link other bank accounts with FSS Open Banking HUB.
</div>
<!-- div  class="col-lg-9 col-lg-offset-3">
    <a onclick="javascript:fire_ajax_submit1()" href="#"><strong>Click Here</strong></a> to Update Account Details with FSS Open Banking HUB.
 </div> -->
</c:if>
</div>
</div>
</body>
</html>