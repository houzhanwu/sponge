package com.apr7.sponge.protocol.server.hjt212.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.netty.channel.ChannelHandlerContext;

public abstract class Hjt212Command {
	private static final int A = 0x0001;
	private static final int D = 0x0002;

	protected String qn;
	protected String st;
	protected String mn;
	protected byte flag;
	protected int pnum;
	protected int pno;
	protected String cp;
	private Map<String, String> cpMap;

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
		if (cpMap == null) {
			cpMap = this.parseCp(cp);
		}
		return cpMap.get(key);
	}

	public abstract void doProcess(ChannelHandlerContext ctx);

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
}
