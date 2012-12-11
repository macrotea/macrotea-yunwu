package com.mtea.yunwu.service;

import java.util.List;

import com.mtea.yunwu.dao.exception.DaoException;
import com.mtea.yunwu.model.core.Dept;

/**
 * 部门服务接口
 * @author macrotea@qq.com
 * @date 2012-12-1 下午9:09:02
 * @version 1.0
 * @note
 */
public interface DeptService {
	
	/**
	 * 添加部门
	 * @return
	 * macrotea / 2012-11-29 下午5:14:10
	 */
	Dept addDept(Dept dept);
	
	/**
	 * 根据Id删除部门且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:26
	 */
	long deleteDept(long id);
	
	/**
	 * 删除所有部门且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午6:39:33
	 */
	long deleleAllDept();
	
	/**
	 * 获得所有的部门
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	List<Dept> getAllDept();
	
	/**
	 * 计算总行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	Long countAllDept();
	
	/**
	 * 根据Id获得部门
	 * @param id
	 * @return
	 * macrotea / 2012-11-29 下午6:06:18
	 */
	Dept getDeptById(long id);
	
	/**
	 * 根据Id更新部门且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:46
	 * @throws DaoException 
	 */
	long updateDeptById(Dept dept) throws DaoException;

}
