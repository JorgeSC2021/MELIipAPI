package com.meli.ipAPI.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class ipResponseDTO {
	private String myIP;
	private String idioma;
	private Date horaActual;
	private Long distanciaBA;
	private String monedaLocal;
	private BigDecimal cotizacionUSD;
	
	public ipResponseDTO()
	{
		
	}
	
	public ipResponseDTO(String pMyIP, String pIdioma, Date pHoraActual, Long pDistanciaBA, String pMonedaLocal, BigDecimal pCotizacionUSD)
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
	
	public void setIdioma(String pIdioma)
	{
		this.idioma = pIdioma;
	}
	
	public String getIdioma()
	{
		return idioma;
	}
	
	public void setHoraActual(Date pHoraActual)
	{
		this.horaActual = pHoraActual;
	}
	
	public Date getHoraActual()
	{
		return horaActual;
	}
	
	public void setDistanciaBA(Long pDistanciaBA)
	{
		this.distanciaBA = pDistanciaBA;
	}
	
	public Long getDistanciaBA()
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
	
	public void setCotizacionUSD(BigDecimal pCotizacionUSD)
	{
		this.cotizacionUSD = pCotizacionUSD;
	}
	
	public BigDecimal getCotizacionUSD()
	{
		return cotizacionUSD;
	}
}
