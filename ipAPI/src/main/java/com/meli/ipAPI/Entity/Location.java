package com.meli.ipAPI.Entity;

import java.util.Date;

import jakarta.persistence.*;

import com.meli.ipAPI.DTO.cityAvgResponseDTO;

@Entity
@Table(name = "Location")
@SqlResultSetMapping(
	    name = "cityAvgResponseDTOMapping",
	    classes = @ConstructorResult(
	        targetClass = cityAvgResponseDTO.class,
	        columns = {
	            @ColumnResult(name = "pais", type = String.class),
	            @ColumnResult(name = "promedio", type = Double.class)
	        }
	    )
	)
@NamedNativeQuery(
		name = "Location.cityAvgDistanceQ",
		query = "SELECT l.pais AS pais, AVG(l.distancia) AS promedio FROM location l GROUP BY pais",
		resultSetMapping = "cityAvgResponseDTOMapping"
		)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pais;
    private String ciudad;
    private Double distancia;
    private String ip;
    private Date horaAcceso;
    
    public Location()
    {
    	
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
    
    public String getIp()
    {
    	return ip;
    }
    
    public void setIp(String ip)
    {
    	this.ip = ip;
    }
    
    public Date getHoraAcceso()
    {
    	return horaAcceso;
    }
    
    public void setHoraAcceso(Date horaAcceso)
    {
    	this.horaAcceso = horaAcceso;
    }
}