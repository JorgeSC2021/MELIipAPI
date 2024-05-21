package com.meli.ipAPI.DTO;

public class cityAvgResponseDTO {
	
	private String pais;
	private Double promedio;
	
	public cityAvgResponseDTO(String pais, Double promedio)
	{
		this.pais = pais;
		this.promedio = promedio;
	}
	
	public String getPais()
	{
		return pais;
	}
	
	public void setPais(String pais)
	{
		this.pais = pais;
	}
	
	public Double getPromedio()
	{
		return promedio;
	}
	
	public void setPromedio(Double promedio)
	{
		this.promedio = promedio;
	}
}
