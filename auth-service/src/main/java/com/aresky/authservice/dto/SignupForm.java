package com.aresky.authservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupForm {
	private String username;
	private String email;
	private String password;
}
