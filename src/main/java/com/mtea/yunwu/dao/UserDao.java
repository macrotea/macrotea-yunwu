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
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:07
	 * @param user
	 * @return
	 */
	User save(User user);
	
	/**
	 * 根据Id删除用户且返回影响行数
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:14
	 * @param id
	 * @return
	 */
	long delete(long id);
	
	/**
	 * 删除所有用户且返回影响行数
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:19
	 * @return
	 */
	long deleleAll();
	
	/**
	 * 查找所有的用户
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:25
	 * @return
	 */
	List<User> findAll();
	
	/**
	 * 计算总行数
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:33
	 * @return
	 */
	Long countAll();
	
	/**
	 * 根据Id获得用户
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:40
	 * @param id
	 * @return
	 */
	User findById(long id);
	
	/**
	 * 根据Id更新用户且返回影响行数
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:46
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	long updateById(User user) throws DaoException;

	/**
	 * 根据页码,条件实例查找数据,返回分页器
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:52
	 * @param page
	 * @param criteriaUser
	 * @return
	 */
	Pager<User> findPage(int page, User criteriaUser);
	
	/**
	 * 根据页码,页大小,条件实例查找数据,返回分页器
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午10:52:59
	 * @param page
	 * @param pageSize
	 * @param criteriaUser
	 * @return
	 */
	Pager<User> findPage(int page,int pageSize, User criteriaUser);
	
}
