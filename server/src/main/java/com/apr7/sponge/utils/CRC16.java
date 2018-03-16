package com.apr7.sponge.utils;

import org.apache.commons.lang3.StringUtils;

public class CRC16 {
	public static String checkout(String puchMsg) {
		int crcReg = 0xFFFF;
		for (int i = 0; i < puchMsg.length(); i++) {
			crcReg = (crcReg >> 8) ^ puchMsg.charAt(i);
			for (int j = 0; j < 8; j++) {
				int check = crcReg & 0x0001;
				crcReg >>= 1;
				if (check == 0x0001) {
					crcReg ^= 0xA001;
				}
			}
		}
		return StringUtils.leftPad(Integer.toHexString(crcReg), 4, '0');
	}

	public static void main(String[] args) {
		System.out.println(CRC16.checkout("ST=32;CN=9014;PW=123456;MN=26807585000028;Flag=0;CP=&&CN=2081;QN=20180316164927&&"));
	}
}
