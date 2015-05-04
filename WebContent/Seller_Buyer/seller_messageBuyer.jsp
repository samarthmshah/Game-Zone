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
<link rel="stylesheet" href="css/bootstrap-select.css">
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
					<div class="row">
						<%
							String msg = (String) session.getAttribute("msg");
							if (msg != null) {
								if (msg.equals("The message has been sent successfully.")) {
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
						
							<form action="<%=request.getContextPath()%>/BuyerController" method="POST" id="contactBuyer">
								
								<div class="form-group">
									<label class="control-label form-label" for="buyer_id">Select Buyer Name*</label>
									<div>
										<select class="selectpicker" data-live-search="true" name="buyer_id" id="buyer_id" required="required">
											<option value="-1" disabled="disabled" selected="selected">Name of Buyers</option>
											<c:forEach var="i" items="${sessionScope.buyerList }">
												<option value="${i.buyer_id }">${i.firstname } ${i.lastname }</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="form-label" for="game_name"><strong>Subject*</strong></label>
									<input type="text" class="form-control form-control-line"
										placeholder="Subject of your message..." name="subject"
										id="subject" required="required">
								</div>


								<div class="form-group">
									<label class="control-label form-label" for="game_description">Message*</label>
									<textarea class="form-control form-control-line" rows="2"
										name="message" id="message"
										placeholder="Please enter a brief message..."></textarea>
								</div>
								
								<input type="hidden" name="flag" value="contactBuyer">
								<input type="hidden" name="seller_id" value="<%=seller_id%>">
								<button type="submit" class="btn btn-lg btn-success">Send Email</button>
								<button type="reset" class="btn btn-lg btn-light">Reset</button>
							</form>
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
	<script src="js/bootstrap-select.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/slidebars.min.js"></script>
	<script src="js/myscript.js"></script>
</body>
</html>