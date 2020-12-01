<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript">
	$("#loading-text").text('Fetching Offers from Bank');
</script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="container-fluid">

		<h4>Products</h4>

		<br>
		<div class="row">

			<c:if test="${not empty hubOffersResponse.offerDetails}">
						<table
							class="table table-hover table-striped table-bordered mpayTable">
							<thead>
								<tr>
									<td>Remitter Account Number</td>
									<td>Offer Id</td>
									<td>Offer Type</td>
									<td>Offer Description</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="offerObject"
									items="${hubOffersResponse.offerDetails}">
									<tr>
										<td><c:out value="${offerObject.accountNumber}" /></td>
										<td><c:out
												value="${offerObject.offerId}" /></td>
										<td><c:out value="${offerObject.offerType}" /></td>
										<td><c:out
												value="${offerObject.offerDescription}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
			</c:if>
			<div style="clear: both"></div>
			<c:if test="${not empty hubOffersResponse.offerDetails}">
				<div class="col-lg-9 col-lg-offset-3">
					<a href="showBanks"><strong>Click Here</strong></a> to link other
					bank accounts with FSS Open Banking HUB.
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>