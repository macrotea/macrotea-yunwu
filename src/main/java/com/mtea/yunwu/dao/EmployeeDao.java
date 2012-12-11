package com.mtea.yunwu.dao;

import java.util.List;

import com.mtea.yunwu.dao.exception.DaoException;
import com.mtea.yunwu.model.core.Employee;

/**
 * 员工数据库访问接口
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-11-29 下午5:14:55
 * @note
 */
public interface EmployeeDao {
	
	/**
	 * 插入员工
	 * @return
	 * macrotea / 2012-11-29 下午5:14:10
	 */
	Employee save(Employee employee);
	
	/**
	 * 根据Id删除员工且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:26
	 */
	long delete(long id);
	
	/**
	 * 删除所有员工且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午6:39:33
	 */
	long deleleAll();
	
	/**
	 * 查找所有的员工
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	List<Employee> findAll();
	
	/**
	 * 计算总行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	Long countAll();
	
	/**
	 * 根据Id获得员工
	 * @param id
	 * @return
	 * macrotea / 2012-11-29 下午6:06:18
	 */
	Employee findById(long id);
	
	/**
	 * 根据Id更新员工且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:46
	 * @throws DaoException 
	 */
	long updateById(Employee employee) throws DaoException;
	
}
