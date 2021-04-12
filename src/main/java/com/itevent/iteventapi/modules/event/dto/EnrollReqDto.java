package com.itevent.iteventapi.modules.event.dto;

import lombok.Getter;
import lombok.Setter;

public class EnrollReqDto {

	@Getter
	@Setter
	public static class Accept {

		private boolean accepted;
		private String attendeeName;

	}

	@Getter
	@Setter
	public static class Attend {
		private boolean attended;
	}
}
