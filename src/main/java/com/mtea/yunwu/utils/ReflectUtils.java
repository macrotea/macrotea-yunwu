package com.mtea.yunwu.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * @author macrotea@qq.com
 * @date 2012-11-30 下午8:33:16
 * @version 1.0
 * @note
 */
public final class ReflectUtils {
	
	/**
	 * 根据对象及其属性名获得属性值
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午8:34:28
	 * @param obj
	 * @param propertyName
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object getProperty(Object obj, String propertyName) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, obj.getClass());
		Method methodGetX = descriptor.getReadMethod();
		return methodGetX.invoke(obj);
	}
}
