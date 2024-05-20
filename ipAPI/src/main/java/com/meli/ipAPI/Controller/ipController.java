package com.meli.ipAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<?> showIpInfo(@PathVariable String ipID)
	{
			ipResponseDTO ipResponse = ipService.showIpInfo(ipID);
			if(ipResponse == null)
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontró información para la IP data");
			
			return ResponseEntity.ok(ipResponse);
	}
	
	@GetMapping("/findCityMaxDistance")
	public cityMaxDistanceResponseDTO findCityMaxDistance()
	{
		cityMaxDistanceResponseDTO response = ipService.findCityMaxDistance();
		return response; 
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error: " + ex.getMessage());
    }
}
