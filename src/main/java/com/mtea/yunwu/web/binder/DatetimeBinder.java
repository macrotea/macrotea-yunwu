package com.mtea.yunwu.web.binder;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.mtea.yunwu.utils.DatetimeUtils;

public class DatetimeBinder implements WebBindingInitializer{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void initBinder(WebDataBinder dataBinder, WebRequest request) {
		
		//打印请求参数名和值
		Map<String, String[]> paramNameValueMap=request.getParameterMap();
		for (String paramName : paramNameValueMap.keySet()) {
			String[] paramValues=paramNameValueMap.get(paramName);
			for (String value : paramValues) {
				logger.debug(String.format("参数名:%s , 参数值:%s", paramName,value));
			}
		}
		
		// false->非宽松
		DatetimeUtils.FORMATTER.setLenient(false);
		// true->允许为空
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DatetimeUtils.FORMATTER, true));
	}

}
