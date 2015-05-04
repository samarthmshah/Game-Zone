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
				if (msg.equals("The seller has been deleted successfully.")) {
					response.setContentType("text/html");
					out.println("<div class=\"col-md-12\">");
					out.println("<p class=\"bg-success\">");
					out.println(msg);
					out.println("</p></div>");
				}
				else{
					response.setContentType("text/html");
					out.println("<div class=\"col-md-12\">");
					out.println("<p class=\"bg-danger\">");
					out.println(msg);
					out.println("</p></div>");
				}
				session.removeAttribute("msg");
			}
		%>
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-title">All the sellers in the database.</div>
					<div class="panel-body table-responsive">
						<table id="seller" class="table display compact">
							<thead>
								<tr>
									<th>Company Name</th>
									<th>Name</th>
									<th>Username</th>
									<th>Email</th>
									<th>Phone Number</th>
									<th>Status</th>
									<th>Delete</th>
								</tr>
							</thead>

							<tfoot>
								<tr>
									<th>Company Name</th>
									<th>Name</th>
									<th>Username</th>
									<th>Email</th>
									<th>Phone Number</th>
									<th>Status</th>
									<th>Delete</th>
								</tr>
							</tfoot>
							
							<tbody>
								<c:forEach var="i" items="${sessionScope.sellerList }">
									<tr>
										<td>${i.companyname }</td>
										<td>${i.firstname } ${i.lastname }</td>
										<td>${i.username }</td>
										<td>${i.email }</td>
										<td>${i.phNo }</td>
										<td>
										<c:choose>
											<c:when test="${i.status == 0}">Declined</c:when>
											<c:otherwise>Approved</c:otherwise>
										</c:choose>
										</td>
										<td>
                							<a href="<%=request.getContextPath()%>/SellerController?seller_id=${i.seller_id }&flag=delete&url=view_sellers.jsp">
                								<button type="button" class="btn btn-square btn-danger btn-icon" data-toggle="tooltip" 
												data-placement="top" data-original-title="Delete">
                									<i class="fa fa-remove"></i>
                								</button>
                							</a>
									</tr>
								</c:forEach>
								<%session.removeAttribute("sellerList"); %>
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
    $('#seller').dataTable({
    	"scrollX": true
    } );
} );
</script>

</body>
</html>