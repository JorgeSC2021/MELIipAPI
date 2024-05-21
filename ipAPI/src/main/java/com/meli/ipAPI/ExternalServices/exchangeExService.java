package com.meli.ipAPI.ExternalServices;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class exchangeExService {

	//variables de uso del api de Country
	@Value("${api.Exchange.accessKey}")
	private String accessKeyExchange;
	@Value("${api.Exchange.url}")
	private String urlExchange;
	
	@Async
	public Map<String, Object> exchangeInfoApi()
	{
		String url = urlExchange + "latest?access_key=" + accessKeyExchange;
		RestTemplate ipApiService = new RestTemplate();
		
		return ipApiService.getForObject(url, Map.class);
	}

}
