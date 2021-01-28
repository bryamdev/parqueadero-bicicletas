package com.citcd.prueba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class HomeController {

	
	@GetMapping(value = "/home")
	public String home() {
		
		System.out.println("Si entró al handler");
		return "index";
	}
	
}
