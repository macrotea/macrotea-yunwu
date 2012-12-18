package com.mtea.yunwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtea.yunwu.dao.EmployeeDao;
import com.mtea.yunwu.model.core.Employee;
import com.mtea.yunwu.service.EmployeeService;
import com.mtea.yunwu.utils.Pager;

/**
 * 部门服务接口实现类
 * @author macrotea@qq.com
 * @date 2012-12-1 下午9:08:44
 * @version 1.0
 * @note
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao employeeDao;

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.EmployeeService#addEmployee(com.mtea.yunwu.model.core.Employee)
	 */
	public Employee addEmployee(Employee employee) {
		return employeeDao.save(employee);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.EmployeeService#deleteEmployee(long)
	 */
	public long deleteEmployee(long id) {
		return employeeDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.EmployeeService#deleleAllEmployee()
	 */
	public long deleleAllEmployee() {
		return employeeDao.deleleAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.EmployeeService#getAllEmployee()
	 */
	public List<Employee> getAllEmployee() {
		return employeeDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.EmployeeService#countAllEmployee()
	 */
	public Long countAllEmployee() {
		return employeeDao.countAll();
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.EmployeeService#getEmployeeById(long)
	 */
	public Employee getEmployeeById(long id) {
		return employeeDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.EmployeeService#updateEmployeeById(com.mtea.yunwu.model.core.Employee)
	 */
	public long updateEmployeeById(Employee employee) {
		Employee oriEmployee = getEmployeeById(employee.getId());
		employee.setAddTime(oriEmployee.getAddTime());
		return employeeDao.updateById(employee);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.service.EmployeeService#searchPage(int, com.mtea.yunwu.model.core.Employee)
	 */
	@Override
	public Pager<Employee> searchPage(int page, Employee employee) {
		return employeeDao.findPage(page,employee);
	}

}
