package com.mtea.yunwu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mtea.yunwu.web.common.BaseController;

/**
 * 管理控制器
 * 
 * @author macrotea@qq.com
 * @date 2012-12-1 下午10:04:03
 * @version 1.0
 * @note
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController { // http://localhost:8080/yunwu/admin/index.do

	@RequestMapping("index")
	public String index(Model model) {
		return "/admin/index";
	}

}
