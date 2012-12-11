package com.mtea.yunwu.model.core;

import java.io.Serializable;

/**
 * 部门表
 * 
 * @author macrotea@qq.com
 * @date 2012-11-29 下午9:15:59
 * @version 1.0
 * @note
 */
public class Dept extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String deptName;
	
	private Double orderValue;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Double getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Double orderValue) {
		this.orderValue = orderValue;
	}

}