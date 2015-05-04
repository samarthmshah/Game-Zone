<%@page import="VO.GameCategoryVO"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp"%>
<%@include file="sidebar.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!-- <CONTENT> -->
<div class="content">
	<div class="container-padding">
		<div class="row">
			<!-- <MESSAGE> -->
			<%
				String msg = (String) session.getAttribute("msg");
				if (msg != null
						&& (msg.equals("The category has been added successfully.") || 
							msg.equals("The category is successfully updated."))) {
					response.setContentType("text/html");
					out.println("<div class=\"col-md-12\">");
					out.println("<p class=\"bg-success\">");
					out.println(msg);
					out.println("</p></div>");
					session.removeAttribute("msg");
				}
			%>
			<!-- </MESSAGE> -->
			<div class="col-md-6">
				<div class="panel panel-default">

					<div class="panel-title">
						Category of the Game:
						<ul class="panel-tools">
							<li><a class="icon minimise-tool"><i class="fa fa-minus"></i></a></li>
							<li><a class="icon expand-tool"><i class="fa fa-expand"></i></a></li>
						</ul>
					</div>
					
					<!-- Check if its for editing. -->
					<%
						@SuppressWarnings("unchecked")
						List<GameCategoryVO> gcvo = (List<GameCategoryVO>) session.getAttribute("category2bedited");
						if(gcvo == null){
					%>
					<!-- If not: -->
					<div class="panel-body">
						<form action="<%=request.getContextPath()%>/CategoryController" method="GET">
							<div class="form-group">
								<label class="form-label" for="cat_name">Category
									Name</label> <input type="text" class="form-control form-control-line"
									placeholder="Name of the Category..." name="cat_name"
									id="cat_name" required="required">
							</div>
							<div class="form-group">
								<label class="control-label form-label"
									for="cat_description">Category Description</label>
								<textarea class="form-control form-control-line" rows="2"
									name="cat_description" id="cat_description"
									placeholder="Enter brief description..."></textarea>
							</div>
							<input type="hidden" name="flag" value="insert">
							<button type="submit" class="btn btn-default">Submit</button>
							<button type="reset" class="btn btn-light">Reset</button>
						</form>
					</div>
					<%
						}
						else{
					%>
					<!--  If it is for editing -->
					<div class="panel-body">
						<form action="<%=request.getContextPath()%>/CategoryController" method="GET">
						<c:forEach var="i" items="${sessionScope.category2bedited }">
							<div class="form-group">
								<label class="form-label" for="cat_name">Edit Category
									Name</label> <input type="text" class="form-control form-control-line"
									placeholder="Name of the Category..." name="cat_name"
									id="cat_name" required="required" value="${i.cat_name }">
							</div>
							<div class="form-group">
								<label class="control-label form-label"
									for="cat_description">Edit Category Description</label>
								<textarea class="form-control form-control-line" rows="2"
									name="cat_description" id="cat_description"
									placeholder="Enter brief description...">${i.cat_description }</textarea>
							</div>
							<input type="hidden" name="cat_id" value="${i.cat_id }">
							<input type="hidden" name="flag" value="update">
							<button type="submit" class="btn btn-default">Update</button>
							<button type="reset" class="btn btn-light">Reset</button>
							</c:forEach>
							<%session.removeAttribute("category2bedited"); %>
						</form>
					</div>
					<%		
						}
					%>
					
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
</body>
</html>