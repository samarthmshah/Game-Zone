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
					if (msg.equals("The game was successfully updated")) {
						response.setContentType("text/html");
						out.println("<div class=\"col-md-12\">");
						out.println("<p class=\"bg-success\">");
						out.println(msg);
						out.println("</p></div>");
						session.removeAttribute("msg");
					}
				}
			%>
			<!-- </MESSAGE> -->
			<div class="col-md-7">
				<div class="panel panel-default">

					<div class="panel-title">
						Game Details:
						<ul class="panel-tools">
							<li><a class="icon minimise-tool"><i class="fa fa-minus"></i></a></li>
							<li><a class="icon expand-tool"><i class="fa fa-expand"></i></a></li>
						</ul>
					</div>

					<!-- sessionName = game2bedited and categoryList -->

					<!--  If it is for editing -->
					<div class="panel-body">
							<form action="<%=request.getContextPath()%>/GameController" method="POST" id="updateGame">
							<c:forEach var="i" items="${sessionScope.game2bedited }">
									<div class="form-group">
										<label class="control-label form-label" for="cat_id">Select
											Category. <br /> Previous Selection: <strong>${i.cat_id.getCat_name() }</strong>
										</label>
										<div>
											<select class="selectpicker" data-live-search="true"
												name="cat_id" id="cat_id" required="required">
												<option value="-1" disabled="disabled" selected="selected">Category list</option>
												<c:forEach var="x" items="${sessionScope.categoryList}">
													<option value="${x.cat_id }">${x.cat_name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									
									
									<div class="form-group">
										<label class="control-label form-label" for="scat_id">Select
											Subcategory. <br /> Previous Selection: <strong>${i.scat_id.getScat_name() }</strong>
										</label>
										<div>
											<select class="selectpicker" data-live-search="true"
												name="scat_id" id="scat_id" required="required">
												<option value="-1" disabled="disabled" selected="selected">Will be loaded dynamically</option>
											</select>
										</div>
									</div>
									
									
									<div class="form-group">
										<label class="control-label form-label" for="game_console">Select
											Console Type. <br /> Previous Selection: <strong>${i.game_console }</strong>
										</label>
										<div>
											<select class="selectpicker" data-live-search="true"
												name="game_console" id="game_console" required="required">
												<option value="-1" disabled="disabled" selected="selected">Console List</option>
												<option value="PC">PC</option>
												<option value="XBox">XBox</option>
												<option value="PlayStation">PlayStation</option>
												<option value="Wii U">Wii U</option>
												<option value="Ps Vita">Ps Vita</option>
												<option value="Nintendo">Ninetendo 3DS</option>
											</select>
										</div>
									</div>
									
									
									<div class="form-group">
										<label class="control-label form-label" for="game_name">Edit Game Name</label> <input type="text"
											class="form-control form-control-line"
											placeholder="Name of the Game..." name="game_name"
											id="game_name" required="required" value="${i.game_name }">
									</div>
									
									<div class="form-group">
										<label class="control-label form-label" for="game_price">Edit Game Price</label> <input type="text"
											class="form-control form-control-line"
											placeholder="Price of the Game..." name="game_price"
											id="game_price" required="required" value="${i.game_price }">
									</div>
									
									<div class="form-group">
										<label class="control-label form-label" for="game_stock">Edit Game Stock</label> <input type="text"
											class="form-control form-control-line"
											placeholder="Number of game units..." name="game_stock"
											id="game_stock" required="required" value="${i.game_stock }">
									</div>
									
									<div class="form-group">
										<label class="control-label form-label" for="game_shipping_charges">Shipping Charges</label> <input type="text"
											class="form-control form-control-line"
											placeholder="Shipping charges..." name="game_shipping_charges"
											id="game_shipping_charges" required="required" value="${i.game_shipping_charges }">
									</div>
									
									<div class="form-group">
										<label class="control-label form-label" for="game_youtube_url">Video URL</label> <input type="text"
											class="form-control form-control-line"
											placeholder="Youtube URL..." name="game_youtube_url"
											id="game_youtube_url" required="required" value="${i.game_youtube_url }">
									</div>
									
									<div class="form-group">
										<label class="control-label form-label" for="game_description">Game Description</label>
										<textarea class="form-control form-control-line" rows="2"
											name="game_description" id="game_description"
											placeholder="Enter brief description...">${i.game_description }</textarea>
									</div>
									<input type="hidden" name="game_id" value="${i.game_id }">
									<input type="hidden" name="flag" value="update">
									<button type="submit" class="btn btn-default">Update the product</button>
									<button type="reset" class="btn">Reset</button>
							</c:forEach>
							<%
							session.removeAttribute("game2bedited"); 
							%>
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
<script type="text/javascript" src="js/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<script>
	$(function(){
		console.log("entered ajax method");
		$("#cat_id").change(function(event){
			var $cat_id = $("#cat_id").val();
			$.ajax({
				method: "GET",
				url: "<%=request.getContextPath()%>/GameController?flag=loadScatDynamically",
				data: {cat_id: $cat_id},
				success: function(responseJson){
					var $select = $("#scat_id");
					$select.find("option").remove();
					$.each(responseJson, function(key, value){
						$("<option>").val(key).text(value).appendTo($select);
					});
				},
				error: function(){
					console.log("Error!!");
				}
			});
			
		});
	});
	</script>
	<script type="text/javascript">
	$(function(){
		$("#updateGame").validate({
			rules:{
				"game_price":{ 
					required: true,
					number: true
					},
				"game_stock": {
					required: true,
					number: true
					}
			}, 
			highlight:function(element){
				$(element).closest(".form-group")
				.removeClass("success").addClass("error");
			}, 
			success:function(element){
				element
				.text("OK!").addClass("valid")
				.closest(".form-group").removeClass("error").addClass("success");
			}
			});
	});
	</script>
</body>
</html>