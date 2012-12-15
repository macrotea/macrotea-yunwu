<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String ctxPath = request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html lang="en">

	<%@ include file="../includes/inc_header.jsp"%>

<body>

	<%@ include file="../includes/inc_navbar.jsp"%>

	<div class="container-fluid">
		<div class="row-fluid">
		
			<%@ include file="../includes/inc_sliderbar.jsp"%>
			
			<div class="span10 well">
				<h3 class="title">404 - 错误提示</h3>
				<div class="box">
					<p align="left" class="red bold">1.资源没有找到 ; 2.请求没有正确映射</p>
				</div>
			</div>
			<!--/span10-->
		</div>
		<!--/row-fluid-->

	<%@ include file="../includes/inc_footer.jsp"%>

	</div>
</body>
</html>
