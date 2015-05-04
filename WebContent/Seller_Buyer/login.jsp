<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- As in slidebar -->
<title>Game-Zone</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/login.css">
</head>

<body>

<!-- <header> -->
<header id="header">
  <div id="logo">
  <a href="index.jsp"><img src="images/logo.png"/></a></div>
</header>
<!-- </header> -->

	<div class="container">
		<div class="card card-container">
			<%
				String msg = (String) session.getAttribute("msg");
				if (msg != null) {
					if (msg.equals("The activation link has been sent to your email")) {
						response.setContentType("text/html");
						out.println("<div class=\"col-md-12\">");
						out.println("<p class=\"bg-success text-center\">");
						out.println("<strong>" + msg + "</strong>");
						out.println("</p></div>");
						session.removeAttribute("msg");
					} 
					else if (msg.equals("You have logged out successfully")) {
						response.setContentType("text/html");
						out.println("<div class=\"col-md-12\">");
						out.println("<p class=\"bg-success text-center\">");
						out.println("<strong>" + msg + "</strong>");
						out.println("</p></div>");
						session.removeAttribute("msg");
					} 
					else if (msg.equals("Account Activated")) {
						response.setContentType("text/html");
						out.println("<div class=\"col-md-12\">");
						out.println("<p class=\"bg-success text-center\">");
						out.println("<strong>" + msg + "</strong>");
						out.println("</p></div>");
						session.removeAttribute("msg");
					} 
					else if (msg.equals("Account created successfully. An activation link has been sent to your email")) {
						response.setContentType("text/html");
						out.println("<div class=\"col-md-12\">");
						out.println("<p class=\"bg-success text-center\">");
						out.println("<strong>" + msg + "</strong>");
						out.println("</p></div>");
						session.removeAttribute("msg");
					} 
					else{
						response.setContentType("text/html");
						out.println("<div class=\"col-md-12\">");
						out.println("<p class=\"bg-danger text-center\">");
						out.println("<strong>" + msg + "</strong>");
						out.println("</p></div>");
						session.removeAttribute("msg");
					}
				}
				session.removeAttribute("msg");
				String loggedIn = (String) session.getAttribute("loggedIn");
				if (loggedIn != null && loggedIn.equals("true")) {
					out.print("<p class=\"text-primary\">");
					
					if(((String)session.getAttribute("userType")).equals("buyer")){
						out.print("You are already logged in. Go ");
						out.println("<a href=\""+ request.getContextPath() +"/Seller_Buyer/buyer_index.jsp\">Home.</a>");
					}
					else{
						out.print("You are already logged in. Go ");
						out.println("<a href=\""+ request.getContextPath() +"/Seller_Buyer/seller_index.jsp\">Home.</a>");
					}
					
					
					out.print("</p>");
				} 
				else {
			%>
			<img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
			<p id="profile-name" class="profile-name-card"></p>
			
			<form action="<%=request.getContextPath()%>/SBLoginServlet" method="POST" class="form-signin" id="logform">
				<input type="text" name="username" id="username" class="form-control"
					placeholder="Username" required autofocus> 
				<input type="password" id="password" name="password" class="form-control"
					placeholder="Password" required autofocus>
				<div class="form-group">
					<label for="usertype">Login As:</label> 
					<select name="usertype" required="required"
						class="form-control" id="usertype">
						<option value="buyer">BUYER</option>
						<option value="seller">SELLER</option>
					</select>
				</div>
				<div id="remember" class="checkbox">
					<label><input type="checkbox" value="remember-me">Remember me</label>
				</div>
				
				<button class="btn btn-lg btn-primary btn-block btn-signin"
					type="submit" id="signin">Sign in</button>
			
			<a href="forgot_password.jsp" class="forgot-password"> Forgot password? </a>

			<div class="btn-group" style="margin-left: 63px">
				<button type="button" class="btn btn-md btn-primary btn-block dropdown-toggle btn-signin"
					data-toggle="dropdown" aria-expanded="false">
					Sign Up <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li><a href="registration_buyer.jsp">As a Buyer</a></li>
					<li><a href="registration_seller.jsp">As a Seller</a></li>
				</ul>
			</div>
			</form>
			<!-- /form -->
		<%}%>
		</div>
		<!-- /card-container -->
	</div>
	<!-- /container -->
	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>