package com.meli.ipAPI.ExternalServices;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Service
public class exchangeExService {

	//variables de uso del api de Country
	@Value("${api.Country.accessKey}")
	private String accessKeyExchange;
	@Value("${api.Country.url}")
	private String urlExchange;
	
	public Map<String, Object> exchangeInfoApi()
	{
		String accessKey = "88e6a3031d744857610ac4e43b429556";
		String url = urlExchange + "latest?access_key=" + accessKeyExchange;
		RestTemplate ipApiService = new RestTemplate();
		
		return ipApiService.getForObject(url, Map.class);
	}

}
