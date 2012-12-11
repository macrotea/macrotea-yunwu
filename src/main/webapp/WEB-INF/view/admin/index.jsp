<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">

	<%@ include file="includes/inc_header.jsp"%>

<body>

	<%@ include file="includes/inc_navbar.jsp"%>

	<div class="container-fluid">
		<div class="row-fluid">
		
			<%@ include file="includes/inc_sliderbar.jsp"%>
			
			<div class="span10 well">
				云端通讯录
			</div>
			<!--/span10-->
		</div>
		<!--/row-fluid-->

	<%@ include file="includes/inc_footer.jsp"%>

	</div>
</body>
</html>
