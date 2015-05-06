<!doctype html>
<%@page import="java.util.Date"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- As in slidebar -->

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<title>Game-Zone</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/slick.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="fonts/typicons/typicons.css">
<link rel="stylesheet" href="css/mystyle.css">
<script src="js/modernizr.custom.js"></script>
</head>

<body class="off-canvas">
	<div id="left-border"></div>
	<div id="right-border"></div>
	<div id="top-border"></div>
	<div id="bottom-border"></div>
	<div id="fixed-bottom" class="sb-slide"></div>


	<!--  Header -->
	<%@include file="seller_header.jsp"%>
	<!--  END Header -->
	<div id="sb-site">
		<div id="main">
			<div id="loadergif">
				<div class="loader">Loading...</div>
			</div>


	
    <div id="content">
      <div class="container">
        <h1><span>Your Orders</span></h1>
      </div>
      <div class="container">
        <div class="row">
        
        <%
        String msg = (String) session.getAttribute("msg");
		if (msg != null) {
			if (msg.equals("Order cancelled successfully")
					|| msg.equals("Order shipped successfully")) {
				response.setContentType("text/html");
				out.println("<div class=\"col-md-12\">");
				out.println("<p class=\"text-center bg-success\">");
				out.println("<strong>"+msg+"</strong>");
				out.println("</p></div>");
			}
			else{
				response.setContentType("text/html");
				out.println("<div class=\"col-md-12\">");
				out.println("<p class=\"text-center bg-danger\">");
				out.println("<strong>"+msg+"</strong>");
				out.println("</p></div>");
			}
			session.removeAttribute("msg");
		}
        %>
          <div class="col-md-12">
            <div class="rect">
              <h4 class="default-font upper">History</h4>
              <table class="table-lined">
                <thead>
                  <tr>
                    <th class="text-center">Order date</th>
                    <th class="text-center">Game</th>
                    <th class="text-center">Quantity </th>
                    <th class="text-center">EmailID </th>
                    <th class="text-center">Status</th>
                    <th class="text-center">Cost</th>
                    <th class="text-center">Stock Remaining</th>
                    <th class="text-center">Action</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${sessionScope.sellerOrders }">
                <c:set var="ovo" value="${i[0] }"></c:set>
                <c:set var="gvo" value="${i[1] }"></c:set>
                  <tr>
                    <td class="text-center">${ovo.orderDT } </td>
                    <td class="text-center">${gvo.game_name }</td>
                    <td class="text-center">${ovo.quantity }</td>
                    <td class="text-center">${ovo.buyer_id.getEmail() }</td>
                    <c:choose>
                    	<c:when test="${ovo.order_status == 0}">
                    		<td class="text-center">Not Shipped</td>
                    	</c:when>
                    	<c:otherwise>
                    		<c:choose>
                    			<c:when test="${ovo.order_status == -1}">
                    				<td class="text-center">Cancelled</td>
                    			</c:when>
                    			<c:otherwise>
                    				<td class="text-center">Shipped</td>
                    			</c:otherwise>
                    		</c:choose>
                    	</c:otherwise>
                    </c:choose>
                    <td class="text-center">${ovo.order_total_cost }</td>
                    <td class="text-center">${gvo.game_stock}</td>
                    <td class="text-center">
                    	<a href="<%=request.getContextPath()%>/OrderController?order_id=${ovo.order_id}&flag=setShipped&seller_id=<%=seller_id%>">
							<button type="button" class="btn btn-info" data-toggle="tooltip" 
								data-placement="top" data-original-title="Set Shipped">
								<i class="typcn typcn-plane"></i>
							</button>
						</a>
                    	<a href="<%=request.getContextPath()%>/OrderController?order_id=${ovo.order_id}&flag=cancel&seller_id=<%=seller_id%>&userType=seller">
							<button type="button" class="btn btn-danger" data-toggle="tooltip" 
								data-placement="top" data-original-title="Cancel">
								<i class="typcn typcn-times"></i>
							</button>
						</a>
                    </td>
                  </tr>
                  </c:forEach>
                  <%session.removeAttribute("sellerOrders"); %>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
    
			<!--  Footer -->
			<%@include file="footer.jsp"%>
			<!-- END Footer -->
		</div>
	</div>

	<!--  The main menu will be showed here. This comes after the sb-site ends. -->
	<%@include file="seller_main_menu.jsp"%>
	<!-- End slidebar. -->

	<script src="js/jquery-1.11.0.min.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.inview.js"></script> 
<script src="js/slick.min.js"></script> 
<script src='js/jquery.magnific-popup.min.js'></script> 
<script src='js/jquery.catslider.js'></script> 
<script src="js/slidebars.min.js"></script> 
<script src="js/bootstrap-select.min.js"></script> 
<script src="js/myscript.js"></script>
</body>
</html>