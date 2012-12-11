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
	protected static final String KEY_ACTION = "action";
	protected static final String KEY_ACTION_TIP = "actionTip";
	protected static final String KEY_FLAG = "flag";
	protected static final String KEY_CREATE = "create";
	protected static final String KEY_EDIT = "edit";
	protected static final String KEY_DOMANNAME = "domainName";
	protected static final String KEY_ACTION_NAME = "actionName";
	
	/*动作类型*/
	protected static final int ACTION_DELETE = 0;
	protected static final int ACTION_CREATE = 1;
	protected static final int ACTION_EDIT = 2;
	protected static final int ACTION_VIEW = 3;
	
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
	 * 错误视图路径
	 */
	protected String ERROR_VIEW_PATH = "/publics/error";

	
	/**
	 * 异常统一处理
	 * @param ex
	 * @return
	 */
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		ModelMap model = new ModelMap();
		model.addAttribute("underlyingExceptionClazz", ClassUtils.getShortName(ex.getClass()));
		model.addAttribute("exceptionMessage", ex.getMessage());
		model.addAttribute("controllerClazz", ClassUtils.getShortName(getClass()));
		return new ModelAndView(ERROR_VIEW_PATH, model);
	}
	
	/**
	 * 生成动作提示
	 * @param flag
	 * @param actionType
	 * @return
	 */
	protected String buildActionTip(boolean flag, int actionType) {
		String actionDesc = null;
		switch (actionType) {
			case ACTION_DELETE:
				actionDesc = "删除";
				break;
			case ACTION_CREATE:
				actionDesc = "新增";
				break;
			case ACTION_EDIT:
				actionDesc = "编辑";
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
		if(action==null||action.trim().length()==0) throw new IllegalArgumentException("动作名不能为空");
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
	 * 动作绑定
	 *------------------------------------------------------------------------------*/
	
	/**
	 * 绑定创建动作
	 * @return
	 */
	protected String bindCreateActionURL(){
		return bindAction(KEY_CREATE);
	}
	/**
	 * 绑定编辑动作
	 * @return
	 */
	protected String bindEditAction(Object... param){
		return bindAction(KEY_EDIT,param);
	}
	
	/*
	 * 扩展
	 *------------------------------------------------------------------------------*/
	
	protected void traceBindingResult(BindingResult result) {
		List<FieldError> fieldErrorList=result.getFieldErrors();
		for (FieldError fieldError : fieldErrorList) {
			logger.error(String.format("字段名:%s,注入值:%s,提示信息:%s",fieldError.getField(),fieldError.getRejectedValue(),fieldError.getDefaultMessage()));
		}
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

}
