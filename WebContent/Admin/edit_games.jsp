<%@include file="header.jsp"%>
<%@include file="sidebar.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!-- <CONTENT> -->

<div class="content">
	<div class="container-padding">
		<div class="row">
		<%
			String msg = (String) session.getAttribute("msg");
			if (msg != null) {
				if (msg.equals("The game is successfully deleted.")) {
					response.setContentType("text/html");
					out.println("<div class=\"col-md-12\">");
					out.println("<p class=\"bg-success\">");
					out.println(msg);
					out.println("</p></div>");
					session.removeAttribute("msg");
				}
			}
		%>
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-title">Games</div>
					<div class="panel-body table-responsive">
						<table id="subcategories" class="table display compact">
							<thead>
								<tr>
									<th>Name</th>
									<th>Company Name</th>
									<th>Price</th>
									<th>Stock</th>
									<th>Ratings</th>
									<th>Action</th>
								</tr>
							</thead>

							<tfoot>
								<tr>
									<th>Name</th>
									<th>Company Name</th>
									<th>Price</th>
									<th>Stock</th>
									<th>Ratings</th>
									<th>Action</th>
								</tr>
							</tfoot>
							
							<tbody>
								<c:forEach var="i" items="${sessionScope.allGames }">
									<tr>
										<td>${i.game_name }</td>
										<td>${i.seller_id.getCompanyname() }</td>
										<td>${i.game_price }</td>
										<td>${i.game_stock }</td>
										<td>${i.game_ratings }</td>
										<td>
										<a href="<%=request.getContextPath()%>/GameController?game_id=${i.game_id }&flag=editGame&userType=admin">
												<button type="button" class="btn btn-square btn-basic btn-icon" data-toggle="tooltip" 
												data-placement="top" data-original-title="Update">
													<i class="fa fa-edit"></i>
												</button>
											</a>
                							<a href="<%=request.getContextPath()%>/SubCategoryController?scat_id=${i.scat_id }&flag=delete&userType=admin">
                								<button type="button" class="btn btn-square btn-danger btn-icon" data-toggle="tooltip" 
												data-placement="top" data-original-title="Delete">
                									<i class="fa fa-remove"></i>
                								</button>
                							</a>
										</td>
									</tr>
								</c:forEach>
								<%session.removeAttribute("allGames"); %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		<%@include file="footer.jsp" %>
</div>
<!-- </CONTENT> -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/plugins.js"></script>
<script type="text/javascript"
	src="js/bootstrap-select/bootstrap-select.js"></script>
<script type="text/javascript"
	src="js/bootstrap-toggle/bootstrap-toggle.min.js"></script>
<script type="text/javascript" src="js/moment/moment.min.js"></script>

<script src="js/datatables/datatables.min.js"></script>

<script>
$(document).ready(function() {
    $('#subcategories').DataTable();
} );
</script>

</body>
</html>