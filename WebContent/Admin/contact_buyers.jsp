<%@page import="VO.GameSubCategoryVO"%>
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
				if (msg != null) {
					if (msg.equals("The message has been sent successfully")) {
						response.setContentType("text/html");
						out.println("<div class=\"col-md-12\">");
						out.println("<p class=\"bg-success\">");
						out.println("<strong>"+msg+"</strong>");
						out.println("</p></div>");
					}
					else{
						response.setContentType("text/html");
						out.println("<div class=\"col-md-12\">");
						out.println("<p class=\"bg-danger\">");
						out.println("<strong>"+msg+"</strong>");
						out.println("</p></div>");
					}
					session.removeAttribute("msg");
				}
			%>
			<!-- </MESSAGE> -->
			<div class="col-md-7">
				<div class="panel panel-default">

					<div class="panel-title">
						Send Email to the Buyer:
						<ul class="panel-tools">
							<li><a class="icon minimise-tool"><i class="fa fa-minus"></i></a></li>
							<li><a class="icon expand-tool"><i class="fa fa-expand"></i></a></li>
						</ul>
					</div>

					<!-- sessionName =sellerList-->
					<div class="panel-body">
						<form action="<%=request.getContextPath()%>/BuyerController" method="POST">
							<div class="form-group">
								<label class="control-label form-label" for="buyer_id">Choose Buyer:</label>
								<div>
									<select class="selectpicker" data-live-search="true"
										name="buyer_id" id="buyer_id" required="required">
										<option value="-1" disabled="disabled" selected="selected">Buyer List</option>
										<c:forEach var="i" items="${sessionScope.buyerList}">
											<option value="${i.buyer_id }">${i.firstname } ${i.lastname }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label form-label" for="subject">Subject:</label>
								<input type="text" class="form-control form-control-line"
									placeholder="Subject of the message..." name="subject"
									id="subject" required="required">
							</div>
							<div class="form-group">
								<label class="control-label form-label" for="message">Message Body:</label>
								<textarea class="form-control form-control-line" rows="2"
									name="message" id="message"
									placeholder="Enter message here..."></textarea>
							</div>
							<input type="hidden" name="flag" value="messageBuyer4mAdmin">
							<button type="submit" class="btn btn-default">Send Message</button>
							<button type="reset" class="btn">Reset</button>
						</form>
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
</body>
</html>