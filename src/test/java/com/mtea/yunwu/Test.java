package com.mtea.yunwu;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mtea.yunwu.dao.UserDao;
import com.mtea.yunwu.model.core.User;

/**
 * @author macrotea@qq.com
 * @date 2012-12-1 上午12:01:14
 * @version 1.0
 * @note
 */
public class Test {
	
	public static UserDao userDao;
	static{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean(UserDao.class);
	}
	
	public static void main(String[] args) {
		//new Test().updateById();
	}
	
	public void updateById() {
		User u = mockUser();
		userDao.save(u);
		
		User result = userDao.findById(u.getId());
		result.setUsername("XX");
		userDao.updateById(result);
		
		User result2 = userDao.findById(u.getId());
		Assert.assertTrue(result2.getUsername().equals("XX"));
	}
	public void findAll() {
		List<User> result = userDao.findAll();
		for (User user : result) {
			System.out.println(user.toString());
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
		User u =new User();
		u.setUsername("macrotea");
		u.setPassword("茶叶");
		u.setEmail("macrotea@qq.com");
		u.setRemark("备注人生");
		u.setAddTime(new Date());
		u.setEditTime(new Date());
		u.setAdmin(true);
		u.setEnable(true);
		return u;
	}

}
