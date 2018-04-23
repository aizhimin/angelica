package org.angelica.core.security;

import org.junit.Test;

public class BCryptUtilTest {
	
	@Test
	public void testEncode() {
		String rawPassword = "123456";
		String encodedPassword = BCryptUtil.encode(rawPassword);
		System.out.println(encodedPassword);
	}
	
	@Test
	public void testMatches() {
		String rawPassword = "123456";
		String encodedPassword = "$2a$10$5FOrqMYhhf0ri3aWMyAd3e4Xu/0O7N0ROXTxVf3aouaBMDU2BLhAS";
		boolean isMatched = BCryptUtil.matches(rawPassword, encodedPassword);
		System.out.println(isMatched);
	}
}
