<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:forEach var="i" items="${sessionScope.gameInfo}">
<%
System.out.println("quickiew page");
%>
<div class="product-preview-popup">
	<div class="row" style="overflow: hidden;">
		<div class="col-sm-6">
			<div class="popup-carousel">
				<div class="item">
					<img src="images/posters/${i.game_poster_name }" alt="" />
				</div>
			</div>
			<div class="popup-carousel-thumbs arrow-small">
				<div class="item">
					<img src="images/posters/${i.game_poster_name }" alt="" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="product-name">
				<h2>${i.game_name }</h2>
			</div>
			<div class="price">
				<span class="price-new"><span class="currency">$</span>${i.game_price }
				</span>
			</div>

			<div class="product-stock">
			<c:choose>
				<c:when test="${i.game_stock > 0}">In Stock</c:when>
				<c:otherwise>Out Of stock</c:otherwise>
			</c:choose>
				<strong>In Stock</strong>
			</div>
			<div class="line-divider"></div>
			<strong>Game Information:</strong>
			<br/>
			<p>Description: <strong>${i.game_description }</strong></p>
			
			<div class="line-divider"></div>
			<div>
				<form action="<%=request.getContextPath()%>/GameController?flag=productPage&game_id=${i.game_id}" class="form-inline">
					<button type="submit" class="btn-buy"
						onClick="window.location.href=window.location.href">
						<span class="icon typcn typcn-shopping-cart"></span><span
							class="text">GO TO PRODUCT PAGE</span>
					</button>
				</form>
			</div>
		</div>
	</div>
</div>
</c:forEach>