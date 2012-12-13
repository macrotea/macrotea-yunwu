<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String ctxPath = request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html lang="en">

	<%@ include file="../includes/inc_header.jsp"%>
	
	<script type="text/javascript">
		$(function(){
			function toggleCheckboxValue(){
	 			$(this).attr("value", $(this).attr("checked")=="checked");
			}
	 		$(".form .cb").change(toggleCheckboxValue);
		})
	</script>
<body>

	<%@ include file="../includes/inc_navbar.jsp"%>

	<div class="container-fluid">
		<div class="row-fluid">
		
			<%@ include file="../includes/inc_sliderbar.jsp"%>
			
			<div class="span10 well">
				<form name="userForm" method="post" action="<%=ctxPath %>${action}" class="form">
					<fieldset>
						<legend>${actionName}用户</legend>
						<label>用户名</label> 
						<input id="username" name="username" type="text" placeholder="Username" value="${user.username}" AUTOCOMPLETE="off"/> 
						
						<label>密码</label>
						<input id="password" name="password" type="password" placeholder="Password" value="${user.password}" AUTOCOMPLETE="off"/> 
						
						<label>邮箱</label> <input id="email" name="email" type="text" placeholder="Email" value="${user.email}" AUTOCOMPLETE="off"/>
						<label>备注</label> <input id="remark" name="remark" type="text" placeholder="Remark" value="${user.remark}" AUTOCOMPLETE="off"/> 
						<label class="checkbox"> <input id="admin" name="admin" class="cb" type="checkbox" ${user.admin ? "checked='checked'" : ""}> 管理员 </label> 
						<label class="checkbox"> <input id="enable" name="enable" class="cb" type="checkbox" ${user.enable ? "checked='checked'" : ""}> 启用 </label>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>
							<button type="reset" class="btn">清空</button>
							<h5>${actionTip}</h5>
						</div>
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
