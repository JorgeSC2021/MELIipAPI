package com.meli.ipAPI.ExternalServices;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.meli.ipAPI.DTO.countryResponseServiceDTO;

@Service
public class countryExService {

	//variables de uso del api de Country
	@Value("${api.Country.accessKey}")
	private String accessKeyCountry;
	@Value("${api.Country.url}")
	private String urlCountry;
	
	public countryExService()
	{
		System.out.print("Instanciación de CountryExService");
	}
	
	public List<countryResponseServiceDTO> countryInfoApi(String pContryName)
	{
		String url = urlCountry + "name/" + pContryName + "?access_key=" + accessKeyCountry;
		RestTemplate countryApiService = new RestTemplate();
        
		try {
            countryResponseServiceDTO[] response = countryApiService.getForObject(url, countryResponseServiceDTO[].class);
            List<countryResponseServiceDTO> responseList = Arrays.asList(response);
            return responseList;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al llamar al servicio externo de país: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la respuesta del servicio de país", e);
        }
	}

}
