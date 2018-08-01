package de.axp.framework.internal.authentication;

public class Authentication {

	private final String username;

	public Authentication(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
