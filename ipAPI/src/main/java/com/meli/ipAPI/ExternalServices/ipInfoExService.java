package com.meli.ipAPI.ExternalServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meli.ipAPI.DTO.ipResponseServiceDTO;

@Service
public class ipInfoExService {
	
	@Value("${api.ipApi.accessKey}")
	private String accessKeyIpApi;
	@Value("${api.ipApi.url}")
	private String urlIpApi;
	
	public ipResponseServiceDTO ipApiInfo(String pIpID)
	{
		String url = urlIpApi + pIpID + "?access_key=" + accessKeyIpApi;
		RestTemplate ipApiService = new RestTemplate();
		
		return ipApiService.getForObject(url, ipResponseServiceDTO.class);
	}
}
