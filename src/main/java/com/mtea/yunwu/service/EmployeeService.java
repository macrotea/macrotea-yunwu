package com.mtea.yunwu.service;

import java.util.List;

import com.mtea.yunwu.dao.exception.DaoException;
import com.mtea.yunwu.model.core.Employee;

/**
 * 员工服务接口
 * @author macrotea@qq.com
 * @date 2012-12-1 下午9:09:02
 * @version 1.0
 * @note
 */
public interface EmployeeService {
	
	/**
	 * 添加员工
	 * @return
	 * macrotea / 2012-11-29 下午5:14:10
	 */
	Employee addEmployee(Employee employee);
	
	/**
	 * 根据Id删除员工且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:26
	 */
	long deleteEmployee(long id);
	
	/**
	 * 删除所有员工且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午6:39:33
	 */
	long deleleAllEmployee();
	
	/**
	 * 获得所有的员工
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	List<Employee> getAllEmployee();
	
	/**
	 * 计算总行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:39
	 */
	Long countAllEmployee();
	
	/**
	 * 根据Id获得员工
	 * @param id
	 * @return
	 * macrotea / 2012-11-29 下午6:06:18
	 */
	Employee getEmployeeById(long id);
	
	/**
	 * 根据Id更新员工且返回影响行数
	 * @return
	 * macrotea / 2012-11-29 下午5:14:46
	 * @throws DaoException 
	 */
	long updateEmployeeById(Employee employee) throws DaoException;

}
