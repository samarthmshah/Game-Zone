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
	<%@include file="seller_header.jsp"%>
	<!--  END Header -->
	<%
	long seller_id = svo.getSeller_id();
	%>

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
              <h1><span>Your games</span></h1>
              <p>The set of games that you sell</p>
            </div>
            <!-- Filters -->
            <div class="filters-panel">
              <div class="row">
                <!-- <div class="col-sm-3 col-md-4 hidden-xs">
                  <div class="view-mode"><a href="#" class="view-grid active"><span class="typcn typcn-th-small"></span></a> <a href="#" class="view-list"><span class="typcn typcn-th-list"></span></a> </div>
                </div>
                <div class="col-xs-6 col-sm-5 col-md-4"> <span>Show per page:</span>
                  <div class="btn-group"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">15</a>
                    <ul class="dropdown-menu">
                      <li><a href="#">30</a></li>
                      <li><a href="#">50</a></li>
                      <li><a href="#">100</a></li>
                      <li><a href="#">All</a></li>
                    </ul>
                  </div>
                </div>
                <div class="col-xs-6 col-sm-4">
                  <div class="pagination pull-right"><a href="#">1</a><a href="#">2</a><a href="#" class="hidden-sm hidden-xs">3</a><a href="#" class="hidden-sm hidden-xs">4</a><a href="#" class="next">next</a></div>
                </div> -->
              </div>
            </div>
            <!-- //end Filters --> 
            
            <!-- Listing products -->
            <div class="row products-grid show-3">
              <div class="product">
                <a href="product.html" class="product-image">
                	<img src="images/posters/COD.jpg" style="height: 200px"/>
                </a>
                <div class="info">
                  <div class="product-hover"> <a  href="<%=request.getContextPath()%>/GameController?flag=quickview?game_id="
                    class="ajax-popup-link quick-view"><span>View More Info</span></a>
                  </div>
                  <span class="title">
                  <a href="#">game_name</a></span> 
                  <span class="product-rating in-row"><span class="typcn typcn-star"></span><span class="typcn typcn-star"></span><span class="typcn typcn-star"></span><span class="typcn typcn-star"></span><span class="typcn typcn-star"></span></span> 
                  <span class="price"><span class="currency">$</span>game_price</span>
                  <div class="description in-row">game_description</div>
                </div>
              </div>
              
              <!-- <div class="product">
                <a href="product.html" title="lectus ipsum" class="product-image"><img src="images/posters/gta5.jpg" style="height: 200px"/></a>
                <div class="info">
                  <div class="product-hover"> <a  href="ajax_view-product.html"  class="ajax-popup-link quick-view"><span>Quick View</span></a>
                  </div>
                  <div class="product-options-outer">
                    <div class="product-options"> <span class="disable icon icon-size">S</span><span class="icon icon-size">M</span><span class="disable icon icon-size">L</span><span class="icon icon-size">XL</span><span class="icon icon-size">XXL</span></div>
                    <div class="product-options"><span class="icon icon-color icon-color-chocolate"></span><span class="icon icon-color icon-color-black"></span><span class="icon icon-color icon-color-gray"></span> <span class="icon icon-color icon-color-brown"></span></div>
                  </div>
                  <span class="title"><a href="#">Vans Unisex Era Sneaker</a></span> <span class="product-rating in-row"><span class="typcn typcn-star"></span><span class="typcn typcn-star"></span><span class="typcn typcn-star"></span><span class="typcn typcn-star"></span><span class="typcn typcn-star"></span></span> <span class="price"><span class="currency">$</span>42.84</span>
                  <div class="description in-row">Nulla neque purus, ullamcorper nec, eleifend at, fermentum ut, turpis. Mauris et ligula quis erat dignissim imperdiet. Integer ligula magna, dictum et, pulvinar non, ultricies ac, nibh. </div>
                  <div class="product-options-outer in-row">
                    <div class="product-options"> <span class="disable icon icon-size">S</span><span class="icon icon-size">M</span><span class="disable icon icon-size">L</span><span class="icon icon-size">XL</span><span class="icon icon-size">XXL</span></div>
                    <div class="product-options"><span class="icon icon-color icon-color-chocolate"></span><span class="icon icon-color icon-color-black"></span><span class="icon icon-color icon-color-gray"></span> <span class="icon icon-color icon-color-brown"></span></div>
                  </div>
                  <button type="submit" class="btn btn-default in-row" onclick="window.location.href=window.location.href">ADD TO CART</button>
                </div>
              </div> -->
            </div>
            <!-- END Listing products -->
             </div>
          <!-- Left column -->
          <div class="col-sm-4 col-md-3 col-lg-3" id="content-aside">
            <div class="block shop-by">
              <h3 class="block-title">CATEGORY</h3>
              <div class="block-content">
                <ul class="mtree mtree-simple">
                  <li><a href="#">SHOES </a>
                    <ul>
                      <li><a href="#"> women shoes</a>
                        <ul>
                          <li><a href="#"> Athletic Shoes</a></li>
                          <li><a href="#"> Boots</a></li>
                          <li><a href="#"> Casual Shoes</a></li>
                        </ul>
                      </li>
                      <li><a href="#"> men shoes </a>
                        <ul>
                          <li><a href="#"> Athletic Shoes</a></li>
                          <li><a href="#"> Boots</a></li>
                          <li><a href="#"> Casual Shoes</a></li>
                        </ul>
                      </li>
                    </ul>
                  </li>
                  <li><a href="#">BAGS</a></li>
                  <li><a href="#"> BELTS</a></li>
                  <li><a href="#">ACCESSORIES</a></li>
                </ul>
              </div>
            </div>
            <div class="block block-price">
              <h3 class="block-title">Price</h3>
              <div class="block-content">
                <ul class="list-options">
                  <li><a href="#">$0.00 - $10,000.00 (13)</a></li>
                  <li><a href="#">$10,000.00 - $20,000.00 (2)</a></li>
                </ul>
              </div>
            </div>
            <div class="block block-color">
              <h3 class="block-title">Color</h3>
              <div class="block-content">
                <ul class="list-options">
                  <li><a href="#">Black (9)</a></li>
                  <li><a href="#">Brown (2)</a></li>
                  <li><a href="#">White (1)</a></li>
                  <li><a href="#">Gray (3)</a></li>
                </ul>
              </div>
            </div>
            <div class="block block-tags">
              <h3 class="block-title">Popular tags</h3>
              <div class="block-content">
                <ul class="tags-list">
                  <li><a href="#">Apparel</a></li>
                  <li><a href="#">Design</a></li>
                  <li><a href="#">Models</a></li>
                  <li><a href="#">Clothing</a></li>
                  <li><a href="#">Underwear</a></li>
                  <li><a href="#">Fashion industry</a></li>
                  <li><a href="#">Evening dresses</a></li>
                  <li><a href="#">Dresses</a></li>
                  <li><a href="#">Accessories</a></li>
                  <li><a href="#">Outerwear</a></li>
                  <li><a href="#">Handbags</a></li>
                </ul>
              </div>
            </div>
            <div class="block block-compare">
              <h3 class="block-title">Compare products</h3>
              <div class="block-content">You have no items to Compare</div>
            </div>
            <div class="block block-poll">
              <h3 class="block-title">Community Poll</h3>
              <div class="block-content">
                <p><strong>What is your favorite Magento feature?</strong></p>
                <form action="#">
                  <div class="radio-row">
                    <input type="radio" id="rb1" name="rb" value="" checked>
                    <label for="rb1">Layered Navigation</label>
                  </div>
                  <div class="radio-row">
                    <input type="radio" id="rb2" name="rb" value="">
                    <label for="rb2">Price Rules</label>
                  </div>
                  <div class="radio-row">
                    <input type="radio" id="rb3" name="rb" value="">
                    <label for="rb3">Category Management</label>
                  </div>
                  <div class="radio-row">
                    <input type="radio" id="rb4" name="rb" value="">
                    <label for="rb4">Compare Products</label>
                  </div>
                  <br>
                  <button class="btn">Vote</button>
                </form>
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

	<!--  The main menu will be showed here. This comes after the sb-site ends. -->
	<%@include file="seller_main_menu.jsp"%>
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
