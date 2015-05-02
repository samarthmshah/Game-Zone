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
</head>
<body>
<%
			String msg = (String) session.getAttribute("msg");
			if (msg != null) {
				if (msg.equals("The Image has been uploaded successfully")) {
					response.setContentType("text/html");
					out.println("<div class=\"col-md-12\">");
					out.println("<p class=\"bg-success\">");
					out.println("<strong>" + msg + "</strong>");
					out.println("</p></div>");
				}
					session.removeAttribute("msg");
			}
			else{
		%>

<div class="form-group">
	<a href="seller_addGamePoster.jsp" class="ajax-popup-link"><span><strong>Upload
				Image</strong></span></a>
</div>

<%} %>

<script src="js/jquery-1.11.0.min.js"></script>
	
	<script src="js/bootstrap.min.js"></script>
 	<script src="js/moment.min.js"></script>
	<script src="js/daterangepicker.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/jquery.maskedinput.min.js"></script>
	<script src="js/bootbox.min.js"></script>
	<script src="js/jquery.bank.js"></script> 
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="js/slick.min.js"></script>
	<script src='js/jquery.magnific-popup.min.js'></script>
	<script src='js/jquery.catslider.js'></script>
	<script src="js/slidebars.min.js"></script>
	<script src="js/bootstrap-select.min.js"></script>
	<script src="js/myscript.js"></script>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script> 
</body>
</html>