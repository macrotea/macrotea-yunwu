package com.mtea.yunwu.web.controller;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*Context.xml"})
public class JobControllerTest {
	
	@Autowired
	public RequestMappingHandlerAdapter handlerAdapter;
	
	@Autowired
	private UserController userController;

	private static MockHttpServletRequest request;

	private static MockHttpServletResponse response;

	@BeforeClass
	public static void before() {
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
	}

	@Test
	public void testList() {
		request.setMethod(HttpMethod.GET.name());
        request.setRequestURI("/admin/user/json/142");  
		ModelAndView mv = null;
		try {
			mv = handlerAdapter.handle(request, response, new HandlerMethod(userController, "json"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(mv);
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertEquals(mv.getViewName(), "/admin/user/json/142");
	}
}