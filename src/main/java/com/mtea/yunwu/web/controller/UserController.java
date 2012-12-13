package com.mtea.yunwu.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtea.yunwu.model.core.User;
import com.mtea.yunwu.service.UserService;
import com.mtea.yunwu.utils.Pager;
import com.mtea.yunwu.web.common.BaseController;

/**
 * 用户控制器
 * 
 * @author macrotea@qq.com
 * @date 2012-12-1 下午10:04:03
 * @version 1.0
 * @note
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController { // http://localhost:8080/yunwu/admin/user/edit.do
	
	public UserController() {
		super(User.class, "用户", true);
	}

	@Autowired
	private UserService userService;
	
	/*
	 * 默认
	 *------------------------------------------------*/
	
	/**
	 * 默认跳转
	 * @param model
	 * @return
	 * @throws Exception
	 * @author liangqiye
	 * @date 2012-12-13下午8:03:41
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String defaultGoto(ModelMap model) throws Exception {
		return redirect("index");
	}
	
	/**
	 * 默认页
	 * @param model
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午8:04:01
	 */
	@RequestMapping("index")
	public String index(Model model) {
		return redirectList();
	}

	/*
	 * 新增
	 *------------------------------------------------*/
	
	/**
	 * 新增页
	 * @param model
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午7:43:29
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		//填充需要默认值
		model.addAttribute(new User().reset());
		
		model.addAttribute(KEY_ACTION_NAME, ACTION_NAME_CREATE);
		model.addAttribute(KEY_ACTION_URL, toCreateActionUrl());
		return bindEditorView();
	}

	/**
	 * 根据提交的User数据执行新增
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午7:43:09
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@ModelAttribute User user, BindingResult result, Model model) {
		if (result.hasErrors())
			traceBindingResult(result);
		
		//设置时间
		user.fromNow();
		
		//新增
		User retVal = userService.addUser(user);
		
		model.addAttribute(KEY_ACTION_NAME, ACTION_NAME_CREATE);
		model.addAttribute(KEY_ACTION_URL, toCreateActionUrl());
		model.addAttribute(KEY_ACTION_TIP, buildActionTip(retVal.getId() > 0, ACTION_TIP_CREATE));
		return bindEditorView();
	}

	/*
	 * 编辑
	 *------------------------------------------------*/
	
	/**
	 * 编辑页
	 * @param id
	 * @param model
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午7:42:58
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model) {
		
		//获得需被编辑的User实例同时填充页面组件数据
		User user = userService.getUserById(id);
		
		model.addAttribute(user);
		model.addAttribute(KEY_ACTION_NAME, ACTION_NAME_EDIT);
		model.addAttribute(KEY_ACTION_URL, toEditActionUrl(id));
		return bindEditorView();
	}

	/**
	 * 根据提交的User数据执行编辑
	 * @param user
	 * @param id
	 * @param result
	 * @param model
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午7:42:08
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
	public String edit(@ModelAttribute User user, @PathVariable long id, BindingResult result,  Model model) {
		if (result.hasErrors())
			traceBindingResult(result);

		//保存Id
		user.setId(id);
		user.touchMe();
		
		long retVal = userService.updateUserById(user);
		
		model.addAttribute(KEY_ACTION_NAME, ACTION_NAME_EDIT);
		model.addAttribute(KEY_ACTION_URL, toEditActionUrl(id));
		model.addAttribute(KEY_ACTION_TIP, buildActionTip(retVal > 0, ACTION_TIP_EDIT));
		return bindEditorView();
	}

	/*
	 * 删除
	 *------------------------------------------------*/
	
	/**
	 * 根据Id删除
	 * @param id
	 * @param model
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午7:41:56
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id, Model model) {
		long retVal = userService.deleteUser(id);
		model.addAttribute(KEY_ACTION_TIP, buildActionTip(retVal > 0, ACTION_TIP_DELETE));
		return forwardList();
	}
	
	/**
	 * 根据Id删除且返回当前列表页
	 * @param id
	 * @param page
	 * @param criteria
	 * @param request
	 * @param model
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午7:40:07
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "delete/{id}/{page}", method = RequestMethod.GET)
	public String delete(@PathVariable long id, @PathVariable("page") Integer page, @ModelAttribute User criteria, HttpServletRequest request , Model model) {
		page=checkPage(page);
		
		long retVal = userService.deleteUser(id);
		model.addAttribute(KEY_ACTION_TIP, buildActionTip(retVal > 0, ACTION_TIP_DELETE));
		
		//若无参数说明在普通列表中删除,否则在搜索结果列表中删除
		Map paramMap = request.getParameterMap();
		if(paramMap==null || paramMap.size()==0){
			return forwardPageOfList(page);
		}else{
			return forwardPageOfSearch(page);
		}
	}
	
	
	/*
	 * 列表
	 *------------------------------------------------*/

	/**
	 * 列表页
	 * @param criteria
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午8:04:07
	 */
	@RequestMapping("list")
	public ModelAndView list(@ModelAttribute User criteria) {
		criteria.reset();
		
		//便于页面某些组件存在默认状态
		criteria.reset();
		
		//获得分页数据
		Pager<User> pager = userService.searchPage(1, criteria);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(bindListView());
		mav.addObject("userList", pager.getDataList());
		mav.addObject(KEY_PAGER, pager);
		mav.addObject(KEY_SEARCH_ACTION, toSearchActionUrl());
		mav.addObject(KEY_PAGE_ACTION, toListActionUrl());
		return mav;
	}
	
	/**
	 * 返回特定列表页
	 * @param page
	 * @param criteria
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午8:41:34
	 */
	@RequestMapping("list/{page}")
	public ModelAndView list(@PathVariable("page") Integer page ,@ModelAttribute User criteria) {
		page=checkPage(page);
		
		//便于页面某些组件存在默认状态
		criteria.reset();
		
		//获得分页数据
		Pager<User> pager = userService.searchPage(page, criteria);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(bindListView());
		mav.addObject("userList", pager.getDataList());
		mav.addObject(KEY_PAGER, pager);
		mav.addObject(KEY_SEARCH_ACTION, toSearchActionUrl());
		mav.addObject(KEY_PAGE_ACTION, toListActionUrl());
		return mav;
	}
	
	/*
	 * 搜索
	 *------------------------------------------------*/

	/**
	 * 根据提交的User数据搜索且返回第1页数据
	 * @param criteria
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午8:29:08
	 */
	@RequestMapping("search")
	public ModelAndView search(@ModelAttribute User criteria) {
		
		//获得分页数据
		Pager<User> pager = userService.searchPage(1, criteria);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(bindListView());
		mav.addObject("userList", pager.getDataList());
		mav.addObject(KEY_PAGER, pager);
		mav.addObject(KEY_CRITERIA, buildCriteriaText(criteria));
		mav.addObject(KEY_SEARCH_ACTION, toSearchActionUrl());
		mav.addObject(KEY_PAGE_ACTION, toSearchActionUrl());
		return mav;
	}
	
	/**
	 * 根据提交的User数据搜索且返回特定页数据
	 * @param page
	 * @param criteria
	 * @return
	 * @author liangqiye
	 * @date 2012-12-13下午7:46:34
	 */
	@RequestMapping("search/{page}")
	public ModelAndView search(@PathVariable("page") Integer page , @ModelAttribute User criteria ) {
		//检查页码
		page=checkPage(page);
		
		//获得分页数据
		Pager<User> pager = userService.searchPage(page, criteria);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(bindListView());
		mav.addObject("userList", pager.getDataList());
		mav.addObject(KEY_PAGER, pager);
		mav.addObject(KEY_CRITERIA, buildCriteriaText(criteria));
		mav.addObject(KEY_SEARCH_ACTION, toSearchActionUrl());
		mav.addObject(KEY_PAGE_ACTION, toSearchActionUrl());
		return mav;
	}
	
	/**
	 * 构建查询条件文本
	 * @author macrotea@qq.com
	 * @date 2012-12-13 上午12:02:09
	 * @param criteria
	 * @return
	 */
	private String buildCriteriaText(User criteria){
		StringBuilder builder =new StringBuilder();
		if(criteria!=null){
			if (StringUtils.isNotBlank(criteria.getUsername())) {
				toggleConnectorChar(builder);
				builder.append("username=" + criteria.getUsername());
			}
			if(StringUtils.isNotBlank(criteria.getEmail())){
				toggleConnectorChar(builder);
				builder.append("email="+criteria.getEmail());
			}
			toggleConnectorChar(builder);
			builder.append("enable="+criteria.isEnable());
		}
		return builder.toString();
	}

	/*
	 * JSON
	 *------------------------------------------------*/

	@ResponseBody
	@RequestMapping("json/{id}")
	public User userjson(@PathVariable("id") long id, Model model) {
		return userService.getUserById(id);
	}

}
