<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<fieldset>
				<legend>用户列表</legend>
				
				<%--搜索框 --%>
			    <form id="userSearchForm" class="form-inline" action="<%=ctxPath %>${searchAction }" method="post" >
				    <input id="username" name ="username" type="text" class="input-small" placeholder="Username" value="${user.username}" AUTOCOMPLETE="off" />
				    &nbsp;
				    <input id="email"  name ="email" type="text" class="input-small" placeholder="Email" value="${user.email}" AUTOCOMPLETE="off" />
				    &nbsp;
				    <label class="checkbox">
				    	<input id="enable" name="enable" type="checkbox" ${user.enable ? "checked='checked'" : ""} /> 是否启用
				    </label>
				    &nbsp;
				    <button type="submit" class="btn">搜索</button>
			    </form>
			    <c:if test="${!(null eq actionTip) }">    
				 <div class="alert">
              	 	<button data-dismiss="alert" class="close" type="button">×</button>
              		${actionTip}
            	 </div>
            	</c:if>
			    
				<%--数据列表 --%>
				<table class="table table-hover">
	              <thead>
	                <tr>
	                  <th>编号</th>
	                  <th>用户名</th>
	                  <th>邮箱</th>
	                  <th>备注</th>
	                  <th>管理员</th>
	                  <th>启用</th>
	                  <th>添加时间</th>
	                  <th>编辑时间</th>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody>
					<c:forEach var="u" items="${userList}">
		                <tr>
		                  <td> <span class="badge">${u.id}</span> </td>
		                  <td>${u.username}</td>
		                  <td>${u.email}</td>
		                  <td>${u.remark}</td>
		                  <td><i class="${u.admin?'icon-ok':'icon-remove' }"></i></td>
		                  <td><i class="${u.enable?'icon-ok':'icon-remove' }"></i></td>
		                  <td>${u.addTime}</td>
		                  <td>${u.editTime}</td>
		                  <td>    
							    <div class="btn-group">
								    <a class="btn" href="<%=ctxPath%>admin/user/edit/${u.id}"><i class="icon-edit"></i></a>
								    <a class="btn" href="<%=ctxPath%>admin/user/delete/${u.id}/${pager.currentPage}${criteria}"><i class="icon-remove"></i></a>
							    </div>
    					  </td> 
		                </tr>
					</c:forEach>
	              </tbody>
	            </table>
	            
    			<%--分页 --%>
				<div class="pagination pagination-centered">
	              <ul>
	                <li${pager.hasPrev?'':' class="disabled"'}><a href="<%=ctxPath%>${pageAction}/${pager.prevPage}${criteria}">&lt;</a></li>
					<c:forEach var="i" items="${pager.indexList}">
						<li${pager.currentPage!=i ? '' : ' class="active"'}><a href="<%=ctxPath%>${pageAction}/${i}${criteria}">${i}</a></li>
				    </c:forEach>
	                <li${pager.hasNext?'':' class="disabled"'}><a href="<%=ctxPath%>${pageAction}/${pager.nextPage}${criteria}">&gt;</a></li>
	              </ul>
	            </div>
			</fieldset>
			
		</div><!--/span-->
			<!--/span10-->
		</div>
		<!--/row-fluid-->

	<%@ include file="../includes/inc_footer.jsp"%>

	</div>
</body>
</html>
