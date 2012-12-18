package com.mtea.yunwu.service;

import java.util.List;

import com.mtea.yunwu.model.core.User;
import com.mtea.yunwu.utils.Pager;

/**
 * 用户服务接口
 * @author macrotea@qq.com
 * @date 2012-12-1 下午9:09:02
 * @version 1.0
 * @note
 */
public interface UserService {
	
	/**
	 * 添加用户
	 * @return
	 * macrotea / 2012-11-29 下午5:14:10
	 */
	User addUser(User user);
	
	/**
	 * 根据Id删除用户且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:26
	 */
	long deleteUser(long id);
	
	/**
	 * 删除所有用户且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午6:39:33
	 */
	long deleleAllUser();
	
	/**
	 * 获得所有的用户
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	List<User> getAllUser();
	
	/**
	 * 计算总行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	Long countAllUser();
	
	/**
	 * 根据Id获得用户
	 * @param id
	 * @return
	 * macrotea / 2012-11-29 下午6:06:18
	 */
	User getUserById(long id);
	
	/**
	 * 根据Id更新用户且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:46
	 * @throws DaoException 
	 */
	long updateUserById(User user);

	/**
	 * 搜索分页数据
	 * @author macrotea@qq.com
	 * @date 2012-12-10 下午11:38:19
	 * @param i
	 * @param userCriteria
	 * @return
	 */
	Pager<User> searchPage(int page, User userCriteria);

}
