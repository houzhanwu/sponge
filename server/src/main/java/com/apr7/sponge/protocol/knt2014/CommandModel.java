package com.apr7.sponge.protocol.knt2014;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class CommandModel {
	protected String qn;
	protected String st;
	protected String cn;
	protected String pw;
	protected String mn;
	protected String flag;
	protected String sver;
	protected String svdata;
	protected String cp;
	private String raw;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSver() {
		return sver;
	}

	public void setSver(String sver) {
		this.sver = sver;
	}

	public String getSvdata() {
		return svdata;
	}

	public void setSvdata(String svdata) {
		this.svdata = svdata;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getCpValue(String key) {
		if (_cpMap == null) {
			_cpMap = this.parseCp(cp);
		}
		return _cpMap.get(key);
	}

	public void setCpValue(String key, String value) {
		if (_cpMap == null) {
			_cpMap = new HashMap<>();
		}
		_cpMap.put(key, value);
	}

	private Map<String, String> parseCp(String cp) {
		Map<String, String> result = new HashMap<String, String>();
		String[] tokens = StringUtils.split(StringUtils.substring(cp, 2, -2), ';');
		for (String token : tokens) {
			String[] kv = StringUtils.split(token, '=');
			result.put(kv[0].toUpperCase(), kv[1]);
		}
		return result;
	}

	public String makeCpString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : MapUtils.emptyIfNull(_cpMap).entrySet()) {
			sb.append(entry.getKey()).append('=').append(entry.getValue()).append(';');
		}
		if (StringUtils.isNotEmpty(sb)) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public CommandModel createResCommand() {
		CommandModel res = new CommandModel();
		res.setQn(DateFormatUtils.format(new Date(), "YYYYMMddHHmmss"));
		res.setSt(this.getSt());
		res.setPw(this.getPw());
		res.setMn(this.getMn());
		res.setFlag("0");
		return res;
	}
}
