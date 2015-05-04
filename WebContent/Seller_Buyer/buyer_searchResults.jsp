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
              <h1><span>Search Results</span></h1>
              <h5>
               Here are the results based on your search query... 
              </h5>
            </div>
            <!-- Filters -->
            <div class="filters-panel">
              <div class="row"> </div>  <!-- SPACE HACK HUAHUAH -->
            </div>
            <!-- //end Filters --> 
            
            <!-- Listing products -->
            <div class="row products-grid show-2 products-list-in-row">
							
				<c:forEach var="i" items="${sessionScope.searchResults}">
              <div class="product">
                <a href="<%=request.getContextPath()%>/GameController?flag=productPage&game_id=${i.game_id}" class="product-image">
                	<img src="images/posters/${i.game_poster_name }" style="height: 225px; width:225px"/>
                </a>
                <div class="info">
                  <span class="title">
                  <a href="#">${i.game_name }</a> <strong>By ${i.seller_id.getCompanyname() }</strong></span> 
                  	<span class="product-rating in-row">
                  	<span class="typcn typcn-star"></span>
                  	<span class="typcn typcn-star"></span>
                  	<span class="typcn typcn-star"></span>
                  	<span class="typcn typcn-star"></span>
                  	<span class="typcn typcn-star"></span>
                  </span> 
                  <span class="price"><span class="currency">Price: $</span>${i.game_price }</span>
                  <div class="description in-row">Description: ${i.game_description }</div>
                  <div class="description in-row"><a href="<%=request.getContextPath()%>/GameController?"><button type="button" class="btn btn-md btn-danger">Add to Cart</button></a></div>
                </div>
              </div>
              </c:forEach>
              <%session.removeAttribute("searchResults"); %>
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