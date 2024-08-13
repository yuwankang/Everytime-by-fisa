package com.fisa.land.fisaland.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("common")
public class UserController {
	
	@GetMapping("test")
	public String test() {
		System.out.println("123");
		return "123";
	}
}
