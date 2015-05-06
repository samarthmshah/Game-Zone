<%@page import="VO.SellerVO"%>
<%
long non_duplicate_seller_id = ((SellerVO)session.getAttribute("userInfoObj")).getSeller_id();
%>
<div class="sb-slidebar sb-left">
	<nav id="main-menu" class="canvas-content">
		<button title="Close (Esc)" type="button" class="mfp-close sb-close">×</button>
		
		<ul class="mtree mtree-simple">
			<li><a href="<%=request.getContextPath()%>/GameController?flag=loadCatAndScat">Add Game</a></li>
			<li><a href="<%=request.getContextPath()%>/GameController?flag=showAllGamesFromSeller&seller_id=<%=non_duplicate_seller_id%>">View My Games</a></li>
			<li><a href="<%=request.getContextPath()%>/BuyerController?flag=contactBuyerThroughEmail">Contact Buyers</a></li>
			<li><a href="<%=request.getContextPath()%>/OrderController?flag=loadSellerOrders&seller_id=<%=non_duplicate_seller_id%>">Update Order/ Shipping status</a></li>
			<li><a href="<%=request.getContextPath()%>/SBLogoutServlet">Log Out</a></li>
		</ul>
	</nav>
</div>