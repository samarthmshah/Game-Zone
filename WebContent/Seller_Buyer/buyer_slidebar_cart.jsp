<%@page import="Model.CartDAO"%>
<%@page import="java.util.List"%>
<%@page import="VO.BuyerVO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="sb-slidebar sb-right">
	<div class="shopping-cart-mini canvas-content">
		<button type="button" class="mfp-close sb-close">×</button>
		<p class="title">Item(s) in your cart</p>
		
		<%
		@SuppressWarnings("rawtypes")
		List universal_mycart = CartDAO.loadCart(((BuyerVO)session.getAttribute("userInfoObj")).getBuyer_id()); // every fkn time.
	 	if(universal_mycart != null && universal_mycart.size() > 0){
	 		pageContext.setAttribute("universal_mycart", universal_mycart);
	 %>
	 <ul class="mini-products-list">
			 <c:forEach var="i" items="${universal_mycart }">
             <c:set var="universalCartvo" value="${i[0] }"></c:set>
             <c:set var="universalGamevo" value="${i[1] }"></c:set>
             <c:set var="total_cost" value="${universalCartvo.game_quantity * universalGamevo.game_price }"></c:set>
			<li class="item"><a href="#" class="product-image"><img
					class="img-responsive" src="images/posters/${universalGamevo.game_poster_name }"></a>
				<div class="product-details">
					<a href="<%=request.getContextPath()%>/CartController?flag=deleteFromCart&cart_id=${universalCartvo.cart_id}&buyer_id=<%=((BuyerVO)session.getAttribute("userInfoObj")).getBuyer_id()%>"
					 title="Remove This Item" class="btn-remove">
					 <span class="typcn typcn-times"></span>
					</a>
					<p class="product-name">
						<a href="#">${universalGamevo.game_name }</a>
					</p>
					<strong>${universalCartvo.game_quantity }</strong> x <span class="price">$ ${universalGamevo.game_price }</span>
				</div>
			</li>
			</c:forEach>
			<%pageContext.removeAttribute("universal_mycart"); %>
		</ul>

		<!-- Total amt -->
		<p class="total pull-right">
			<span class="label">Total:</span> <span class="price">$ ${total_cost }</span>
		</p>

		<div class="clearfix"></div>
		<div class="actions">
			<a href="<%=request.getContextPath()%>/CartController?flag=loadCart&buyer_id=<%=((BuyerVO)session.getAttribute("userInfoObj")).getBuyer_id()%>" class="btn btn-default" title="Cart">Go to Shopping Cart Page</a>
		</div>
		<%} else{%>
		<ul class="mini-products-list">
		<li class="item">NONE</li>
		</ul>
		<%} %>
		<%session.removeAttribute("universalCart"); %>
	</div>
</div>
