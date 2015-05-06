<%@page import="VO.BuyerVO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="product">
	<a
		href="#"
		class="product-image"> <img
		src="images/posters/${i.game_poster_name }"
		style="height: 225px; width: 225px" />
	</a>
	<div class="info">
		<h5>
			<span class="title"> <a
				href="#">${i.game_name }</a>
			</span>
		</h5>
		<c:set var="seller_name" value="${i.seller_id.getCompanyname() }"></c:set>
		<div class="description in-row">
			<strong>By ${seller_name }</strong>
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
			<strong>Video URL: </strong> <a href="${i.game_youtube_url }"
				target="_blank">Click here</a>
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
		<%
                  }
                  %>
		<div class="description in-row">
			<strong>Description:</strong> ${i.game_description }
		</div>
		<c:choose>
			<c:when test="${i.game_stock > 0 }">
				<div class="description in-row">
				<form action="<%=request.getContextPath()%>/CartController"
				method="POST" id="add2cart" class="form-inline">
					
					<div class="form-group">
							<input type="number" class="form-control form-control-line"
							placeholder="Quantity" name="game_quantity" min="1" max="${i.game_stock }"
							id="game_quantity" required="required" value="1" style="width: 40px;">
					</div>
					<input type="hidden" name="flag" value="insert">
					<input type="hidden" name="buyer_id" value="<%= ((BuyerVO)session.getAttribute("userInfoObj")).getBuyer_id()%>">
					<input type="hidden" name="game_id" value="${i.game_id}">
					<button type="submit" class="btn btn-danger" style="margin-left: 15px;">Add to Cart</button>
				</form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="description in-row">
					<h5>Sorry! Out of Stock!</h5>
					<a
						href="<%=request.getContextPath()%>/SellerController?flag=contactSellerThroughEmail">Message ${seller_name } about Re-stocking.</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>