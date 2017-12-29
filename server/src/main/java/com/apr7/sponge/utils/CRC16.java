package com.apr7.sponge.utils;

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
		return Integer.toHexString(crcReg);
	}

	public static void main(String[] args) {
		System.out.println(CRC16.checkout("QN=20160801085857223;ST=32;CN=1062;PW=100000;MN=010000A8900016F000169DC0;Flag=5;CP=&&RtdInterval=30&&"));
	}
}
