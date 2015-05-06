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
	 <%
          String msg = (String) session.getAttribute("msg");
			if (msg != null) {
				if (msg.equals("The Game has been successfully removed from your shopping cart") 
					|| msg.equals("The cart has been updated successfully")
					|| msg.equals("Cart successfully cleared")) {
					response.setContentType("text/html");
					out.println("<div class=\"col-md-12\">");
					out.println("<p class=\"bg-success text-center\">");
					out.println("<strong>"+msg+"</strong>");
					out.println("</p></div>");
					session.removeAttribute("msg");
				}
			}
          %>
	 <%
	 	@SuppressWarnings("unchecked")
	 	List<Object> mycart = (List<Object>)session.getAttribute("myCart");
	 	if(mycart != null && mycart.size() != 0){
        	double total_game_amt = 0d,	// Initialize this.
        			total_shipping_amt=0d,
        			total_overall_amt=0d;
        %>
	 %>
      <div class="container">
        <section id="shopping-cart-section">
          <h1><span>Your Shopping Cart</span></h1>
          <div class="row">
            <div class="col-md-8 col-lg-offset-2">
              <div class="rect">
                <div class="table-products-block">
                
                  <table class="table-products">
                    <tbody>
                    
                    <c:forEach var="i" items="${sessionScope.myCart }">
                    <c:set var="cartvo" value="${i[0] }"></c:set>
                    <c:set var="gamevo" value="${i[1] }"></c:set>
                    <c:set var="game_cost" value="${cartvo.game_quantity * gamevo.game_price }"></c:set>
                    <c:set var="shipping_cost" value="${gamevo.game_shipping_charges }"></c:set>
                    <% 
                    	total_game_amt += (Double)pageContext.getAttribute("game_cost");
                    	total_shipping_amt += (Double)pageContext.getAttribute("shipping_cost");
                    	total_overall_amt += (Double)pageContext.getAttribute("game_cost") + (Double)pageContext.getAttribute("shipping_cost");
                    %>
                      <tr>
                        <td class="product-image">
                        <a href="#"><img src="images/posters/${gamevo.game_poster_name }" alt=""></a>
                        <td class="product-name hidden-xs"><a href="product.html">${gamevo.game_name }</a></td>
                        <td class="product-qty">
                         <form action="<%=request.getContextPath()%>/CartController?flag=updateCartItem" class="form-inline" method="post">
                             <label>Qty:</label>
				          	 <input type="number" class="form-control input-quantity" 
				          	 min="1" max="${gamevo.game_stock }" placeholder="${cartvo.game_quantity }" id="quantity" name="quantity"
				          	 onChange="document.getElementById('#quantity').click()">
				          	 <input type="hidden" name="cart_id" value="${cartvo.cart_id }">
				          	 <input type="hidden" name="buyer_id" value="<%=buyer_id%>">
				    	 </form>
                        </td>
                        <td class="product-price">$ ${gamevo.game_price } </td>
                        <td class="product-delete">
                        	<a class="typcn typcn-times" 
                        		href="<%=request.getContextPath()%>/CartController?flag=deleteFromCart&cart_id=${cartvo.cart_id}&buyer_id=<%=buyer_id%>">
                        	</a>
                        </td>
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                  
                  <div class="clearfix"> <a href="<%=request.getContextPath()%>/GameController?flag=showAllGames&userType=buyer" class="btn btn-primary pull-left"
                  style="margin-left: 100px;">CONTINUE SHOPPING</a> 
                  <span class="pull-left btn-space">&nbsp;</span > 
                  <a href="<%=request.getContextPath()%>/CartController?flag=clearCart&buyer_id=<%=buyer_id%>" 
                  style="margin-left: 100px;" class="btn  btn-danger pull-left">CLEAR SHOPPING CART</a>
                    </div>
                </div>
              </div>
            </div>
          </div>
          <div class="space"></div>
          <div class="row">
            <div class="col-md-4 col-lg-offset-2">
               <div class="rect">
                <h4 class="default-font upper">DISCOUNT CODES</h4>
                <form action="#">
                  <input type="text" class="form-control" placeholder="Under construction..." disabled="disabled">
                  <div class="space space-lg"></div>
                  <button class="btn btn-primary" type="submit" disabled="disabled">APPLY COUPON</button>
                </form>
              </div>
            </div>
            <div class="col-md-4 ">
             
              <div class="divider divider-sm visible-sm"></div>
              <div class="total-block">
                <table class="table table-total">
                  <tr>
                    <th class="text-right">Subtotal</th>
                    <th class="td-divider"></th>
                    <th> $ <%=total_game_amt %></th>
                  </tr>
                  <tr>
                    <th class="text-right">Shipping Costs</th>
                    <th class="td-divider"></th>
                    <th> $ <%=total_shipping_amt %></th>
                  </tr>
                  <tr>
                    <th class="text-right">Tax</th>
                    <th class="td-divider"></th>
                    <th>+ 8%</th>
                  </tr>
                  
                  <tr style="border-top-style: groove;">
                    <th class="text-right">TOTAL (Inc. Taxes and Shipping.)</th>
                    <th class="td-divider"></th>
                    <th>$ <%=new DecimalFormat("#.##").format(total_overall_amt * 1.08) %></th>
                  </tr>
                </table>
                <div class="text-center">
                <form action="<%=request.getContextPath()%>/OrderController" method="post">
	                <input type="hidden" name="flag" value="loadCheckout">
	                <input type="hidden" name="buyer_id" value="<%=buyer_id%>">
	                <input type="hidden" name="total_overall_cost" value="<%=total_overall_amt * 1.08%>">
	                <button class="btn btn-success btn-lg fullwidth" type="submit"> CHECKOUT</button>
                </form>
                  <div class="space space-lg"></div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      
      <%
	 	}
	 	else{
	   %>
	  <div class="space-xl"></div>
      <div class="container">
        <div class="text-center">
          <div class="big-icon"><span class="typcn typcn-shopping-cart"></span></div>
          <h1 class="no-margin">Shopping Cart is Empty </h1>
          <p class="font-xl">You have no items in your shopping cart.</p>
          <div class="space-lg"></div>
          <a href="buyer_index.jsp" class="btn btn-primary btn-white">HOME </a>
          <div class="space-xl"></div>
        </div>
      </div> 
	  <%
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