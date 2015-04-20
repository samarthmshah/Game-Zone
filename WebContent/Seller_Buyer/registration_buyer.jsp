<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>Game-Zone</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/daterangepicker-bs3.css">

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
<body>

	<!-- <header> -->
	<header id="header">
		<div id="logo">
			<a href="index.jsp"><img src="images/logo.png" /></a>
		</div>
	</header>
	<!-- </header> -->

	<div class="container">
		<section class="container" style="margin-top: 5px;">
			<div class="container-page">

				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<div style="float: left">
							<a href="#reg">
							<button type="submit" class="btn btn-md btn-default" style="padding: 0px 8px 0px 8px;">Registration</button>
								<!-- <h2 class="dark-grey">Registration</h2> -->
							</a>
						</div>
						<div style="float:right">
							<a href="login.jsp">
								<button type="submit" class="btn btn-md btn-warning" style="padding: 0px 8px 0px 8px;">Log
									In</button>
							</a>
						</div>
					</div>
					<div class="col-md-4"></div>
				</div>

				<form action="<%=request.getContextPath()%>/BuyerController?flag=insert" method="POST" id="regform">

				<div class="row" style="margin-top: 30px">
					<div class="col-md-6">
						<div class="form-group col-lg-12">
							<label for="firstname">First Name (required)</label> <input type="text"
								id="firstname" name="firstname" class="form-control">
						</div>
						

						<div class="form-group col-lg-12">
							<label for="lastname">Last Name (required)</label> <input type="text"
								id="lastname" name="lastname" class="form-control">
						</div>

						<div class="form-group col-lg-12">
							<label for="username">User Name (required)</label> <input type="text"
								id="username" name="username" class="form-control">
						</div>

						<div class="form-group col-lg-6">
							<label for="password">Password (required)</label> <input type="password"
								id="password" name="password" class="form-control">
						</div>

						<div class="form-group col-lg-6">
							<label for="rpassword">Repeat Password</label> <input
								type="password" id="rpassword" name="rpassword"
								class="form-control">
						</div>

						<div class="form-group col-lg-6">
							<label for="emailid">Email Address (required)</label> <input type="email"
								id="emailid" name="emailid" class="form-control">
						</div>

						<div class="form-group col-lg-6">
							<label for="remailid">Repeat Email Address</label> <input
								type="email" name="remailid" class="form-control" id="remailid">
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group col-lg-12">
							<label for="phno">Phone Number (required)</label> <input type="text"
								name="phno" class="form-control" id="phno">
						</div>

						<div class="form-group col-lg-6">
							<label for="dob">Date of Birth (required)</label> <input type="text"
								name="dob" class="form-control" id="dob">
						</div>

						<div class="form-group col-lg-6">
							<label for="age">Age:</label> <input type="text" name="age"
								class="form-control" id="age" disabled="disabled">
						</div>

						<div class="form-group col-lg-12">
                  			<label for="address">Address (required)</label>
                      		<textarea class="form-control" rows="2" id="address" name="address" placeholder="Type your address..."></textarea>
						</div>
						
						<div class="form-group col-lg-12">
							<label for="zip">Zipcode</label> <input type="text"
								name="zip" class="form-control" id="zip">
						</div>
						
						<div class="form-group col-lg-12">
							<label for="paypal">PayPal ID (required)</label> <input type="email"
								name="paypal" class="form-control" id="paypal">
						</div>
						
						<div class="col-lg-12">
							<input type="checkbox" class="checkbox" name="agree" />Agree to the terms and conditions.</div>
						
					</div>
				</div>

					<div class="row" style="margin-top: 15px">
						<div class="col-md-4"></div>
						<div class="col-md-4">
						
					<a id="reg">
							<button type="submit" class="btn btn-block btn-success"
								style="margin-top: 10px; margin-left: 15px">Register</button>
					</a>
						</div>
						<div class="col-md-4"></div>
					</div>
				</form>
			</div>
		</section>
	</div>
	
	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/moment.min.js"></script>
	<script src="js/daterangepicker.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script src="js/jquery.maskedinput.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootbox.min.js"></script>
	
	<script type="text/javascript">
		$('#phno').mask('999-999-9999');
		$('#zip').mask('99999');
	</script>
	
	<script>
		$(function() {
			$('#dob').daterangepicker({
				singleDatePicker : true,
				showDropdowns : true
			}, function(start, end, label) {
				var years = moment().diff(start, 'years');
				$('#age').val(years);
				if (years < 18){
					bootbox.alert("Minimum age should be 18 years");
				}
			});
		});
	</script>
	
	<script type="text/javascript">
	$().ready(function(){
		$('#regform').validate({
			rules: {
				'firstname': {required: true},
				'lastname': {required: true},
				'username': {
					required: true,
					minlength: 5
				},
				'password': {
					required: true,
					minlength: 5
				},
				'rpassword': {
					required: true,
					equalTo: '#password'
				},
				'emailid': {
					required: true,
					email: true
				},
				'remailid': {
					required: true,
					equalTo: '#emailid'
				},
				'phno': {
					required: true,
					phoneUS: true
				},
				'dob': {required: true},
				'age': {
					min: 18
				},
				'address': {
					required: true,
					minlength: 10
				},
				'paypal': {
					required: true,
					email: true
					},
				'agree': {required: true},
				
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