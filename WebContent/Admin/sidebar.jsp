<!-- <SIDEBAR> -->
	<div class="sidebar clearfix">

		<ul class="sidebar-panel nav">
			<li class="sidetitle">Functionalities:</li>
			<li><a href="index.jsp"><span class="icon color5"><i
						class="fa fa-home"></i></span>Home</a></li>
			<li><a href="#"><span class="icon color7"><i
						class="fa fa-bars"></i></span>Category<span class="caret"></span></a>
				<ul>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="addCategory.jsp">Add Category</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="<%=request.getContextPath()%>/SubCategoryController?flag=loadCategories">Add SubCategory</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="<%=request.getContextPath()%>/CategoryController?flag=load">Edit Categories</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="<%=request.getContextPath()%>/SubCategoryController?flag=loadSubCategories">Edit SubCategories</a></li>
				</ul></li>

			<li><a href="#"><span class="icon color7"><i
						class="fa fa-bars"></i></span>Games<span class="caret"></span></a>
				<ul>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="<%=request.getContextPath()%>/GameController?flag=showAllGames&userType=admin">Edit Games</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="#">Delete Games</a></li>
				</ul></li>
			<li><a href="#"><span class="icon color7"><i
						class="fa fa-bars"></i></span>Sellers<span class="caret"></span></a>
				<ul>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="<%=request.getContextPath()%>/SellerController?flag=load&url=seller_approval.jsp">Approve/ Decline Sellers</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="<%=request.getContextPath()%>/SellerController?flag=load&url=view_sellers.jsp">View Sellers</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="<%=request.getContextPath()%>/SellerController?flag=load&url=contact_sellers.jsp">Contact Sellers</a></li>
				</ul></li>
			<li><a href="#"><span class="icon color7"><i
						class="fa fa-bars"></i></span>Buyers<span class="caret"></span></a>
				<ul>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="<%=request.getContextPath()%>/BuyerController?flag=load&url=buyer_approval.jsp">Approve/ Decline Buyers</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="<%=request.getContextPath()%>/BuyerController?flag=load&url=view_buyers.jsp">View Buyers</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="<%=request.getContextPath()%>/BuyerController?flag=load&url=contact_buyers.jsp">Contact Buyers</a></li>
				</ul></li>
		</ul>
	</div>
	<!-- </SIDEBAR> -->