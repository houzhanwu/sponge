package com.apr7.sponge.protocol;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apr7.sponge.protocol.hjt212.server.HJT212Server;
import com.apr7.sponge.protocol.knt2014.server.KNT2014Server;

@Component
public class SpongeServerListener implements ServletContextListener {

	@Autowired
	private HJT212Server hjt212Server;

	@Autowired
	private KNT2014Server knt2014Server;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		hjt212Server.start();
		knt2014Server.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		hjt212Server.stop();
		knt2014Server.stop();
	}

}
