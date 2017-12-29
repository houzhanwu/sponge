package com.apr7.sponge.protocol.hjt212.client.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CommandModel {
	private static final int A = 0x0001;
	private static final int D = 0x0002;

	protected String qn;
	protected String st;
	protected String cn;
	protected String pw;
	protected String mn;
	protected byte flag;
	protected int pnum;
	protected int pno;
	protected String cp;
	private Map<String, String> _cpMap;

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getCpValue(String key) {
		if (_cpMap == null) {
			_cpMap = this.parseCp(cp);
		}
		return _cpMap.get(key);
	}

	private Map<String, String> parseCp(String cp) {
		Map<String, String> result = new HashMap<String, String>();
		if (!cp.startsWith("&&") || !cp.endsWith("&&")) {
			throw new IllegalArgumentException("CP format error.");
		}
		String[] tokens = StringUtils.split(StringUtils.substring(cp, 2, -2), ';');
		for (String token : tokens) {
			String[] kv = StringUtils.split(token, '=');
			result.put(kv[0].toUpperCase(), kv[1]);
		}
		return result;
	}

	public boolean isA() {
		return (flag & A) != 0;
	}

	public boolean isD() {
		return (flag & D) != 0;
	}

	public static CommandModel create(String dataString) {
		CommandModel cmd = new CommandModel();
		String tmp;
		int fromIndex = 0;
		int nextIndex;
		nextIndex = dataString.indexOf(';', fromIndex);
		tmp = dataString.substring(fromIndex, nextIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "QN=")) {
			throw new IllegalArgumentException("QN not found.");
		}
		String qn = tmp.substring(3);
		cmd.setQn(qn);
		fromIndex = nextIndex + 1;
		nextIndex = dataString.indexOf(';', fromIndex);
		tmp = dataString.substring(fromIndex, nextIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "ST=")) {
			throw new IllegalArgumentException("ST not found.");
		}
		String st = tmp.substring(3);
		cmd.setSt(st);
		fromIndex = nextIndex + 1;
		nextIndex = dataString.indexOf(';', fromIndex);
		tmp = dataString.substring(fromIndex, nextIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "CN=")) {
			throw new IllegalArgumentException("CN not found.");
		}
		String cn = tmp.substring(3);
		cmd.setCn(cn);
		fromIndex = nextIndex + 1;
		nextIndex = dataString.indexOf(';', fromIndex);
		tmp = dataString.substring(fromIndex, nextIndex);
		if (!StringUtils.startsWithIgnoreCase(tmp, "PW=")) {
			throw new IllegalArgumentException("PW not found.");
		}
		String pw = tmp.substring(3);
		cmd.setPw(pw);
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
}
