package com.mtea.yunwu.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//删除跳转的问题
	//basePath和ctxPath问题

	public UserController() {
		super(User.class, "用户", true);
	}

	@Autowired
	private UserService userService;
	

	/*
	 * 列表
	 *------------------------------------------------*/

	@RequestMapping("index")
	public String index(Model model) {
		return redirectList();
	}

	@RequestMapping("list")
	public ModelAndView list(@ModelAttribute User criteria) {
		criteria.reset();
		ModelAndView mav = new ModelAndView();
		mav.setViewName(bindListView());
		Pager<User> pager = userService.searchPage(1, criteria);
		mav.addObject("pager", pager);
		mav.addObject("userList", pager.getDataList());
		mav.addObject(KEY_SEARCH_ACTION, bindSearchActionURL());
		mav.addObject(KEY_PAGE_ACTION, bindListActionURL());
		return mav;
	}
	
	@RequestMapping("list/{page}")
	public ModelAndView list(@PathVariable("page") Integer page ,@ModelAttribute User criteria) {
		criteria.reset();
		page=checkPage(page);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(bindListView());
		Pager<User> pager = userService.searchPage(page == null ? 1 : page, criteria);
		mav.addObject("pager", pager);
		mav.addObject("userList", pager.getDataList());
		mav.addObject(KEY_SEARCH_ACTION, bindSearchActionURL());
		mav.addObject(KEY_PAGE_ACTION, bindListActionURL());
		return mav;
	}

	@RequestMapping("search")
	public ModelAndView search(@ModelAttribute User criteria) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(bindListView());
		Pager<User> pager = userService.searchPage(1, criteria);
		mav.addObject("pager", pager);
		mav.addObject("userList", pager.getDataList());
		mav.addObject(KEY_CRITERIA_TEXT, buildCriteriaText(criteria));
		mav.addObject(KEY_SEARCH_ACTION, bindSearchActionURL());
		mav.addObject(KEY_PAGE_ACTION, bindSearchActionURL());
		return mav;
	}
	
	@RequestMapping("search/{page}")
	public ModelAndView search(@PathVariable("page") Integer page , @ModelAttribute User criteria ) {
		page=checkPage(page);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(bindListView());
		Pager<User> pager = userService.searchPage(page == null ? 1 : page, criteria);
		mav.addObject("pager", pager);
		mav.addObject("userList", pager.getDataList());
		mav.addObject(KEY_CRITERIA_TEXT, buildCriteriaText(criteria));
		mav.addObject(KEY_SEARCH_ACTION, bindSearchActionURL());
		mav.addObject(KEY_PAGE_ACTION, bindSearchActionURL());
		return mav;
	}

	/*
	 * 新增
	 *------------------------------------------------*/
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute(new User().reset());
		model.addAttribute(KEY_ACTION_NAME, "新增");
		model.addAttribute(KEY_ACTION, bindCreateActionURL());
		return bindEditorView();
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@ModelAttribute User user, BindingResult result, Model model) {
		if (result.hasErrors())
			traceBindingResult(result);
		
		user.fromNow();
		User ret = userService.addUser(user);
		model.addAttribute(KEY_ACTION_NAME, "新增");
		model.addAttribute(KEY_ACTION, bindCreateActionURL());
		model.addAttribute(KEY_ACTION_TIP, buildActionTip(ret.getId() > 0, ACTION_CREATE));
		return bindEditorView();
	}

	/*
	 * 编辑
	 *------------------------------------------------*/
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model) {
		model.addAttribute(userService.getUserById(id));
		model.addAttribute(KEY_ACTION_NAME, "编辑");
		model.addAttribute(KEY_ACTION, bindEditAction(id));
		return bindEditorView();
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
	public String edit(@ModelAttribute User user, @PathVariable long id, BindingResult result,  Model model) {
		if (result.hasErrors())
			traceBindingResult(result);

		//保存Id
		user.setId(id);
		user.touchMe();
		long ret = userService.updateUserById(user);
		model.addAttribute(KEY_ACTION_NAME, "编辑");
		model.addAttribute(KEY_ACTION, bindEditAction(id));
		model.addAttribute(KEY_ACTION_TIP, buildActionTip(ret > 0, ACTION_EDIT));
		return bindEditorView();
	}

	/*
	 * 删除
	 *------------------------------------------------*/
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id, Model model) {
		long ret = userService.deleteUser(id);
		model.addAttribute(KEY_ACTION_TIP, buildActionTip(ret > 0, ACTION_DELETE));
		return forwardList();
	}

	/*
	 * 临时
	 *------------------------------------------------*/

	@ResponseBody
	@RequestMapping("json/{id}")
	public User userjson(@PathVariable("id") long id, Model model) {
		return userService.getUserById(id);
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test(Model model) {
		return "/admin/user/test";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.POST)
	public String test(String isEnable , Boolean isAdmin , boolean isMan , Model model) {
		System.out.println("isEnable >> "+ isEnable);
		System.out.println("isAdmin >> "+ isAdmin);
		System.out.println("isMan >> "+ isMan);
		return "/admin/user/test";
	}
	
	/**
	 * 构建查询条件
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

}
