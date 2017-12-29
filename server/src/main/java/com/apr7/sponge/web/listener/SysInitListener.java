package com.apr7.sponge.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.apr7.sponge.protocol.SpongeServerListener;

public class SysInitListener implements ServletContextListener {
	private SpongeServerListener spongeServerListener;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		spongeServerListener = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(SpongeServerListener.class);
		spongeServerListener.contextInitialized(sce);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		spongeServerListener.contextDestroyed(sce);
	}

}
