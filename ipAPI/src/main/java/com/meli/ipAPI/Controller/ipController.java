package com.meli.ipAPI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.meli.ipAPI.DTO.*;
import com.meli.ipAPI.IService.IipService;

@RestController
@RequestMapping("/ipAPI")
public class ipController {
	
	@Autowired
	private IipService ipService;
	
	@GetMapping("/showIpInfo/{ipID}")
	public ipResponseDTO showIpInfo(@PathVariable String ipID)
	{
			ipResponseDTO ipResponse = ipService.showIpInfo(ipID);
			return ipResponse;
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error: " + ex.getMessage());
    }
}
