<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<script type="text/javascript">
</script>
</head>
<body>

<div class="container-fluid">
   	
   
<br><br><br>

<c:if test="${responseFlag eq 0}">
<div class="alert alert-danger">
    	<strong>Error!</strong> <c:out value="${responseMessage}"/> Please <a href="showBanks"><strong>Click Here</strong></a> to link your accounts with FSS Open Banking HUB. 
  	</div>
</c:if>
<c:if test="${responseFlag eq 1}">
<div class="alert alert-success">
    	<strong>Success!</strong> <c:out value="${responseMessage}"/>. 
  	</div>
</c:if>
<br>
<br>

                     	<div class="col-lg-8 col-lg-offset-2 text-center padding_up" style="padding-top:5%;">
	<h4>Welcome to FSS Open Banking</h4>
	<p>FSS Open Banking being a scalable application is capable of integrating the large business enterprises and organizations into its scope and range. Already, with millions of people across the globe are using state-of-the-art FSS Open Banking application every day, FSS is determined to leverage cutting-edge technologies and create innovative open banking experiences for its customers.</p>
	
	</div>
</div>
</body>
</html>