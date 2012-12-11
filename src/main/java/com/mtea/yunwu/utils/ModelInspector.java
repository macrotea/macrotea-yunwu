/**
 * 
 */
package com.mtea.yunwu.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mtea.yunwu.model.core.BaseModel;

/**
 * 模型内省器
 * 
 * @author macrotea@qq.com
 * @date 2012-11-29 下午10:50:17
 * @version 1.0
 * @note
 */
public class ModelInspector{

	private static String IGNORE_PROP_NAME = "class";
	private static String TB_NAME_SEPERATOR = "_";
	private static String TB_NAME_PREFIX = "tb" + TB_NAME_SEPERATOR;
	
	private Class<?> modelClazz;
	
	private ModelInspector(Class<?> modelClazz) {
		super();
		this.modelClazz = modelClazz;
	}

	public static ModelInspector forClazz(Class<?> modelClazz) {
		return new ModelInspector(modelClazz);
	}

	/**
	 * 获得常规列名
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午7:47:28
	 * @return
	 */
	public String[] getCommonColumns() {
		List<String> retVal = new ArrayList<String>();
		PropertyDescriptor[] propDescriptors = ModelDescriptorCache.getInstance().getDescriptors(modelClazz);
		try {
			for (PropertyDescriptor propertyDescriptor : propDescriptors) {
				// 属性名==列名
				String propertyName = propertyDescriptor.getName();
				// 排除主键列
				if (!propertyName.equals(getPKColumn()) && !propertyName.equals(IGNORE_PROP_NAME)) {
					retVal.add(propertyName);
				}
			}
		} catch (Exception ignoreEx) {
			ignoreEx.printStackTrace();
		}
		return retVal.toArray(new String[retVal.size()]);
	}

	/**
	 * 获得主键列名
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午7:47:38
	 * @return
	 */
	public String getPKColumn() {
		return BaseModel.PK;
	}

	/**
	 * 获得表名
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午7:48:39
	 * @return
	 */
	public String getTableName() {
		String clazzName = modelClazz.getSimpleName();
		String[] c = StringUtils.splitByCharacterTypeCamelCase(clazzName);
		String r = StringUtils.join(c, TB_NAME_SEPERATOR);
		return TB_NAME_PREFIX + r.toLowerCase();
	}

	public Class<?> getModelClazz() {
		return modelClazz;
	}

	public void setModelClazz(Class<?> modelClazz) {
		this.modelClazz = modelClazz;
	}
	
	
	
}
