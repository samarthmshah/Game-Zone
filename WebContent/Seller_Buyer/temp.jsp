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
</head>
<body>

 <form action="<%=request.getContextPath()%>/GameController?flag=search" method="post">
          	 <input type="text" class="form-control input-search" placeholder="SEARCH" id="search" name="search"
          	 onkeyup="if (event.keyCode == 13) document.getElementById('#search').click()">
     </form>
<script src="js/jquery-1.11.0.min.js"></script> 

</body>
</html>
