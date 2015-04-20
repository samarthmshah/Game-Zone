<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
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
<title>Game-Zone</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h1>Twitter Bootstrap jQuery Validate Registration form</h1>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span6 offset6">
				<div id="maincontent" class="span8">

					<form id="registration-form" class="form-horizontal">

						<h1>
							Sample Registration form <small>(Fill up the forms to get
								register)</small>
						</h1>
						<br />

						<div class="form-control-group">
							<label class="control-label" for="name">Your Name</label>
							<div class="controls">
								<input type="text" class="input-xlarge" name="name" id="name">
							</div>
						</div>

						<div class="form-control-group">
							<label class="control-label" for="name">User Name</label>
							<div class="controls">
								<input type="text" class="input-xlarge" name="username"
									id="username">
							</div>
						</div>

						<div class="form-control-group">
							<label class="control-label" for="name">Password</label>
							<div class="controls">
								<input type="password" class="input-xlarge" name="password"
									id="password">
							</div>
						</div>

						<div class="form-control-group">
							<label class="control-label" for="name"> Retype Password</label>
							<div class="controls">
								<input type="password" class="input-xlarge"
									name="confirm_password" id="confirm_password">
							</div>
						</div>

						<div class="form-control-group">
							<label class="control-label" for="email">Email Address</label>
							<div class="controls">
								<input type="text" class="input-xlarge" name="email" id="email">
							</div>
						</div>
						<div class="form-control-group">
							<label class="control-label" for="message">Your Address</label>
							<div class="controls">
								<textarea class="input-xlarge" name="address" id="address"
									rows="3"></textarea>
							</div>
						</div>

						<div class="form-control-group">
							<label class="control-label" for="message"> Please agree
								to our policy</label>
							<div class="controls">
								<input id="agree" class="checkbox" type="checkbox" name="agree">
							</div>
						</div>

						<div class="form-actions">
							<button type="submit" class="btn btn-success btn-large">Register</button>
							<button type="reset" class="btn">Cancel</button>
						</div>

					</form>
				</div>
				<!-- .span -->
			</div>
			<!-- .row -->

		</div>
	</div>
	<!-- .container -->

	<script src="js/jquery-1.11.0.min.js"></script>

	<script src="js/jquery.validate.js"></script>

<script type="text/javascript">
$(document).ready(function(){


	$('#registration-form').validate({
    rules: {
       name: {
        required: true
      },
	  
	 username: {
        minlength: 6,
        required: true
      },
	  
	  password: {
			required: true,
			minlength: 6
		},
		
		confirm_password: {
			required: true,
			minlength: 6,
			equalTo: "#password"
		},
	  
      email: {
        required: true,
        email: true
      },
	  
     
	   address: {
      	minlength: 10,
        required: true
      },
	  
	  agree: "required"
	  
    },
		highlight: function(element) {
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element
			.text('OK!').addClass('valid')
			.closest('.control-group').removeClass('error').addClass('success');
		}
  });

}); // end document.ready

</script>
<script>
		addEventListener('load', prettyPrint, false);
		$(document).ready(function(){
		$('pre').addClass('prettyprint linenums');
			});
</script>

</body>
</html>