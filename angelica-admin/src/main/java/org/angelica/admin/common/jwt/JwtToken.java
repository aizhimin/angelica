package org.angelica.admin.common.jwt;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
	private static final long serialVersionUID = 2658667758279328176L;
	
	private String token;
	
	public JwtToken(String token) {
		this.token = token;
	}
	
	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}

}
