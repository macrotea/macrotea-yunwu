package com.mtea.yunwu.dao;

import java.util.List;

import com.mtea.yunwu.dao.exception.DaoException;
import com.mtea.yunwu.model.core.User;
import com.mtea.yunwu.utils.Pager;

/**
 * 用户数据库访问接口
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-11-29 下午5:14:55
 * @note
 */
public interface UserDao {
	
	/**
	 * 插入用户
	 * @return
	 * macrotea / 2012-11-29 下午5:14:10
	 */
	User save(User user);
	
	/**
	 * 根据Id删除用户且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:26
	 */
	long delete(long id);
	
	/**
	 * 删除所有用户且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午6:39:33
	 */
	long deleleAll();
	
	/**
	 * 查找所有的用户
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	List<User> findAll();
	
	/**
	 * 计算总行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	Long countAll();
	
	/**
	 * 根据Id获得用户
	 * @param id
	 * @return
	 * macrotea / 2012-11-29 下午6:06:18
	 */
	User findById(long id);
	
	/**
	 * 根据Id更新用户且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:46
	 * @throws DaoException 
	 */
	long updateById(User user) throws DaoException;

	/**
	 * @author macrotea@qq.com
	 * @date 2012-12-10 下午11:45:29
	 * @param page
	 * @param user
	 * @return
	 */
	Pager<User> findPage(int page, User user);
	
}
