<%@include file="header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@include file="sidebar.jsp"%>
<!-- <CONTENT> -->
<div class="content">
	<div class="container-padding">
		<div class="row">
			<!-- <MESSAGE> -->
			<%
				String msg = (String) session.getAttribute("msg");
				if (msg != null
						&& msg.equals("The subcategory has been added successfully.")) {
					response.setContentType("text/html");
					out.println("<div class=\"col-md-12\">");
					out.println("<p class=\"bg-success\">");
					out.println(msg);
					out.println("</p></div>");
					session.removeAttribute("msg");
				}
			%>
			<!-- </MESSAGE> -->
			<div class="col-md-7">
				<div class="panel panel-default">

					<div class="panel-title">
						SubCategory of the Game:
						<ul class="panel-tools">
							<li><a class="icon minimise-tool"><i class="fa fa-minus"></i></a></li>
							<li><a class="icon expand-tool"><i class="fa fa-expand"></i></a></li>
						</ul>
					</div>

					<div class="panel-body">
						<form action="<%=request.getContextPath()%>/SubCategoryController"
							method="GET">
							<div class="form-group">
								<label class="control-label form-label" for="cat_id">Select
									Category</label>
								<div>
									<select class="selectpicker" data-live-search="true"
										name="cat_id" id="cat_id" required="required">
										<option value="-1" disabled="disabled" selected="selected">Category
											list</option>
										<c:forEach var="i" items="${sessionScope.load}">
											<option value="${i.cat_id }">${i.cat_name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label form-label" for="scat_name">SubCategoryName</label>
								<input type="text" class="form-control form-control-line"
									placeholder="Name of the SubCategory..." name="scat_name"
									id="scat_name" required="required">
							</div>
							<div class="form-group">
								<label class="control-label form-label" for="scat_description">SubCategory
									Description</label>
								<textarea class="form-control form-control-line" rows="2"
									name="scat_description" id="scat_description"
									placeholder="Enter brief description..."></textarea>
							</div>
							<input type="hidden" name="flag" value="insert">
							<button type="submit" class="btn btn-default">Submit</button>
							<button type="reset" class="btn">Reset</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
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
</body>
</html>