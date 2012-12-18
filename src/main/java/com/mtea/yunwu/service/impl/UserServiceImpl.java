package com.mtea.yunwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtea.yunwu.dao.UserDao;
import com.mtea.yunwu.model.core.User;
import com.mtea.yunwu.service.UserService;
import com.mtea.yunwu.utils.Pager;

/**
 * 用户服务接口实现类
 * @author macrotea@qq.com
 * @date 2012-12-1 下午9:08:44
 * @version 1.0
 * @note
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.UserService#addUser(com.mtea.yunwu.model.core.User)
	 */
	public User addUser(User user) {
		return userDao.save(user);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.UserService#deleteUser(long)
	 */
	public long deleteUser(long id) {
		return userDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.UserService#deleleAllUser()
	 */
	public long deleleAllUser() {
		return userDao.deleleAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.UserService#getAllUser()
	 */
	public List<User> getAllUser() {
		return userDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.UserService#countAllUser()
	 */
	public Long countAllUser() {
		return userDao.countAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.UserService#getUserById(long)
	 */
	public User getUserById(long id) {
		return userDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.UserService#updateUserById(com.mtea.yunwu.model.core.User)
	 */
	public long updateUserById(User user) {
		User oriUser = getUserById(user.getId());
		user.setAddTime(oriUser.getAddTime());
		return userDao.updateById(user);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.UserService#searchPage(int, com.mtea.yunwu.model.core.User)
	 */
	@Override
	public Pager<User> searchPage(int page, User user) {
		return userDao.findPage(page,user);
	}

}
