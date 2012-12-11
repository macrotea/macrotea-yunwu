package com.mtea.yunwu.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mtea.yunwu.model.core.User;
import com.mtea.yunwu.test.AbstractTestCase;

/**
 * UserDao测试类
 * 
 * @author macrotea@qq.com
 * @date 2012-11-30 下午9:25:15
 * @version 1.0
 * @note
 */
public class UserDaoTest extends AbstractTestCase {

	@Autowired
	private UserDao userDao;

	@Test
	public void save() {
		User u = mockUser();
		userDao.save(u);
		Assert.assertTrue(u.getId() != null);
	}

	@Test
	public void delete() {
		User u = mockUser();
		userDao.save(u);
		long rowsAffected = userDao.delete(u.getId());
		Assert.assertTrue(rowsAffected == 1);
	}

	@Test
	public void deleleAll() {
		userDao.deleleAll();
		Assert.assertTrue(userDao.countAll() == 0);
	}

	@Test
	public void findAll() {
		userDao.deleleAll();
		User u = mockUser();
		userDao.save(u);
		List<User> list = userDao.findAll();
		Assert.assertTrue(list.size() == 1);

		u = mockUser();
		userDao.save(u);
		list = userDao.findAll();
		Assert.assertTrue(list.size() == 2);
	}

	@Test
	public void countAll() {
		userDao.deleleAll();
		User u = mockUser();
		userDao.save(u);
		Assert.assertTrue(userDao.countAll() == 1);

		u = mockUser();
		userDao.save(u);
		Assert.assertTrue(userDao.countAll() == 2);
	}

	@Test
	public void findById() {
		User u = mockUser();
		userDao.save(u);
		User result = userDao.findById(u.getId());
		Assert.assertTrue(result.getId().longValue() == u.getId().longValue());
	}

	@Test
	public void updateById() {
		User u = mockUser();
		userDao.save(u);

		User result = userDao.findById(u.getId());
		result.setUsername("XX");
		userDao.updateById(result);

		User result2 = userDao.findById(u.getId());
		Assert.assertTrue(result2.getUsername().equals("XX"));
	}
	
	@Test
	public void batchAddUser() {
		for (int i = 0; i < 100; i++) {
			User u = mockUser();
			userDao.save(u);
		}
	}

	/**
	 * 模拟用户
	 * 
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午9:33:14
	 * @return
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
