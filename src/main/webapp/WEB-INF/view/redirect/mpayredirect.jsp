<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function callThisOnLoad()
	{
		document.redirectPage.action = document.redirectPage.url.value ;
		document.redirectPage.submit();
	}
</script>

<form:form name="redirectPage" method="post">
	<input type="hidden" name="url" value="<c:out value="${url}"/>"/>
	<input type="hidden" name="bankId" value=" <c:out value="${bankId}"/>"/>
	<input type="hidden" name="bankName" value=" <c:out value="${bankName}"/>"/>
	<input type="hidden" name="mobileNumber" value=" <c:out value="${mobileNumber}"/>"/>
	<input type="hidden" name="uuId" value=" <c:out value="${uuId}"/>"/>
	<input type="hidden" name="accountNumber" value="<c:out value="${accountNumber}"/>"/>
	<input type="hidden" name="userId" value="<c:out value="${userId}"/>"/>
	<input type="hidden" name="provider" value="<c:out value="${provider}"/>"/>
	<input type="hidden" name="amount" value="<c:out value="${amount}"/>"/>
	<input type="hidden" name="moduleName" value="<c:out value="${moduleName}"/>"/>
	<input type="hidden" name="transactionId" value="<c:out value="${transactionId}"/>"/>
	<input type="hidden" name="responseFlag" value="<c:out value="${responseFlag}"/>"/>
	<input type="hidden" name="responseMessage" value="<c:out value="${responseMessage}"/>"/>
</form:form>

<script>
javascript:callThisOnLoad();
</script>