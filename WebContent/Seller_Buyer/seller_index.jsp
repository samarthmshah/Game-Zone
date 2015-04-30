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
<%@include file="seller_header.jsp"%>
<!--  END Header -->

<div id="sb-site">
  <div id="main">
    <div id="loadergif">
      <div class="loader">Loading...</div>
    </div>
    
    <div id="content">
      <div class="container">
      		<!-- CONTENT GOES HERE. -->
      		<h1><span>Welcome, Seller</span></h1>
        <div class="text-center">
          <h2>Things you can do on GameZone. </h2>
        </div>
        <div class="row">
          <div class="col-sm-6 col-md-3 col-lg-3 text-center">
            <div class="icon-circle icon-xl"><span class="typcn typcn-briefcase"></span></div>
            <h5 class="default-font upper">Sell your games</h5>
          </div>
          
          <div class="col-sm-6 col-md-3 col-lg-3 text-center">
            <div class="icon-circle icon-xl"><span class="typcn typcn-arrow-sync-outline"></span></div>
            <h5 class="default-font upper">View feedbacks</h5>
          </div>
          <div class="col-sm-6 col-md-3 col-lg-3 text-center">
            <div class="icon-circle icon-xl"><span class="typcn typcn-credit-card"></span></div>
            <h5 class="default-font upper">Manage Orders</h5>
          </div>
          
          <div class="col-sm-6 col-md-3 col-lg-3 text-center">
            <div class="icon-circle icon-xl"><span class="typcn typcn-messages"></span></div>
            <h5 class="default-font upper">24/7 Support</h5>
          </div>
        </div>
      </div>
    </div>
    
    <!--  Footer -->
   <%@include file="footer.jsp" %>
    <!-- END Footer --> 
  </div>
</div>

<!--  The main menu will be showed here. This comes after the sb-site ends. -->
<%@include file="seller_main_menu.jsp" %>
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
</body>

</html>