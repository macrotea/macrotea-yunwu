/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.yunwu.service;

import java.util.Date;
import java.util.Random;

import junit.framework.Assert;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mtea.yunwu.model.core.User;
import com.mtea.yunwu.test.AbstractTestCase;

/**
 * UserService测试类
 * @author 	liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2012-12-18 上午11:19:17
 */
public class UserServiceTest extends AbstractTestCase {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void updateUserById() throws InterruptedException {
		User u = mockUser();
		userService.addUser(u);
		User result = userService.getUserById(u.getId());
		
		u.setUsername("Username-update");
		u.setPassword("Password-update");
		
		//为测试更新时间服务
		Thread.sleep(1000);
		
		long flag = userService.updateUserById(result);
		Assert.assertTrue(flag > 0);
		
		User newUser = userService.getUserById(u.getId());
		Assert.assertTrue(newUser.getUsername().equals("Username-update"));
		Assert.assertTrue(newUser.getPassword().equals("Password-update"));
		Assert.assertTrue(newUser.getId().longValue()==result.getId().longValue());
		Assert.assertEquals(newUser.getEmail(),result.getEmail());
		Assert.assertTrue(newUser.getAddTime().getTime()==result.getAddTime().getTime());
		Assert.assertTrue(newUser.getEditTime().getTime()!=result.getEditTime().getTime());
		
	}
	
	/**
	 * 模拟用户
	 * @return
	 * @author liangqiye
	 * @date 2012-12-12上午9:16:35
	 */
	private User mockUser() {
		int r = new Random().nextInt(10);
		User u = new User();
		u.setUsername("macrotea" + RandomStringUtils.randomAlphabetic(5));
		u.setPassword("茶叶" + r);
		u.setEmail("macrotea@qq.com" + RandomStringUtils.randomAlphabetic(5));
		u.setRemark("备注人生" + r);
		u.setAddTime(new Date());
		u.setEditTime(new Date());
		u.setAdmin(true);
		u.setEnable(true);
		return u;
	}

}
