<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript">
	$("#loading-text").text('Fetching Parties from Bank');
</script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="container-fluid">

		<h4>Products</h4>

		<br>
		<div class="row">

			<c:if test="${not empty hubProductsResponse.productDetails}">
						<table
							class="table table-hover table-striped table-bordered mpayTable">
							<thead>
								<tr>
									<td>Remitter Account Number</td>
									<td>Product Id</td>
									<td>Product Type</td>
									<td>Product Name</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="productObject"
									items="${hubProductsResponse.productDetails}">
									<tr>
										<td><c:out value="${productObject.accountNumber}" /></td>
										<td><c:out
												value="${productObject.productId}" /></td>
										<td><c:out value="${productObject.productType}" /></td>
										<td><c:out
												value="${productObject.productName}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
			</c:if>
			<div style="clear: both"></div>
			<c:if test="${not empty hubProductsResponse.productDetails}">
				<div class="col-lg-9 col-lg-offset-3">
					<a href="showBanks"><strong>Click Here</strong></a> to link other
					bank accounts with FSS Open Banking HUB.
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>