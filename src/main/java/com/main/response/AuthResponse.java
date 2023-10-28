package com.main.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AuthResponse {
	
	private String jwt;
	private String message;
	
	public AuthResponse() {
		
	}
	
	public AuthResponse(String jwt, String message) {
		super();
		this.jwt = jwt;
		this.message = message;
	}
}
