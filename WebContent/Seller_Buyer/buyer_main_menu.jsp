<%@page import="VO.BuyerVO"%>
<% long non_duplicate_buyer_id = ((BuyerVO)session.getAttribute("userInfoObj")).getBuyer_id(); %>
<div class="sb-slidebar sb-left">
	<nav id="main-menu" class="canvas-content">
		<button title="Close (Esc)" type="button" class="mfp-close sb-close">×</button>

		<ul class="mtree mtree-simple">
			<li><a href="<%=request.getContextPath()%>/GameController?flag=showAllGames&userType=buyer">View Games</a></li>
			<li><a href="<%=request.getContextPath()%>/CartController?flag=loadCart&buyer_id=<%=non_duplicate_buyer_id%>">My Shopping Cart</a></li> 
			<li><a href="<%=request.getContextPath()%>/OrderController?flag=loadMyOrders&buyer_id=<%=non_duplicate_buyer_id%>">Order History</a></li>
			<!-- <li><a href="#">Rate </a></li> -->
			<!-- <li><a href="#">Checkout</a></li> -->
			<li><a href="<%=request.getContextPath()%>/SellerController?flag=contactSellerThroughEmail">Contact Sellers</a></li>
			<li><a href="<%=request.getContextPath()%>/SBLogoutServlet">Log Out</a></li>
		</ul>
	</nav>
</div>