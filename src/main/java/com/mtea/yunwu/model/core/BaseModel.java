package com.mtea.yunwu.model.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 父级模型
 * @author macrotea@qq.com
 * @date 2012-11-29 下午9:17:20
 * @version 1.0
 * @note
 */
@MappedSuperclass
public class BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static String PK = "id";
	public static final String ADDTIME = "addTime";
	public static final String EDITTIME = "editTime";
	public static final String REMARK = "remark";
	public static final String ORDERVALUE = "orderValue";

	@Id
	@GeneratedValue
	private Long id;
	
	private Date addTime;
	
	private Date editTime;
	
	private String remark;
	
	/**
	 * order 是MySQL关键字
	 */
	private Double orderValue;
	
	public BaseModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Double getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Double orderValue) {
		this.orderValue = orderValue;
	}
	
}
