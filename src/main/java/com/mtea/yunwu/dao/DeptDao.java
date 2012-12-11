package com.mtea.yunwu.dao;

import java.util.List;

import com.mtea.yunwu.dao.exception.DaoException;
import com.mtea.yunwu.model.core.Dept;

/**
 * 部门数据库访问接口
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-11-29 下午5:14:55
 * @note
 */
public interface DeptDao {
	
	/**
	 * 插入部门
	 * @return
	 * macrotea / 2012-11-29 下午5:14:10
	 */
	Dept save(Dept dept);
	
	/**
	 * 根据Id删除部门且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:26
	 */
	long delete(long id);
	
	/**
	 * 删除所有部门且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午6:39:33
	 */
	long deleleAll();
	
	/**
	 * 查找所有的部门
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	List<Dept> findAll();
	
	/**
	 * 计算总行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	Long countAll();
	
	/**
	 * 根据Id获得部门
	 * @param id
	 * @return
	 * macrotea / 2012-11-29 下午6:06:18
	 */
	Dept findById(long id);
	
	/**
	 * 根据Id更新部门且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:46
	 * @throws DaoException 
	 */
	long updateById(Dept dept) throws DaoException;
	
}
