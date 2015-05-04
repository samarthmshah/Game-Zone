<%@page import="VO.BuyerVO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
BuyerVO bvo = (BuyerVO) session.getAttribute("userInfoObj");
System.out.println("USerInfo Object type: "+session.getAttribute("userInfoObj").getClass().getName());
System.out.println("UserType: "+(String) session.getAttribute("userType"));
String firstname = bvo.getFirstname(),
	   lastname = bvo.getLastname();
long buyer_id = bvo.getBuyer_id();
%>
<header id="header"  class="sb-slide">

  <div id="logo"><a href="buyer_index.jsp"><img src="images/logo.png"/></a></div>
  <!-- Navigation -->
  <div id="main-menu-button"><a href="#" class="sb-toggle-left"><span class="typcn typcn-th-menu"></span>MAIN MENU</a></div>
  <!-- END Navigation -->
  <div id="shopping-cart-button"> <a href="#" title="Shopping Cart" class="sb-toggle-right"><span class="typcn typcn-shopping-cart"></span>MY CART</a> </div>
  <div id="top-nav">
    <div class="pull-left">
      <div id="search">
        <div class="input-group"><span class="input-group-btn">
          <button type="button" class="btn" onClick="window.location.href=window.location.href" ><span class="typcn typcn-zoom-outline"></span></button>
          </span>
          <form action="<%=request.getContextPath()%>/GameController?flag=search&userType=buyer" method="post">
          	 <input type="text" class="form-control input-search" placeholder="SEARCH FOR GAMES..." id="search" name="search"
          	 onkeyup="if (event.keyCode == 13) document.getElementById('#search').click()">
    	 </form>
         
        </div>
      </div>
    </div>

    <div class="pull-right">
      <div id="toplinks">
        <div class="btn-group hidden-xs"><a href="#" title="Account" class="dropdown-toggle"><span class="typcn typcn-user"></span><%=firstname %> <%=lastname %></a>
          <ul class="dropdown-menu">
            <li><a href="<%=request.getContextPath()%>/BuyerController?flag=loadAccountDetails&buyer_id=<%=buyer_id%>">My Account</a></li>
            <li><a href="<%=request.getContextPath()%>/SBLogoutServlet">Log Out</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</header>