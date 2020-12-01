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
		submitGrantUpdate(bankId);
	});
	
	$(".selectAll").bind('click', function() {
		var bankId = $(this).attr('id');
		if($(this).prop('checked') == true){
			$('input[name="'+bankId+'_consentName"]').each(function() {
				$(this).prop('checked','true');
			});
		}
		if($(this).prop('checked') == false){
			$('input[name="'+bankId+'_consentName"]').each(function() {
				$(this).prop('checked',false);
			});
		}
		
		
	});
	
	function submitGrantUpdate(bankId) {
		var consentArr = [];
		var expiryDate = $("#"+bankId+"_expiryDate").val();;
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
<input type="hidden" name="selectedConsents" value="" />
<input type="hidden" name="expiryDate" value="" />
<input type="hidden" name="transactionStartDate" value="" />
<input type="hidden" name="transactionEndDate" value="" />
</form:form>
<!-- <div style="height :10%"></div> -->

<h4> List of Banks / Merchants</h4>
<div class="container row">



<div class="accordion col-lg-offset-1">
<c:forEach var="instDetail" items="${instDetails}">
<div class="group col-lg-6" >
  <h2><c:out value="${instDetail.bankName}" />&nbsp;&nbsp;&nbsp;<img src="${instDetail.bankIcon}"  style="width:25px;height:25px;"/></h2>
<div class="content">
<div class="graybox" style="background-image: url('${instDetail.bankLogo}');background-repeat: no-repeat;
  background-position: center;background-position: top right;background-size: 100px;">

<input type="checkbox" name="selectAll" class="selectAll" value="Select All" id="<c:out value="${instDetail.bankId}"/>"/>Select All<br>
<c:forEach  var="consentDetail" items="${instDetail.consentDetails}">
 <input type="checkbox" class="consentName" name="<c:out value="${instDetail.bankId}"/>_consentName" value="<c:out value="${consentDetail.consentShortName}"/>"/><c:out value="${consentDetail.consentDisplayName}"/><br>
</c:forEach>
<div class="col-lg-1"></div>

<br>
<div class="col-lg-12">
<div class="col-lg-4">
<label>Read Transaction Start Date</label>
</div>
<div class="col-lg-5">
<span><input type="text" id="<c:out value="${instDetail.bankId}"/>_startDate" class="date-from-pciker"
						value="" readonly="readonly"><img
						src="images/common/calendar-icon.png" onclick="javascript:NewCssCal('<c:out value="${instDetail.bankId}"/>_startDate','DDMMYYYY','-','',false,'-', false, 'past'),lightbox()" class="calendar-icon"></span>
</div>
</div>
<br>
<div class="col-lg-12">
<div class="col-lg-4">
<label>Read Transaction End Date</label>
</div>
<div class="col-lg-5">
<input type="text" id="<c:out value="${instDetail.bankId}"/>_endDate" class="date-from-pciker"
						value="" readonly="readonly"><img
						src="images/common/calendar-icon.png" onclick="javascript:NewCssCal('<c:out value="${instDetail.bankId}"/>_endDate','DDMMYYYY','-','',false,'-', false, '-'),lightbox()" class="calendar-icon">
</div>
</div>
<br>
<div class="col-lg-12">
<div class="col-lg-4">
<label>Expiry Date</label>
</div>
<div class="col-lg-5">
<input type="text" id="<c:out value="${instDetail.bankId}"/>_expiryDate" class="date-from-pciker"
						value="" readonly="readonly"><img
						src="images/common/calendar-icon.png" onclick="javascript:NewCssCal('<c:out value="${instDetail.bankId}"/>_expiryDate','DDMMYYYY','-','',false,'-', false, 'future'),lightbox()" class="calendar-icon">
</div>
</div>
<br>
<label>Note : We are using the data only for aggregation.</label> 
<br>
<label class="red <c:out value="${instDetail.bankId}"/>_error"></label> 
<br>
   <%--  <a href="<c:out value="${instDetail.clientUrl}"/>authorize?client_id=<c:out value="${instDetail.bankId}"/>&scope=<c:out value="${instDetail.allowedScope}"/>&redirect_uri=<c:out value="${instDetail.redirectUri}"/>&nonce=<c:out value="${instDetail.nonce}"/>&state=<c:out value="${instDetail.state}"/>&request=fagsddsrrerdweweffefcecdc" class="btn btn-primary">Grant or Update Consent</a> --%>
   <input type="button" value="Grant Or Update Consent" class="grantUpdate" id="grantUpdate_<c:out value="${instDetail.bankId}"/>"/>
  </div>
</div>
</div>

</c:forEach>

</div>
<c:if test="${empty instDetails}">
      <br>
      <div class="col-lg-6 col-lg-offset-3">
      <div class="alert alert-info">
    	<strong>Info!</strong>&nbsp;You have linked  all the Banks / Merchants connected with FSS Open Banking HUB. 
  	</div>
      </div>   
      </c:if>  
</div>

</div>
<br>
</body>
</html>

