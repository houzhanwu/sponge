package com.apr7.sponge.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.apr7.sponge.protocol.SpongeServerListener;

@WebListener
public class SysInitListener implements ServletContextListener {
	@Autowired
	private SpongeServerListener spongeServerListener;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		spongeServerListener.contextInitialized(sce);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		spongeServerListener.contextDestroyed(sce);
	}

}
