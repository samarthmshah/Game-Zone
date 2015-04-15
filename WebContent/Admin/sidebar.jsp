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
						href="<%=request.getContextPath()%>/SubCategoryController?flag=load">Add SubCategory</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="editCategory.jsp">Edit Categories</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="editSubCategory.jsp">Edit SubCategories</a></li>
				</ul></li>

			<li><a href="#"><span class="icon color7"><i
						class="fa fa-bars"></i></span>Games<span class="caret"></span></a>
				<ul>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="editProducts.jsp">Edit Products</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="deleteProducts.jsp">Delete Products</a></li>
				</ul></li>
			<li><a href="#"><span class="icon color7"><i
						class="fa fa-bars"></i></span>Sellers<span class="caret"></span></a>
				<ul>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="approveSeller.jsp">Approve/ Decline Sellers</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="viewSellers.jsp">View Sellers</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="editSellers.jsp">Edit Sellers</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="contactSellers.jsp">Contact Sellers</a></li>
				</ul></li>
			<li><a href="#"><span class="icon color7"><i
						class="fa fa-bars"></i></span>Buyers<span class="caret"></span></a>
				<ul>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="viewBuyers.jsp">View Buyers</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="editBuyers">Edit Buyers</a></li>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="contactBuyers.jsp">Contact Buyers</a></li>
				</ul></li>
			<li><a href="#"><span class="icon color7"><i
						class="fa fa-bars"></i></span>Orders<span class="caret"></span></a>
				<ul>
					<li><span class="icon color7"><i class="fa fa-circle-o"></i></span><a
						href="viewOrders.jsp">View Orders</a></li>
				</ul></li>
		</ul>
	</div>
	<!-- </SIDEBAR> -->