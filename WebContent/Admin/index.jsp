<%@include file="header.jsp"%>
<%@include file="sidebar.jsp"%>
<!-- <CONTENT> -->
<div class="content">

	<!-- Start Page Header -->
	<div class="page-header">
		<h1 class="title">
			<strong>Welcome!</strong>
		</h1>
		<ol class="breadcrumb">
			<li class="active">This is the Admin panel.</li>
		</ol>

	</div>

	<div class="container-widget">
		<div class="jumbotron" id="myJumb">
			<blockquote>All the functionalities by Admin will be
				done here.</blockquote>
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
<script type="text/javascript">
		$(function() {
			var now = moment().format("dddd, MMMM Do YYYY, h:mm:ss a");
			$('#myJumb').append('Today\'s date is:  ');
			$('#myJumb').append(now);
		});
	</script>
</body>
</html>