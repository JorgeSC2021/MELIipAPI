package com.meli.ipAPI.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
	
	@GetMapping("testService")
	public String testService()
	{
		return "El servicio está funcionando correctamente";
	}
}
