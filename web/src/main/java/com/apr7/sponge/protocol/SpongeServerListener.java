package com.apr7.sponge.protocol;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.apr7.sponge.protocol.hjt212.server.HJ_T212Server;

public class SpongeServerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HJ_T212Server.getInstance().start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HJ_T212Server.getInstance().stop();
	}

}
