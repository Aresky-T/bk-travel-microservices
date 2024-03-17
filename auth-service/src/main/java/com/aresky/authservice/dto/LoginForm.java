package com.aresky.authservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginForm {
	private String username;
	private String password;

	public LoginForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}
