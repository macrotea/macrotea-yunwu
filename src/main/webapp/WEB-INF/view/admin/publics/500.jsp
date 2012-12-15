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
				<h3 class="title">500 - 错误提示</h3>
				<div class="box">
					<p align="left" class="red bold">异常类:${exceptionClazz},异常信息:${exceptionMessage}</p>
					<p align="left" class="red bold">控制器类:${controllerClazz}</p>
					<p align="left"><a href="${requestUrl}">返回</a></p>
				</div>
			</div>
			<!--/span10-->
		</div>
		<!--/row-fluid-->

	<%@ include file="../includes/inc_footer.jsp"%>

	</div>
</body>
</html>
