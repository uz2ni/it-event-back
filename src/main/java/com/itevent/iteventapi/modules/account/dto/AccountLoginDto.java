package com.itevent.iteventapi.modules.account.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AccountLoginDto {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Length(min = 8, max = 20)
	private String password;

}
