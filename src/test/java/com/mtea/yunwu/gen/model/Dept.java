package com.mtea.yunwu.gen.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.mtea.yunwu.model.core.BaseModel;

/**
 * 部门表
 * 
 * @author macrotea@qq.com
 * @date 2012-11-29 下午9:15:59
 * @version 1.0
 * @note
 */
@Entity
@Table(name="tb_dept")
public class Dept extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String deptName;
	
	private Double orderValue;
	
	@ManyToMany
	private Set<Position> positionSet = new HashSet<Position>();
	
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