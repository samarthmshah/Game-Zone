<!doctype html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">  <!-- As in slidebar -->
<title>Game-Zone</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/slick.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="fonts/typicons/typicons.css">
<link rel="stylesheet" href="css/mystyle.css">

<script src="js/modernizr.custom.js"></script>

</head>

<body class="off-canvas">
<div id="left-border"></div>
<div id="right-border"></div>
<div id="top-border"></div>
<div id="bottom-border"></div>
<div id="fixed-bottom"  class="sb-slide"></div>

	<!--  Header -->
	<%@include file="buyer_header.jsp"%>
	<!--  END Header -->
<div id="sb-site">
  <div id="main">
    <div id="loadergif">
      <div class="loader">Loading...</div>
    </div>
    
    <div id="content">
      <div class="container">
      
     <!-- <SLIDER> -->
   	<%@include file="buyer_gameslider.jsp" %>
   	<!-- </SLIDER> -->
      </div>
    </div>
    
    <!--  Footer -->
   <%@include file="footer.jsp" %>
    <!-- END Footer --> 
  </div>
</div>

<!--  The games in the cart will be showed here. This comes after the sb-site ends. -->
<%@include file="buyer_slidebar_cart.jsp" %>
<!-- End slidebar. -->

<!--  The main menu will be showed here. This comes after the sb-site ends. -->
<%@include file="buyer_main_menu.jsp" %>
<!-- End slidebar. -->

<!-- Latest compiled and minified JavaScript --> 
<script src="js/jquery-1.11.0.min.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.inview.js"></script> 
<script src="js/slick.min.js"></script> 
<script src='js/jquery.magnific-popup.min.js'></script> 
<script src='js/jquery.catslider.js'></script> 
<script src="js/slidebars.min.js"></script> 
<script src="js/bootstrap-select.min.js"></script> 
<script src="js/myscript.js"></script>
<script>
$("#search").keyup(function(event){
    if(event.keyCode == 13){
        $("#search").click();
    }
});
</script>
</body>

</html>