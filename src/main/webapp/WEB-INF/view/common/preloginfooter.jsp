<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<footer>
	<div class="row-one">

		<div class="container">
			<ul>
				<li>
					<h6>Contact</h6>
					<p>
						Financial Software &amp; Systems (P) Ltd.<br> G4, 1st Cross
						Street, SIPCOT IT Park,<br> Rajiv Gandhi Salai (OMR),
						Siruseri, Navalur,<br> Chennai, TN - 603 103, India<br>
						Phone : +91 44 4741 5600<br>
						Fax&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: +91 44 4741 5601<br>
						E-mail : info@fss.co.in
					</p>
					<p></p>
				</li>
				


			</ul>
		</div>
	</div>
	<div class="row-two">
		<div class="container">
			<div class="col-xs-11">
				&copy; Copyright
				<c:set var="now" value="<%= new java.util.Date()%>" />
				<fmt:formatDate pattern="yyyy" value="${now}" />
				Financial Software &amp; Systems Pvt. Ltd. All Rights Reserved.
			</div>
			<div class="col-xs-1">
				<img
					src="<%=request.getContextPath()%>/images/common/fss-footer-logo.jpg"
					width="60" height="47">
			</div>
		</div>
	</div>
</footer>