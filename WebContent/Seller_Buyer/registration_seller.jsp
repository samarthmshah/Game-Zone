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
							<button type="submit" class="btn btn-md btn-default" style="padding: 0px 8px 0px 8px;">Registration</button>
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

				<form action="<%=request.getContextPath()%>/SellerController?flag=insert" method="POST" id="regform">

				<div class="row" style="margin-top: 30px">
					<div class="col-md-6">
						
						<div class="form-group col-lg-12">
							<label for="companyname">Company Name (required)</label> <input type="text"
								id="companyname" name="companyname" class="form-control" placeholder="Company name goes here...">
						</div>
						
						<div class="form-group col-lg-12">
							<label for="firstname">First Name (required)</label> <input type="text"
								id="firstname" name="firstname" class="form-control" placeholder="Your first name goes here...">
						</div>
						

						<div class="form-group col-lg-12">
							<label for="lastname">Last Name (required)</label> <input type="text"
								id="lastname" name="lastname" class="form-control" placeholder="Your last name goes here...">
						</div>

						<div class="form-group col-lg-12">
							<label for="username">User Name (required)</label> <input type="text"
								id="username" name="username" class="form-control" placeholder="Your username goes here...">
						</div>

						<div class="form-group col-lg-6">
							<label for="password">Password (required)</label> <input type="password"
								id="password" name="password" class="form-control" placeholder="Your password goes here...">
						</div>

						<div class="form-group col-lg-6">
							<label for="rpassword">Repeat Password</label> <input
								type="password" id="rpassword" name="rpassword"
								class="form-control">
						</div>

						<div class="form-group col-lg-6">
							<label for="emailid">Email Address (required)</label> <input type="email"
								id="emailid" name="emailid" class="form-control" placeholder="Your email goes here...">
						</div>

						<div class="form-group col-lg-6">
							<label for="remailid">Repeat Email Address</label> <input
								type="email" name="remailid" class="form-control" id="remailid">
						</div>
						
						<div class="form-group col-lg-12">
							<label for="phno">Phone Number (required)</label> <input type="text"
								name="phno" class="form-control" id="phno" placeholder="Your phone # goes here...">
						</div>
						
					</div>
					
					<div class="col-md-6">

						<div class="form-group col-lg-6">
							<label for="dob">Date of Birth (required)</label> <input type="text"
								name="dob" class="form-control" id="dob">
						</div>

						<div class="form-group col-lg-6">
							<label for="age">Age:</label> <input type="text" name="age"
								class="form-control" id="age" disabled="disabled" placeholder="Must be 18+">
						</div>

						<div class="form-group col-lg-12">
                  			<label for="address">Address (required)</label>
                      		<textarea class="form-control" rows="2" id="address" name="address" placeholder="Type your address..."></textarea>
						</div>
						
						<div class="form-group col-lg-12">
							<label for="zip">Zipcode</label> <input type="text"
								name="zip" class="form-control" id="zip" placeholder="Your zipcode goes here...">
						</div>
						
						<div class="form-group col-lg-12">
							<label for="routingnumber">Routing Number (required)</label> <input type="text"
								name="routingnumber" class="form-control js-routing-transit-number" id="routingnumber" 
								pattern="\d*" x-autocompletetype="routing-transit-number" 
								placeholder="Routing # goes here...">
						</div>
						
						<div class="form-group col-lg-12">
							<label for="accountnumber">Account Number (required)</label> <input type="text"
								name="accountnumber" class="form-control" id="accountnumber" placeholder="Account # goes here...">
						</div>
						
						<div class="form-group col-lg-12">
							<label for="paypal">PayPal ID (required)</label> <input type="email"
								name="paypal" class="form-control" id="paypal" placeholder="Paypal ID goes here...">
						</div>
						
						<div class="col-lg-12">
							<input type="checkbox" class="checkbox" name="agree" />Agree to the terms and conditions.</div>
						
					</div>
				</div>

					<div class="row" style="margin-top: 15px">
						<div class="col-md-4"></div>
						<div class="col-md-4">
						
							<button type="submit" class="btn btn-block btn-success"
								style="margin-top: 10px; margin-left: 15px">Register</button>
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
	<script src="js/jquery.bank.js"></script>
	
	
	<script type="text/javascript">
		$('#phno').mask('999-999-9999');
		$('#zip').mask('99999');
		$('#routingnumber').mask('999999999');
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
				'companyname': {required: true},
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
				'routingnumber':{
					required: true,
				},
				'accountnumber':{
					required: true,
					maxlength: 17,
					number: true
					/* creditcard: true, */	// Make sure to do this before deployment
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