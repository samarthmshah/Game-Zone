<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Game-Zone</title>

<link href="css/root.css" rel="stylesheet">
<style type="text/css">
</style>
</head>
<!-- <CONTENT> -->
<body>
	
	<div class="login-form">
	<%
		String msg = (String) session.getAttribute("msg");
		if (msg == null) msg = "";
		if(msg.equals("You have logged out successfully"))
			out.print("<p class = \"bg-success text-center\">"+msg+"</p>");
		else if(msg.length() != 0)
			out.print("<p class = \"bg-danger text-center\">"+msg+"</p>");
		session.removeAttribute("msg");
		String loggedIn = (String) session.getAttribute("loggedIn");
		if(loggedIn != null && loggedIn.equals(true)){
			String firstName = (String) session.getAttribute("firstName");
			String lastName = (String) session.getAttribute("lastName");
			out.print("<p class=\"text-primary\">");
			out.print("You are already logged in, "+firstName+" "+lastName);
			out.print("</p>");
		}
		else{
	%>
		<form action="<%=request.getContextPath()%>/AdminLoginServlet"
			method="POST">
			<div class="top">
				<!-- Could do an Image here. Will do if time allows. -->
				<h1>Game Zone</h1>
				<h4>Administrator Login</h4>
			</div>
			<div class="form-area">
				<div class="group">
					<input type="text" class="form-control" placeholder="Username"
						required="required" name="username"> <i class="fa fa-user"></i>
				</div>
				<div class="group">
					<input type="password" class="form-control" placeholder="Password"
						required="required" name="password"> <i class="fa fa-key"></i>
				</div>
				<div class="checkbox checkbox-primary">
					<input id="checkbox101" type="checkbox" checked> <label
						for="checkbox101"> Remember Me</label>
				</div>
				<button type="submit" class="btn btn-default btn-block">LOGIN</button>
				<button type="reset" class="btn btn-block">RESET</button>
			</div>
		</form>
		<!-- <div class="footer-links row">
			<div class="col-xs-6">
				<a href="#"><i class="fa fa-external-link"></i> Register Now</a>
			</div>
			<div class="col-xs-6 text-right">
				<a href="#"><i class="fa fa-lock"></i> Forgot password</a>
			</div>
		</div> -->
	</div>
	<%} %>
</body>
<!-- </CONTENT> -->
</html>