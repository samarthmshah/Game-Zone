<%@page import="VO.SellerVO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
SellerVO svo = (SellerVO) session.getAttribute("userInfoObj");
String firstname = svo.getFirstname(),
	   lastname = svo.getLastname();
long seller_id = svo.getSeller_id();
%>
<header id="header"  class="sb-slide">

  <div id="logo"><a href="seller_index.jsp"><img src="images/logo.png"/></a></div>
  <!-- Navigation -->
  <div id="main-menu-button"><a href="#" class="sb-toggle-left"><span class="typcn typcn-th-menu"></span>MAIN MENU</a></div>
  <!-- END Navigation -->
  <div id="top-nav-seller">
    <div class="pull-left">
      <div id="search">
        <div class="input-group"><span class="input-group-btn">
          <button type="button" class="btn" onClick="window.location.href=window.location.href" ><span class="typcn typcn-zoom-outline"></span></button>
          </span>
		  <form action="<%=request.getContextPath()%>/GameController?flag=search&userType=seller" method="post">
          	 <input type="text" class="form-control input-search" placeholder="Search games..." id="search" name="search"
          	 onkeyup="if (event.keyCode == 13) document.getElementById('#search').click()">
    	 </form>        
    	</div>
      </div>
    </div>

    <div class="pull-right">
      <div id="toplinks">
        <div class="btn-group hidden-xs"><a href="#" title="Account" class="dropdown-toggle"><span class="typcn typcn-user"></span><%=firstname %> <%=lastname %></a>
          <ul class="dropdown-menu">
            <li><a href="<%=request.getContextPath()%>/SellerController?flag=loadAccountDetails&seller_id=<%=seller_id%>">My Account</a></li>
            <li><a href="<%=request.getContextPath()%>/SBLogoutServlet">Log Out</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</header>