<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="firstname" value="${sessionScope.firstName }"></c:set>
<c:set var="lastname" value="${sessionScope.lastName }"></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Game-Zone</title>

<link href="css/root.css" rel="stylesheet">
<style type="text/css">
label.valid {
	width: 24px;
	height: 24px;
	background: url(img/valid.png) center center no-repeat;
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
	<div class="loading">
		<img src="img/loading.gif" alt="loading-img">
	</div>

	<div id="top" class="clearfix">

		<!-- Start App Logo -->
		<div class="applogo">
			<a href="#" class="logo">Game-Zone</a>
		</div>

		<form class="searchform">
			<input type="text" class="searchbox" id="searchbox"
				placeholder="Search games..."> <span class="searchbutton"><i
				class="fa fa-search"></i></span>
		</form>

		<ul class="top-right">

			<li class="dropdown link"><a href="#" data-toggle="dropdown"
				class="dropdown-toggle profilebox"><img src="img/prof_img_1.png"
					alt="img">
					<b><c:out value="${firstname } ${lastname }"></c:out></b>
					<span class="caret"></span></a>
				<ul class="dropdown-menu dropdown-menu-list dropdown-menu-right">
					<li role="presentation" class="dropdown-header">Profile</li>
					<li><a href="<%=request.getContextPath()%>/AdminLogoutServlet"><i class="fa falist fa-power-off"></i>
							Logout</a></li>
				</ul></li>
		</ul>

	</div>