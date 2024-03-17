package com.aresky.authservice.dto;

import com.aresky.authservice.entity.Auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupForm {
	private String username;
	private String email;
	private String password;

	public Auth toEntity() {
		return new Auth(username, email, password);
	}
}
