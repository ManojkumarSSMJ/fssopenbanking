<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript">
$("#loading-text").text('Fetching Banks');
</script>
<script>
$(document).ready(function(){
	$(".grantUpdate").bind('click', function(){
		var bankId = $(this).parent().find(".selectAll").attr('id');
		submitGrantUpdate(bankId.split("/")[0]);
	});
	
	$(".consentRevoke").bind('click', function(){
		var bankId = $(this).parent().find(".selectAll").attr('id');
		var splitedData = bankId.split("/");
		document.consentForm.bankId.value=splitedData[0];
		document.consentForm.mobileNumber.value=splitedData[1];
		document.consentForm.uuId.value=splitedData[2];
		document.consentForm.consentId.value=splitedData[3];
		document.consentForm.action="<%=request.getContextPath()%>/revokeConsent";
		document.consentForm.submit();
	});
	
	
	$(".selectAll").bind('click', function() {
		var bankId = $(this).attr('id');
		if($(this).prop('checked') == true){
			$('input[name="'+bankId.split("/")[0]+'_consentName"]').each(function() {
				$(this).prop('checked','true');
			});
		}
		if($(this).prop('checked') == false){
			$('input[name="'+bankId.split("/")[0]+'_consentName"]').each(function() {
				$(this).prop('checked',false);
			});
		}
		
		
	});
	
	function submitGrantUpdate(bankId) {
		var consentArr = [];
		var expiryDate = $("#"+bankId+"_expiryDate").val();
		var startDate = $("#"+bankId+"_startDate").val();
		var endDate = $("#"+bankId+"_endDate").val();;
		$('input[name="'+bankId+'_consentName"]:checked').each(function() {
			consentArr.push(this.value);
		});
		if(consentArr.length == 0) {
			$("."+bankId+"_error").text('Please select consent')
		}
		
		
		if(consentArr.length > 0) {
			var selectedConsents = '';
			for(var index in consentArr) {
				var consentName = consentArr[index];
				selectedConsents+=consentName;
				selectedConsents+="|";
			}
			
			
			document.consentForm.bankId.value=bankId;
			document.consentForm.selectedConsents.value=selectedConsents;
			document.consentForm.expiryDate.value=expiryDate;
			document.consentForm.transactionStartDate.value=startDate;
			document.consentForm.transactionEndDate.value=endDate;
			document.consentForm.submit();
		}
	}
});
</script>
</head>
<body>
<div class="container-fluid">

<form:form name="consentForm" action="consentUpdate">
<input type="hidden" name="bankId" value="" />
<input type="hidden" name="mobileNumber" value="" />
<input type="hidden" name="uuId" value="" />
<input type="hidden" name="consentId" value="" />
<input type="hidden" name="selectedConsents" value="" />
<input type="hidden" name="expiryDate" value="" />
<input type="hidden" name="transactionStartDate" value="" />
<input type="hidden" name="transactionEndDate" value="" />
</form:form>

<h4> List of Banks / Merchants Consent</h4>

