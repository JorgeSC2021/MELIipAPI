package com.meli.ipAPI.DTO;

public class ipResponseDTO {
	private String myIP;
	private String region;
	private String idioma;
	private String horaActual;
	private Double distanciaBA;
	private String monedaLocal;
	private Double cotizacionUSD;
	
	public ipResponseDTO()
	{
		
	}
	
	public ipResponseDTO(String pMyIP, String pIdioma, String pHoraActual, Double pDistanciaBA, String pMonedaLocal, Double pCotizacionUSD)
	{
		this.myIP = pMyIP;
		this.idioma = pIdioma;
		this.horaActual = pHoraActual;
		this.distanciaBA = pDistanciaBA;
		this.monedaLocal = pMonedaLocal;
		this.cotizacionUSD = pCotizacionUSD;
	}
	
	public void setMyIP(String pMyIP)
	{
		this.myIP = pMyIP;
	}
	
	public String getMyIP()
	{
		return myIP;
	}
	
	public void setRegion(String pRegion)
	{
		this.region = pRegion;
	}
	
	public String getRegion()
	{
		return region;
	}
	
	public void setIdioma(String pIdioma)
	{
		this.idioma = pIdioma;
	}
	
	public String getIdioma()
	{
		return idioma;
	}
	
	public void setHoraActual(String pHoraActual)
	{
		this.horaActual = pHoraActual;
	}
	
	public String getHoraActual()
	{
		return horaActual;
	}
	
	public void setDistanciaBA(Double pDistanciaBA)
	{
		this.distanciaBA = pDistanciaBA;
	}
	
	public Double getDistanciaBA()
	{
		return distanciaBA;
	}
	
	public void setMonedaLocal(String pMonedaLocal)
	{
		this.monedaLocal = pMonedaLocal;
	}
	
	public String getMonedaLocal()
	{
		return monedaLocal;
	}
	
	public void setCotizacionUSD(Double pCotizacionUSD)
	{
		this.cotizacionUSD = pCotizacionUSD;
	}
	
	public Double getCotizacionUSD()
	{
		return cotizacionUSD;
	}
}
