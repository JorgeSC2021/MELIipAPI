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
	
	//Declaración de los servicios externos, se les coloca la declaracion "final" ya que internamente contiene variables anotadas, y su valor debe mantenerse
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
		Double distance2points = getDistanceBetweenPointsNew();
		
		List<ipResponseServiceDTO.Location.Language> languageList = locationResponse.getLanguages();
		
		//Llamado al servicio de información de país
		List<countryResponseServiceDTO> responseCountryService = countryExService.countryInfoApi(responseIpApiService.getCountry_name());
		
		//Llamado al servicio de monedas y TRM basada en Euro
		Map<String, Object> responseExchangeInfo = exchangeExService.exchangeInfoApi();
		
		//Calculo el cambio a USD
		Map<String, Double> ratesValues = (Map<String, Double>) responseExchangeInfo.get("rates");
		ipResponse.setCotizacionUSD(ratesValues.get(responseIpApiService.getCurrency().getCode())/ ratesValues.get("USD"));
		
		ipResponse.setMyIP(responseIpApiService.getIp());
		ipResponse.setRegion(responseCountryService.get(0).getRegion());
		ipResponse.setIdioma(languageList.get(0).getName());
		ipResponse.setMonedaLocal(responseIpApiService.getCurrency().getName());
		ipResponse.setHoraActual(responseIpApiService.getTime_zone().getCurrent_time());
		
		return ipResponse;
	}
	
	public static double getDistanceBetweenPointsNew(double latitude1, double longitude1, double latitude2, double longitude2, String unit) {
	    double theta = longitude1 - longitude2;
	    double distance = 60 * 1.1515 * (180/Math.PI) * Math.acos(
	        Math.sin(latitude1 * (Math.PI/180)) * Math.sin(latitude2 * (Math.PI/180)) + 
	        Math.cos(latitude1 * (Math.PI/180)) * Math.cos(latitude2 * (Math.PI/180)) * Math.cos(theta * (Math.PI/180))
	    );
	    if (unit.equals("miles")) {
	        return Math.round(distance);
	    } else if (unit.equals("kilometers")) {
	        return Math.round(distance * 1.609344);
	    } else {
	        return 0;
	    }
	}
}
