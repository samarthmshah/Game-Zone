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
              <h1><span>All the Games</span></h1>
              <p>Click on Category names to filter results.</p>
            </div>
            <!-- Filters -->
            <div class="filters-panel">
              <div class="row"> </div>  <!-- SPACE HACK HUAHUAH -->
            </div>
            <!-- //end Filters --> 
            
            <!-- Listing products -->
            <div class="row products-grid show-2 products-list-in-row">
								
				<c:forEach var="i" items="${sessionScope.allGames}">
               <div class="product">
                <a href="<%=request.getContextPath()%>/GameController?flag=productPage&game_id=${i.game_id}" class="product-image">
                	<img src="images/posters/${i.game_poster_name }" style="height: 225px; width:225px"/>
                </a>
                <div class="info">
                  <h5>
					<span class="title"> 
					<a href="<%=request.getContextPath()%>/GameController?flag=productPage&game_id=${i.game_id}">${i.game_name }</a>
					</span>
				 </h5>
				 <c:set var="sellerID" value="${i.seller_id.getSeller_id() }"/>
				 <c:set var="seller_name" value="${i.seller_id.getCompanyname() }"></c:set>
				 <% boolean itsMe = seller_id == ((Long)pageContext.getAttribute("sellerID")); %>
					<div class="description in-row">
						<% if(itsMe){ %>
								<strong>By You</strong>
						<%	
							} else{ %>
								<strong>By ${seller_name }</strong>		
						<% } %>
						
				  </div>
                  <div class="description in-row">
					<strong>Price: </strong> $ ${i.game_price }
			      </div>
			      <div class="description in-row">
					<strong>Console Type: </strong> ${i.game_console }
				</div>
				<div class="description in-row">
					<strong>Ratings: </strong> ${i.game_ratings } out of 5
				</div>
				<div class="description in-row">
					<strong>Genre: </strong> ${i.cat_id.getCat_name() }
				</div>
				<c:set var="scat_id" value="${i.scat_id.getScat_id() }"></c:set>
				<%
                  Long scatID = (Long) pageContext.getAttribute("scat_id");
                  if(scatID != null){
                  %>
				<div class="description in-row">
					<strong>Sub-category: </strong> ${i.scat_id.getScat_name() }
				</div>
				<%} %>
				
                  <div class="description in-row">
					<strong>Description:</strong> ${i.game_description }
				</div>
                 <c:set var="game_stock" value="${i.game_stock }"></c:set>
                  <%
                  if(itsMe){
                  %>
                  <c:choose>
                  	<c:when test="${game_stock > 0}">
                  	<c:choose>
                  		<c:when test="${game_stock < 10 }">
                  			<div class="description in-row"><strong>Refill the stock! Only ${game_stock } left!</strong></div>
                  		</c:when>
                  		<c:otherwise>
                  			<div class="description in-row"><strong>${game_stock } left!</strong></div>
                  		</c:otherwise>
                  	</c:choose>
                  	</c:when>
                  	<c:otherwise>
                  		<div class="description in-row"><strong>Out of stock!</strong></div>
                  	</c:otherwise>
                  </c:choose>
                  <div class="description in-row" style="float:left"><a href="<%=request.getContextPath()%>/GameController?flag=editGame&game_id=${i.game_id}&userType=seller"><button type="button" class="btn btn-md btn-warning">Update</button></a></div>
                  <div class="description in-row"><a href="<%=request.getContextPath()%>/GameController?flag=deleteGame&seller_id=<%=seller_id %>&game_id=${i.game_id}&userType=seller"><button type="button" class="btn btn-md btn-danger">DELETE</button></a></div>
                  <%} %>
                </div>
              </div>
              </c:forEach>
              <%session.removeAttribute("allGames"); %>
            </div>
            <!-- END Listing products -->
             </div>
          <!-- Left column -->
          <div class="col-sm-4 col-md-3 col-lg-3" id="content-aside">
            <div class="block shop-by">
              <h3 class="block-title">CATEGORY</h3>
              <div class="block-content">
                <ul class="mtree mtree-simple">
                 	 <li><a href="<%=request.getContextPath()%>/GameController?flag=showAllGames&userType=seller">ALL</a></li>
                	
                	<c:forEach var="i" items="${sessionScope.categoryList }">
                 	 <li><a href="<%=request.getContextPath()%>/GameController?flag=showGamesByCat&cat_id=${i.cat_id}&userType=seller">${i.cat_name }</a></li>
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