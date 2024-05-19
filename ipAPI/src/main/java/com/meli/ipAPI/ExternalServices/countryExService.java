package com.meli.ipAPI.ExternalServices;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meli.ipAPI.DTO.countryResponseServiceDTO;

import jakarta.annotation.PostConstruct;

@Service
public class countryExService {

	//variables de uso del api de Country
	@Value("${api.Country.accessKey}")
	private String accessKeyCountry;
	@Value("${api.Country.url}")
	private String urlCountry;
	
	public List<countryResponseServiceDTO> countryInfoApi(String pContryName)
	{
		String url = urlCountry + "name/" + pContryName + "?access_key=" + accessKeyCountry;
		RestTemplate countryApiService = new RestTemplate();
		
		//Se intermedia la respuesta debido a que el servicio responde una lista de objetos
		countryResponseServiceDTO[] response = countryApiService.getForObject(url, countryResponseServiceDTO[].class);
		return Arrays.asList(response);
	}

}
