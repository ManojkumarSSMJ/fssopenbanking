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
	            	
	        		$("#ajaxUpdateSuccess").text("Updated successfully, Please refresh the page");
	        		document.getElementById("updateSucces").style.display = 'block';
	             }
            else if(response == "uptodate")
	            {
		           	document.getElementById("updateLoading").style.display = 'none';
		           	$("#ajaxUpdateLoading").text("");
		           	
		       		$("#ajaxUpdateSuccess").text("You're up-to-date!");
		       		document.getElementById("updateSucces").style.display = 'block';
	            }
            else
	             {
	            	document.getElementById("updateLoading").style.display = 'none';
	            	$("#ajaxUpdateLoading").text("");
	            	
	            	$("#ajaxUpdateFail").text("Updation failed");
	
	            	document.getElementById("updateLoading").style.display = 'none';
	            	$("#ajaxUpdateLoading").text("");
	
	            	$("#ajaxUpdateFail").text("Failed to update, try again later");
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
            	
        		$("#ajaxUpdateSuccess").text("The update was successfull, Please refresh the page");
        		document.getElementById("updateSucces").style.display = 'block';
             }
            else
             {
            	document.getElementById("updateLoading").style.display = 'none';
            	$("#ajaxUpdateLoading").text("");

            	$("#ajaxUpdateFail").text("Failed to update, try again later");
            	document.getElementById("updateFail").style.display = 'block';
             }
        }
    });
}
</script>
</head>
<body>

<div class="container-fluid">
   
   <h4>Activity</h4>
   
    <c:if test="${empty tppConsentResponse.institutionDetailsList}">
		<div class="alert alert-danger">
   			 Please <a href="showBanks"><strong>Click Here</strong></a> link the Bank with FSS Open Banking HUB.
		</div>
	</c:if>
   
<br>

<div class="row">
<c:if test="${not empty tppConsentResponse.institutionDetailsList}">
	<div class="accordion col-lg-offset-1">
		<c:forEach var="institutionDetail" items="${tppConsentResponse.institutionDetailsList}">
			<div class="group col-lg-5">
				
				<h2><c:out value="${institutionDetail.bankName}"/> &nbsp;&nbsp;&nbsp;<img src="${institutionDetail.bankIcon}"  style="width:25px;height:25px;"/></h2>
				
				<div class="content">
				
						<div class="graybox" >
							
							<table class="table table-hover table-striped table-bordered mpayTable">
							<thead>
								<tr>
									<td>Bank Id</td>
									<td>Bank Name</td>
									<td>Updated DateTime</td>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="updateDTDetails" items="${tppConsentResponse.updateDTDetailsList}">
								<c:if test="${institutionDetail.bankId eq updateDTDetails.bankId}">
									<tr>
										<td><c:out value="${updateDTDetails.bankId}" /></td>
										<td><c:out value="${updateDTDetails.bankName}" /></td>
										<td><c:out value="${updateDTDetails.updateDt}" /></td>
									</tr>
								</c:if>
							</c:forEach>
							</tbody>
						</table>
							
							<div style="clear:both"></div>
							
						</div>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>
</div>
<div style="clear: both"></div>
<c:if test="${not empty tppConsentResponse.institutionDetailsList}">
<div class="col-lg-9 col-lg-offset-3">
    <a href="showBanks"><strong>Click Here</strong></a> to link other bank accounts with FSS Open Banking HUB.
</div>
</c:if>
</div>
</body>
</html>