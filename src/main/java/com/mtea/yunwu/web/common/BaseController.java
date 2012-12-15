package com.mtea.yunwu.web.common;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 父级控制器
 * @author 	Macrotea
 * @version 0.1
 * @date	2011-9-23
 * @note
 */
public class BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*模型的键*/
	protected static final String KEY_FLAG = "flag";
	protected static final String KEY_CREATE = "create";
	protected static final String KEY_EDIT = "edit";
	protected static final String KEY_LIST = "list";
	protected static final String KEY_SEARCH = "search";
	protected static final String KEY_CRITERIA = "criteria";
	protected static final String KEY_PAGER = "pager";
	
	protected static final String KEY_ACTION_URL = "action";
	protected static final String KEY_ACTION_TIP = "actionTip";
	
	protected static final String KEY_PAGE_ACTION = "pageAction";
	protected static final String KEY_SEARCH_ACTION = "searchAction";
	
	protected static final String KEY_DOMAIN_NAME = "domainName";
	protected static final String KEY_ACTION_NAME = "actionName";
	
	/*动作提示类型*/
	protected static final int ACTION_VIEW = 0;
	protected static final int ACTION_TIP_CREATE = 1;
	protected static final int ACTION_TIP_EDIT = 2;
	protected static final int ACTION_TIP_DELETE = 3;
	
	/*动作名字*/
	protected static final String ACTION_NAME_CREATE = "新增";
	protected static final String ACTION_NAME_EDIT = "编辑";
	protected static final String ACTION_NAME_DELETE = "删除";
	
	/**
	 * 领域名
	 * 如:
	 * User.class -> 用户
	 */
	private String domainName;
	
	/**
	 * 领域类
	 */
	private Class<?> domainClazz;
	
	/**
	 * 是否后台管理员页面
	 */
	private boolean isAdmin;
	
	/**
	 * URL 分隔符
	 */
	protected String urlSeparator = "/";

	/**
	 * 处理 500 错误
	 * @param ex
	 * @return
	 */
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ModelAndView handleServerError(Exception ex) {
		ModelMap model = new ModelMap();
		model.addAttribute("exceptionClazz", ClassUtils.getShortName(ex.getClass()));
		model.addAttribute("exceptionMessage", ex.getMessage());
		model.addAttribute("controllerClazz", ClassUtils.getShortName(getClass()));
		String serverErrorViewPath = getServerErrorViewPath();
		logger.error("500 错误", ex);
		return new ModelAndView(serverErrorViewPath, model);
	}

	/**
	 * 500 视图路径
	 * @author macrotea@qq.com
	 * @date 2012-12-13 下午10:18:11
	 * @return
	 */
	public String getServerErrorViewPath() {
		return isAdmin ? "admin/publics/500" :"publics/500";
	}
	
	/**
	 * 处理 404
	 * @param ex
	 * @return
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView handleResNotFound() {
		String resNotFoundViewPath = getResNotFoundViewPath();
		return new ModelAndView(resNotFoundViewPath);
	}

	/**
	 * 404  视图路径
	 * @author macrotea@qq.com
	 * @date 2012-12-13 下午10:18:11
	 * @return
	 */
	public String getResNotFoundViewPath() {
		return isAdmin ? "admin/publics/404" : "publics/404";
	}
	
	/**
	 * 构建动作操作完成提示信息
	 * @param flag
	 * @param actionType
	 * @return
	 */
	protected String buildActionTip(boolean flag, int actionType) {
		String actionDesc = null;
		switch (actionType) {
			case ACTION_TIP_DELETE:
				actionDesc = ACTION_NAME_DELETE;
				break;
			case ACTION_TIP_CREATE:
				actionDesc = ACTION_NAME_CREATE;
				break;
			case ACTION_TIP_EDIT:
				actionDesc = ACTION_NAME_EDIT;
				break;
			case ACTION_VIEW:
				actionDesc = "查看";
				break;
			default:
				actionDesc = "未知";
				break;
		}
		if (flag) {
			return domainName + actionDesc + "成功";
		} else {
			return domainName + actionDesc + "失败";
		}
	}
	
	/*
	 * 基本方法
	 *------------------------------------------------------------------------------*/

	/**
	 * 重定向
	 * @author macrotea@qq.com
	 * @date 2012-12-1 下午9:39:08
	 * @param action
	 * @return
	 */
	protected String redirect(String action) {
		if (StringUtils.isBlank(action)) throw new IllegalArgumentException("动作名不能为空!");
		String clazzName = StringUtils.uncapitalize(domainClazz.getSimpleName());
		String mappingPath = isAdmin ? "redirect:/admin/%s/%s" : "redirect:/%s/%s";
		return String.format(mappingPath, clazzName, action);
	}

	/**
	 * 转发
	 * @author macrotea@qq.com
	 * @date 2012-12-1 下午9:46:07
	 * @param action
	 * @return
	 */
	protected String forward(String action){
		if (StringUtils.isBlank(action)) throw new IllegalArgumentException("动作名不能为空");
		String clazzName = StringUtils.uncapitalize(domainClazz.getSimpleName());
		String mappingPath = isAdmin ? "forward:/admin/%s/%s" : "forward:/%s/%s";
		return String.format(mappingPath, clazzName,action);
	}
	
	/**
	 * 绑定视图名
	 * @author macrotea@qq.com
	 * @date 2012-12-1 下午9:47:18
	 * @param viewPathName
	 * @return
	 */
	protected String bindView(String viewPathName){
		if(StringUtils.isBlank(viewPathName)) throw new IllegalArgumentException("视图路径名不能为空");
		String clazzName = StringUtils.uncapitalize(domainClazz.getSimpleName());
		String mappingPath = isAdmin ? "/admin/%s/%s" : "/%s/%s";
		return String.format(mappingPath, clazzName, clazzName + viewPathName);
	}
	
	/**
	 * 绑定动作名+参数
	 * @author macrotea@qq.com
	 * @date 2012-12-1 下午9:49:47
	 * @param action
	 * @param params
	 * @return
	 */
	protected String bindAction(String action, Object... params) {
		if (action == null || action.trim().length() == 0)
			throw new IllegalArgumentException("动作名不能为空");

		String clazzName = StringUtils.uncapitalize(domainClazz.getSimpleName());

		/* 若有参数,则构建参数序列 */
		StringBuilder builder = new StringBuilder();
		if (params.length > 0) {
			for (Object p : params) {
				builder.append(getUrlSeparator());
				builder.append(p.toString());
			}
		}
		
		String mappingPath = isAdmin ? "admin/%s/%s" : "%s/%s";
		return String.format(mappingPath, clazzName, action + builder.toString());
	}
	
	/**
	 * 重定向到列表页面
	 * @return
	 */
	protected String redirectList(){
		return redirect("list");
	}
	
	/**
	 * 转发到列表页面
	 * @return
	 */
	protected String forwardList(){
		return forward("list");
	}
	
	/**
	 * 转发到普通列表特定分页
	 * @return
	 */
	protected String forwardPageOfList(int page) {
		return forward("list") + "/" + page;
	}
	
	/**
	 * 转发到搜索列表特定分页
	 * @return
	 */
	protected String forwardPageOfSearch(int page) {
		return forward("search") + "/" + page;
	}
	
	/*
	 * 视图绑定
	 *------------------------------------------------------------------------------*/
	
	/**
	 * 绑定数据编辑器视图名
	 * @return
	 */	
	protected String bindEditorView(){
		return bindView("Editor");
	}
	
	/**
	 * 绑定数据列表视图名
	 * @return
	 */	
	protected String bindListView(){
		return bindView("List");
	}
	
	/*
	 * 动作URL
	 *------------------------------------------------------------------------------*/
	
	/**
	 * 创建动作URL
	 * @return
	 */
	protected String toCreateActionUrl(){
		return bindAction(KEY_CREATE);
	}
	/**
	 * 编辑动作URL
	 * @return
	 */
	protected String toEditActionUrl(Object... param){
		return bindAction(KEY_EDIT,param);
	}
	/**
	 * 列表动作URL
	 * @return
	 */
	protected String toListActionUrl(){
		return bindAction(KEY_LIST);
	}
	/**
	 * 搜索动作URL
	 * @return
	 */
	protected String toSearchActionUrl(){
		return bindAction(KEY_SEARCH);
	}
	
	/*
	 * 构造 & Getter & Setter
	 *------------------------------------------------------------------------------*/
	
	public BaseController() {
		super();
	}
	/**
	 * 领域类,领域名称,是否后台管理员页面
	 * @param domainClazz
	 * @param domainName
	 * @param isAdmin
	 */
	public BaseController(Class<?> domainClazz, String domainName, boolean isAdmin) {
		super();
		this.domainClazz = domainClazz;
		this.domainName=domainName;
		this.isAdmin=isAdmin;
		
	}
	protected Class<?> getDomainClazz() {
		return domainClazz;
	}
	protected void setDomainClazz(Class<?> domainClazz) {
		this.domainClazz = domainClazz;
	}
	
	public String getUrlSeparator() {
		return urlSeparator;
	}
	public void setUrlSeparator(String urlSeparator) {
		this.urlSeparator = urlSeparator;
	}
	protected String getDomainName() {
		return domainName;
	}
	protected void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	
	/*
	 * 扩展
	 *------------------------------------------------------------------------------*/
	
	protected void traceBindingResult(BindingResult result) {
		List<FieldError> fieldErrorList=result.getFieldErrors();
		for (FieldError fieldError : fieldErrorList) {
			logger.error(String.format("字段名: s%s , 注入值: %s , 提示信息: %s",fieldError.getField(),fieldError.getRejectedValue(),fieldError.getDefaultMessage()));
		}
	}

	
	/**
	 * 切换连接符
	 * @author macrotea@qq.com
	 * @date 2012-12-13 上午12:09:37
	 * @param builder
	 */
	public void toggleConnectorChar(StringBuilder builder) {
		if(builder.length()==0){
			builder.append("?");
		}else{
			builder.append("&");
		}
	}
	
	/**
	 * 检查页码
	 * @author macrotea@qq.com
	 * @date 2012-12-13 上午12:09:37
	 * @param builder
	 */
	public int checkPage(Integer page) {
		if (page ==null || page <= 0) {
			page = 1;
		}
		return page;
	}

}
