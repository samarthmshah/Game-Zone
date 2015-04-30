<!doctype html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- As in slidebar -->
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
						<div class="col-md-3"></div>
						<div class="col-md-6">
							<form action="<%=request.getContextPath()%>/GameController" method="POST" id="addGame">

								<div class="form-group">
									<label class="control-label form-label" for="cat_id">Select Category</label>
									<div>
										<select class="selectpicker" data-live-search="true" name="cat_id" id="cat_id" required="required">
											<option value="-1" disabled="disabled" selected="selected">Category list</option>
											<option value="">One</option>
											<option value="">One</option>
											<option value="">One</option>
											<option value="">One</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label form-label" for="scat_id">Select Subcategory</label>
									<div>
										<select class="selectpicker" data-live-search="true" name="scat_id" id="scat_id" required="required">
											<option value="-1" disabled="disabled" selected="selected">Category list</option>
											<option value="">One</option>
											<option value="">One</option>
											<option value="">One</option>
											<option value="">One</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label form-label" for="scat_id">Select Console type:</label>
									<div>
										<select class="selectpicker" name="game_console" id="game_console" required="required">
											<option value="">PC</option>
											<option value="">XBox</option>
											<option value="">PlayStation</option>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="form-label" for="game_name"><strong>Name:</strong></label>
									<input type="text" class="form-control form-control-line"
										placeholder="Name of the Game..." name="game_name"
										id="game_name" required="required">
								</div>
								
								<div class="form-group">
									<label class="form-label" for="game_price">Price:</label>
									<input type="text" class="form-control form-control-line"
										placeholder="Price of the Game..." name="game_price"
										id="game_price" required="required">
								</div>
								
								<div class="form-group">
									<label class="form-label" for="game_youtube_url">Youtube URL:</label>
									<input type="text" class="form-control form-control-line"
										placeholder="Youtube URL of Game..." name="game_youtube_url"
										id="game_youtube_url" required="required">
								</div>
								
								<div class="form-group">
									<label class="control-label form-label" for="game_description">Game Description</label>
									<textarea class="form-control form-control-line" rows="2"
										name="game_description" id="game_description"
										placeholder="Enter brief description about the game..."></textarea>
								</div>

								<input type="hidden" name="flag" value="insert">
								<button type="submit" class="btn btn-default">Submit</button>
								<button type="reset" class="btn btn-light">Reset</button>
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
	<script type="text/javascript">
	$(document).ready(function(){
		console.log("Hello");
		$("#addGame").validate({
			rules: {
			    'game_price': {
			        required: true
			    }
			},
			highlight: function(element){
				$(element).closest('.form-group')
				.removeClass('success').addClass('error');
			}, 
			success: function(element){
				element
				.text('OK!').addClass('valid')
				.closest('.form-group').removeClass('error').addClass('success');
			}
			});
	});
		
	</script>
	
</body>
</html>