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
					<div class="row">
					<%
							String msg = (String) session.getAttribute("msg");
							if (msg != null) {
								if (msg.equals("The game was successfully updated")) {
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
					<c:forEach var="game" items="${sessionScope.game2bedited }">
						<div class="col-md-3"></div>
						
						<div class="col-md-6">
							<form action="<%=request.getContextPath()%>/GameController" method="POST" id="updateGame">
								
								<input type="hidden" name="game_poster_name" value="${game.game_poster_name }">
								
								<div class="form-group">
									<label class="control-label form-label" for="cat_id">Select Category*	-- <strong>Previous choice: ${game.cat_id.getCat_name() }</strong></label>
									<div>
										<select class="selectpicker" data-live-search="true" name="cat_id" id="cat_id" required="required">
											<option value="-1" disabled="disabled" selected="selected">Category list</option>
											<c:forEach var="i" items="${sessionScope.categoryList }">
												<option value="${i.cat_id }">${i.cat_name }</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label form-label" for="scat_id">Select Subcategory	-- <strong>Previous choice: ${game.scat_id.getScat_name() }</strong></label>
									<div>
										<select class="selectpicker" data-live-search="true" name="scat_id" id="scat_id" required="required">
											<option value="-1" disabled="disabled" selected="selected">Will be loaded dynamically.</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label form-label" for="game_console">Select
										Console type*	--	Previous Choice: ${game.game_console }</label>
									<div>
										<select class="selectpicker" name="game_console"
											id="game_console" required="required">
											<option value="pc">PC</option>
											<option value="xbox">XBox</option>
											<option value="ps">PlayStation</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="form-label" for="game_name"><strong>Name*</strong></label>
									<input type="text" class="form-control form-control-line"
										placeholder="Name of the Game..." name="game_name"
										id="game_name" required="required" value="${game.game_name }">
								</div>

								<div class="form-group">
									<label class="form-label" for="game_price">Price*</label> <input
										type="text" class="form-control form-control-line"
										placeholder="Price of the Game..." name="game_price"
										id="game_price" required="required" value="${game.game_price }">
								</div>

								<div class="form-group">
									<label class="form-label" for="game_stock">Stock*:</label> <input
										type="text" class="form-control form-control-line"
										placeholder="Quantity of the product you have..."
										name="game_stock" id="game_stock" required="required" value="${game.game_stock }">
								</div>

								<div class="form-group">
									<label class="form-label" for="game_shipping_charges">Shipping Charges*:</label> <input
										type="text" class="form-control form-control-line"
										placeholder="Charges for Shipping this product..."
										name="game_shipping_charges" id="game_shipping_charges" required="required" value="${game.game_shipping_charges }">
								</div>

								<div class="form-group">
									<label class="form-label" for="game_youtube_url">Youtube URL:</label> 
									<input type="text" class="form-control form-control-line"
										placeholder="Youtube URL of Game..." name="game_youtube_url"
										id="game_youtube_url" required="required" value="${game.game_youtube_url }">
								</div>

								<div class="form-group">
									<label class="control-label form-label" for="game_description">Game Description*</label>
									<textarea class="form-control form-control-line" rows="2"
										name="game_description" id="game_description"
										placeholder="Enter brief description about the game...">${game.game_description }</textarea>
								</div>
								
								<input type="hidden" name="game_id" value="${game.game_id }">
								<input type="hidden" name="flag" value="update">
								<input type="hidden" name="seller_id" value="<%=seller_id%>">
								<button type="submit" class="btn btn-lg btn-success">Update your Product</button>
								<button type="reset" class="btn btn-lg btn-light">Reset</button>
							</form>
						</div>
						<div class="col-md-3"></div>
					</c:forEach>
					<%session.removeAttribute("game2bedited"); %>
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
	<script>
	$(document).ready(function(){
		$("#cat_id").change(function(event){
			var $cat_id = $("#cat_id").val();
			
			$.ajax({
				method: "GET",
				url: "<%=request.getContextPath()%>/GameController?flag=loadScatDynamically",
				data: {cat_id: $cat_id},
				success: function(responseJson){
					var $select = $("#scat_id");
					$select.find("option").remove();
					$.each(responseJson, function(key, value){
						$("<option>").val(key).text(value).appendTo($select);
					});
				},
				error: function(){
					console.log("Error!!");
				}
			});
			
		});
	});
	</script>
	<script type="text/javascript">
	$(function(){
		$("#updateGame").validate({
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