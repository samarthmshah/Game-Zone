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
	<div id="fixed-bottom" class="sb-slide"></div>


	<!--  Header -->
	<%@include file="buyer_header.jsp"%>
	<!--  END Header -->

	<div id="sb-site">
		<div id="main">
			<div id="loadergif">
				<div class="loader">Loading...</div>
			</div>

    <div id="content"> 
      
      <!-- Two columns content -->
      <div class="container">
        <div class="row"> 
          
          <!-- Center column -->
          <div class="col-sm-8 col-md-9 col-lg-9" id="content-center">
            <div class="category-info text-center">
              <h1><span>Buy Games</span></h1>
              <p>Games Shown according to the Category. Please click on the Categories to filter results.</p>
            </div>
            <!-- Filters -->
            <div class="filters-panel">
              <div class="row"> </div>  <!-- SPACE HACK HUeeehueHUAH -->
            </div>
            <!-- //end Filters --> 
            
            <!-- Listing products -->
            <div class="row products-grid show-2 products-list-in-row">
								
				<c:forEach var="i" items="${sessionScope.gamesByCat}">
               <%@include file="buyer_insideForEachLoop.jsp" %>
              </c:forEach>
              <%session.removeAttribute("gamesByCat");%>
            </div>
            <!-- END Listing products -->
             </div>
          <!-- Left column -->
          <div class="col-sm-4 col-md-3 col-lg-3" id="content-aside">
            <div class="block shop-by">
              <h3 class="block-title">CATEGORY</h3>
              <div class="block-content">
                <ul class="mtree mtree-simple">
                	<li><a href="<%=request.getContextPath()%>/GameController?flag=showAllGames&userType=buyer">ALL</a></li>
                
                	<c:forEach var="i" items="${sessionScope.categoryList }">
                 	 <li><a href="<%=request.getContextPath()%>/GameController?flag=showGamesByCat&cat_id=${i.cat_id}&userType=buyer">${i.cat_name }</a></li>
                	</c:forEach>
               	<%session.removeAttribute("categoryList"); %>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
			<!--  Footer -->
			<%@include file="footer.jsp"%>
			<!-- END Footer -->
		</div>
	</div>
<!--  The games in the cart will be showed here. This comes after the sb-site ends. -->
<%@include file="buyer_slidebar_cart.jsp" %>
<!-- End slidebar. -->
	<!--  The main menu will be showed here. This comes after the sb-site ends. -->
	<%@include file="buyer_main_menu.jsp"%>
	<!-- End slidebar. -->

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