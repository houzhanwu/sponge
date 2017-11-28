package com.apr7.sponge.protocol.server.hjt212.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Hjt212CommandFactory {
	private final Map<String, Class<? extends Hjt212Command>> _hjt212Commands = new HashMap<>();

	public Hjt212CommandFactory() {
		_hjt212Commands.put(CN2021Command.CN, CN2021Command.class);
		_hjt212Commands.put(CN9011Command.CN, CN9011Command.class);
	}

	public Hjt212Command getCommand(String dataString) {
		String tmp;
		int fromIndex = 0;
		int nextIndex;
		nextIndex = dataString.indexOf(';', fromIndex);
		tmp = dataString.substring(fromIndex, nextIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "QN=")) {
			throw new IllegalArgumentException("QN not found.");
		}
		String qn = tmp.substring(3);
		fromIndex = nextIndex + 1;
		nextIndex = dataString.indexOf(';', fromIndex);
		tmp = dataString.substring(fromIndex, nextIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "ST=")) {
			throw new IllegalArgumentException("ST not found.");
		}
		String st = tmp.substring(3);
		fromIndex = nextIndex + 1;
		nextIndex = dataString.indexOf(';', fromIndex);
		tmp = dataString.substring(fromIndex, nextIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "CN=")) {
			throw new IllegalArgumentException("CN not found.");
		}
		Hjt212Command cmd = this.createCommand(tmp.substring(3));
		cmd.setQn(qn);
		cmd.setSt(st);
		fromIndex = nextIndex + 1;
		nextIndex = dataString.indexOf(';', fromIndex);
		tmp = dataString.substring(fromIndex, nextIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "PW=")) {
			throw new IllegalArgumentException("PW not found.");
		}
		fromIndex = nextIndex + 1;
		nextIndex = dataString.indexOf(';', fromIndex);
		tmp = dataString.substring(fromIndex, nextIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "MN=")) {
			throw new IllegalArgumentException("MN not found.");
		}
		cmd.setMn(tmp.substring(3));
		fromIndex = nextIndex + 1;
		nextIndex = dataString.indexOf(';', fromIndex);
		tmp = dataString.substring(fromIndex, nextIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "FLAG=")) {
			throw new IllegalArgumentException("FLAG not found.");
		}
		cmd.setFlag(Byte.parseByte(tmp.substring(5)));
		fromIndex = nextIndex + 1;
		if (cmd.isD()) {
			nextIndex = dataString.indexOf(';', fromIndex);
			tmp = dataString.substring(fromIndex, nextIndex);
			if (!StringUtils.startsWithIgnoreCase(tmp, "PNUM=")) {
				throw new IllegalArgumentException("PNUM not found.");
			}
			cmd.setPnum(Integer.parseInt(tmp.substring(5)));
			fromIndex = nextIndex + 1;
			nextIndex = dataString.indexOf(';', fromIndex);
			tmp = dataString.substring(fromIndex, nextIndex);
			if (!StringUtils.startsWithIgnoreCase(tmp, "PNO=")) {
				throw new IllegalArgumentException("PNO not found.");
			}
			cmd.setPno(Integer.parseInt(tmp.substring(4)));
			fromIndex = nextIndex + 1;
		}
		tmp = dataString.substring(fromIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "CP=")) {
			throw new IllegalArgumentException("CP not found.");
		}
		cmd.setCp(tmp.substring(3));
		return cmd;
	}

	private Hjt212Command createCommand(String cn) {
		Class<? extends Hjt212Command> commandClass = _hjt212Commands.get(cn);
		if (commandClass == null) {
			return null;
		} else {
			Hjt212Command cmd;
			try {
				cmd = commandClass.newInstance();
			} catch (Exception e) {
				throw new IllegalArgumentException("Can not create command instance " + commandClass.getName(), e);
			}
			return cmd;
		}
	}
}
