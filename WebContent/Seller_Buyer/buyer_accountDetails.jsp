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
</head>

<body class="off-canvas">
	<div id="left-border"></div>
	<div id="right-border"></div>
	<div id="top-border"></div>
	<div id="bottom-border"></div>
	<div id="fixed-bottom" class="sb-slide"></div>


	<!--  Header -->
	<%@include file="buyer_header.jsp"%>
	<!--  END Header -->
	<div id="sb-site">
		<div id="main">
			<div id="loadergif">
				<div class="loader">Loading...</div>
			</div>

			<div id="content">
				<div class="container">
				<h1>
					<span>Your Account Information</span>
				</h1>
					<div class="row">
						<%
							String msg = (String) session.getAttribute("msg");
							if (msg != null) {
								if (msg.equals("The account is updated successfully")) {
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
							<ul class="styled-list text-left">
							<c:forEach var="i" items="${sessionScope.buyerInfo }">
								<li><h6>First Name:</h6>	${i.firstname }</li>
								<li><h6>Last Name:</h6>		${i.lastname }</li>
								<li><h6>Userame:</h6>		${i.username }</li>
								<li><h6>Email:</h6>			${i.email }</li>
								<li><h6>Phone Number:</h6>	${i.phNo }</li>
								<li><h6>Address:</h6>		${i.address }</li>
								<li><h6>Zipcode:</h6>		
								<c:choose>
									<c:when test=" ${i.zip == \"\"}">Not entered</c:when>
									<c:otherwise>${i.zip }</c:otherwise>
								</c:choose>
								</li>
								<li><h6>Paypal:</h6>		${i.paypal }</li>
							</c:forEach>
							</ul>
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

<!--  The games in the cart will be showed here. This comes after the sb-site ends. -->
<%@include file="buyer_slidebar_cart.jsp" %>
<!-- End slidebar. -->

	<!--  The main menu will be showed here. This comes after the sb-site ends. -->
	<%@include file="buyer_main_menu.jsp"%>
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
				"game_price":{ required: true},
				"game_stock": {required: true}
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