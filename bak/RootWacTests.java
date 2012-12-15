package com.mtea.yunwu.web.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

@ContextConfiguration(locations = {"classpath*:*Context.xml"})
public class RootWacTests {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockServletContext servletContext;

    @Autowired
    private MockHttpServletRequest request;

    @Autowired
    private MockHttpServletResponse response;

    @Autowired
    private MockHttpSession session;

    @Autowired
    private ServletWebRequest webRequest;
    
    @Test
    public void json(){
    	System.out.println(request);
    }
}