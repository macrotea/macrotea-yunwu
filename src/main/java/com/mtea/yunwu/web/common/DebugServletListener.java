package com.mtea.yunwu.web.common;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugServletListener 
				implements 
				ServletContextListener, 
				ServletContextAttributeListener, 
				HttpSessionActivationListener, 
				HttpSessionAttributeListener, 
				HttpSessionListener,
				ServletRequestAttributeListener, 
				ServletRequestListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 切换日志等级
	 * @param text
	 * @author liangqiye / 2012-12-18 上午10:41:25
	 */
	private void toggleLogOutputLevel(String text){
		logger.debug(text);
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		toggleLogOutputLevel("create session:" + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		toggleLogOutputLevel("destroy session:" + event.getSession().getId());
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		toggleLogOutputLevel("session add attribute:" + event.getName() + "=" + event.getValue() + "[" + event.getSession().getId());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		toggleLogOutputLevel("session remove attribute:" + event.getName() + "=" + event.getValue() + "[" + event.getSession().getId());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		toggleLogOutputLevel("session replace attribute:" + event.getName() + "=" + event.getValue() + "[" + event.getSession().getId());
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent event) {
		toggleLogOutputLevel("session activate:" + event.getSession().getId());
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent event) {
		toggleLogOutputLevel("session passivate:" + event.getSession().getId());
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		toggleLogOutputLevel("context add attribute:" + event.getName() + "=" + event.getValue() + "[" + event.getServletContext());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		toggleLogOutputLevel("context remove attribute:" + event.getName() + "=" + event.getValue() + "[" + event.getServletContext());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		toggleLogOutputLevel("context replace attribute:" + event.getName() + "=" + event.getValue() + "[" + event.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		toggleLogOutputLevel("context destroy:" + event.getServletContext());

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		toggleLogOutputLevel("context init:" + event.getServletContext());
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent event) {
		toggleLogOutputLevel("request add attribute:" + event.getName() + "=" + event.getValue() + "[" + event.getServletRequest());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent event) {
		toggleLogOutputLevel("request remove attribute:" + event.getName() + "=" + event.getValue() + "[" + event.getServletRequest());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent event) {
		toggleLogOutputLevel("request replace attribute:" + event.getName() + "=" + event.getValue() + "[" + event.getServletRequest());
	}

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		toggleLogOutputLevel("request destroy:" + event.getServletRequest());
	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		toggleLogOutputLevel("request init:" + event.getServletRequest());
	}

}
