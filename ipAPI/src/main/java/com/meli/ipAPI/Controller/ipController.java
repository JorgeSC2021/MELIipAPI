package com.meli.ipAPI.Controller;

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
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró información para la IP data");
			
			return ResponseEntity.ok(ipResponse);
	}
	
	@GetMapping("/findCityMaxDistance")
	public ResponseEntity<?> findCityMaxDistance()
	{
		cityDistanceResponseDTO response = ipService.findCityMaxDistance();
		
		if(response == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se tienen datos de peticiones por el momento");
		else
			return ResponseEntity.ok(response); 
	}
	
	@GetMapping("/findCityMinDistance")
	public ResponseEntity<?> findCityMinDistance()
	{
		cityDistanceResponseDTO response = ipService.findCityMinDistance();
		
		if(response == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se tienen datos de peticiones por el momento");
		else
			return ResponseEntity.ok(response);
	}
	
	@GetMapping("/AvgDistance")
	public ResponseEntity<?> avgDistance()
	{
		Double response = ipService.cityAvgDistance();
		
		if(response == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se tienen datos de peticiones por el momento para calcular el promedio");
		else
			return ResponseEntity.ok("Distancia promedio de todas las ejecuciones del servicio:  " + response.toString());
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error: " + ex.getMessage());
    }
}
