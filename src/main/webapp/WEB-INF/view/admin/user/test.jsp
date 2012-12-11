<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String ctxPath = request.getContextPath() + "/";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">

	<%@ include file="../includes/inc_header.jsp"%>
	
	<script>
// 		$("#isAdmin").click(function () {
// 			$(this).attr('value', $(this).attr("checked"));
// 		});
	</script>
<body>

	<%@ include file="../includes/inc_navbar.jsp"%>

	<div class="container-fluid">
		<div class="row-fluid">
		
			<%@ include file="../includes/inc_sliderbar.jsp"%>
			
			<div class="span10 well">
				<form name="userForm" method="post" action="<%=ctxPath %>admin/user/test">
					<fieldset>
						<label class="checkbox"> <input name="isEnable" type="checkbox" checked="checked" /> isEnable </label>
						<label class="checkbox"> <input name="isAdmin" type="checkbox" checked="checked" /> isAdmin </label>
						<label class="checkbox"> <input name="isMan" type="checkbox" checked="checked" /> isMan </label>
						
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>
							<button type="reset" class="btn">清空</button>
						</div>
						
						<h3>${actionTip}</h3>
						
					</fieldset>
				</form>
			</div>
			<!--/span10-->
		</div>
		<!--/row-fluid-->

	<%@ include file="../includes/inc_footer.jsp"%>

	</div>
</body>
</html>
