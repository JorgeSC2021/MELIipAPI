package com.meli.ipAPI.IService;

import com.meli.ipAPI.DTO.cityDistanceResponseDTO;
import com.meli.ipAPI.DTO.ipResponseDTO;

public interface IipService {
	ipResponseDTO showIpInfo(String ipID);
	cityDistanceResponseDTO findCityMaxDistance();
	cityDistanceResponseDTO findCityMinDistance();
	Double cityAvgDistance();
}