<div class="container row">

	<div class="row">
		<c:if test="${responseFlag eq 1}">
			<div class="alert alert-success">
		    	<strong>Success!</strong> <c:out value="${responseMessage}"/>
		  	</div>
		</c:if>
		<c:if test="${responseFlag eq 0}">
			<div class="alert alert-danger">
		    	<strong>Failure!</strong> <c:out value="${responseMessage}"/>
		  	</div>
		</c:if>
	</div>
	
	<div class="accordion col-lg-offset-1">
		<c:forEach var="consentTokenDetail" items="${consentTokenDetails}">
			<div class="group col-lg-6">
				<h2><c:out value="${consentTokenDetail.bankName}"/> &nbsp;&nbsp;&nbsp;<img src="${consentTokenDetail.bankIcon}"  style="width:25px;height:25px;"/></h2>
				
				<div class="content">
					<div class="graybox" style="background-image: url('${consentTokenDetail.bankLogo}');background-repeat: no-repeat;
					  background-position: center;background-position: top right;background-size: 100px;">    
						
						<input type="checkbox" name="selectAll" class="selectAll" value="Select All" id="<c:out value="${consentTokenDetail.bankId}"/>/<c:out value="${consentTokenDetail.mobileNumber}"/>/<c:out value="${consentTokenDetail.uuId}"/>/<c:out value="${consentTokenDetail.consentId}"/>" <c:if test="${consentTokenDetail.selectAll eq 'Y'}">checked</c:if>/>Select All<br>
						
						<c:forEach  var="consentDetail" items="${consentTokenDetail.consentDetails}">
						 <input type="checkbox" class="consentName" name="<c:out value="${consentTokenDetail.bankId}"/>_consentName" value="<c:out value="${consentDetail.consentShortName}"/>" <c:if test="${consentDetail.consentStatus eq 'Y'}">checked</c:if>/><c:out value="${consentDetail.consentDisplayName}"/><br>
						</c:forEach>
						
						<div class="col-lg-1"></div>
						<br>
						<div class="col-lg-12">
							<div class="col-lg-4">
								<label>Read Transaction Start Date</label>
							</div>
							<div class="col-lg-5">
								<span><input type="text" id="<c:out value="${consentTokenDetail.bankId}"/>_startDate" class="date-from-pciker"
													value="<c:out value="${consentTokenDetail.startDate}"/>" readonly="readonly"><img
													src="images/common/calendar-icon.png" onclick="javascript:NewCssCal('<c:out value="${consentTokenDetail.bankId}"/>_startDate','DDMMYYYY','-','',false,'-', false, 'past'),lightbox()" class="calendar-icon"></span>
							</div>
						</div>
						<br>
						<div class="col-lg-12">
							<div class="col-lg-4">
								<label>Read Transaction End Date</label>
							</div>
							<div class="col-lg-5">
								<input type="text" id="<c:out value="${consentTokenDetail.bankId}"/>_endDate" class="date-from-pciker"
													value="<c:out value="${consentTokenDetail.endDate}"/>" readonly="readonly"><img
													src="images/common/calendar-icon.png" onclick="javascript:NewCssCal('<c:out value="${consentTokenDetail.bankId}"/>_endDate','DDMMYYYY','-','',false,'-', false, '-'),lightbox()" class="calendar-icon">
							</div>
						</div>
						<br>
						<div class="col-lg-12">
							<div class="col-lg-4">
								<label>Expiry Date</label>
							</div>
							<div class="col-lg-5">
								<input type="text" id="<c:out value="${consentTokenDetail.bankId}"/>_expiryDate" class="date-from-pciker"
													value="<c:out value="${consentTokenDetail.expiryDate}"/>" readonly="readonly"><img
													src="images/common/calendar-icon.png" onclick="javascript:NewCssCal('<c:out value="${consentTokenDetail.bankId}"/>_expiryDate','DDMMYYYY','-','',false,'-', false, 'future'),lightbox()" class="calendar-icon">
							</div>
						</div>
						<br>
						<c:if test="${not empty consentTokenDetail.accountDetails}">
							<label>Account Details :</label><br>
							<c:forEach var="accountsObject" items="${consentTokenDetail.accountDetails}"> 
								<span><c:out value="${accountsObject.maskAccountId}"/> - <c:out value="${accountsObject.accountSubType}"/></span><br>
							</c:forEach>
						</c:if>
						<label>Note : We are using the data only for aggregation.</label> 
						<br>
						<label class="red <c:out value="${consentTokenDetail.bankId}"/>_error"></label> 
						<br>
						   <%--  <a href="<c:out value="${consentTokenDetail.clientUrl}"/>authorize?client_id=<c:out value="${consentTokenDetail.bankId}"/>&scope=<c:out value="${consentTokenDetail.allowedScope}"/>&redirect_uri=<c:out value="${consentTokenDetail.redirectUri}"/>&nonce=<c:out value="${consentTokenDetail.nonce}"/>&state=<c:out value="${consentTokenDetail.state}"/>&request=fagsddsrrerdweweffefcecdc" class="btn btn-primary">Grant or Update Consent</a> --%>
						   <input type="button" value="Update Consent" class="grantUpdate" id="grantUpdate_<c:out value="${consentTokenDetail.bankId}"/>"/>
						     <input type="button" value="Revoke Consent" class="consentRevoke" id="consentRevoke_<c:out value="${consentTokenDetail.bankId}"/>"/>
					  </div>
				</div>
			</div>
		</c:forEach>
	</div>
	<c:if test="${empty consentTokenDetails}">
		<br>
		<div class="col-lg-6 col-lg-offset-3">
		<div class="alert alert-danger">
			<strong>Error!</strong> You are not given consent for any banks. Please <a href="showBanks"><strong>Click Here</strong></a> to give consents with FSS Open Banking HUB. 
		</div>
		</div>   
	</c:if>     
</div>
</div>
<br>
</body>
</html>

