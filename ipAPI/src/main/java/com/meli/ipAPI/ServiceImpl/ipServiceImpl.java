package com.meli.ipAPI.ServiceImpl;

import java.util.Map;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import com.meli.ipAPI.DTO.*;
import com.meli.ipAPI.IService.IipService;
import com.meli.ipAPI.ExternalServices.ipInfoExService;
import com.meli.ipAPI.ExternalServices.countryExService;
import com.meli.ipAPI.ExternalServices.exchangeExService;

import com.meli.ipAPI.Entity.Location;
import com.meli.ipAPI.Repository.ILocationRepository;

@Primary
@Service
public class ipServiceImpl implements IipService{

    @Autowired
    private EntityManager entityManager;
    
	private ipResponseDTO ipResponse = new ipResponseDTO();
	
	//Declaración de los servicios externos, se les coloca la declaracion "final" ya que internamente contiene variables anotadas, y su valor debe mantenerse
	private final ipInfoExService ipinfoExService;
	private final countryExService countryExService;
	private final exchangeExService exchangeExService;
    
	private final ILocationRepository locationRepository;
	
	public ipServiceImpl(ipInfoExService ipinfoExService, countryExService countryExService, exchangeExService exchangeExService, ILocationRepository locationRepository)
	{
		this.ipinfoExService = ipinfoExService;
		this.countryExService = countryExService;
		this.exchangeExService = exchangeExService;
		this.locationRepository = locationRepository;
	}
	
	public ipResponseDTO showIpInfo(String ipID)
	{
		//Llamado al servicio de información de IP
		ipResponseServiceDTO responseIpApiService = ipinfoExService.ipApiInfo(ipID);
		
		if(responseIpApiService==null)
			return null; 
		
		//Obtengo los datos de las subclases
		ipResponseServiceDTO.Location locationResponse = responseIpApiService.getLocation();
		
		//Traigo las coordenadas de Buenos aires
		List<countryResponseServiceDTO> responseBAService = countryExService.capitalInfoApi("Buenos Aires");
		
		//Calculo la distancia entre dos puntos
		Double distance2points = getDistanceBetweenPointsNew(responseIpApiService.getLatitude(), responseIpApiService.getLongitude(),
				                                             responseBAService.get(0).getLatlng().get(0), responseBAService.get(0).getLatlng().get(1),
				                                             "kilometers");
		
		List<ipResponseServiceDTO.Location.Language> languageList = locationResponse.getLanguages();
		
		//Llamado al servicio de información de país
		List<countryResponseServiceDTO> responseCountryService = countryExService.countryInfoApi(responseIpApiService.getCountry_name());
		
		//Llamado al servicio de monedas y TRM basada en Euro
		Map<String, Object> responseExchangeInfo = exchangeExService.exchangeInfoApi();
		
		//Calculo el cambio a USD
		Map<String, Double> ratesValues = (Map<String, Double>) responseExchangeInfo.get("rates");
		
		//Esto se hace debido a que el API actual de Monedas solo devuelve como base EURO, y por ende, en el listado de cambios el EURO aparece como un entero
		Object rateIPValue = ratesValues.get(responseIpApiService.getCurrency().getCode());
		Double currencyIP;
		
		if(rateIPValue instanceof Integer)
			currencyIP = ((Integer) rateIPValue).doubleValue();
		else
			currencyIP = ratesValues.get(responseIpApiService.getCurrency().getCode());
		
		//Transformo la fecha del servicio para dejarla con hora
		OffsetDateTime fechaHora = OffsetDateTime.parse(responseIpApiService.getTime_zone().getCurrent_time());
		String horaActual = fechaHora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		
		//Diligencio la variable de respuesta
		ipResponse.setCotizacionUSD(currencyIP/ ratesValues.get("USD"));
		ipResponse.setMyIP(responseIpApiService.getIp());
		ipResponse.setRegion(responseCountryService.get(0).getRegion());
		ipResponse.setDistanciaBA(distance2points);
		ipResponse.setIdioma(languageList.get(0).getName());
		ipResponse.setMonedaLocal(responseIpApiService.getCurrency().getName());
		ipResponse.setHoraActual(horaActual);
		
		//Almaceno el resultado para generar estadísticos
		saveLocation(responseIpApiService, ipResponse.getDistanciaBA());
		
		return ipResponse;
	}
	
	public cityDistanceResponseDTO findCityMaxDistance()
	{
		Optional<Location> response = locationRepository.findCityMaxDistance();
		if(response.isPresent())
		{
			Location location = response.get();
			return new cityDistanceResponseDTO
					( 
							location.getPais(), location.getCiudad(), location.getDistancia()
					);
		}
		else
			return null;
	}
	
	public cityDistanceResponseDTO findCityMinDistance()
	{
		Optional<Location> response = locationRepository.findCityMinDistance();
		if(response.isPresent())
		{
			Location location = response.get();
			return new cityDistanceResponseDTO
					( 
							location.getPais(), location.getCiudad(), location.getDistancia()
					);
		}
		else
			return null;
	}
	
	public Double cityAvgDistance()
	{
		Query query = entityManager.createNamedQuery("Location.cityAvgDistanceQ");
		
		List<cityAvgResponseDTO> response = query.getResultList(); 
		if(response.size()>0)
		{
			double suma = 0;
			for(cityAvgResponseDTO city: response)
			{
				suma = suma + city.getPromedio();
			}
			
			return suma / response.size();
		}
		
		return null;
	}
	
	//A partir de aquí se encuentran los métodos locales de la clase
	static double getDistanceBetweenPointsNew(double latitude1, double longitude1, double latitude2, double longitude2, String unit) {
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
	
	private void saveLocation(ipResponseServiceDTO ipResponse, Double distancia)
	{
		Location location = new Location();
		
		location.setPais(ipResponse.getCountry_name());
		location.setCiudad(ipResponse.getCity());
		location.setDistancia(distancia);
		location.setIp(ipResponse.getIp());
		location.setHoraAcceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		
		locationRepository.save(location);
	}
}
