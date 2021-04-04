package com.itevent.iteventapi.modules.account.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class AccountUpdateDto {

	@Getter
	@Setter
	public static class Nickname {

		@NotBlank
		@Length(min = 3, max = 20)
		@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
		private String newNickname;

	}

	@Getter
	@Setter
	public static class Password {

		@NotBlank
		@Length(min = 8, max = 20)
		private String password;

		@NotBlank
		@Length(min = 8, max = 20)
		private String newPassword;

		@NotBlank
		@Length(min = 8, max = 20)
		private String confirmPassword;

	}

}