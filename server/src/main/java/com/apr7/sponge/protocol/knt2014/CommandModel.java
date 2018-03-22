package com.apr7.sponge.protocol.knt2014;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.apr7.sponge.utils.CRC16;

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
		_cpMap = null;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public Map<String, String> getCpValuesMap() {
		if (_cpMap == null) {
			_cpMap = this.parseCp(cp);
		}
		return Collections.unmodifiableMap(_cpMap);
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
		String[] tokens = StringUtils.split(cp, ';');
		for (String token : tokens) {
			if (StringUtils.isEmpty(token)) {
				continue;
			}
			String[] fields = StringUtils.split(token, ',');
			for (String field : fields) {
				String[] kv = StringUtils.split(field, '=');
				result.put(kv[0], kv[1]);
			}
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

	@Override
	public String toString() {
		return CommandModel.encode(this);
	}

	public static String encode(CommandModel cmd) {
		StringBuilder sb = new StringBuilder();
		sb.append("QN=").append(cmd.getQn()).append(';');
		sb.append("ST=").append(cmd.getSt()).append(';');
		sb.append("CN=").append(cmd.getCn()).append(';');
		sb.append("PW=").append(cmd.getPw()).append(';');
		sb.append("MN=").append(cmd.getMn()).append(';');
		sb.append("Flag=").append(cmd.getFlag()).append(';');
		sb.append("CP=").append("&&").append(cmd.makeCpString()).append("&&");
		String dataString = sb.toString();
		return "##" + String.format("%04d", dataString.length()) + dataString + CRC16.checkout(dataString);
	}

	public static CommandModel decode(String msg) {
		if (!msg.startsWith("##")) {
			throw new IllegalArgumentException("error command: " + msg);
		}
		int dataLength = Integer.parseInt(msg.substring(2, 6));
		String dataString = msg.substring(6, 6 + dataLength);
		String crc16 = msg.substring(6 + dataLength);
		if (!StringUtils.equalsIgnoreCase(crc16, CRC16.checkout(dataString))) {
			throw new IllegalArgumentException("error crc checkout: " + msg);
		}
		Map<String, String> cmdParam = new HashMap<>();
		String[] tokens = StringUtils.splitByWholeSeparator(dataString, ";CP=&&");
		String[] cmdParamtokens = StringUtils.split(tokens[0], ';');
		for (String token : cmdParamtokens) {
			String[] kv = StringUtils.split(token, '=');
			cmdParam.put(kv[0].toUpperCase(), kv[1]);
		}
		CommandModel cmd = new CommandModel();
		cmd.setRaw(msg);
		cmd.setQn(cmdParam.get("QN"));
		cmd.setSt(cmdParam.get("ST"));
		cmd.setCn(cmdParam.get("CN"));
		cmd.setPw(cmdParam.get("PW"));
		cmd.setMn(cmdParam.get("MN"));
		cmd.setFlag(cmdParam.get("FLAG"));
		cmd.setSver(cmdParam.get("SVER"));
		cmd.setSvdata(cmdParam.get("SVDATA"));
		cmd.setCp(StringUtils.substring(tokens[1], 0, -2));
		return cmd;
	}

	public static CommandModel createCommand(String cn) {
		CommandModel cmd = new CommandModel();
		cmd.setQn(DateFormatUtils.format(new Date(), "YYYYMMddHHmmss"));
		cmd.setSt("32");
		cmd.setCn(cn);
		cmd.setPw("123456");
		cmd.setFlag("0");
		return cmd;
	}
}
