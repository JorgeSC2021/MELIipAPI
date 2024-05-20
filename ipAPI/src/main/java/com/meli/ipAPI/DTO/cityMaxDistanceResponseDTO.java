package com.meli.ipAPI.DTO;

public class cityMaxDistanceResponseDTO {
	private String pais;
	private String ciudad;
	private Double distancia;
	
	public cityMaxDistanceResponseDTO()
	{
		
	}
	
	public cityMaxDistanceResponseDTO(String pais, String ciudad, Double distancia)
	{
		this.pais = pais;
		this.ciudad = ciudad;
		this.distancia = distancia;
	}
	
	public String getPais()
	{
		return pais;
	}
	
	public void setPais(String pais)
	{
		this.pais = pais;
	}
	
	public String getCiudad()
	{
		return ciudad;
	}
	
	public void setCiudad(String ciudad)
	{
		this.ciudad = ciudad;
	}
	
	public Double getDistancia()
	{
		return distancia;
	}
	
	public void setDistancia(Double distancia)
	{
		this.distancia = distancia;
	}
}
