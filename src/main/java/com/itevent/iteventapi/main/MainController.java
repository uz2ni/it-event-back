package com.itevent.iteventapi.main;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/")
	public String home(@CurrentAccount Account account, Model model) {
		if (account != null) {
			model.addAttribute(account);
		}

		return "index";
	}
}
