package com.meli.ipAPI.ServiceImpl;

import java.util.Map;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.meli.ipAPI.DTO.ipResponseDTO;
import com.meli.ipAPI.DTO.ipResponseServiceDTO;
import com.meli.ipAPI.DTO.countryResponseServiceDTO;
import com.meli.ipAPI.IService.IipService;
import com.meli.ipAPI.ExternalServices.ipInfoExService;
import com.meli.ipAPI.ExternalServices.countryExService;
import com.meli.ipAPI.ExternalServices.exchangeExService;

@Primary
@Service
public class ipServiceImpl implements IipService{
	
	private ipResponseDTO ipResponse = new ipResponseDTO();
	
	//Declaración de los servicios externos
	private final ipInfoExService ipinfoExService;
	private final countryExService countryExService;
	private final exchangeExService exchangeExService;
	
	@Autowired
	public ipServiceImpl(ipInfoExService ipinfoExService, countryExService countryExService, exchangeExService exchangeExService)
	{
		this.ipinfoExService = ipinfoExService;
		this.countryExService = countryExService;
		this.exchangeExService = exchangeExService;
	}
	
	public ipResponseDTO showIpInfo(String ipID)
	{
		//Llamado al servicio de información de IP
		ipResponseServiceDTO responseIpApiService = ipinfoExService.ipApiInfo(ipID);
		
		//Obtengo los datos de las subclases
		ipResponseServiceDTO.Location locationResponse = responseIpApiService.getLocation();
		List<ipResponseServiceDTO.Location.Language> languageList = locationResponse.getLanguages();
		
		//Llamado al servicio de información de país
		List<countryResponseServiceDTO> responseCountryService = countryExService.countryInfoApi(responseIpApiService.getCountry_name());
		
		//Llamado al servicio de monedas y TRM basada en Euro
		Map<String, Object> responseExchangeInfo = exchangeExService.exchangeInfoApi();
		
		//Calculo el cambio a USD
		Map<String, Double> ratesValues = (Map<String, Double>) responseExchangeInfo.get("rates");
		ipResponse.setCotizacionUSD(ratesValues.get(responseIpApiService.getCurrency().getCode())/ ratesValues.get("USD"));
		
		
		ipResponse.setMyIP(responseIpApiService.getIp());
		ipResponse.setIdioma(languageList.get(0).getName());
		ipResponse.setMonedaLocal(responseIpApiService.getCurrency().getName());
		ipResponse.setHoraActual(responseIpApiService.getTime_zone().getCurrent_time());
		
		return ipResponse;
	}
}
