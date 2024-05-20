package com.meli.ipAPI.IService;

import java.util.List;

import com.meli.ipAPI.DTO.cityMaxDistanceResponseDTO;
import com.meli.ipAPI.DTO.ipResponseDTO;

public interface IipService {
	ipResponseDTO showIpInfo(String ipID);
	public cityMaxDistanceResponseDTO findCityMaxDistance();
}
