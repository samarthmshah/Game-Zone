<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- As in slidebar -->

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<title>Game-Zone</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/slick.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="fonts/typicons/typicons.css">
<link rel="stylesheet" href="css/mystyle.css">

<script src="js/modernizr.custom.js"></script>
<style type="text/css">
label.valid {
	width: 24px;
	height: 24px;
	background: url(images/valid.png) center center no-repeat;
	display: inline-block;
	text-indent: -9999px;
}

label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}
</style>
</head>

<body class="off-canvas">
	<div id="left-border"></div>
	<div id="right-border"></div>
	<div id="top-border"></div>
	<div id="bottom-border"></div>
	<div id="fixed-bottom" class="sb-slide"></div>


	<!--  Header -->
	<%@include file="seller_header.jsp"%>
	<!--  END Header -->
	<div id="sb-site">
		<div id="main">
			<div id="loadergif">
				<div class="loader">Loading...</div>
			</div>

			<div id="content">
				<div class="container">
				<h1>
					<span>Update your information</span>
				</h1>
					<div class="row">
						<%
							String msg = (String) session.getAttribute("msg");
							if (msg != null) {
								if (msg.equals("as")) {
									response.setContentType("text/html");
									out.println("<div class=\"col-md-12\">");
									out.println("<p class=\"text-center bg-success\">");
									out.println("<strong>"+msg+"</strong>");
									out.println("</p></div>");
								}
								else{
									response.setContentType("text/html");
									out.println("<div class=\"col-md-12\">");
									out.println("<p class=\"text-center bg-danger\">");
									out.println("<strong>"+msg+"</strong>");
									out.println("</p></div>");
								}
								session.removeAttribute("msg");
							}
						%>
						<div class="col-md-3"></div>
						
						<div class="col-md-6">
							<form action="<%=request.getContextPath()%>/SellerController" method="POST" id="updateAccount">
							<c:forEach var="i" items="${sessionScope.sellerInfo }">
							<div class="form-group">
									<label class="form-label" for="companyname"><strong>Company Name*</strong></label>
									<input type="text" class="form-control form-control-line" name="companyname"
										id="companyname" required="required" value="${i.companyname }">
							</div>
							
							<div class="form-group">
									<label class="form-label" for="firstname"><strong>First Name*</strong></label>
									<input type="text" class="form-control form-control-line" name="firstname"
										id="firstname" required="required" value="${i.firstname }">
							</div>
							
							<div class="form-group">
									<label class="form-label" for="lastname"><strong>Last Name*</strong></label>
									<input type="text" class="form-control form-control-line" name="lastname"
										id="lastname" required="required" value="${i.lastname }">
							</div>
							
							<div class="form-group">
									<label class="form-label" for="email"><strong>Email*</strong></label>
									<input type="text" class="form-control form-control-line" name="email"
										id="email" required="required" value="${i.email }">
							</div>
							
							<div class="form-group">
									<label class="form-label" for="phNo"><strong>Phone Number*</strong></label>
									<input type="text" class="form-control form-control-line" name="phNo"
										id="phNo" required="required" value="${i.phNo }">
							</div>
							
							<div class="form-group">
									<label class="control-label form-label" for="address">Address*</label>
									<textarea class="form-control form-control-line" rows="2"
										name="address" id="address">${i.address }</textarea>
							</div>
							
							<div class="form-group">
									<label class="form-label" for="zip"><strong>Zipcode</strong></label>
									<input type="text" class="form-control form-control-line" name="zip"
										id="zip" required="required" value="${i.zip }">
							</div>
							
							<div class="form-group">
									<label class="form-label" for="routingNumber"><strong>Routing Number*</strong></label>
									<input type="text" class="form-control form-control-line" name="routingNumber"
										id="routingNumber" required="required" value="${i.routingNumber }">
							</div>
							
							<div class="form-group">
									<label class="form-label" for="accountNumber"><strong>Account Number*</strong></label>
									<input type="text" class="form-control form-control-line" name="accountNumber"
										id="accountNumber" required="required" value="${i.accountNumber }">
							</div>
							
							<div class="form-group">
									<label class="form-label" for="paypal"><strong>PayPal ID*</strong></label>
									<input type="text" class="form-control form-control-line" name="paypal"
										id="paypal" required="required" value="${i.paypal }">
							</div>
							
							<input type="hidden" name="flag" value="update">
							<input type="hidden" name="seller_id" value="${i.seller_id }">
							<button type="submit" class="btn btn-lg btn-success">Update</button>
							<button type="reset" class="btn btn-lg btn-light">Reset</button>
							</c:forEach>
							</form>
							<%session.removeAttribute("sellerInfo"); %>
						</div>
						<div class="col-md-3"></div>
					</div>
				</div>
			</div>

			<!--  Footer -->
			<%@include file="footer.jsp"%>
			<!-- END Footer -->
		</div>
	</div>

	<!--  The main menu will be showed here. This comes after the sb-site ends. -->
	<%@include file="seller_main_menu.jsp"%>
	<!-- End slidebar. -->

	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script src="js/jquery.maskedinput.min.js"></script>
	
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src='js/jquery.magnific-popup.min.js'></script>
	<script src="js/slidebars.min.js"></script>
	<script src="js/myscript.js"></script>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script> 
	
	<script type="text/javascript">
	$(function(){
		$("#updateAccount").validate({
			rules:{
				"zip":{ required: true},
				"phNo": {required: true}
			}, 
			highlight:function(element){
				$(element).closest(".form-group")
				.removeClass("success").addClass("error");
			}, 
			success:function(element){
				element
				.text("OK!").addClass("valid")
				.closest(".form-group").removeClass("error").addClass("success");
			}
			});
	});
	</script>
</body>
</html>