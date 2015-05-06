<!doctype html>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
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
      <div class="container">
        <h1><span>Checkout</span></h1>
      </div>
      
      <%
	 	@SuppressWarnings("unchecked")
	 	List<Object> mycart = (List<Object>)session.getAttribute("myCart");
	 	if(mycart != null && mycart.size() != 0){
	 		double total_game_amt = 0d,	// Initialize this.
        			total_shipping_amt=0d,
        			total_overall_amt=0d;
	 	%>
	 	<c:forEach var="i" items="${sessionScope.myCart }">
           <c:set var="cartvo" value="${i[0] }"></c:set>
		   <c:set var="gamevo" value="${i[1] }"></c:set>
		   <c:set var="game_cost" value="${cartvo.game_quantity * gamevo.game_price }"></c:set>
           <c:set var="shipping_cost" value="${gamevo.game_shipping_charges }"></c:set>
           <% 
             total_game_amt += (Double)pageContext.getAttribute("game_cost");	// Sum of game_MRP
             total_shipping_amt += (Double)pageContext.getAttribute("shipping_cost");	// Sum of shipping of each game
             total_overall_amt += (Double)pageContext.getAttribute("game_cost") + (Double)pageContext.getAttribute("shipping_cost");
           %>
        </c:forEach>	
	 
      <div class="container">
        <div class="row">
        
        <form action="<%=request.getContextPath()%>/OrderController" method="POST">
          <div class="col-md-8">
            <div class="rect nopad">
              <div class="checkout-tab">
                
                <ul class="nav nav-tabs">
                  <li class="active"><a href="#Tab1" data-toggle="tab"><span class="tab-icon typcn typcn-location-outline"></span>Shipping Address</a></li>
                  <li><a href="#Tab2" data-toggle="tab"><span class="tab-icon typcn typcn-plane-outline"></span>Payment Method</a></li>
                </ul>
                
                <div class="tab-content">
                  <div class="tab-pane fade in active" id="Tab1">
                    <h4 class="default-font upper">Shipping Address</h4>
                    <div class="row">
                      <div class="col-md-12">
                      
                        <div class="form-group">
                      		<textarea class="form-control" rows="4" id="shipping_address" name="shipping_address" placeholder="Address (required)..." required="required"></textarea>
						</div>
						
                        <div class="space space-lg"></div>
                        <div class="form-group">
						<input type="text" name="zip" class="form-control" id="zip" placeholder="Your zipcode goes here..." required="required">
						</div>
                      </div>
                    </div>
                    </div>
                  <div class="tab-pane fade" id="Tab2">
                    <h4 class="default-font upper">Payment method</h4>
                    <div class="row">
                      
                      <div class="col-md-12">
                        <input name="paypal" type="text" class="form-control" placeholder="Paypal (Under Construction)" disabled="disabled">
                        <div class="space space-lg"></div>
                      </div>
                    </div>
                     </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-4 ">
            <div class="rect">
              <h4 class="default-font upper">Total</h4>
              <table class="table table-total-simple">
                <thead>
                  <tr>
                    <th> Total</th>
                    <th> $ <%=new DecimalFormat("#.##").format(total_overall_amt * 1.08) %></th>
                  </tr>
                </thead>
               <tbody>
                  <tr>
                    <td> Game MRP Sum: </td>
                    <td> $ <%=total_game_amt %></td>
                  </tr>
                  <tr>
                  <tr>
                    <td> Shipping Charges: </td>
                    <td> $ <%= total_shipping_amt%></td>
                  </tr>
                  <tr>
                    <td> Tax: </td>
                    <td> 8%</td>
                  </tr>
                </tbody>
              </table>
               <input type="hidden" name="flag" value="insert">
               <input type="hidden" name="total_overall_amt" value="<%=total_overall_amt * 1.08%>">
               <input type="hidden" name="buyer_id" value="<%=buyer_id%>">
               <button class="btn btn-success btn-lg fullwidth" type="submit">PURCHASE</button>
            </div>
          </div>
          </form>
        </div>
      </div>
      	<%}
	 	else{
	 		
	 	}
	 %>
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