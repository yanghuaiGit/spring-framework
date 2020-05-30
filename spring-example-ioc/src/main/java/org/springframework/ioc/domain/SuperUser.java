package org.springframework.ioc.domain;

public class SuperUser extends User {
	private static final long serialVersionUID = 1L;

	public String account;


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
